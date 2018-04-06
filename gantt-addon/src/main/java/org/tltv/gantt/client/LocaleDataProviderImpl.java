package org.tltv.gantt.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat;

public class LocaleDataProviderImpl implements LocaleDataProvider {

    public String locale;
    public String timeZoneId;
    public TimeZone timeZone;
    public GanttDateTimeService dateTimeService;
    public LocaleProvider localeProvider;

    @Override
    public String[] getWeekdayNames() {
        try {
            return localeProvider.getLocaleDayNames();
        } catch (Exception e) {
            GWT.log(e.getMessage(), e);
        }
        // return default
        return new String[] { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
    }

    @Override
    public String[] getMonthNames() {
        try {
            return localeProvider.getLocaleMonthNames();
        } catch (Exception e) {
            GWT.log(e.getMessage(), e);
        }
        // return default
        return new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
    }

    @Override
    public int getFirstDayOfWeek() {
        try {
            // Gantt uses 1-based index, just as the server-side Java
            // Locale does. LocaleProvider (and Vaadin Framework) locale state
            // has 0-based value.
            return localeProvider.getLocaleFirstDayOfWeek() + 1;
        } catch (Exception e) {
            GWT.log(e.getMessage(), e);
        }
        // return default
        return 1; // sunday
    }

    @Override
    public String formatDate(Date zonedDate, String formatStr) {
        if (dateTimeService == null) {
            dateTimeService = new GanttDateTimeService(getLocale(), localeProvider);
        }
        return dateTimeService.formatDate(zonedDate, formatStr, getTimeZone());
    }

    @Override
    public String formatDate(Date zonedDate, DateTimeFormat formatter) {
        return formatter.format(zonedDate, getTimeZone());
    }

    @Override
    public boolean isTwelveHourClock() {
        try {
            return localeProvider.isLocaleTwelveHourClock();
        } catch (Exception e) {
            GWT.log(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public long getTimeZoneOffset(Date zonedDate) {
        int offset = -getTimeZone().getOffset(zonedDate) * 60000;
        return offset;
    }

    @Override
    public TimeZone getTimeZone() {
        return timeZone;
    }

    @Override
    public long getDaylightAdjustment(Date zonedDate) {
        return getTimeZone().getDaylightAdjustment(zonedDate) * 60000;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public GanttDateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(GanttDateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
}
