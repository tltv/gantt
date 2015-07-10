/*
 * Copyright 2014 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tltv.gantt.client;

import static org.tltv.gantt.client.shared.GanttUtil.getBoundingClientRectLeft;
import static org.tltv.gantt.client.shared.GanttUtil.getBoundingClientRectRight;
import static org.tltv.gantt.client.shared.GanttUtil.getBoundingClientRectWidth;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tltv.gantt.client.shared.GanttUtil;
import org.tltv.gantt.client.shared.Resolution;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractNativeScrollbar;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT widget to build a scalable timeline that supports more than one
 * resolutions ({@link org.tltv.gantt.client.shared.Resolution}). When timeline
 * element doesn't overflow horizontally in it's parent element, it scales the
 * content width up to fit in the space available.
 * <p>
 * When this component scales up, all widths are calculated as percentages.
 * Pixel widths are used otherwise. Some browsers may not support percentages
 * accurately enough, and for those it's best to call
 * {@link #setAlwaysCalculatePixelWidths(boolean)} with 'true' to disable
 * percentage values.
 * <p>
 * There's always a minimum width calculated and updated to the timeline
 * element. Percentage values set some limitation for the component's width.
 * Wider the component (> 4000px), bigger the change to get year, month and date
 * blocks not being vertically in-line with each others.
 * <p>
 * Supports setting a scroll left position.
 * <p>
 * After construction, attach the component to it's parent and call update
 * method with a required parameters and the timeline is ready. After that, all
 * widths are calculated and all other API methods available can be used safely.
 * 
 * @author Tltv
 * 
 */
public class TimelineWidget extends Widget {

    public static final String STYLE_TIMELINE = "timeline";
    public static final String STYLE_ROW = "row";
    public static final String STYLE_COL = "col";
    public static final String STYLE_MONTH = "month";
    public static final String STYLE_YEAR = "year";
    public static final String STYLE_DAY = "day";
    public static final String STYLE_RESOLUTION = "resolution";
    public static final String STYLE_WEEK_FIRST = "week-f";
    public static final String STYLE_WEEK_LAST = "week-l";
    public static final String STYLE_WEEK_MIDDLE = "week-m";
    public static final String STYLE_EVEN = "even";
    public static final String STYLE_WEEKEND = "weekend";
    public static final String STYLE_SPACER = "spacer";
    public static final String STYLE_FIRST = "f-col";
    public static final String STYLE_CENTER = "c-col";
    public static final String STYLE_LAST = "l-col";

    public static final int DAYS_IN_WEEK = 7;
    public static final int HOURS_IN_DAY = 24;
    public static final long DAY_INTERVAL = 24 * 60 * 60 * 1000;
    public static final long HOUR_INTERVAL = 60 * 60 * 1000;
    public static final int RESOLUTION_WEEK_DAYBLOCK_WIDTH = 4;
    public static final int RESOLUTION_HOUR_DAYBLOCK_WIDTH = 4;

    private boolean ie, ie8;

    private boolean forceUpdateFlag;

    private TimeZone gmt = TimeZone.createTimeZone(0);

    private LocaleDataProvider localeDataProvider;
    private DateTimeFormat yearDateTimeFormat;
    private DateTimeFormat monthDateTimeFormat;
    private DateTimeFormat weekDateTimeFormat;
    private DateTimeFormat dayDateTimeFormat;
    private DateTimeFormat hour12DateTimeFormat;
    private DateTimeFormat hour24DateTimeFormat;

    private String locale;
    private Resolution resolution;
    private long startDate;
    private long endDate;
    private int firstDayOfWeek;
    private int lastDayOfWeek;
    private int firstDayOfRange;
    private int firstHourOfRange;
    private String[] monthNames;
    private String[] weekdayNames;

    /*
     * number of blocks in resolution range. Days for Day/Week resolution, Hours
     * for hour resolution..
     */
    private int blocksInRange = 0;
    /*
     * number of elements in resolution range. Same as blocksInRange for
     * Day/Hour resolution. blocksInRange / 7 for Week resolution.
     */
    private int resolutionBlockCount = 0;
    private int firstResBlockCount;
    private int lastResBlockCount;
    private boolean firstDay;
    private boolean timelineOverflowingHorizontally;
    private boolean noticeVerticalScrollbarWidth;
    private boolean monthRowVisible;
    private boolean yearRowVisible;
    private String monthFormat;
    private String yearFormat;
    private String weekFormat;
    private String dayFormat;

    /*
     * resolutionDiv contains the resolution specific elements that represents a
     * timeline's sub-parts like hour, day or week.
     */
    private DivElement resolutionDiv;
    private DivElement resSpacerDiv;
    private Set<DivElement> spacerBlocks = new HashSet<DivElement>();

    private BlockRowData yearRowData = new BlockRowData();
    private BlockRowData monthRowData = new BlockRowData();
    // days/daysLength are needed only with resolutions smaller than Day.
    private BlockRowData dayRowData = new BlockRowData();

    /*
     * Currently active widths. Updated each time when timeline column widths
     * are updated.
     */
    private double dayWidthPercentage;
    private double dayOrHourWidthPx;
    private double resBlockMinWidthPx;
    private double resBlockWidthPx;
    private double resBlockWidthPercentage;

    private int minResolutionWidth = -1;
    private int minWidth = -1;
    private boolean calcPixels = false;
    private double positionLeft;

    private Timer lazyResolutionPaint = new Timer() {

        @Override
        public void run() {
            fillVisibleTimeline();
        }

    };

    private StyleElement styleElement;
    private StyleElement styleElementForLeft;

    private boolean firstWeekBlockHidden;

    enum Weekday {
        First,
        Between,
        Last
    }

    /**
     * Constructs the widget. Call
     * {@link #update(Resolution, long, long, int, LocaleDataProvider)} after
     * the component is attached to some parent widget.
     */
    public TimelineWidget() {
        setElement(DivElement.as(DOM.createDiv()));
        setStyleName(STYLE_TIMELINE);
    }

    @Override
    protected void onUnload() {
        super.onDetach();

        if (styleElement != null) {
            styleElement.removeFromParent();
        }
        if (styleElementForLeft != null) {
            styleElementForLeft.removeFromParent();
        }
    }

