package org.tltv.gantt.model;

import java.util.Arrays;
import java.util.List;

public class Settings {

    private Double startDate;
    private Double endDate;
    private String locale = "en_US";
    private String resolution = "Day";
    private boolean backgroundGridEnabled = true;
    private String timeZoneJson;
    private String timeZoneId;
    private int firstDayOfRange;
    private int firstHourOfRange;
    private List<String> localeDayNames = Arrays.asList("sunday", "monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday");
    private List<String> localeShortDayNames = Arrays.asList("sun", "mon", "tue", "wed", "thu", "fri", "sat");
    private List<String> localeMonthNames = Arrays.asList("January", "February", "March", "April", "May", "June",
            "July", "August",
            "September", "October", "November", "December");
    private List<String> localeShortMonthNames = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec");
    private int localeFirstDayOfWeek = 0;
    private boolean localeTwelveHourClock = false;

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

    public List<String> getLocaleDayNames() {
        return localeDayNames;
    }

    public void setLocaleDayNames(List<String> localeDayNames) {
        this.localeDayNames = localeDayNames;
    }

    public List<String> getLocaleShortDayNames() {
        return localeShortDayNames;
    }

    public void setLocaleShortDayNames(List<String> localeShortDayNames) {
        this.localeShortDayNames = localeShortDayNames;
    }

    public List<String> getLocaleMonthNames() {
        return localeMonthNames;
    }

    public void setLocaleMonthNames(List<String> localeMonthNames) {
        this.localeMonthNames = localeMonthNames;
    }

    public List<String> getLocaleShortMonthNames() {
        return localeShortMonthNames;
    }

    public void setLocaleShortMonthNames(List<String> localeShortMonthNames) {
        this.localeShortMonthNames = localeShortMonthNames;
    }

    public int getLocaleFirstDayOfWeek() {
        return localeFirstDayOfWeek;
    }

    public void setLocaleFirstDayOfWeek(int localeFirstDayOfWeek) {
        this.localeFirstDayOfWeek = localeFirstDayOfWeek;
    }

    public boolean isLocaleTwelveHourClock() {
        return localeTwelveHourClock;
    }

    public void setLocaleTwelveHourClock(boolean localeTwelveHourClock) {
        this.localeTwelveHourClock = localeTwelveHourClock;
    }
}
