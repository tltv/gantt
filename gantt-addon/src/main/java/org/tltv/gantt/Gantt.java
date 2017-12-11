/*
 * Copyright 2016 Tomi Virtanen
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.GanttServerRpc;
import org.tltv.gantt.client.shared.GanttState;
import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

import com.vaadin.shared.Connector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HasComponents;
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
public class Gantt extends AbstractComponent implements HasComponents {

    private Date startDate;
    private Date endDate;
    private TimeZone timezone;
    private Calendar calendar;

    protected final Map<Step, StepComponent> stepComponents = new HashMap<Step, StepComponent>();
    protected final Map<SubStep, SubStepComponent> subStepMap = new HashMap<SubStep, SubStepComponent>();

    protected Map<String, String> timezoneJsonCache = new HashMap<String, String>();

    protected static final String TZ_PATTERN = "^[A-Za-z]+ = (.*\"id\": \"([A-Za-z_/]+)\".*)$";
    protected static Set<String> timezoneIdCache;

    private GanttServerRpc rpc = new GanttServerRpc() {

        @Override
        public void stepClicked(String stepUid, MouseEventDetails details) {
            fireClickEvent(stepUid, details);
        }

        @Override
        public void onMove(String stepUid, String newStepUid, long startDate, long endDate, MouseEventDetails details) {
            fireMoveEvent(stepUid, newStepUid, startDate, endDate, details);
        }

        @Override
        public void onResize(String stepUid, long startDate, long endDate, MouseEventDetails details) {
            fireResizeEvent(stepUid, startDate, endDate, details);
        }

        @Override
        public void onPredecessorChanged(String newPredecessorStepUid, String forTargetStepUid,
                String clearPredecessorForStepUid) {
            if (newPredecessorStepUid == forTargetStepUid) {
                return;
            }
            firePredecessorChangeEvent(newPredecessorStepUid, forTargetStepUid, clearPredecessorForStepUid);
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

    @Override
    protected GanttState getState(boolean markAsDirty) {
        return (GanttState) super.getState(markAsDirty);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        getState(true).readOnly = readOnly;
    }

    @Override
    public boolean isReadOnly() {
        return getState(false).readOnly;
    }

    /**
     * Set start date of the Gantt chart's timeline. When resolution is
     * {@link Resolution#Day} or {@link Resolution#Week}, time will be adjusted
     * to a minimum possible for the given date (1/1/2010 12:12:12 &rarr;
     * 1/1/20120 00:00:00).
     * <p>
     * For {@link Resolution#Hour}, given date is adjusted like this: 1/1/2010
     * 12:12:12 &rarr; 1/1/2010 12:00:00.
     *
     * @param date
     */
    public void setStartDate(Date date) {
        if (date == null) {
            throw new UnsupportedOperationException("Setting a null start date for the Gantt is not allowed.");
        }
        setInternalStartDate(date);
        updateTimelineStartTimeDetails();
    }

    public void setStartDate(LocalDate date) {
        setStartDate((date != null) ? Date.from(date.atStartOfDay(getTimeZone().toZoneId()).toInstant()) : null);
    }

    public void setStartDate(Instant instant) {
        setStartDate((instant != null) ? Date.from(instant) : null);
    }

    public void setStartDate(LocalDateTime dateTime) {
        setStartDate((dateTime != null) ? Date.from(dateTime.atZone(getTimeZone().toZoneId()).toInstant()) : null);
    }

    public void setStartDate(ZonedDateTime dateTime) {
        setStartDate((dateTime != null) ? Date.from(dateTime.toInstant()) : null);
    }

    /**
     * Set end date of the Gantt chart's timeline. When resolution is
     * {@link Resolution#Day} or {@link Resolution#Week}, time will be adjusted
     * to a maximum possible for the given date (1/1/2010 12:12:12 &rarr;
     * 1/1/20120 23:59:59).
     *
     * @param date
     */
    public void setEndDate(Date date) {
        if (date == null) {
            throw new UnsupportedOperationException("Setting a null end date for the Gantt is not allowed.");
        }
        setInternalEndDate(date);
        updateTimelineStartTimeDetails();
    }

    public void setEndDate(LocalDate date) {
        setEndDate((date != null) ? Date.from(date.atStartOfDay(getTimeZone().toZoneId()).toInstant()) : null);
    }

    public void setEndDate(Instant instant) {
        setEndDate((instant != null) ? Date.from(instant) : null);
    }

    public void setEndDate(LocalDateTime dateTime) {
        setEndDate((dateTime != null) ? Date.from(dateTime.atZone(getTimeZone().toZoneId()).toInstant()) : null);
    }

    public void setEndDate(ZonedDateTime dateTime) {
        setEndDate((dateTime != null) ? Date.from(dateTime.toInstant()) : null);
    }

    /**
     * Get start date of the Gantt chart's timeline.
     *
     * @return Start Date
     */
    public Date getStartDate() {
        return startDate;
    }

    public LocalDateTime getStartLocalDateTime() {
        return (startDate != null) ? LocalDateTime.ofInstant(startDate.toInstant(), getTimeZone().toZoneId()) : null;
    }

    public ZonedDateTime getStartZonedDateTime() {
        return (startDate != null) ? ZonedDateTime.ofInstant(startDate.toInstant(), getTimeZone().toZoneId()) : null;
    }

    /**
     * Get end date of the Gantt chart's timeline.
     *
     * @return End Date
     */
    public Date getEndDate() {
        return endDate;
    }

    public LocalDateTime getEndLocalDateTime() {
        return (endDate != null) ? LocalDateTime.ofInstant(endDate.toInstant(), getTimeZone().toZoneId()) : null;
    }

    public ZonedDateTime getEndZonedDateTime() {
        return (endDate != null) ? ZonedDateTime.ofInstant(endDate.toInstant(), getTimeZone().toZoneId()) : null;
    }

    /**
     * Set new timeline resolution. Allowed resolutions are
     * {@link Resolution#Hour}, {@link Resolution#Day} and
     * {@link Resolution#Week}.
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
        if (!stepComponents.containsKey(step)) {
            StepComponent sc = createStepComponent(step);
            stepComponents.put(step, sc);
            getState().steps.add(sc);
        }
    }

    /**
     * Add new {@link Step} at a specific index and shifts the current object at
     * that index and any subsequent objects forward.
     *
     * @param step
     *            New Step object
     *
     * @throws IndexOutOfBoundsException
     *             if the index is out of range (index < 0 || index >=
     *             getState().steps.size())
     */
    public void addStep(int index, Step step) {
        if (stepComponents.containsKey(step)) {
            moveStep(index, step);
        } else {
            StepComponent sc = createStepComponent(step);
            stepComponents.put(step, sc);
            getState().steps.add(index, sc);
        }
    }

    /**
     * Move existing step at a specific index and shifts the current object at
     * that index and any subsequent objects forward.
     *
     * @throws IndexOutOfBoundsException
     *             if the index is out of range (index < 0 || index >=
     *             getState().steps.size())
     */
    public void moveStep(int toIndex, Step step) {
        if (!stepComponents.containsKey(step)) {
            return;
        }
        StepComponent targetComponent = stepComponents.get(getStep(toIndex));
        StepComponent moveComponent = stepComponents.get(step);
        if (targetComponent == moveComponent) {
            return;
        }
        getState().steps.remove(moveComponent);
        moveComponent.setParent(null);
        getState().steps.add(indexOf(targetComponent), moveComponent);
        moveComponent.setParent(this);
    }

    /**
     * Moves sub-step to other step. Does nothing, if either is null, or
     * sub-step's owner is null or owner is same as target step.
     */
    public void moveSubStep(SubStep subStep, Step toStep) {
        if (subStep == null || toStep == null || subStep.getOwner() == null || subStep.getOwner().equals(toStep)) {
            return;
        }
        subStep.getOwner().removeSubStep(subStep);
        toStep.addSubStep(subStep);
    }

    /**
     * Remove {@link Step}.
     *
     * @param step
     *            Target Step
     * @return true when target step exists and was removed successfully.
     */
    public boolean removeStep(Step step) {
        StepComponent sc = stepComponents.remove(step);
        if (sc != null) {
            for (SubStep subStep : new HashSet<SubStep>(step.getSubSteps())) {
                sc.onRemoveSubStep(subStep);
            }
            sc.setParent(null);
            return getState().steps.remove(sc);
        }
        return false;
    }

    public int getStepIndex(Step step) {
        StepComponent sc = stepComponents.get(step);
        return indexOf(sc);
    }

    private int indexOf(StepComponent stepComponent) {
        return getState(false).steps.indexOf(stepComponent);
    }

    /**
     * Return a step at a specific index in list of steps. Returns null if index
     * is out of bounds.
     *
     * @param index
     *            Index of Step.
     * @return Step
     */
    public Step getStep(int index) {
        if (index >= 0 && index < getState().steps.size()) {
            return ((StepComponent) getState().steps.get(index)).getState().step;
        }
        return null;
    }

    /**
     * Get Step by UID.
     *
     * @param uid
     *            Unique Identifier of Step
     * @return Step with given UID or null if step doesn't exist.
     */
    public AbstractStep getStep(String uid) {
        if (uid == null) {
            return null;
        }
        AbstractStep key = new Step();
        key.setUid(uid);

        StepComponent sc = stepComponents.get(key);
        if (sc != null) {
            return sc.getState().step;
        }
        key = new SubStep();
        key.setUid(uid);
        SubStepComponent sub = subStepMap.get(key);
        if (sub != null) {
            return sub.getState().step;
        }
        return null;
    }

    /**
     * Returns a unmodifiable List of steps.
     *
     * @return List of steps
     */
    public List<Step> getSteps() {
        List<Step> steps = new ArrayList<Step>();
        for (Connector sc : getState(false).steps) {
            steps.add(((StepComponent) sc).getState(false).step);
        }
        return Collections.unmodifiableList(steps);
    }

    /**
     * Removes all the steps in this Gantt chart.
     */
    public void removeSteps() {
        Set<Step> allSteps = new HashSet<Step>(stepComponents.keySet());
        for (Step step : allSteps) {
            removeStep(step);
        }
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
     * Return true if user can move the step between rows. Default is false.
     *
     * @return true if movable
     */
    public boolean isMovableStepsBetweenRows() {
        return getState().movableStepsBetweenRows;
    }

    /**
     * Enable or disable step moving between rows -feature. Disabled by default.
     *
     * @param movable
     *            True enables step moving vertically.
     */
    public void setMovableStepsBetweenRows(boolean movable) {
        getState().movableStepsBetweenRows = movable;
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
     * @param weekFormat
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
     * @param dayFormat
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

    /**
     * Return true when Default Context Menu will show up on "Right click". It's
     * disabled by default.
     */
    public boolean isDefaultContextMenuEnabled() {
        return getState().defaultContextMenuEnabled;
    }

    public void setDefaultContextMenuEnabled(boolean enabled) {
        getState().defaultContextMenuEnabled = enabled;
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
            int startdate = 0;
            int startmonth = 0;
            int startyear = 0;
            int starthour = 0;
            int enddate = 0;
            int endmonth = 0;
            int endyear = 0;
            int endhour = 0;
            // keep previous date/month/year
            if (getStartDate() != null) {
                getCalendar().setTime(getStartDate());
                startdate = getCalendar().get(Calendar.DATE);
                startmonth = getCalendar().get(Calendar.MONTH);
                startyear = getCalendar().get(Calendar.YEAR);
                starthour = getCalendar().get(Calendar.HOUR_OF_DAY);
            }
            if (getEndDate() != null) {
                getCalendar().setTime(getEndDate());
                enddate = getCalendar().get(Calendar.DATE);
                endmonth = getCalendar().get(Calendar.MONTH);
                endyear = getCalendar().get(Calendar.YEAR);
                endhour = getCalendar().get(Calendar.HOUR_OF_DAY);
            }

            timezone = zone; // internal timezone may be null
            if (zone == null) {
                zone = TimeZone.getDefault();
            }

            // refresh timeline range. Depending on the resolution, it might
            // need some adjusting.

            getCalendar().setTimeZone(zone);
            if (getStartDate() != null) {
                getCalendar().setTime(getStartDate());
                getCalendar().set(Calendar.DATE, startdate);
                getCalendar().set(Calendar.MONTH, startmonth);
                getCalendar().set(Calendar.YEAR, startyear);
                if (getResolution() == Resolution.Hour) {
                    getCalendar().set(Calendar.HOUR_OF_DAY, starthour);
                }
                setInternalStartDate(getCalendar().getTime());
            }

            if (getEndDate() != null) {
                getCalendar().setTime(getEndDate());
                getCalendar().set(Calendar.DATE, enddate);
                getCalendar().set(Calendar.MONTH, endmonth);
                getCalendar().set(Calendar.YEAR, endyear);
                if (getResolution() == Resolution.Hour) {
                    getCalendar().set(Calendar.HOUR_OF_DAY, endhour);
                }
                setInternalEndDate(getCalendar().getTime());
            }

            updateTimelineStartTimeDetails();
            markAsDirty();
        }
    }

    /**
     * Set target Grid component that will scroll vertically with the Gantt
     * component and vice versa.
     * <p>
     * Grid height is maintained by Gantt after this call. Grid header height is
     * changed to match the Gantt header. Grid rows have to have exactly same
     * heights as the Gantt steps have.
     * <p>
     * Component resize events of the Grid and Gantt widgets are handled by this
     * component.
     * <p>
     * Grid's internal column reordering and sorting are not listened by Gantt
     * and can be handled explicitly via Grid's server side event listeners.
     *
     * @param table
     *            Target Grid component.
     */
    public <T> void setVerticalScrollDelegateTarget(Grid<T> grid) {
        if (grid != null) {
            grid.setHeight(getHeight(), getHeightUnits());
        }
        if (getState().verticalScrollDelegateTarget != null && getState().verticalScrollDelegateTarget != grid) {
            // target has changed. Remove value change listener from the old
            // target.
            cleanScrollDelegateTargetListeners();
        }
        getState().verticalScrollDelegateTarget = grid;
        if (grid != null) {
            addScrollDelegateTargetListeners(grid);
        }
    }

    protected StepComponent createStepComponent(Step step) {
        return new StepComponent(this, step);
    }

    protected void addScrollDelegateTargetListeners(Grid grid) {

    }

    protected void cleanScrollDelegateTargetListeners() {
    }

    @Override
    public void setHeight(float height, Unit unit) {
        super.setHeight(height, unit);
        if (getState().verticalScrollDelegateTarget != null) {
            ((Component) getState().verticalScrollDelegateTarget).setHeight(getHeight(), getHeightUnits());
        }
    }

    /**
     * Bind application specific data object into step. The component does not
     * use or modify this.
     * <p>
     * Step must exist. Otherwise data is not set.
     */
    public void setData(AbstractStep step, Object data) {
        AbstractStepComponent c = getStepComponent(step);
        if (c != null) {
            c.setData(data);
        }
    }

    /**
     * Get application specific data object for specific step. The component
     * does not use or modify this.
     */
    public Object getData(AbstractStep step) {
        AbstractStepComponent c = getStepComponent(step);
        return (c != null) ? c.getData() : null;
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
            cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
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
        getState().timeZoneId = getTimeZone().getID();
        getState().timeZoneJson = getTimeZoneJson(getTimeZone().getID());
    }

    private void closeStream(String failMsg, InputStream is) {
        try {
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(failMsg, e);
        }
    }

    private void closeReader(String failMsg, Reader r) {
        try {
            r.close();
        } catch (IOException e) {
            throw new RuntimeException(failMsg, e);
        }
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

    AbstractStepComponent getStepComponent(AbstractStep step) {
        if (stepComponents.containsKey(step)) {
            return stepComponents.get(step);
        } else if (subStepMap.containsKey(step)) {
            return subStepMap.get(step);
        }
        return null;
    }

    private BufferedReader createTimeZonePropertiesReader(InputStream inputStream) {
        InputStreamReader isr = new InputStreamReader(inputStream);
        return new BufferedReader(isr);
    }

    private String readTimeZoneJson(String id, BufferedReader reader) throws IOException {
        for (String line; (line = reader.readLine()) != null;) {
            Pattern pattern = Pattern.compile(TZ_PATTERN);
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                if (id.equals(matcher.group(2))) {
                    String json = matcher.group(1);
                    timezoneJsonCache.put(id, json);
                    return json;
                }
            }
        }
        return null;
    }

    private static Set<String> readSupportedTimezones(BufferedReader reader) throws IOException {
        HashSet<String> ids = new HashSet<>();
        for (String line; (line = reader.readLine()) != null;) {
            Pattern pattern = Pattern.compile(TZ_PATTERN);
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                ids.add(matcher.group(2));
            }
        }
        return ids;
    }

    protected InputStream createTimeZonePropertiesInputStream(String propertiesFileName) {
        // read time zone json from TimeZoneConstants.properties.
        return Gantt.class.getResourceAsStream(propertiesFileName);
    }

    protected String getTimeZoneJson(String id) {
        if (timezoneJsonCache.containsKey(id)) {
            return timezoneJsonCache.get(id);
        }
        String propertiesFileName = "TimeZoneConstants.properties";
        // read time zone json from TimeZoneConstants.properties.
        InputStream is = createTimeZonePropertiesInputStream(propertiesFileName);
        BufferedReader reader = createTimeZonePropertiesReader(is);
        try {
            String json = readTimeZoneJson(id, reader);
            if (json != null) {
                return json;
            }
            throw new IllegalArgumentException(String.format("Time zone %s not found in %s", id, propertiesFileName));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to read time zone from %s", propertiesFileName), e);
        } finally {
            closeReader("Failed to close time-zone resource stream reader.", reader);
            closeStream("Failed to close time-zone resource stream.", is);
        }
    }

    /**
     * Get all supported Time zone identifiers (like "Europe/Rome"). Reads them
     * by default from TimeZoneConstants.properties. First call will cache
     * result, and following calls will return cached set.
     */
    public static Set<String> getSupportedTimeZoneIDs() {
        if (timezoneIdCache != null) {
            return Collections.unmodifiableSet(timezoneIdCache);
        }
        timezoneIdCache = new HashSet<>();

        String propertiesFileName = "TimeZoneConstants.properties";
        // read time zones from TimeZoneConstants.properties.
        InputStream is = Gantt.class.getResourceAsStream(propertiesFileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        try {
            timezoneIdCache = readSupportedTimezones(reader);
            return timezoneIdCache;
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to read time zone from %s", propertiesFileName), e);
        } finally {
            try {
                reader.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close open resources.", e);
            }
        }
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

    protected void fireClickEvent(String stepUid, MouseEventDetails details) {
        AbstractStep step = getStep(stepUid);
        fireEvent(new ClickEvent(this, step, details));
    }

    protected void fireMoveEvent(String stepUid, String newStepUid, long startDate, long endDate,
            MouseEventDetails details) {
        AbstractStep step = getStep(stepUid);
        AbstractStep newStep = getStep(newStepUid);
        int previousStepIndex = (step instanceof Step) ? getStepIndex((Step) step)
                : getStepIndex(((SubStep) step).getOwner());
        long previousStartDate = step.getStartDate();
        long previousEndDate = step.getEndDate();
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        int newStepIndex;
        if (isMovableStepsBetweenRows() && newStep instanceof Step) {
            newStepIndex = getStepIndex((Step) newStep);
            if (step instanceof Step) {
                // move to new row
                moveStep(newStepIndex, (Step) step);
            } else {
                // move sub-step to new owner
                moveSubStep((SubStep) step, (Step) newStep);
            }
        } else {
            newStepIndex = previousStepIndex;
        }
        moveDatesByOwnerStep(step, previousStartDate, previousEndDate);
        adjustDatesByAbstractStep(step);
        fireEvent(new MoveEvent(this, step, step.getStartDate(), step.getEndDate(), newStepIndex, previousStartDate,
                previousEndDate, previousStepIndex, details));
    }

    protected void fireResizeEvent(String stepUid, long startDate, long endDate, MouseEventDetails details) {
        AbstractStep step = getStep(stepUid);
        long previousStartDate = step.getStartDate();
        long previousEndDate = step.getEndDate();
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        resizeDatesByOwnerStep(step, previousStartDate, previousEndDate);
        adjustDatesByAbstractStep(step);
        fireEvent(new ResizeEvent(this, step, step.getStartDate(), step.getEndDate(), previousStartDate,
                previousEndDate, details));
    }

    protected void firePredecessorChangeEvent(String newPredecessorStepUid, String forTargetStepUid,
            String clearPredecessorForStepUid) {
        Step newPredecessorStep = (Step) getStep(newPredecessorStepUid);
        Step forTargetStep = (Step) getStep(forTargetStepUid);
        Step clearPredecessorForStep = (Step) getStep(clearPredecessorForStepUid);
        if (forTargetStep != null) {
            forTargetStep.setPredecessor(newPredecessorStep);
            stepComponents.get(forTargetStep).getState(true);
            fireEvent(new PredecessorChangeEvent(this, forTargetStep));
        }
        if (clearPredecessorForStep != null) {
            clearPredecessorForStep.setPredecessor(null);
            stepComponents.get(clearPredecessorForStep).getState(true);
            fireEvent(new PredecessorChangeEvent(this, clearPredecessorForStep));
        }
    }

    protected void moveDatesByOwnerStep(AbstractStep step, long previousStartDate, long previousEndDate) {
        if (!(step instanceof Step)) {
            return;
        }
        Step s = (Step) step;

        long startDelta = step.getStartDate() - previousStartDate;
        long endDelta = step.getEndDate() - previousEndDate;
        if (!s.getSubSteps().isEmpty()) {
            for (SubStep sub : s.getSubSteps()) {
                if (startDelta != 0) {
                    sub.setStartDate(sub.getStartDate() + startDelta);
                }
                if (endDelta != 0) {
                    sub.setEndDate(sub.getEndDate() + endDelta);
                }
                subStepMap.get(sub).getState(true);
            }
        }
    }

    protected void resizeDatesByOwnerStep(AbstractStep step, long previousStartDate, long previousEndDate) {
        // may be overridden to handle resizing parent step
    }

    protected void adjustDatesByAbstractStep(AbstractStep step) {
        Step owner = null;
        if (step instanceof SubStep) {
            owner = ajustDatesBySubStep(step);

        } else if (step instanceof Step) {
            owner = (Step) step;
        }

        if (owner != null) {
            // adjust owner start/end dates to fit with all sub-steps.
            if (!owner.getSubSteps().isEmpty()) {
                if (owner.isStartDateUndefined() || owner.getMinStartDateBySubSteps() != owner.getStartDate()) {
                    owner.setStartDate(owner.getMinStartDateBySubSteps());
                }
                if (owner.isEndDateUndefined() || owner.getMaxEndDateBySubSteps() != owner.getEndDate()) {
                    owner.setEndDate(owner.getMaxEndDateBySubSteps());
                }
            }
            if (stepComponents.containsKey(owner)) {
                stepComponents.get(owner).getState(true);
            }
        }
    }

    protected Step ajustDatesBySubStep(AbstractStep step) {
        // adjust parent step start/end date:
        SubStep subStep = ((SubStep) step);
        Step owner = subStep.getOwner();
        // Cut owner step's start/end date to fit sub-steps in.
        if (owner.isStartDateUndefined() || subStep.getStartDate() < owner.getStartDate()) {
            owner.setStartDate(subStep.getStartDate());
        }
        if (owner.isEndDateUndefined() || subStep.getEndDate() > owner.getEndDate()) {
            owner.setEndDate(subStep.getEndDate());
        }
        return owner;
    }

    /**
     * Return Date which will be used to detect timezone offset and daylight
     * saving offset. Returns new Date() by default.
     *
     * @deprecated All time-zone specific information is in
     *             {@link GanttState#timeZoneJson}.
     */
    @Deprecated
    protected Date getTimezoneOffsetDate() {
        return new Date();
    }

    /** Mark step/substeps dirty. */
    public void markStepDirty(AbstractStep step) {
        AbstractStepComponent component = getStepComponent(step);
        if (component != null) {
            component.markAsDirtyRecursive();
        }
    }

    /**
     * Add {@link ClickListener} to listen clicks on steps.
     */
    public void addClickListener(ClickListener listener) {
        addListener(ClickEvent.class, listener, ClickListener.GANTT_CLICK_METHOD);
    }

    /**
     * Add {@link MoveListener} to listen step's move events.
     */
    public void addMoveListener(MoveListener listener) {
        addListener(MoveEvent.class, listener, MoveListener.GANTT_MOVE_METHOD);
    }

    /**
     * Add {@link ResizeListener} to listen step's resize events.
     */
    public void addResizeListener(ResizeListener listener) {
        addListener(ResizeEvent.class, listener, ResizeListener.GANTT_RESIZE_METHOD);
    }

    /**
     * Add {@link PredecessorChangeListener} to listen step's predecessor step
     * change events.
     */
    public void addPredecessorChangeListener(PredecessorChangeListener listener) {
        addListener(PredecessorChangeEvent.class, listener, PredecessorChangeListener.GANTT_PERDECESSORCHANGE_METHOD);
    }

    public void removeClickListener(ClickListener listener) {
        removeListener(ClickEvent.class, listener, ClickListener.GANTT_CLICK_METHOD);
    }

    public void removeMoveListener(MoveListener listener) {
        removeListener(MoveEvent.class, listener, MoveListener.GANTT_MOVE_METHOD);
    }

    public void removeResizeListener(ResizeListener listener) {
        removeListener(ResizeEvent.class, listener, ResizeListener.GANTT_RESIZE_METHOD);
    }

    public void removePredecessorChangeListener(PredecessorChangeListener listener) {
        removeListener(PredecessorChangeEvent.class, listener,
                PredecessorChangeListener.GANTT_PERDECESSORCHANGE_METHOD);
    }

    public class ClickEvent extends Component.Event {

        private AbstractStep step;
        private MouseEventDetails details;

        public ClickEvent(Gantt source, AbstractStep step, MouseEventDetails details) {
            super(source);
            this.step = step;
            this.details = details;
        }

        /**
         * Get target Step.
         *
         * @return
         */
        public AbstractStep getStep() {
            return step;
        }

        public void setStep(AbstractStep step) {
            this.step = step;
        }

        public MouseEventDetails getDetails() {
            return details;
        }

    }

    public class MoveEvent extends Component.Event {

        private AbstractStep step;
        private long startDate;
        private long endDate;
        private int stepIndex;
        private long previousStartDate;
        private long previousEndDate;
        private int previousStepIndex;
        private MouseEventDetails details;

        public MoveEvent(Gantt source, AbstractStep step, long startDate, long endDate, int stepIndex,
                long previousStartDate, long previousEndDate, int previousStepIndex, MouseEventDetails details) {
            super(source);
            this.step = step;
            this.startDate = startDate;
            this.endDate = endDate;
            this.stepIndex = stepIndex;
            this.previousStartDate = previousStartDate;
            this.previousEndDate = previousEndDate;
            this.previousStepIndex = previousStepIndex;
            this.details = details;
        }

        /**
         * Get target Step.
         *
         * @return
         */
        public AbstractStep getStep() {
            return step;
        }

        public void setStep(AbstractStep step) {
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

        public long getPreviousStartDate() {
            return previousStartDate;
        }

        public long getPreviousEndDate() {
            return previousEndDate;
        }

        public int getStepIndex() {
            return stepIndex;
        }

        public int getPreviousStepIndex() {
            return previousStepIndex;
        }

        public MouseEventDetails getDetails() {
            return details;
        }
    }

    public class ResizeEvent extends Component.Event {

        private AbstractStep step;
        private long startDate;
        private long endDate;
        private long previousStartDate;
        private long previousEndDate;
        private MouseEventDetails details;

        public ResizeEvent(Gantt source, AbstractStep step, long startDate, long endDate, long previousStartDate,
                long previousEndDate, MouseEventDetails details) {
            super(source);
            this.step = step;
            this.startDate = startDate;
            this.endDate = endDate;
            this.previousStartDate = previousStartDate;
            this.previousEndDate = previousEndDate;
            this.details = details;
        }

        /**
         * Get target Step.
         *
         * @return
         */
        public AbstractStep getStep() {
            return step;
        }

        public void setStep(AbstractStep step) {
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

        public long getPreviousStartDate() {
            return previousStartDate;
        }

        public long getPreviousEndDate() {
            return previousEndDate;
        }

        public MouseEventDetails getDetails() {
            return details;
        }
    }

    public class PredecessorChangeEvent extends Component.Event {

        private Step step;

        public PredecessorChangeEvent(Gantt source, Step targetStep) {
            super(source);
            step = targetStep;
        }

        /**
         * Get target Step.
         */
        public Step getStep() {
            return step;
        }

        public void setStep(Step targetStep) {
            step = targetStep;
        }

        /** Get new predecessor step or null if none. */
        public Step getPredecessor() {
            return (step != null) ? step.getPredecessor() : null;
        }
    }

    public interface ClickListener extends Serializable {

        public static final Method GANTT_CLICK_METHOD = ReflectTools.findMethod(ClickListener.class, "onGanttClick",
                ClickEvent.class);

        public void onGanttClick(ClickEvent event);
    }

    public interface MoveListener extends Serializable {

        public static final Method GANTT_MOVE_METHOD = ReflectTools.findMethod(MoveListener.class, "onGanttMove",
                MoveEvent.class);

        public void onGanttMove(MoveEvent event);
    }

    public interface ResizeListener extends Serializable {

        public static final Method GANTT_RESIZE_METHOD = ReflectTools.findMethod(ResizeListener.class, "onGanttResize",
                ResizeEvent.class);

        public void onGanttResize(ResizeEvent event);
    }

    public interface PredecessorChangeListener extends Serializable {
        public static final Method GANTT_PERDECESSORCHANGE_METHOD = ReflectTools
                .findMethod(PredecessorChangeListener.class, "onPredecessorChange", PredecessorChangeEvent.class);

        public void onPredecessorChange(PredecessorChangeEvent event);
    }

    @Override
    public Iterator<Component> iterator() {
        List<Component> l = new ArrayList<Component>();
        for (Connector c : getState(false).steps) {
            l.add((Component) c);
        }
        return l.iterator();
    }

}