    /**
     * <p>
     * Updates the content of this widget. Builds the time-line and calculates
     * width and heights for the content (calls in the end
     * {@link #updateWidths()}). This should be called explicitly. Otherwise the
     * widget will be empty.
     * <p>
     * Date values should always follow specification in {@link Date#getTime()}.
     * Start and end date is always required.
     * 
     * @param resolution
     *            Resolution enum (not null)
     * @param startDate
     *            Time-line's start date in milliseconds. (not null)
     * @param endDate
     *            Time-line's end date in milliseconds. (not null)
     * @param firstDayOfRange
     *            First day of the whole range. Allowed values are 1-7. 1 is
     *            Sunday. Required with {@link Resolution#Week}.
     * @param firstHourOfRange
     *            First hour of the range. Allowed values are 0-23. Required
     *            with {@link Resolution#Hour}.
     * @param localeDataProvider
     *            Data provider for locale specific data. month names, first day
     *            of week etc.
     * 
     */
    public void update(Resolution resolution, long startDate, long endDate,
            int firstDayOfRange, int firstHourOfRange,
            LocaleDataProvider localeDataProvider) {
        if (localeDataProvider == null) {
            GWT.log(getClass().getSimpleName()
                    + " requires LocaleDataProvider. Can't complete update(...) operation.");
            return;
        }
        if (isChanged(resolution, startDate, endDate,
                localeDataProvider.getFirstDayOfWeek(), firstDayOfRange,
                firstHourOfRange, localeDataProvider.getLocale())) {
            clear();
            GWT.log(getClass().getSimpleName() + " content cleared.");
        } else {
            return;
        }

        GWT.log(getClass().getSimpleName() + " Updating content.");

        injectStyle();
        injectLeftStyle();

        if (styleElementForLeft != null) {
            StyleInjector.setContents(styleElementForLeft, "." + STYLE_COL
                    + " { position: relative; left: 0px; }");
        }

        locale = localeDataProvider.getLocale();
        this.resolution = resolution;
        this.startDate = startDate;
        this.endDate = endDate;
        // Required with Resolution.Week.
        firstDayOfWeek = localeDataProvider.getFirstDayOfWeek();
        lastDayOfWeek = (firstDayOfWeek == 1) ? 7 : Math.max(
                (firstDayOfWeek - 1) % 8, 1);
        this.firstDayOfRange = firstDayOfRange;
        this.firstHourOfRange = firstHourOfRange;
        monthNames = localeDataProvider.getMonthNames();
        weekdayNames = localeDataProvider.getWeekdayNames();
        this.localeDataProvider = localeDataProvider;
        resolutionDiv = DivElement.as(DOM.createDiv());
        resolutionDiv.setClassName(STYLE_ROW + " " + STYLE_RESOLUTION);

        if (minResolutionWidth < 0) {
            minResolutionWidth = calculateResolutionMinWidth();
        }

        if (resolution == Resolution.Day || resolution == Resolution.Week) {
            prepareTimelineForDayResolution(startDate, endDate);
        } else if (resolution == Resolution.Hour) {
            prepareTimelineForHourResolution(startDate, endDate);
        } else {
            GWT.log(getClass().getSimpleName() + " resolution "
                    + (resolution != null ? resolution.name() : "null")
                    + " is not supported");
            return;
        }

        if (isYearRowVisible()) {
            appendTimelineBlocks(yearRowData, STYLE_YEAR);
        }
        if (isMonthRowVisible()) {
            appendTimelineBlocks(monthRowData, STYLE_MONTH);
        }
        if (isDayRowVisible()) {
            appendTimelineBlocks(dayRowData, STYLE_DAY);
        }
        getElement().appendChild(resolutionDiv);

        GWT.log(getClass().getSimpleName() + " Constructed content.");

        updateWidths();

        GWT.log(getClass().getSimpleName() + " is updated for resolution "
                + resolution.name() + ".");
    }

    /**
     * Injects custom stylesheet just for this widget. It helps to update styles
     * for a big group of elements in the DOM, like resolution blocks.
     * <p>
     * Warning, this feature is not working with Internet Explorer reliably
     * enough. Read more at {@link StyleInjector#injectStylesheetAtEnd(String)}.
     * This method has no effect when {@link #ie} is set to true.
     */
    private void injectStyle() {
        if (ie || styleElement != null) {
            return;
        }

        styleElement = StyleInjector.injectStylesheetAtEnd("." + STYLE_FIRST
                + " { } ." + STYLE_CENTER + " { } ." + STYLE_LAST + " { } ."
                + STYLE_COL + " { } ");

        StyleInjector.flush();
    }

    private void injectLeftStyle() {
        if (ie || styleElementForLeft != null) {
            return;
        }

        styleElementForLeft = StyleInjector.injectStylesheetAtEnd("."
                + STYLE_COL + " { } ");

        StyleInjector.flush();
    }

