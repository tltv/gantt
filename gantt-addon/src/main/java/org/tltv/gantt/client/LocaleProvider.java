package org.tltv.gantt.client;

public interface LocaleProvider {

    /** Array of day names. Starting from sunday with 0-index. */
    String[] getLocaleDayNames();

    /** Array of short day names. Starting from sunday with 0-index. */
    String[] getLocaleShortDayNames();

    /** Array of month names. Starting from January with 0-index. */
    String[] getLocaleMonthNames();

    /** Array of short month names. Starting from January with 0-index. */
    String[] getLocaleShortMonthNames();

    /** Index of first day of week found in {@link #getLocaleDayNames()} */
    int getLocaleFirstDayOfWeek();

    /** Return true when using 12h clock. False when 24h clock. */
    boolean isLocaleTwelveHourClock();
}
