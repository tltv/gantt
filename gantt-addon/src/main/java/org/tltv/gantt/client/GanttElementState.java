package org.tltv.gantt.client;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportConstructor;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;
import org.tltv.gantt.client.shared.Resolution;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

@Export
@ExportPackage(value = "gantt")
public class GanttElementState implements Exportable {

    public String width = "100%";

    public String height = "100%";

    public boolean readOnly = false;

    public String locale;

    public String timeZoneJson;

    public String timeZoneId;

    public Resolution resolution = Resolution.Day;

    public int firstDayOfRange;

    public int firstHourOfRange;

    /** GMT start date */
    public long startDate = 0;

    /** GMT end date */
    public long endDate = 0;

    public boolean resizableSteps = true;

    public boolean movableSteps = true;

    public boolean movableStepsBetweenRows = false;

    public boolean monthRowVisible = true;

    public boolean yearRowVisible = true;

    public boolean backgroundGridEnabled = true;

    public String weekFormat;

    public String dayFormat;

    public String monthFormat;

    public String yearFormat;

    public boolean defaultContextMenuEnabled = false;

    private static GanttElementState instance;

    @ExportConstructor
    public static GanttElementState constructor() {
        if (instance == null) {
            instance = new GanttElementState();
        }
        return instance;
    }

    private GanttElementState() {
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimeZoneJson() {
        return timeZoneJson;
    }

    public void setTimeZoneJson(String timeZoneJson) {
        this.timeZoneJson = timeZoneJson;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public int getFirstDayOfRange() {
        return firstDayOfRange;
    }

    public void setFirstDayOfRange(int firstDayOfRange) {
        this.firstDayOfRange = firstDayOfRange;
    }

    public int getFirstHourOfRange() {
        return firstHourOfRange;
    }

    public void setFirstHourOfRange(int firstHourOfRange) {
        this.firstHourOfRange = firstHourOfRange;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public boolean isResizableSteps() {
        return resizableSteps;
    }

    public void setResizableSteps(boolean resizableSteps) {
        this.resizableSteps = resizableSteps;
    }

    public boolean isMovableSteps() {
        return movableSteps;
    }

    public void setMovableSteps(boolean movableSteps) {
        this.movableSteps = movableSteps;
    }

    public boolean isMovableStepsBetweenRows() {
        return movableStepsBetweenRows;
    }

    public void setMovableStepsBetweenRows(boolean movableStepsBetweenRows) {
        this.movableStepsBetweenRows = movableStepsBetweenRows;
    }

    public boolean isMonthRowVisible() {
        return monthRowVisible;
    }

    public void setMonthRowVisible(boolean monthRowVisible) {
        this.monthRowVisible = monthRowVisible;
    }

    public boolean isYearRowVisible() {
        return yearRowVisible;
    }

    public void setYearRowVisible(boolean yearRowVisible) {
        this.yearRowVisible = yearRowVisible;
    }

    public boolean isBackgroundGridEnabled() {
        return backgroundGridEnabled;
    }

    public void setBackgroundGridEnabled(boolean backgroundGridEnabled) {
        this.backgroundGridEnabled = backgroundGridEnabled;
    }

    public String getWeekFormat() {
        return weekFormat;
    }

    public void setWeekFormat(String weekFormat) {
        this.weekFormat = weekFormat;
    }

    public String getDayFormat() {
        return dayFormat;
    }

    public void setDayFormat(String dayFormat) {
        this.dayFormat = dayFormat;
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

    public boolean isDefaultContextMenuEnabled() {
        return defaultContextMenuEnabled;
    }

    public void setDefaultContextMenuEnabled(boolean defaultContextMenuEnabled) {
        this.defaultContextMenuEnabled = defaultContextMenuEnabled;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public static GanttElementState toState(JavaScriptObject o) {
        JSONObject json = new JSONObject(o);
        GanttElementState s = new GanttElementState();
        // TODO ugly.. how about automating by annotating fields with name+type?
        if (json.containsKey("width")) {
            s.setWidth(json.get("width").isString().stringValue());
        }
        if (json.containsKey("height")) {
            s.setHeight(json.get("height").isString().stringValue());
        }
        if (json.containsKey("startDate")) {
            s.setStartDate(Double.valueOf(json.get("startDate").isNumber().doubleValue()).longValue());
        }
        if (json.containsKey("endDate")) {
            s.setEndDate(Double.valueOf(json.get("endDate").isNumber().doubleValue()).longValue());
        }
        if (json.containsKey("locale")) {
            s.setLocale(json.get("locale").isString().stringValue());
        }
        if (json.containsKey("timeZoneJson")) {
            s.setTimeZoneJson(json.get("timeZoneJson").isString().stringValue());
        }
        if (json.containsKey("timeZoneId")) {
            s.setTimeZoneId(json.get("timeZoneId").isString().stringValue());
        }
        if (json.containsKey("resolution")) {
            s.setResolution(Resolution.valueOf(json.get("resolution").isString().stringValue()));
        }
        if (json.containsKey("resizableSteps")) {
            s.setResizableSteps(json.get("resizableSteps").isBoolean().booleanValue());
        }
        if (json.containsKey("movableSteps")) {
            s.setMovableSteps(json.get("movableSteps").isBoolean().booleanValue());
        }
        if (json.containsKey("movableStepsBetweenRows")) {
            s.setMovableStepsBetweenRows(json.get("movableStepsBetweenRows").isBoolean().booleanValue());
        }
        if (json.containsKey("monthRowVisible")) {
            s.setMonthRowVisible(json.get("monthRowVisible").isBoolean().booleanValue());
        }
        if (json.containsKey("yearRowVisible")) {
            s.setYearRowVisible(json.get("yearRowVisible").isBoolean().booleanValue());
        }
        if (json.containsKey("backgroundGridEnabled")) {
            s.setBackgroundGridEnabled(json.get("backgroundGridEnabled").isBoolean().booleanValue());
        }
        if (json.containsKey("defaultContextMenuEnabled")) {
            s.setDefaultContextMenuEnabled(json.get("defaultContextMenuEnabled").isBoolean().booleanValue());
        }
        if (json.containsKey("weekFormat")) {
            s.setWeekFormat(json.get("weekFormat").isString().stringValue());
        }
        if (json.containsKey("dayFormat")) {
            s.setDayFormat(json.get("dayFormat").isString().stringValue());
        }
        if (json.containsKey("monthFormat")) {
            s.setMonthFormat(json.get("monthFormat").isString().stringValue());
        }
        if (json.containsKey("yearFormat")) {
            s.setYearFormat(json.get("yearFormat").isString().stringValue());
        }
        if (json.containsKey("readOnly")) {
            s.setReadOnly(json.get("readOnly").isBoolean().booleanValue());
        }
        if (json.containsKey("firstDayOfRange")) {
            s.setFirstDayOfRange(Double.valueOf(json.get("firstDayOfRange").isNumber().doubleValue()).intValue());
        }
        if (json.containsKey("firstHourOfRange")) {
            s.setFirstHourOfRange(Double.valueOf(json.get("firstHourOfRange").isNumber().doubleValue()).intValue());
        }
        return s;
    }
}
