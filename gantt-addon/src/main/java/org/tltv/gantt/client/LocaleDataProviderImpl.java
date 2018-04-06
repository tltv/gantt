package org.tltv.gantt.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.vaadin.client.LocaleNotLoadedException;
import com.vaadin.client.LocaleService;

public class LocaleDataProviderImpl implements LocaleDataProvider {

    public String locale;
    public String timeZoneId;
    public TimeZone timeZone;
    public GanttDateTimeService dateTimeService;

    @Override
    public String[] getWeekdayNames() {
        try {
            // TODO replace
            return LocaleService.getDayNames(locale);
        } catch (LocaleNotLoadedException e) {
            GWT.log(e.getMessage(), e);
        }
        // return default
        return new String[] { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
    }

    @Override
    public String[] getMonthNames() {
        try {
            // TODO replace
            return LocaleService.getMonthNames(locale);
        } catch (LocaleNotLoadedException e) {
            GWT.log(e.getMessage(), e);
        }
        // return default
        return new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
    }

    @Override
    public int getFirstDayOfWeek() {
        try {
            // TODO replace
            // Gantt uses 1-based index, just as the server-side Java
            // Locale does. Vaadin locale state has 0-based value.
            return LocaleService.getFirstDayOfWeek(locale) + 1;
        } catch (LocaleNotLoadedException e) {
            GWT.log(e.getMessage(), e);
        }
        // return default
        return 1; // sunday
    }

    @Override
    public String formatDate(Date zonedDate, String formatStr) {
        if (dateTimeService == null) {
            dateTimeService = new GanttDateTimeService(getLocale());
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
            // TODO replace
            return LocaleService.isTwelveHourClock(locale);
        } catch (LocaleNotLoadedException e) {
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