    /**
     * Set minimum width (pixels) of this widget's root DIV element. Default is
     * -1. Notice that {@link #update(Resolution, long, long)} will calculate
     * min-width and call this internally.
     * 
     * @param minWidth
     *            Minimum width in pixels.
     */
    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
        getElement().getStyle().setProperty("minWidth", this.minWidth + "px");
        getResolutionDiv().getStyle().setProperty("minWidth",
                this.minWidth + "px");
    }

    /**
     * Return minimum width (pixels) of this widget's root DIV element. Returns
     * -1 if not set.
     * 
     * @return min-width
     */
    public int getMinWidth() {
        return minWidth;
    }

    /**
     * Calculate matching left offset in percentage for a date (
     * {@link Date#getTime()}).
     * 
     * @param date
     *            Target date in milliseconds.
     * @param contentWidth
     *            Width of the content that the given 'date' is relative to.
     * @return Left offset in percentage.
     */
    public double getLeftPositionPercentageForDate(long date,
            double contentWidth) {
        double timelineLeft = getLeftPositionForDate(date);
        double relativeLeft = convertRelativeLeftPosition(timelineLeft,
                contentWidth);

        double width = getResolutionWidth();
        return (100.0 / width) * relativeLeft;
    }

    /**
     * Calculate CSS value for 'left' property matching left offset in
     * percentage for a date ( {@link Date#getTime()}).
     * <p>
     * May return '2.123456%' or 'calc(2.123456%)' if IE(>8);
     * 
     * @param date
     *            Target date in milliseconds.
     * @param contentWidth
     *            Width of the content that the given 'date' is relative to.
     * @return Left offset as a String value.
     */
    public String getLeftPositionPercentageStringForDate(long date,
            double contentWidth) {
        double timelineLeft = getLeftPositionForDate(date);
        double relativeLeft = convertRelativeLeftPosition(timelineLeft,
                contentWidth);

        double width = getResolutionWidth();
        String calc = createCalcCssValue(width, relativeLeft);

        if (calc != null) {
            return calc;
        }
        return (100.0 / width) * relativeLeft + "" + Unit.PCT.getType();
    }

    public String getLeftPositionPercentageStringForDate(long date,
            double rangeWidth, long rangeStartDate, long rangeEndDate) {
        double rangeLeft = getLeftPositionForDate(date, rangeWidth,
                rangeStartDate, rangeEndDate);
        double width = rangeWidth;
        String calc = createCalcCssValue(width, rangeLeft);

        if (calc != null) {
            return calc;
        }
        return (100.0 / width) * rangeLeft + "" + Unit.PCT.getType();
    }

    /**
     * Calculate CSS value for 'width' property matching date interval inside
     * the time-line. Returns percentage value. Interval is in milliseconds.
     * <p>
     * May return '2.123456%' or 'calc(2.123456%)' if IE(>8);
     * 
     * @param interval
     *            Date interval in milliseconds.
     * @return
     */
    public String getWidthPercentageStringForDateInterval(long interval) {
        double range = endDate - startDate;
        return getWidthPercentageStringForDateInterval(interval, range);
    }

    /** @see {@link #getWidthPercentageStringForDateInterval(long)} */
    public String getWidthPercentageStringForDateInterval(long interval,
            double range) {
        String calc = createCalcCssValue(range, interval);
        if (calc != null) {
            return calc;
        }
        return (100.0 / range) * interval + "" + Unit.PCT.getType();
    }

    /**
     * Calculate matching left offset in pixels for a date (
     * {@link Date#getTime()}).
     * 
     * @param date
     *            Target date in milliseconds.
     * @return Left offset in pixels.
     */
    public double getLeftPositionForDate(long date) {
        return getLeftPositionForDate(date, getResolutionWidth(), startDate,
                endDate);
    }

    public double getLeftPositionForDate(long date, double rangeWidth,
            long rangeStartDate, long rangeEndDate) {
        double width = rangeWidth;
        double range = rangeEndDate - rangeStartDate;
        if (range <= 0) {
            return 0;
        }
        double p = width / range;
        double offset = date - rangeStartDate;
        double left = p * offset;
        return left;
    }

    /**
     * Calculate matching date ({@link Date#getTime()}) for the target left
     * pixel offset.
     * 
     * @param left
     *            Left offset in pixels.
     * @return Date in a milliseconds.
     */
    public long getDateForLeftPosition(double left) {
        double width = getResolutionWidth();
        if (width <= 0) {
            return 0;
        }
        double range = endDate - startDate;
        double p = range / width;
        double offset = p * left;
        double date = startDate + offset;
        return (long) date;
    }

    /**
     * Convert left position for other relative target width.
     * 
     * @param left
     * @param contentWidthToConvertFor
     * @return
     */
    public double convertRelativeLeftPosition(double left,
            double contentWidthToConvertFor) {
        double width = getResolutionWidth();
        if (width <= 0 || contentWidthToConvertFor <= 0) {
            return 0;
        }

        double relativePosition = (1.0 / contentWidthToConvertFor) * left;
        double timelineLeft = relativePosition * width;
        return timelineLeft;
    }

    /**
     * Set horizontal scroll position for the time-line.
     * 
     * @param left
     *            Scroll position in pixels.
     */
    public void setScrollLeft(double left) {
        if (positionLeft == left) {
            return;
        }
        positionLeft = left;
        getElement().getStyle().setLeft(-left, Unit.PX);
        lazyResolutionPaint.schedule(20);
    }

    /**
     * Re-calculates required widths for this widget.
     * <p>
     * Re-creates and fills the visible part of the resolution element.
     */
    public void updateWidths() {
        if (resolutionDiv == null) {
            GWT.log(getClass().getSimpleName()
                    + " is not ready for updateWidths() call. Call update(...) instead.");
            return;
        }

        GWT.log(getClass().getSimpleName() + " Started updating widths.");

        // start by clearing old content in resolution element
        resolutionDiv.removeAllChildren();

        setMinWidth(blocksInRange * minResolutionWidth);

        // update horizontal overflow state here, after min-width is updated.
        updateTimelineOverflowingHorizontally();

        createTimelineElementsOnVisibleArea();
        // fill timeline
        fillVisibleTimeline();

        // remove spacer block if it exist
        removeResolutionSpacerBlock();

        // calculate new block width for day-resolution.
        // Year and month blocks are vertically in-line with days.
        dayWidthPercentage = 100.0 / blocksInRange;
        dayOrHourWidthPx = calculateDayOrHourResolutionBlockWidthPx(blocksInRange);

        // calculate block width for currently selected resolution
        // (day,week,...)
        // resolution div's content may not be vertically in-line with
        // year/month blocks. This is the case for example with Week resolution.
        resBlockMinWidthPx = minResolutionWidth;
        resBlockWidthPx = calculateActualResolutionBlockWidthPx(dayOrHourWidthPx);
        resBlockWidthPercentage = 100.0 / resolutionBlockCount;
        String pct = createCalcCssValue(resolutionBlockCount);
        if (resolution == Resolution.Week) {
            resBlockMinWidthPx = DAYS_IN_WEEK * minResolutionWidth;
            resBlockWidthPercentage = dayWidthPercentage * DAYS_IN_WEEK;
            pct = createCalcCssValue(blocksInRange, DAYS_IN_WEEK);

        }

        // update resolution block widths
        updateResolutionBlockWidths(pct);

        if (isYearRowVisible()) {
            // update year block widths
            updateBlockWidths(yearRowData);
        }

        if (isMonthRowVisible()) {
            // update month block widths
            updateBlockWidths(monthRowData);
        }

        if (isDayRowVisible()) {
            updateBlockWidths(dayRowData);
        }

        if (isAlwaysCalculatePixelWidths()) {
            updateSpacerBlocks(dayOrHourWidthPx);
        }

        GWT.log(getClass().getSimpleName() + " Widths are updated.");
    }

    /*
     * Calculates either day or hour resolution block width depending on the
     * current resolution.
     */
    private double calculateDayOrHourResolutionBlockWidthPx(int blockCount) {
        double dayOrHourWidthPx = Math.round(resolutionDiv.getClientWidth()
                / blockCount);
        while ((resolutionDiv.getClientWidth() % (blockCount * dayOrHourWidthPx)) >= blockCount) {
            dayOrHourWidthPx++;
        }

        return dayOrHourWidthPx;
    }

    /*
     * Calculates the actual width of one resolution block element. For example:
     * week resolution will return 7 * dayOrHourBlockWidthPx.
     */
    private double calculateActualResolutionBlockWidthPx(
            double dayOrHourBlockWidthPx) {
        if (resolution == Resolution.Week) {
            return DAYS_IN_WEEK * dayOrHourBlockWidthPx;
        }
        return dayOrHourBlockWidthPx;
    }

    /**
     * Returns true if the timeline is overflowing the parent's width. This
     * works only when this widget is attached to some parent.
     * 
     * @return True when timeline width is more than the parent's width (@see
     *         {@link Element#getClientWidth()}).
     */
    public boolean isTimelineOverflowingHorizontally() {
        return timelineOverflowingHorizontally;
    }

    /**
     * Updates horizontal overflow state and returns true if the timeline is
     * overflowing the parent's width. This works only when this widget is
     * attached to some parent.
     * 
     * @return True when timeline width is more than the parent's width (@see
     *         {@link Element#getClientWidth()}).
     */
    public boolean checkTimelineOverflowingHorizontally() {
        updateTimelineOverflowingHorizontally();
        return isTimelineOverflowingHorizontally();
    }

    /**
     * Return true if timeline should notice vertical scrollbar width in it's
     * calculations.
     * 
     * @return
     */
    public boolean isNoticeVerticalScrollbarWidth() {
        return noticeVerticalScrollbarWidth;
    }

    public void setNoticeVerticalScrollbarWidth(
            boolean noticeVerticalScrollbarWidth) {
        this.noticeVerticalScrollbarWidth = noticeVerticalScrollbarWidth;
        if (noticeVerticalScrollbarWidth) {
            getElement().getStyle().setMarginRight(
                    AbstractNativeScrollbar.getNativeScrollbarWidth(), Unit.PX);
        } else {
            getElement().getStyle().clearMarginRight();
        }
    }

    public void setBrowserInfo(boolean ie, boolean ie8) {
        this.ie = ie;
        this.ie8 = ie8;
    }

    /**
     * Tells this Widget to calculate widths by itself. Percentage widths are
     * not used. Some browsers may not handle sub-pixel calculating accurately
     * enough. Setting this to true works as a fallback mode for those browsers.
     * <p>
     * Default value is false.
     * 
     * @param calcPx
     * @return
     */
    public void setAlwaysCalculatePixelWidths(boolean calcPx) {
        calcPixels = calcPx;
    }

    /**
     * Returns true if Widget is set to calculate widths by itself. Default is
     * false.
     * 
     * @return
     */
    public boolean isAlwaysCalculatePixelWidths() {
        return calcPixels;
    }

    /**
     * Get actual width of the timeline.
     * 
     * @return
     */
    public double getResolutionWidth() {
        if (!ie8 && !isTimelineOverflowingHorizontally()) {
            return calculateTimelineWidth();
        }

        double width = getResolutionDivWidth();
        if (isAlwaysCalculatePixelWidths() && containsResBlockSpacer()) {
            width = width - getElementWidth(resSpacerDiv);
        }
        return width;
    }

    /**
     * Calculate the exact width of the timeline. Excludes any spacers in the
     * end.
     * 
     * @return
     */
    public double calculateTimelineWidth() {
        Element last = getLastResolutionElement();
        if (last == null) {
            return 0.0;
        }
        double r = getBoundingClientRectRight(last);
        double l = getBoundingClientRectLeft(getFirstResolutionElement());
        double timelineRealWidth = r - l;
        return timelineRealWidth;
    }

    /*
     * Get width of the resolution div element.
     */
    private double getResolutionDivWidth() {
        if (!isTimelineOverflowingHorizontally()) {
            return getElementWidth(resolutionDiv);
        }

        return blocksInRange * minResolutionWidth;
    }

    private double getElementWidth(Element element) {
        if (!ie8) {
            return GanttUtil.getBoundingClientRectWidth(element);
        }
        return element.getClientWidth();
    }

    public boolean isDayRowVisible() {
        return resolution == Resolution.Hour;
    }

    public boolean isMonthRowVisible() {
        return monthRowVisible;
    }

    public boolean isYearRowVisible() {
        return yearRowVisible;
    }

    public void setMonthRowVisible(boolean monthRowVisible) {
        this.monthRowVisible = monthRowVisible;
    }

    public void setYearRowVisible(boolean yearRowVisible) {
        this.yearRowVisible = yearRowVisible;
    }

    public String getMonthFormat() {
        return monthFormat;
    }

    public void setMonthFormat(String monthFormat) {
        this.monthFormat = monthFormat;
    }

    public String getYearFormat() {
        return yearFormat;
    }

    public void setYearFormat(String yearFormat) {
        this.yearFormat = yearFormat;
    }

    public void setWeekFormat(String weekFormat) {
        this.weekFormat = weekFormat;
    }

    public void setDayFormat(String dayFormat) {
        this.dayFormat = dayFormat;
    }

    /**
     * Sets force update flag up. Next
     * {@link #update(Resolution, long, long, int, LocaleDataProvider)} call
     * knows then to update everything.
     */
    public void setForceUpdate() {
        forceUpdateFlag = true;
    }

    public DateTimeFormat getYearDateTimeFormat() {
        if (yearDateTimeFormat == null) {
            yearDateTimeFormat = DateTimeFormat.getFormat("yyyy");
        }
        return yearDateTimeFormat;
    }

    public DateTimeFormat getMonthDateTimeFormat() {
        if (monthDateTimeFormat == null) {
            monthDateTimeFormat = DateTimeFormat.getFormat("M");
        }
        return monthDateTimeFormat;
    }

    public DateTimeFormat getWeekDateTimeFormat() {
        if (weekDateTimeFormat == null) {
            weekDateTimeFormat = DateTimeFormat.getFormat("d");
        }
        return weekDateTimeFormat;
    }

    public DateTimeFormat getDayDateTimeFormat() {
        if (dayDateTimeFormat == null) {
            dayDateTimeFormat = DateTimeFormat.getFormat("d");
        }
        return dayDateTimeFormat;
    }

    public DateTimeFormat getHour12DateTimeFormat() {
        if (hour12DateTimeFormat == null) {
            hour12DateTimeFormat = DateTimeFormat.getFormat("h");
        }
        return hour12DateTimeFormat;
    }

    public DateTimeFormat getHour24DateTimeFormat() {
        if (hour24DateTimeFormat == null) {
            hour24DateTimeFormat = DateTimeFormat.getFormat("HH");
        }
        return hour24DateTimeFormat;
    }

    /**
     * Returns a width of the first resolution block.
     * 
     * @return
     */
    public double getFirstResolutionElementWidth() {
        if (isFirstResBlockShort()) {
            if (isTimelineOverflowingHorizontally()) {
                return firstResBlockCount * minResolutionWidth;
            } else {
                return getBoundingClientRectWidth(getFirstResolutionElement());
            }
        } else {
            if (isTimelineOverflowingHorizontally()) {
                return resBlockMinWidthPx;
            } else {
                return getBoundingClientRectWidth(getFirstResolutionElement());
            }
        }
    }

    /**
     * Returns the amount of visible blocks in the timeline for the active
     * resolution. Day blocks for Day/Week, hour blocks for Hour resolution.
     * 
     * @return
     */
    public int getVisibleResolutionBlockCount() {
        return resolutionBlockCount;
    }

    private void fillVisibleTimeline() {
        if (isTimelineOverflowingHorizontally()) {
            showResolutionBlocksOnView();
        } else {
            showAllResolutionBlocks();
        }
    }

    private Element getLastResolutionElement() {
        DivElement div = getResolutionDiv();
        if (div == null) {
            return null;
        }
        NodeList<Node> nodeList = div.getChildNodes();
        if (nodeList == null) {
            return null;
        }
        int blockCount = nodeList.getLength();
        if (blockCount < 1) {
            return null;
        }
        if (containsResBlockSpacer()) {
            int index = blockCount - 2;
            if (blockCount > 1 && index >= 0) {
                return Element.as(getResolutionDiv().getChild(index));
            }
            return null;
        }
        return Element.as(getResolutionDiv().getLastChild());
    }

    private Element getFirstResolutionElement() {
        if (getResolutionDiv().hasChildNodes()) {
            return getResolutionDiv().getFirstChildElement();
        }
        return null;
    }

    private void appendTimelineBlocks(BlockRowData rowData, String style) {
        for (Entry<String, Element> entry : rowData.getBlockEntries()) {
            getElement().appendChild(entry.getValue());
        }
        if (isAlwaysCalculatePixelWidths()) {
            getElement().appendChild(createSpacerBlock(style));
        }
    }

    /**
     * Update horizontal overflow state.
     */
    private void updateTimelineOverflowingHorizontally() {
        timelineOverflowingHorizontally = (getElementWidth(resolutionDiv) > getElementWidth(getElement()
                .getParentElement()));
    }

    private DivElement createSpacerBlock(String className) {
        DivElement block = DivElement.as(DOM.createDiv());
        block.setClassName(STYLE_ROW + " " + STYLE_YEAR);
        block.addClassName(STYLE_SPACER);
        block.setInnerText(" ");
        block.getStyle().setDisplay(Display.NONE); // not visible by default
        spacerBlocks.add(block);
        return block;
    }

    private void updateSpacerBlocks(double dayWidthPx) {
        double spaceLeft = getResolutionDivWidth()
                - (blocksInRange * dayWidthPx);
        if (spaceLeft > 0) {
            for (DivElement e : spacerBlocks) {
                e.getStyle().clearDisplay();
                e.getStyle().setWidth(spaceLeft, Unit.PX);
            }

            resSpacerDiv = createResolutionBlock();
            resSpacerDiv.addClassName(STYLE_SPACER);
            resSpacerDiv.getStyle().setWidth(spaceLeft, Unit.PX);
            resSpacerDiv.setInnerText(" ");
            resolutionDiv.appendChild(resSpacerDiv);
        } else {
            hideSpacerBlocks();
        }
    }

    private void hideSpacerBlocks() {
        for (DivElement e : spacerBlocks) {
            e.getStyle().setDisplay(Display.NONE);
        }
    }

    private void updateBlockWidths(BlockRowData rowData) {
        for (Entry<String, Element> entry : rowData.getBlockEntries()) {
            setWidth(entry.getValue(), rowData.getBlockLength(entry.getKey()));
        }
    }

    private boolean isFirstResBlockShort() {
        return firstResBlockCount > 0
                && ((resolution == Resolution.Week && firstResBlockCount < DAYS_IN_WEEK));
    }

    private boolean isLastResBlockShort() {
        return lastResBlockCount > 0
                && ((resolution == Resolution.Week && lastResBlockCount < DAYS_IN_WEEK));
    }

    private void updateResolutionBlockWidths(String pct) {

        if (styleElement == null) {
            boolean firstResBlockIsShort = isFirstResBlockShort();
            boolean lastResBlockIsShort = isLastResBlockShort();
            // styleElement is not set, set width for each block explicitly.
            int count = resolutionDiv.getChildCount();
            if (containsResBlockSpacer()) {
                count--;
            }
            int lastIndex = count - 1;
            int i;
            Element resBlock;
            for (i = 0; i < count; i++) {
                resBlock = Element.as(resolutionDiv.getChild(i));

                // first and last week blocks may be thinner than other
                // resolution blocks.
                if (firstResBlockIsShort && i == 0) {
                    setWidth(resBlock, firstResBlockCount);
                } else if (lastResBlockIsShort && i == lastIndex) {
                    setWidth(resBlock, lastResBlockCount);
                } else {
                    setWidth(resBlockWidthPx, pct, resBlock);
                }
            }

        } else {
            // set widths by updating injected styles in one place. Faster than
            // setting widths explicitly for each element.
            String center = getWidthStyleValue(pct);
            String first = center;
            String last = center;
            if (isFirstResBlockShort()) {
                first = getWidth(firstResBlockCount);
            }
            if (isLastResBlockShort()) {
                last = getWidth(lastResBlockCount);
            }
            StyleInjector.setContents(styleElement, "." + STYLE_CENTER
                    + " { width: " + center + "; } ." + STYLE_FIRST
                    + " { width: " + first + "; } ." + STYLE_LAST
                    + " { width: " + last + "; } ");
        }
    }

    private void removeResolutionSpacerBlock() {
        if (containsResBlockSpacer()) {
            resSpacerDiv.removeFromParent();
        }
    }

    private boolean containsResBlockSpacer() {
        return resSpacerDiv != null && resSpacerDiv.hasParentElement()
                && resSpacerDiv.getParentElement().equals(resolutionDiv);
    }

    private void prepareTimelineForHourResolution(long startDate, long endDate) {
        firstDay = true;
        prepareTimelineForResolution(HOUR_INTERVAL, startDate, endDate,
                new ResolutionBlockRegisterer() {

                    int hourCounter = firstHourOfRange;

                    @Override
                    public void registerResolutionBlock(int index, Date date,
                            String currentYear, boolean lastTimelineBlock) {

                        registerHourResolutionBlock();
                        hourCounter = Math.max((hourCounter + 1) % 25, 1);
                    }
                });
    }

    private void prepareTimelineForDayResolution(long startDate, long endDate) {
        prepareTimelineForResolution(DAY_INTERVAL, startDate, endDate,
                new ResolutionBlockRegisterer() {

                    int dayCounter = firstDayOfRange;
                    Weekday weekday;
                    boolean firstWeek = true;

                    @Override
                    public void registerResolutionBlock(int index, Date date,
                            String currentYear, boolean lastTimelineBlock) {

                        weekday = getWeekday(dayCounter);

                        if (resolution == Resolution.Week) {
                            registerWeekResolutionBlock(index, weekday,
                                    lastTimelineBlock, firstWeek);
                            if (firstWeek
                                    && (weekday == Weekday.Last || lastTimelineBlock)) {
                                firstWeek = false;
                            }
                        } else {
                            registerDayResolutionBlock();
                        }

                        dayCounter = Math.max((dayCounter + 1) % 8, 1);
                    }
                });
    }

    private void fillTimelineForResolution(final long startDate, long endDate,
            final int left) {
        if (resolution == Resolution.Day || resolution == Resolution.Week) {
            fillTimelineForDayResolution(startDate, endDate, left);
        } else if (resolution == Resolution.Hour) {
            fillTimelineForHourResolution(startDate, endDate, left);
        } else {
            GWT.log(getClass().getSimpleName() + " resolution "
                    + (resolution != null ? resolution.name() : "null")
                    + " is not supported");
            return;
        }

        GWT.log(getClass().getSimpleName()
                + " Filled new data and styles to visible timeline elements");
    }

    private void fillTimelineForHourResolution(final long startDate,
            long endDate, final int left) {
        firstDay = true;
        fillTimelineForResolution(HOUR_INTERVAL, startDate, endDate,
                new ResolutionBlockFiller() {

                    int hourCounter = getFirstHourOfVisibleRange(startDate);
                    boolean even = isEven(startDate);

                    @Override
                    public void fillResolutionBlock(int index, Date date,
                            String currentYear, boolean lastTimelineBlock) {
                        DivElement resBlock = DivElement.as(Element
                                .as(getResolutionDiv().getChild(index)));
                        fillHourResolutionBlock(resBlock, date, index,
                                hourCounter, lastTimelineBlock, left, even);
                        hourCounter = Math.max((hourCounter + 1) % 25, 1);
                        even = !even;
                    }

                    private boolean isEven(long startDate) {
                        if (TimelineWidget.this.startDate < startDate) {
                            int hours = (int) ((startDate - TimelineWidget.this.startDate) / HOUR_INTERVAL);
                            return (hours % 2) == 1;
                        }

                        return false;
                    }

                    private int getFirstHourOfVisibleRange(long startDate) {
                        if (TimelineWidget.this.startDate < startDate) {
                            int hours = (int) ((startDate - TimelineWidget.this.startDate) / HOUR_INTERVAL);
                            return (((firstHourOfRange - 1) + hours) % 24);
                        }

                        return firstHourOfRange;
                    }
                });
    }

    private void fillTimelineForDayResolution(final long startDate,
            long endDate, final int left) {
        fillTimelineForResolution(DAY_INTERVAL, startDate, endDate,
                new ResolutionBlockFiller() {

                    int dayCounter = getFirstDayOfVisibleRange(startDate);
                    boolean even = isEven(startDate, firstDayOfRange);
                    boolean firstWeek = true;
                    int weekIndex = 0;
                    Weekday weekday;

                    @Override
                    public void fillResolutionBlock(int index, Date date,
                            String currentYear, boolean lastTimelineBlock) {

                        try {
                            weekday = getWeekday(dayCounter);

                            if (resolution == Resolution.Week) {
                                DivElement resBlock = null;
                                if (index > 0 && weekday == Weekday.First) {
                                    weekIndex++;
                                    firstWeek = false;
                                    even = !even;
                                }
                                if (index == 0 || weekday == Weekday.First) {
                                    int childCount = getResolutionDiv()
                                            .getChildCount();
                                    if (isValidChildIndex(weekIndex, childCount)) {
                                        resBlock = DivElement.as(Element
                                                .as(getResolutionDiv()
                                                        .getChild(weekIndex)));
                                    } else {
                                        logIndexOutOfBounds("week", weekIndex,
                                                childCount);
                                        return;
                                    }
                                }
                                fillWeekResolutionBlock(resBlock, date,
                                        weekIndex, weekday, firstWeek,
                                        lastTimelineBlock, left, even);
                            } else {
                                int childCount = getResolutionDiv()
                                        .getChildCount();
                                if (isValidChildIndex(index, childCount)) {
                                    DivElement resBlock = DivElement.as(Element
                                            .as(getResolutionDiv().getChild(
                                                    index)));
                                    fillDayResolutionBlock(resBlock, date,
                                            index, isWeekEnd(dayCounter), left);
                                } else {
                                    logIndexOutOfBounds("day", index,
                                            childCount);
                                    return;
                                }

                            }

                        } finally {
                            dayCounter = Math.max((dayCounter + 1) % 8, 1);
                        }
                    }

                    private void logIndexOutOfBounds(String indexName,
                            int index, int childCount) {
                        GWT.log(indexName + " index " + index
                                + " out of bounds with childCount "
                                + childCount + ". Can't fill content.");
                    }

                    private int calcDaysLeftInFirstWeek(int startDay) {
                        int daysLeftInWeek = 0;
                        if (startDay != firstDayOfWeek) {
                            for (int i = startDay;; i++) {
                                daysLeftInWeek++;
                                if (Math.max(i % 8, 1) == lastDayOfWeek) {
                                    break;
                                }
                            }
                        }
                        return daysLeftInWeek;
                    }

                    private boolean isEven(long startDate, int startDay) {
                        if (TimelineWidget.this.startDate < startDate) {
                            int daysHidden = (int) ((startDate - TimelineWidget.this.startDate) / DAY_INTERVAL);
                            int daysLeftInFirstWeek = calcDaysLeftInFirstWeek(startDay);
                            if (daysHidden > daysLeftInFirstWeek) {
                                daysHidden -= daysLeftInFirstWeek;
                            }
                            int weeks = (((daysHidden)) / (DAYS_IN_WEEK));
                            boolean even = (weeks % 2) == 1;
                            return (firstWeekBlockHidden) ? !even : even;
                        }
                        return false;
                    }

                    private int getFirstDayOfVisibleRange(long startDate) {
                        if (TimelineWidget.this.startDate < startDate) {
                            int days = (int) ((startDate - TimelineWidget.this.startDate) / DAY_INTERVAL);
                            return (((firstDayOfRange - 1) + days) % 7) + 1;
                        }
                        return firstDayOfRange;
                    }

                });
    }

    private void prepareTimelineForResolution(long interval, long startDate,
            long endDate, ResolutionBlockRegisterer resBlockRegisterer) {
        blocksInRange = 0;
        resolutionBlockCount = 0;
        firstResBlockCount = 0;
        lastResBlockCount = 0;
        String currentYear = null;
        String currentMonth = null;
        String currentDay = null;
        long pos = startDate;
        int index = 0;
        boolean lastTimelineBlock = false;
        Date date;

        while (pos <= endDate) {
            lastTimelineBlock = (pos + interval) > endDate;
            date = new Date(pos);

            resBlockRegisterer.registerResolutionBlock(index, date,
                    currentYear, lastTimelineBlock);

            if (isYearRowVisible()) {
                currentYear = addYearBlock(currentYear, date);
            }
            if (isMonthRowVisible()) {
                currentMonth = addMonthBlock(currentMonth, date);
            }

            if (isDayRowVisible()) {
                currentDay = addDayBlock(currentDay, date);
            }
            pos += interval;
            index++;
        }
    }

    private void fillTimelineForResolution(long interval, long startDate,
            long endDate, ResolutionBlockFiller resBlockFiller) {
        String currentYear = null;
        long pos = startDate;
        int index = 0;
        boolean lastTimelineBlock = false;
        Date date;

        while (pos <= endDate) {
            lastTimelineBlock = (pos + interval) > endDate;
            date = new Date(pos);

            resBlockFiller.fillResolutionBlock(index, date, currentYear,
                    lastTimelineBlock);

            pos += interval;
            index++;
        }
    }

    private interface ResolutionBlockRegisterer {
        void registerResolutionBlock(int index, Date date, String currentYear,
                boolean lastTimelineBlock);
    }

    private interface ResolutionBlockFiller {
        void fillResolutionBlock(int index, Date date, String currentYear,
                boolean lastTimelineBlock);
    }

    public LocaleDataProvider getLocaleDataProvider() {
        return localeDataProvider;
    }

    private Weekday getWeekday(int dayCounter) {
        if (dayCounter == firstDayOfWeek) {
            return Weekday.First;
        }
        if (dayCounter == lastDayOfWeek) {
            return Weekday.Last;
        }
        return Weekday.Between;
    }

    private boolean isWeekEnd(int dayCounter) {
        return dayCounter == 1 || dayCounter == 7;
    }

    private String key(String prefix, BlockRowData rowData) {
        return prefix + "_" + (rowData.size());

    }

    private String newKey(String prefix, BlockRowData rowData) {
        return prefix + "_" + (rowData.size() + 1);
    }

    private String addBlock(String current, String target, Date date,
            BlockRowData rowData, Operation operation) {
        String key;
        if (!target.equals(current)) {
            current = target;
            key = newKey("" + current, rowData);
            operation.run(target, key, date);
        } else {
            key = key("" + current, rowData);
            rowData.setBlockLength(key, rowData.getBlockLength(key) + 1);
        }
        return current;
    }

    private interface Operation {
        void run(String target, String value, Date date);
    }

    private String addDayBlock(String currentDay, Date date) {
        String day = getDay(date);

        return addBlock(currentDay, day, date, dayRowData, new Operation() {

            @Override
            public void run(String day, String key, Date date) {
                addDayBlock(key, formatDayCaption(day, date));
            }
        });
    }

    private String addMonthBlock(String currentMonth, Date date) {
        final int month = getMonth(date);

        return addBlock(currentMonth, String.valueOf(month), date,
                monthRowData, new Operation() {

                    @Override
                    public void run(String target, String key, Date date) {
                        addMonthBlock(key, formatMonthCaption(month, date));
                    }
                });
    }

    private String addYearBlock(String currentYear, Date date) {
        String year = getYear(date);

        return addBlock(currentYear, year, date, yearRowData, new Operation() {

            @Override
            public void run(String year, String key, Date date) {
                addYearBlock(key, formatYearCaption(year, date));
            }
        });
    }

    private void addMonthBlock(String key, String text) {
        DivElement monthBlock = createTimelineBlock(key, text, STYLE_MONTH,
                monthRowData);

        addEvenStyleIfNeeded(monthRowData.size(), monthBlock);
    }

    private void addYearBlock(String key, String text) {
        createTimelineBlock(key, text, STYLE_YEAR, yearRowData);
    }

    private void addDayBlock(String key, String text) {
        DivElement dayBlock = createTimelineBlock(key, text, STYLE_DAY,
                dayRowData);

        addEvenStyleIfNeeded(dayRowData.size(), dayBlock);
    }

    private DivElement createTimelineBlock(String key, String text,
            String styleSuffix, BlockRowData rowData) {
        DivElement div = DivElement.as(DOM.createDiv());
        div.setClassName(STYLE_ROW + " " + styleSuffix);
        div.setInnerText(text);
        rowData.setBlockLength(key, 1);
        rowData.setBlock(key, div);
        return div;
    }

    private void addEvenStyleIfNeeded(int number, Element element) {
        if (ie8 && number % 2 == 0) {
            element.addClassName(STYLE_EVEN);
        }
    }

    private String formatDayCaption(String day, Date date) {
        if (dayFormat == null || dayFormat.isEmpty()) {
            return day;
        }
        return getLocaleDataProvider().formatDate(date, dayFormat);
    }

    private String formatYearCaption(String year, Date date) {
        if (yearFormat == null || yearFormat.isEmpty()) {
            return year;
        }
        return getLocaleDataProvider().formatDate(date, yearFormat);
    }

    private String formatWeekCaption(Date date) {
        if (weekFormat == null || weekFormat.isEmpty()) {
            return ""
                    + getWeekNumber(date, getLocaleDataProvider()
                            .getTimeZoneOffset(), getLocaleDataProvider()
                            .getFirstDayOfWeek());
        }
        return getLocaleDataProvider().formatDate(date, weekFormat);
    }

    private String formatMonthCaption(int month, Date date) {
        if (monthFormat == null || monthFormat.isEmpty()) {
            return monthNames[month];
        }
        return getLocaleDataProvider().formatDate(date, monthFormat);
    }

    private String getDay(Date date) {
        return getDayDateTimeFormat().format(date, gmt);
    }

    private String getYear(Date date) {
        return getYearDateTimeFormat().format(date, gmt);
    }

    private int getMonth(Date date) {
        String m = getMonthDateTimeFormat().format(date, gmt);
        return Integer.parseInt(m) - 1;
    }

    String createCalcCssValue(int resolutionBlockCount) {
        return createCalcCssValue(resolutionBlockCount, null);
    }

    String createCalcCssValue(int resolutionBlockCount, Integer multiplier) {
        if (ie) {
            // IEs up to 11 don't support more than two-decimal precision.
            // That's why we use calc(100% / x) or calc(123.12345%) css value to
            // workaround this limitation.
            // IE8 doesn't support calc() at all.
            if (!ie8) {
                if (multiplier != null) {
                    double percents = 100.0 / resolutionBlockCount
                            * multiplier.intValue();
                    return "calc(" + percents + "%)";
                }
                return "calc(100% / " + resolutionBlockCount + ")";
            }
        }
        return null;
    }

    private String createCalcCssValue(double v, double multiplier) {
        if (ie) {
            // see comments in createCalcCssValue(int, Integer)
            if (!ie8) {
                double percents = 100.0 / v * multiplier;
                return "calc(" + percents + "%)";
            }
        }
        return null;
    }

    /**
     * If unit is '%' , returns a 'calc(xx.xx%)' for IE9+, or just a 'xx.xx%'
     * for other browsers.
     * 
     * @param value
     *            Number
     * @param unit
     *            unit
     * @return Combined number value + unit string that can be passed for
     *         example to a element's css width/height.
     */
    public String toCssCalcOrNumberString(double value, String unit) {
        if (ie) {
            if (!ie8) {
                return "calc(" + value + unit + ")";
            }
        }
        return value + unit;
    }

    private void setWidth(Element element, int multiplier) {
        if (isTimelineOverflowingHorizontally()) {
            element.getStyle().setWidth(multiplier * minResolutionWidth,
                    Unit.PX);
        } else {
            if (isAlwaysCalculatePixelWidths()) {
                element.getStyle().setWidth(multiplier * dayOrHourWidthPx,
                        Unit.PX);
            } else {
                setCssPercentageWidth(element, blocksInRange,
                        dayWidthPercentage, multiplier);
            }
        }
    }

    private String getWidth(int multiplier) {
        if (isTimelineOverflowingHorizontally()) {
            return (multiplier * minResolutionWidth) + Unit.PX.getType();
        } else {
            if (isAlwaysCalculatePixelWidths()) {
                return multiplier * dayOrHourWidthPx + Unit.PX.getType();
            } else {
                return getCssPercentageWidth(blocksInRange, dayWidthPercentage,
                        multiplier);
            }
        }
    }

    private void setWidth(double resBlockWidthPx, String pct, Element element) {
        if (isTimelineOverflowingHorizontally()) {
            element.getStyle().setWidth(resBlockMinWidthPx, Unit.PX);
        } else {
            if (isAlwaysCalculatePixelWidths()) {
                element.getStyle().setWidth(resBlockWidthPx, Unit.PX);
            } else {
                setCssPercentageWidth(element, resBlockWidthPercentage, pct);
            }
        }
    }

    private String getWidthStyleValue(String pct) {
        if (isTimelineOverflowingHorizontally()) {
            return resBlockMinWidthPx + Unit.PX.getType();
        } else {
            if (isAlwaysCalculatePixelWidths()) {
                return resBlockWidthPx + Unit.PX.getType();
            } else {
                return getCssPercentageWidth(resBlockWidthPercentage, pct);
            }
        }
    }

    private void setCssPercentageWidth(Element element, int daysInRange,
            double width, int position) {
        String pct = createCalcCssValue(daysInRange, position);
        setCssPercentageWidth(element, position * width, pct);
    }

    private String getCssPercentageWidth(int daysInRange, double width,
            int position) {
        String pct = createCalcCssValue(daysInRange, position);
        return getCssPercentageWidth(position * width, pct);
    }

    private void setCssPercentageWidth(Element element, double nValue,
            String pct) {

        if (pct != null) {
            element.getStyle().setProperty("width", pct);
        } else {
            element.getStyle().setWidth(nValue, Unit.PCT);
        }
    }

    private String getCssPercentageWidth(double nValue, String pct) {
        if (pct != null) {
            return pct;
        } else {
            return nValue + Unit.PCT.getType();
        }
    }

    private void registerDayResolutionBlock() {
        blocksInRange++;
        resolutionBlockCount++;
    }

    private void fillDayResolutionBlock(DivElement resBlock, Date date,
            int index, boolean weekend, int left) {
        resBlock.setInnerText(getDayDateTimeFormat().format(date, gmt));
        if (weekend) {
            resBlock.addClassName(STYLE_WEEKEND);
        } else {
            resBlock.removeClassName(STYLE_WEEKEND);
        }

        if (styleElementForLeft == null && isTimelineOverflowingHorizontally()) {
            resBlock.getStyle().setPosition(Position.RELATIVE);
            resBlock.getStyle().setLeft(left, Unit.PX);
        }
    }

    private void registerWeekResolutionBlock(int index, Weekday weekDay,
            boolean lastBlock, boolean firstWeek) {
        if (index == 0 || weekDay == Weekday.First) {
            resolutionBlockCount++;
        }

        if (firstWeek && (weekDay == Weekday.Last || lastBlock)) {
            firstResBlockCount = index + 1;
        } else if (lastBlock) {
            lastResBlockCount = (index + 1 - firstResBlockCount) % 7;
        }

        blocksInRange++;
    }

    private void fillWeekResolutionBlock(DivElement resBlock, Date date,
            int index, Weekday weekDay, boolean firstWeek, boolean lastBlock,
            int left, boolean even) {
        if (resBlock != null) {
            resBlock.setInnerText(formatWeekCaption(date));

            if (even) {
                resBlock.addClassName(STYLE_EVEN);
            } else {
                resBlock.removeClassName(STYLE_EVEN);
            }

            if (styleElementForLeft == null
                    && isTimelineOverflowingHorizontally()) {
                resBlock.getStyle().setPosition(Position.RELATIVE);
                resBlock.getStyle().setLeft(left, Unit.PX);
            }

            resBlock.removeClassName(STYLE_FIRST);
            resBlock.removeClassName(STYLE_LAST);
        }

        if (firstWeek && (weekDay == Weekday.Last || lastBlock)) {
            Element firstEl = resolutionDiv.getFirstChildElement();
            if (!firstEl.hasClassName(STYLE_FIRST)) {
                firstEl.addClassName(STYLE_FIRST);
            }
        } else if (lastBlock) {
            Element lastEl = Element.as(resolutionDiv.getLastChild());
            if (!lastEl.hasClassName(STYLE_LAST)) {
                lastEl.addClassName(STYLE_LAST);
            }
        }
    }

    private void registerHourResolutionBlock() {
        blocksInRange++;
        resolutionBlockCount++;
    }

    private void fillHourResolutionBlock(DivElement resBlock, Date date,
            int index, int hourCounter, boolean lastBlock, int left,
            boolean even) {
        if (getLocaleDataProvider().isTwelveHourClock()) {
            resBlock.setInnerText(getHour12DateTimeFormat().format(date, gmt));
        } else {
            resBlock.setInnerText(getHour24DateTimeFormat().format(date, gmt));
        }

        if (even) {
            resBlock.addClassName(STYLE_EVEN);
        } else {
            resBlock.removeClassName(STYLE_EVEN);
        }

        if (firstDay && (hourCounter == 25 || lastBlock)) {
            firstDay = false;
            firstResBlockCount = index + 1;
        } else if (lastBlock) {
            lastResBlockCount = (index + 1 - firstResBlockCount) % 24;
        }

        if (styleElementForLeft == null && isTimelineOverflowingHorizontally()) {
            resBlock.getStyle().setPosition(Position.RELATIVE);
            resBlock.getStyle().setLeft(left, Unit.PX);
        }
    }

    private DivElement createResolutionBlock() {
        DivElement resBlock = DivElement.as(DOM.createDiv());
        resBlock.setClassName("col");
        return resBlock;
    }

    private DivElement createHourResolutionBlock() {
        DivElement resBlock = createResolutionBlock();
        resBlock.addClassName("h");
        resBlock.addClassName(STYLE_CENTER);
        return resBlock;
    }

    private DivElement createDayResolutionBlock() {
        DivElement resBlock = createResolutionBlock();
        resBlock.addClassName(STYLE_CENTER);
        return resBlock;
    }

    private DivElement createWeekResolutionBlock() {
        DivElement resBlock = createResolutionBlock();
        resBlock.addClassName("w");
        resBlock.addClassName(STYLE_CENTER);
        return resBlock;
    }

    private boolean isChanged(Resolution resolution, long startDate,
            long endDate, int firstDayOfWeek, int firstDayOfRange,
            int firstHourOfRange, String locale) {
        boolean resolutionChanged = this.resolution != resolution;
        if (resolutionChanged) {
            minResolutionWidth = -1;
        }

        if (forceUpdateFlag) {
            forceUpdateFlag = false;
            return true;
        }
        return resolutionChanged
                || this.startDate != startDate
                || this.endDate != endDate
                || this.firstDayOfWeek != firstDayOfWeek
                || this.firstDayOfRange != firstDayOfRange
                || this.firstHourOfRange != firstHourOfRange
                || (this.locale == null && locale != null || (this.locale != null && !this.locale
                        .equals(locale)));
    }

    private int calculateResolutionMinWidth() {

        if (resolution == Resolution.Week) {
            return RESOLUTION_WEEK_DAYBLOCK_WIDTH;
        }

        boolean removeResolutionDiv = false;
        if (!resolutionDiv.hasParentElement()) {
            removeResolutionDiv = true;
            getElement().appendChild(resolutionDiv);
        }
        DivElement resBlockMeasure = DivElement.as(DOM.createDiv());
        resBlockMeasure.setInnerText("MM");
        resBlockMeasure.setClassName(STYLE_COL);
        resolutionDiv.appendChild(resBlockMeasure);
        int width = resBlockMeasure.getClientWidth();
        resBlockMeasure.removeFromParent();
        if (removeResolutionDiv) {
            resolutionDiv.removeFromParent();
        }
        return width;
    }

    private void clear() {
        while (getElement().hasChildNodes()) {
            getElement().getLastChild().removeFromParent();
        }
        spacerBlocks.clear();
        yearRowData.clear();
        monthRowData.clear();
        dayRowData.clear();
    }

    private void showResolutionBlocksOnView() {
        double positionLeftSnapshot = positionLeft;
        double datePos = positionLeftSnapshot;
        firstWeekBlockHidden = false;

        int left = (int) positionLeftSnapshot;
        if (positionLeftSnapshot > 0 && resBlockWidthPx > 0) {
            double overflow = 0.0;
            boolean firstResBlockShort = isFirstResBlockShort();
            if (firstResBlockShort && left <= getFirstResolutionElementWidth()) {
                // need to notice a short resolution block due to timeline's
                // start date which is in middle of a week.
                overflow = positionLeftSnapshot
                        % getFirstResolutionElementWidth();
                if (overflow == 0.0) {
                    overflow = getFirstResolutionElementWidth();
                }

            } else {
                double firstBlockWidth = getFirstResolutionElementWidth();
                overflow = (positionLeftSnapshot - (firstResBlockShort ? firstBlockWidth
                        : 0))
                        % resBlockWidthPx;
                if (overflow == 0.0) {
                    overflow = resBlockWidthPx;
                }
                if (firstResBlockShort) {
                    overflow += firstBlockWidth;
                    datePos = positionLeftSnapshot - overflow;
                    firstWeekBlockHidden = true;
                }
            }

            left = (int) (positionLeftSnapshot - overflow);
        }

        if (datePos < 0.0) {
            datePos = positionLeftSnapshot;
        }
        long leftDate = getDateForLeftPosition(datePos);
        double containerWidth = GanttUtil
                .getBoundingClientRectWidth(getElement().getParentElement());
        fillTimelineForResolution(leftDate,
                getDateForLeftPosition(positionLeftSnapshot + containerWidth),
                left);

        if (styleElementForLeft != null) {
            StyleInjector.setContents(styleElementForLeft, "." + STYLE_COL
                    + " { position: relative; left: " + left + "px; }");
        }

        GWT.log(getClass().getSimpleName()
                + " Updated visible timeline elements for horizontal scroll position "
                + left);
    }

    private void showAllResolutionBlocks() {
        if (styleElementForLeft != null) {
            StyleInjector.setContents(styleElementForLeft, "." + STYLE_COL
                    + " { position: relative; left: 0px; }");
        }
        fillTimelineForResolution(startDate, endDate, 0);
    }

    private int calculateMinimumResolutionBlockWidth() {
        if (resolution == Resolution.Week) {
            return DAYS_IN_WEEK * minResolutionWidth;
        }
        return minResolutionWidth;
    }

    private void createTimelineElementsOnVisibleArea() {
        // create place holder elements that represents weeks/days/hours
        // depending on the resolution in the timeline.
        // Only visible blocks are created, and only once, content will change
        // on scroll.

        // first: detect how many blocks we can fit in the screen
        int blocks = resolutionBlockCount;
        if (isTimelineOverflowingHorizontally()) {
            blocks = (int) (GanttUtil.getBoundingClientRectWidth(getElement()
                    .getParentElement()) / calculateMinimumResolutionBlockWidth());
            if (resolutionBlockCount < blocks) {
                // blocks need to be scaled up to fit the screen
                blocks = resolutionBlockCount;
            } else {
                blocks += 2;
            }
        }

        DivElement element = null;
        for (int i = 0; i < blocks; i++) {
            switch (resolution) {
            case Hour:
                element = createHourResolutionBlock();
                break;
            case Day:
                element = createDayResolutionBlock();
                break;
            case Week:
                element = createWeekResolutionBlock();
                break;
            }
            resolutionDiv.appendChild(element);
        }

        GWT.log(getClass().getSimpleName() + " Added " + blocks
                + " visible timeline elements for resolution ."
                + String.valueOf(resolution));
    }

    private boolean isValidChildIndex(int index, int childCount) {
        return (index >= 0) && (index < childCount);
    }

    public static int getWeekNumber(Date d, long timezoneOffset,
            int firstDayOfWeek) {
        return GanttUtil.getWeekNumber(d, timezoneOffset, firstDayOfWeek);
    }

    public DivElement getResolutionDiv() {
        return resolutionDiv;
    }

    private class BlockRowData {

        private final Map<String, Element> blocks = new LinkedHashMap<String, Element>();
        private final Map<String, Integer> blockLength = new LinkedHashMap<String, Integer>();

        public int size() {
            return blocks.size();
        }

        public Element getBlock(String key) {
            return blocks.get(key);
        }

        public Set<Entry<String, Element>> getBlockEntries() {
            return blocks.entrySet();
        }

        public void setBlock(String key, Element element) {
            blocks.put(key, element);
        }

        public Integer getBlockLength(String key) {
            return blockLength.get(key);
        }

        public void setBlockLength(String key, Integer length) {
            blockLength.put(key, length);
        }

        public void clear() {
            blocks.clear();
            blockLength.clear();
        }
    }
}
