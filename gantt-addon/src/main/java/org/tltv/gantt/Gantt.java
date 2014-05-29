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

package org.tltv.gantt;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.tltv.gantt.client.shared.GanttServerRpc;
import org.tltv.gantt.client.shared.GanttState;
import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.util.ReflectTools;

/**
 * Gantt Chart server side component.
 * <p>
 * Default timeline resolution is {@link Resolution#Day}. Timeline range is not
 * set by default. First thing to do after construction is to set timeline range
 * by {@link #setStartDate(Date)} and {@link #setEndDate(Date)}.
 * <p>
 * Notice current Locale and Timezone in effect. It's safest to set the new
 * Locale and/or Timezone first (if it's different than active session's current
 * Locale), and set the dates after that.
 * <p>
 * With a resolution {@link Resolution#Week} and {@link Resolution#Day} timeline
 * range's time is adjusted internally to start from minimum 00:00:00.000 and
 * end to maximum 23:59:59:999 (for 24h locale, 12H is also noticed).
 * 
 * @author Tltv
 * 
 */
public class Gantt extends com.vaadin.ui.AbstractComponent {

    private Date startDate;
    private Date endDate;
    private TimeZone timezone;
    private Calendar calendar;

    private GanttServerRpc rpc = new GanttServerRpc() {

        @Override
        public void stepClicked(int index) {
            fireClickEvent(index);
        }

        @Override
        public void onMove(int stepIndex, int newRowIndex, long startDate,
                long endDate) {
            fireMoveEvent(stepIndex, newRowIndex, startDate, endDate);
        }

        @Override
        public void onResize(int stepIndex, long startDate, long endDate) {
            fireResizeEvent(stepIndex, startDate, endDate);
        }
    };

    public Gantt() {
        registerRpc(rpc);
    }

    @Override
    public void attach() {
        super.attach();

        // update component state's locale.
        updateLocale();
    }

    @Override
    public GanttState getState() {
        return (GanttState) super.getState();
    }

    /**
     * Set start date of the Gantt chart's timeline. When resolution is
     * {@link Resolution#Day} or {@link Resolution#Week}, time will be adjusted
     * to a minimum possible for the given date (1/1/2010 12:12:12 => 1/1/20120
     * 00:00:00).
     * <p>
     * For {@link Resolution#Hour}, given date is adjusted like this: 1/1/2010
     * 12:12:12 => 1/1/2010 12:00:00.
     * 
     * @param date
     */
    public void setStartDate(Date date) {
        if (date == null) {
            throw new UnsupportedOperationException(
                    "Setting a null start date for the Gantt is not allowed.");
        }
        setInternalStartDate(date);
        updateTimelineStartTimeDetails();
    }

    /**
     * Set end date of the Gantt chart's timeline. When resolution is
     * {@link Resolution#Day} or {@link Resolution#Week}, time will be adjusted
     * to a maximum possible for the given date (1/1/2010 12:12:12 => 1/1/20120
     * 23:59:59).
     * 
     * @param date
     */
    public void setEndDate(Date date) {
        if (date == null) {
            throw new UnsupportedOperationException(
                    "Setting a null end date for the Gantt is not allowed.");
        }
        setInternalEndDate(date);
        updateTimelineStartTimeDetails();
    }

    /**
     * Get start date of the Gantt chart's timeline.
     * 
     * @return Start Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Get end date of the Gantt chart's timeline.
     * 
     * @return End Date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set new timeline resolution. Allowed resolutions are
     * {@link Resolution#Day} and {@link Resolution#Week}.
     * 
     * @param resolution
     */
    public void setResolution(Resolution resolution) {
        if (resolution == null) {
            resolution = Resolution.Day;
        }
        boolean changed = !resolution.equals(getResolution());
        getState().resolution = resolution;
        if (changed) {
            // adjust timeline range for the new resolution.
            setInternalStartDate(getStartDate());
            setInternalEndDate(getEndDate());
            updateTimelineStartTimeDetails();
        }
    }

    /**
     * Get current timeline resolution.
     * 
     * @return
     */
    public Resolution getResolution() {
        return getState().resolution;
    }

    /**
     * Add new {@link Step}.
     * 
     * @param step
     *            New Step object
     */
    public void addStep(Step step) {
        getState().steps.add(step);
    }

    /**
     * Remove {@link Step}.
     * 
     * @param step
     *            Target Step
     * @return true when removed successfully.
     */
    public boolean removeStep(Step step) {
        return getState().steps.remove(step);
    }

