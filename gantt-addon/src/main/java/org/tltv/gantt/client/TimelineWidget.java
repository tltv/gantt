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
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.DOM;
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

    public static final int DAYS_IN_WEEK = 7;
    public static final int HOURS_IN_DAY = 24;
    public static final long DAY_INTERVAL = 24 * 60 * 60 * 1000;
    public static final long HOUR_INTERVAL = 60 * 60 * 1000;
    public static final int RESOLUTION_WEEK_DAYBLOCK_WIDTH = 4;
    public static final int RESOLUTION_HOUR_DAYBLOCK_WIDTH = 4;

    private boolean ie, ie8, ie9;

    private boolean forceUpdateFlag;

    private TimeZone gmt = TimeZone.createTimeZone(0);

    private LocaleDataProvider localeDataProvider;
    private DateTimeFormat yearDateTimeFormat;
    private DateTimeFormat monthDateTimeFormat;
    private DateTimeFormat weekDateTimeFormat;
    private DateTimeFormat dayDateTimeFormat;
    private DateTimeFormat hour12DateTimeFormat;
    private DateTimeFormat hour24DateTimeFormat;

    private boolean even;
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
    /* single resolution block interval in milliseconds */
    private long interval;

    /*
     * number of blocks in resolution range. Days for Day/Week resolution, Hours
     * for hour resolution..
     */
    private int blocksInRange = 0;
    private int firstResBlockCount;
    private int lastResBlockCount;
    private boolean firstWeek;
    private boolean firstDay;
    private boolean timelineOverflowingHorizontally;
    private boolean noticeVerticalScrollbarWidth;
    private boolean monthRowVisible;
    private boolean yearRowVisible;
    private String monthFormat;
    private String yearFormat;
    private String weekFormat;
    private String dayFormat;

    private DivElement resolutionDiv;
    private DivElement resSpacerDiv;
    private Set<DivElement> spacerBlocks = new HashSet<DivElement>();

    private BlockRowData yearRowData = new BlockRowData();
    private BlockRowData monthRowData = new BlockRowData();
    // days/daysLength are needed only with resolutions smaller than Day.
    private BlockRowData dayRowData = new BlockRowData();

    private int minResolutionWidth = -1;
    private int minWidth = -1;
    private boolean calcPixels = false;

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
     * @return Left offset in percentage.
     */
    public double getLeftPositionPercentageForDate(long date) {
        double left = getLeftPositionForDate(date);
        double width = getResolutionWidth();
        return (100.0 / width) * left;
    }

    /**
     * Calculate CSS value for 'left' property matching left offset in
     * percentage for a date ( {@link Date#getTime()}).
     * <p>
     * May return '2.123456%' or 'calc(2.123456%)' if IE(>8);
     * 
     * @param date
     *            Target date in milliseconds.
     * @return Left offset as a String value.
     */
    public String getLeftPositionPercentageStringForDate(long date) {
        double left = getLeftPositionForDate(date);
        double width = getResolutionWidth();
        String calc = createCalcCssValue(width, left);

        if (calc != null) {
            return calc;
        }
        return (100.0 / width) * left + "" + Unit.PCT.getType();
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
        double width = getResolutionWidth();
        double range = endDate - startDate;
        if (range <= 0) {
            return 0;
        }
        double p = width / range;
        double offset = date - startDate;
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
        double range = endDate - startDate;
        if (width <= 0) {
            return 0;
        }
        double p = range / width;
        double offset = p * left;
        double date = startDate + offset;
        return (long) date;
    }

    /**
     * Set horizontal scroll position for the time-line.
     * 
     * @param left
     *            Scroll position in pixels.
     */
    public void setScrollLeft(double left) {
        getElement().getStyle().setLeft(-left, Unit.PX);
    }

    /**
     * Re-calculates required widths for this widget.
     */
    public void updateWidths() {
        if (resolutionDiv == null) {
            GWT.log(getClass().getSimpleName()
                    + " is not ready for updateWidths() call. Call update(...) instead.");
            return;
        }

        GWT.log(getClass().getSimpleName() + " Started updating widths.");

        setMinWidth(blocksInRange * minResolutionWidth);

        // update horizontal overflow state here, after min-width is updated.
        updateTimelineOverflowingHorizontally();

        // remove spacer block if it exist
        removeResolutionSpacerBlock();
        // now when the spacer block is removed, count resolution block elements
        int resolutionBlockCount = resolutionDiv.getChildCount();

        // calculate new block width for day-resolution.
        // Year and month blocks are vertically in-line with days.
        double dayWidthPercentage = 100.0 / blocksInRange;
        double dayWidthPx = Math.round(resolutionDiv.getClientWidth()
                / blocksInRange);
        while ((resolutionDiv.getClientWidth() % (blocksInRange * dayWidthPx)) >= blocksInRange) {
            dayWidthPx++;
        }

        // calculate block width for currently selected resolution
        // (day,week,...)
        // resolution div's content may not be vertically in-line with
        // year/month blocks. This is the case for example with Week resolution.
        double resBlockMinWidthPx = minResolutionWidth;
        double resBlockWidthPx = dayWidthPx;
        double resBlockWidthPercentage = 100.0 / resolutionBlockCount;
        String pct = createCalcCssValue(resolutionBlockCount);
        if (resolution == Resolution.Week) {
            resBlockMinWidthPx = DAYS_IN_WEEK * minResolutionWidth;
            resBlockWidthPx = DAYS_IN_WEEK * dayWidthPx;
            resBlockWidthPercentage = dayWidthPercentage * DAYS_IN_WEEK;
            pct = createCalcCssValue(blocksInRange, DAYS_IN_WEEK);

        }

        // update resolution block widths
        updateResolutionBlockWidths(resolutionBlockCount, dayWidthPercentage,
                dayWidthPx, resBlockMinWidthPx, resBlockWidthPx,
                resBlockWidthPercentage, pct);

        if (isYearRowVisible()) {
            // update year block widths
            updateBlockWidths(dayWidthPercentage, dayWidthPx, yearRowData);
        }

        if (isMonthRowVisible()) {
            // update month block widths
            updateBlockWidths(dayWidthPercentage, dayWidthPx, monthRowData);
        }

        if (isDayRowVisible()) {
            updateBlockWidths(dayWidthPercentage, dayWidthPx, dayRowData);
        }

        if (isAlwaysCalculatePixelWidths()) {
            updateSpacerBlocks(dayWidthPx);
        }

        GWT.log(getClass().getSimpleName() + " Widths are updated.");
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

    public void setBrowserInfo(boolean ie, boolean ie8, boolean ie9) {
        this.ie = ie;
        this.ie8 = ie8;
        this.ie9 = ie9;
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
        double width = resolutionDiv.getClientWidth();
        if (isAlwaysCalculatePixelWidths() && resSpacerDiv != null
                && resSpacerDiv.hasParentElement()) {
            width = width - resSpacerDiv.getClientWidth();
        }
        return width;
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
        timelineOverflowingHorizontally = resolutionDiv.getClientWidth() > getElement()
                .getParentElement().getClientWidth();
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
        double spaceLeft = resolutionDiv.getClientWidth()
                - (blocksInRange * dayWidthPx);
        if (spaceLeft > 0) {
            for (DivElement e : spacerBlocks) {
                e.getStyle().clearDisplay();
                e.getStyle().setWidth(spaceLeft, Unit.PX);
            }

            resSpacerDiv = createResolutionBLock();
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

    private void updateBlockWidths(double dayWidthPercentage,
            double dayWidthPx, BlockRowData rowData) {
        int lastIndex;
        int i = 0;
        lastIndex = rowData.size() - 1;
        for (Entry<String, Element> entry : rowData.getBlockEntries()) {
            setWidth(blocksInRange, dayWidthPercentage, dayWidthPx,
                    entry.getValue(), rowData.getBlockLength(entry.getKey()));

            ieFix(i, lastIndex, entry.getValue());
            i++;
        }
    }

    private void updateResolutionBlockWidths(int resolutionBlockCount,
            double dayWidthPercentage, double dayWidthPx,
            double resBlockMinWidthPx, double resBlockWidthPx,
            double resBlockWidthPercentage, String pct) {

        boolean firstResBlockIsShort = firstResBlockCount > 0
                && ((resolution == Resolution.Week && firstResBlockCount < DAYS_IN_WEEK));
        boolean lastResBlockIsShort = lastResBlockCount > 0
                && ((resolution == Resolution.Week && lastResBlockCount < DAYS_IN_WEEK));

        int lastIndex = resolutionBlockCount - 1;
        int i;
        Element resBlock;
        for (i = 0; i < resolutionBlockCount; i++) {
            resBlock = Element.as(resolutionDiv.getChild(i));

            // first and last week blocks may be thinner than other
            // resolution blocks.
            if (firstResBlockIsShort && i == 0) {
                setWidth(blocksInRange, dayWidthPercentage, dayWidthPx,
                        resBlock, firstResBlockCount);
            } else if (lastResBlockIsShort && i == lastIndex) {
                setWidth(blocksInRange, dayWidthPercentage, dayWidthPx,
                        resBlock, lastResBlockCount);
            } else {
                setWidth(resBlockMinWidthPx, resBlockWidthPercentage,
                        resBlockWidthPx, pct, resBlock);
            }

            ieFix(i, lastIndex, resBlock);
        }
    }

    private void removeResolutionSpacerBlock() {
        if (resSpacerDiv != null && resSpacerDiv.hasParentElement()) {
            resSpacerDiv.removeFromParent();
        }
    }

    private void prepareTimelineForHourResolution(long startDate, long endDate) {
        firstDay = true;
        prepareTimelineForResolution(HOUR_INTERVAL, startDate, endDate,
                new ResolutionBlockAdder() {

                    int hourCounter = firstHourOfRange;

                    @Override
                    public void addResolutionBLock(int index, Date date,
                            String currentYear, boolean lastTimelineBlock) {

                        addHourResolutionBlock(date, index, hourCounter,
                                lastTimelineBlock);

                        hourCounter = Math.max((hourCounter + 1) % 25, 1);
                    }
                });
    }

    private void prepareTimelineForDayResolution(long startDate, long endDate) {
        firstWeek = true;
        prepareTimelineForResolution(DAY_INTERVAL, startDate, endDate,
                new ResolutionBlockAdder() {

                    int dayCounter = firstDayOfRange;
                    Weekday weekday;

                    @Override
                    public void addResolutionBLock(int index, Date date,
                            String currentYear, boolean lastTimelineBlock) {

                        weekday = getWeekday(dayCounter);

                        if (resolution == Resolution.Week) {
                            addWeekResolutionBlock(date, index, weekday,
                                    isWeekEnd(dayCounter), lastTimelineBlock);
                        } else {
                            addDayResolutionBlock(date, index,
                                    isWeekEnd(dayCounter));
                        }

                        dayCounter = Math.max((dayCounter + 1) % 8, 1);
                    }
                });
    }

    private void prepareTimelineForResolution(long interval, long startDate,
            long endDate, ResolutionBlockAdder resBlockAdder) {
        this.interval = interval;
        blocksInRange = 0;
        even = false;
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

            resBlockAdder.addResolutionBLock(index, date, currentYear,
                    lastTimelineBlock);

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

    private interface ResolutionBlockAdder {
        void addResolutionBLock(int index, Date date, String currentYear,
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

    private String createCalcCssValue(int resolutionBlockCount) {
        return createCalcCssValue(resolutionBlockCount, null);
    }

    private String createCalcCssValue(int resolutionBlockCount,
            Integer multiplier) {
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

    private void setWidth(int daysInRange, double dayWidthPercentage,
            double dayWidthPx, Element element, int position) {
        if (isTimelineOverflowingHorizontally()) {
            element.getStyle().setWidth(position * minResolutionWidth, Unit.PX);
        } else {
            if (isAlwaysCalculatePixelWidths()) {
                element.getStyle().setWidth(position * dayWidthPx, Unit.PX);
            } else {
                setCssPercentageWidth(element, daysInRange, dayWidthPercentage,
                        position);
            }
        }
    }

    private void setWidth(double minPxValue, double pctValue, double pxValue,
            String pct, Element element) {
        if (isTimelineOverflowingHorizontally()) {
            element.getStyle().setWidth(minPxValue, Unit.PX);
        } else {
            if (isAlwaysCalculatePixelWidths()) {
                element.getStyle().setWidth(pxValue, Unit.PX);
            } else {
                setCssPercentageWidth(element, pctValue, pct);
            }
        }
    }

    private void ieFix(int index, int lastIndex, Element element) {
        if (!ie) {
            return;
        }
        if (index == lastIndex) {
            // last block
            if (ie9
                    || !"-ms-inline-flexbox".equals(element.getParentElement()
                            .getStyle().getProperty("display"))) {
                // IE9 workaround: adjust last block to have a -1px right
                // margin that helps to the lack of -ms-inline-flexbox
                // support.
                // Same workaround is used for all IEs when the parent element
                // don't have 'display: -ms-inline-flexbox'
                element.getStyle().setMarginRight(-1, Unit.PX);
            }
        }
    }

    private void setCssPercentageWidth(Element element, int daysInRange,
            double width, int position) {
        String pct = createCalcCssValue(daysInRange, position);
        setCssPercentageWidth(element, position * width, pct);
    }

    private void setCssPercentageWidth(Element element, double nValue,
            String pct) {

        if (pct != null) {
            element.getStyle().setProperty("width", pct);
        } else {
            element.getStyle().setWidth(nValue, Unit.PCT);
        }
    }

    private void addDayResolutionBlock(Date date, int index, boolean weekend) {
        DivElement resBlock = createResolutionBLock();
        resBlock.setInnerText(getDayDateTimeFormat().format(date, gmt));
        if (weekend) {
            resBlock.addClassName(STYLE_WEEKEND);
        }
        resolutionDiv.appendChild(resBlock);
        blocksInRange++;
    }

    private void addWeekResolutionBlock(Date date, int index, Weekday weekDay,
            boolean weekend, boolean lastBlock) {
        DivElement resBlock;

        if (index == 0 || weekDay == Weekday.First) {
            resBlock = createResolutionBLock();
            resBlock.addClassName("w");
            resBlock.setInnerText(formatWeekCaption(date));

            if (index > 0) {
                even = !even;
            }
            if (even) {
                resBlock.addClassName(STYLE_EVEN);
            }

            // append just one week resolution block per week.
            resolutionDiv.appendChild(resBlock);
        }

        if (firstWeek && (weekDay == Weekday.Last || lastBlock)) {
            firstWeek = false;
            firstResBlockCount = index + 1;
        } else if (lastBlock) {
            lastResBlockCount = (index + 1 - firstResBlockCount) % 7;
        }

        blocksInRange++;
    }

    private void addHourResolutionBlock(Date date, int index, int hourCounter,
            boolean lastBlock) {
        DivElement resBlock;

        resBlock = createResolutionBLock();
        resBlock.addClassName("h");
        if (getLocaleDataProvider().isTwelveHourClock()) {
            resBlock.setInnerText(getHour12DateTimeFormat().format(date, gmt));
        } else {
            resBlock.setInnerText(getHour24DateTimeFormat().format(date, gmt));
        }

        if (index > 0) {
            even = !even;
        }
        if (even) {
            resBlock.addClassName(STYLE_EVEN);
        }

        resolutionDiv.appendChild(resBlock);

        if (firstDay && (hourCounter == 25 || lastBlock)) {
            firstDay = false;
            firstResBlockCount = index + 1;
        } else if (lastBlock) {
            lastResBlockCount = (index + 1 - firstResBlockCount) % 24;
        }

        blocksInRange++;
    }

    private DivElement createResolutionBLock() {
        DivElement resBlock = DivElement.as(DOM.createDiv());
        resBlock.setClassName("col");
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

    public static int getWeekNumber(Date d, long timezoneOffset,
            int firstDayOfWeek) {
        return GanttUtil.getWeekNumber(d, timezoneOffset, firstDayOfWeek);
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
