package org.tltv.gantt.model;

public class State {

    private Double startDate;
    private Double endDate;
    private String locale = "en_US";
    private String resolution = "Day";
    private boolean backgroundGridEnabled = true;
    private String timeZoneJson;
    private String timeZoneId;
    public int firstDayOfRange;
    public int firstHourOfRange;

    public Double getStartDate() {
        return startDate;
    }

    /** Set inclusive start date. */
    public void setStartDate(Double startDate) {
        this.startDate = startDate;
    }

    public Double getEndDate() {
        return endDate;
    }

    /** Set inclusive end date. */
    public void setEndDate(Double endDate) {
        this.endDate = endDate;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isBackgroundGridEnabled() {
        return backgroundGridEnabled;
    }

    public void setBackgroundGridEnabled(boolean backgroundGridEnabled) {
        this.backgroundGridEnabled = backgroundGridEnabled;
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
}