    /**
     * Returns a unmodifiable List of steps.
     * 
     * @return List of steps
     */
    public List<Step> getSteps() {
        return Collections.unmodifiableList(getState().steps);
    }

    /**
     * Returns true if user can resize the step length. Default is true.
     * 
     * @return true if rezisable
     */
    public boolean isResizableSteps() {
        return getState().resizableSteps;
    }

    /**
     * Enable or disable step resizing -feature. Enabled by default.
     * 
     * @param resizable
     *            True enables step resizing.
     */
    public void setResizableSteps(boolean resizable) {
        getState().resizableSteps = resizable;
    }

    /**
     * Return true if user can move the step position in the time line. Default
     * is true.
     * 
     * @return true if movable
     */
    public boolean isMovable() {
        return getState().movableSteps;
    }

    /**
     * Enable or disable step moving -feature. Enabled by default.
     * 
     * @param movable
     *            True enables step moving.
     */
    public void setMovableSteps(boolean movable) {
        getState().movableSteps = movable;
    }

    /**
     * Return true if years should be shown in a row in the time line. Default
     * is true.
     * 
     * @return
     */
    public boolean isYearsVisible() {
        return getState().yearRowVisible;
    }

    /**
     * Set time line's year row visible or hidden.
     * 
     * @param visible
     */
    public void setYearsVisible(boolean visible) {
        getState().yearRowVisible = visible;
    }

    /**
     * Return true if months should be shown in a row in the time line. Default
     * is true.
     * 
     * @return
     */
    public boolean isMonthsVisible() {
        return getState().monthRowVisible;
    }

    /**
     * Set time line's month row visible or hidden.
     * 
     * @param visible
     */
    public void setMonthsVisible(boolean visible) {
        getState().monthRowVisible = visible;
    }

    /**
     * Set timeline's year row's year format. Default is just year number.
     * 
     * @param format
     *            Format string like 'dd yyyy MMM' or 'MMMM'
     */
    public void setTimelineYearFormat(String format) {
        getState().yearFormat = trimFormat(format);
    }

    /**
     * Set timeline's month row's year format. Default is full month name.
     * 
     * @param format
     *            Format string like 'dd yyyy MMM' or 'MMMM'
     */
    public void setTimelineMonthFormat(String format) {
        getState().monthFormat = trimFormat(format);
    }

    /**
     * Timeline's year row's year format.
     * 
     * @return
     */
    public String getTimelineYearFormat() {
        return getState().yearFormat;
    }

    /**
     * Timeline's month row's month format.
     * 
     * @return
     */
    public String getTimelineMonthFormat() {
        return getState().monthFormat;
    }

    /**
     * Set timeline's week row's week format for {@link Resolution#Week}
     * resolution. Default is week number.
     * 
     * @param format
     *            Format string like 'dd'
     */
    public void setTimelineWeekFormat(String weekFormat) {
        getState().weekFormat = weekFormat;
    }

    /**
     * Timeline's week row's week format for {@link Resolution#Week} resolution.
     * 
     * @return
     */
    public String getTimelineWeekFormat() {
        return getState().weekFormat;
    }

    /**
     * Set timeline's day row's format for {@link Resolution#Hour} resolution.
     * Default is number of day in month.
     * 
     * @param format
     *            Format string like 'dd'
     */
    public void setTimelineDayFormat(String dayFormat) {
        getState().dayFormat = dayFormat;
    }

    /**
     * Timeline's day row's day format for {@link Resolution#Hour} resolution.
     * 
     * @return
     */
    public String getTimelineDayFormat() {
        return getState().dayFormat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.AbstractComponent#setLocale(java.util.Locale)
     */
    @Override
    public void setLocale(Locale locale) {
        boolean changed = locale != getLocale();
        super.setLocale(locale);
        if (changed) {
            updateTimelineStartTimeDetails();
            updateLocale();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.AbstractComponent#getLocale()
     */
    @Override
    public Locale getLocale() {
        Locale l = super.getLocale();
        if (l == null) {
            return Locale.getDefault();
        }
        return l;
    }

    /**
     * Get currently active {@link TimeZone}.
     * 
     * @return Component's active Timezone
     */
    public TimeZone getTimeZone() {
        if (timezone == null) {
            return getCalendar().getTimeZone();
        }
        return timezone;
    }

    /**
     * Set component's active {@link TimeZone}.
     * 
     * @param zone
     *            Timezone
     */
    public void setTimeZone(TimeZone zone) {
        if (!getTimeZone().equals(zone)) {
            timezone = zone; // internal timezone cmay be null
            if (zone == null) {
                zone = TimeZone.getDefault();
            }
            getCalendar().setTimeZone(zone);
            // refresh timeline range. Depending on the resolution, it might
            // need some adjusting.
            setInternalStartDate(getStartDate());
            setInternalEndDate(getEndDate());
            updateTimelineStartTimeDetails();
            markAsDirty();
        }
    }

    /**
     * Set target Table component that will scroll vertically with the Gantt
     * component and vice versa.
     * <p>
     * Table height is maintained by Gantt after this call. Table rows have to
     * have exactly same heights as the Gantt steps have.
     * 
     * @param table
     *            Target Table component.
     */
    public void setVerticalScrollDelegateTarget(Table table) {
        table.setHeight(getHeight(), getHeightUnits());
        getState().verticalScrollDelegateTarget = table;
    }

    @Override
    public void setHeight(float height, Unit unit) {
        super.setHeight(height, unit);
        if (getState().verticalScrollDelegateTarget != null) {
            ((Component) getState().verticalScrollDelegateTarget).setHeight(
                    getHeight(), getHeightUnits());
        }
    }

    private Calendar getCalendar() {
        if (calendar == null) {
            if (timezone != null) {
                calendar = Calendar.getInstance(timezone, getLocale());
            } else {
                calendar = Calendar.getInstance(getLocale());
            }
        }
        return calendar;
    }

    private void setInternalStartDate(Date date) {
        if (date == null) {
            return;
        }
        startDate = resetTimeToMin(date);
        getState().startDate = startDate.getTime();
    }

    private void setInternalEndDate(Date date) {
        if (date == null) {
            return;
        }
        endDate = resetTimeToMax(date);
        getState().endDate = endDate.getTime();
    }

    private Date resetTimeToMin(Date date) {
        Calendar cal = getCalendar();
        cal.setTimeInMillis(date.getTime());
        if (!getResolution().equals(Resolution.Hour)) {
            // reset hour only if the resolution is set to DAY, WEEK or MONTH
            cal.set(Calendar.HOUR, cal.getMinimum(Calendar.HOUR));
            cal.set(Calendar.AM_PM, cal.getMinimum(Calendar.AM_PM));
        }
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    private Date resetTimeToMax(Date date) {
        Calendar cal = getCalendar();
        cal.setTimeInMillis(date.getTime());
        if (!getResolution().equals(Resolution.Hour)) {
            // reset hour only if the resolution is set to DAY, WEEK or MONTH
            cal.set(Calendar.HOUR, cal.getMaximum(Calendar.HOUR));
            cal.set(Calendar.AM_PM, cal.getMaximum(Calendar.AM_PM));
        }
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    private void updateTimelineStartTimeDetails() {
        if (startDate == null) {
            return;
        }
        updateTimezoneOffsets();
        updateFirstHourOfRange();
        updateFirstDayOfRange();
    }

    private void updateTimezoneOffsets() {
        if (startDate != null) {
            getState().startDate = startDate.getTime();
        }
        if (endDate != null) {
            getState().endDate = endDate.getTime();
        }
        getState().timeZoneOffset = Long.valueOf(getCalendar().get(
                Calendar.ZONE_OFFSET)
                + getCalendar().get(Calendar.DST_OFFSET));
    }

    private void updateFirstHourOfRange() {
        Calendar cal = getCalendar();
        cal.setTime(startDate);
        getState().firstHourOfRange = cal.get(Calendar.HOUR_OF_DAY);

    }

    private void updateFirstDayOfRange() {
        Calendar cal = getCalendar();
        cal.setTime(startDate);
        getState().firstDayOfRange = cal.get(Calendar.DAY_OF_WEEK);
    }

    private void updateLocale() {
        Locale locale = getLocale();
        getState().locale = locale.toString();
        calendar = null;
    }

    private String trimFormat(String format) {
        if (format != null) {
            format = format.trim();
            if (format.isEmpty()) {
                format = null;
            }
        }
        return format;
    }

    /**
     * Localized display names for week days starting from sunday. Returned
     * array's length is always 7.
     * 
     * @return Array of localized weekday names.
     */
    protected String[] getDayNames() {
        DateFormatSymbols s = new DateFormatSymbols(getLocale());
        return Arrays.copyOfRange(s.getWeekdays(), 1, 8);
    }

    /**
     * Localized display names for months starting from January. Returned
     * array's length is always 12.
     * 
     * @return Array of localized month names.
     */
    protected String[] getMonthNames() {
        DateFormatSymbols s = new DateFormatSymbols(getLocale());
        return Arrays.copyOf(s.getMonths(), 12);
    }

    protected void fireClickEvent(int stepIndex) {
        fireEvent(new ClickEvent(this, getState().steps.get(stepIndex)));
    }

    protected void fireMoveEvent(int stepIndex, int newRowIndex,
            long startDate, long endDate) {
        Step step = getState().steps.get(stepIndex);
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        fireEvent(new MoveEvent(this, step, startDate, endDate));
    }

    protected void fireResizeEvent(int stepIndex, long startDate, long endDate) {
        Step step = getState().steps.get(stepIndex);
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        fireEvent(new ResizeEvent(this, step, startDate, endDate));
    }

    /**
     * Add {@link ClickListener} to listen clicks on steps.
     * 
     * @param listener
     */
    public void addClickListener(ClickListener listener) {
        addListener(ClickEvent.class, listener,
                ClickListener.GANTT_CLICK_METHOD);
    }

    /**
     * Add {@link MoveListener} to listen step's move events.
     * 
     * @param listener
     */
    public void addMoveListener(MoveListener listener) {
        addListener(MoveEvent.class, listener, MoveListener.GANTT_MOVE_METHOD);
    }

    /**
     * Add {@link ResizeListener} to listen step's resize events.
     * 
     * @param listener
     */
    public void addResizeListener(ResizeListener listener) {
        addListener(ResizeEvent.class, listener,
                ResizeListener.GANTT_RESIZE_METHOD);
    }

    public void removeClickListener(ClickListener listener) {
        removeListener(ClickEvent.class, listener,
                ClickListener.GANTT_CLICK_METHOD);
    }

    public void removemoveListener(MoveListener listener) {
        removeListener(MoveEvent.class, listener,
                MoveListener.GANTT_MOVE_METHOD);
    }

    public void removeResizeListener(ResizeListener listener) {
        removeListener(ResizeEvent.class, listener,
                ResizeListener.GANTT_RESIZE_METHOD);
    }

    public class ClickEvent extends Component.Event {

        private Step step;

        public ClickEvent(Gantt source, Step step) {
            super(source);
            this.step = step;
        }

        /**
         * Get target Step.
         * 
         * @return
         */
        public Step getStep() {
            return step;
        }

        public void setStep(Step step) {
            this.step = step;
        }

    }

    public class MoveEvent extends Component.Event {

        private Step step;
        private long startDate;

        private long endDate;

        public MoveEvent(Gantt source, Step step, long startDate, long endDate) {
            super(source);
            this.step = step;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        /**
         * Get target Step.
         * 
         * @return
         */
        public Step getStep() {
            return step;
        }

        public void setStep(Step step) {
            this.step = step;
        }

        /**
         * Get new start date. Value follows specifications of
         * {@link Date#getTime()}.
         * 
         * @return New start date in milliseconds.
         */
        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        /**
         * Get new end date. Value follows specifications of
         * {@link Date#getTime()}.
         * 
         * @return New end date in milliseconds.
         */
        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }
    }

    public class ResizeEvent extends Component.Event {

        private Step step;
        private long startDate;
        private long endDate;

        public ResizeEvent(Gantt source, Step step, long startDate, long endDate) {
            super(source);
            this.step = step;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        /**
         * Get target Step.
         * 
         * @return
         */
        public Step getStep() {
            return step;
        }

        public void setStep(Step step) {
            this.step = step;
        }

        /**
         * Get new start date. Value follows specifications of
         * {@link Date#getTime()}.
         * 
         * @return New start date in milliseconds.
         */
        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        /**
         * Get new end date. Value follows specifications of
         * {@link Date#getTime()}.
         * 
         * @return New end date in milliseconds.
         */
        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }
    }

    public interface ClickListener extends Serializable {

        public static final Method GANTT_CLICK_METHOD = ReflectTools
                .findMethod(ClickListener.class, "onGanttClick",
                        ClickEvent.class);

        public void onGanttClick(ClickEvent event);
    }

    public interface MoveListener extends Serializable {

        public static final Method GANTT_MOVE_METHOD = ReflectTools.findMethod(
                MoveListener.class, "onGanttMove", MoveEvent.class);

        public void onGanttMove(MoveEvent event);
    }

    public interface ResizeListener extends Serializable {

        public static final Method GANTT_RESIZE_METHOD = ReflectTools
                .findMethod(ResizeListener.class, "onGanttResize",
                        ResizeEvent.class);

        public void onGanttResize(ResizeEvent event);
    }
}
