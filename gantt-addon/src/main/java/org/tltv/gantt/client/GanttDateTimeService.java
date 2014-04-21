package org.tltv.gantt.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.LocaleNotLoadedException;

public class GanttDateTimeService extends DateTimeService {

    private TimeZone gmt = TimeZone.createTimeZone(0);

    /**
     * Creates a new date time service with the application default locale.
     */
    public GanttDateTimeService() {
        super();
    }

    /**
     * Creates a new date time service with a given locale.
     * 
     * @param locale
     *            e.g. fi, en etc.
     * @throws LocaleNotLoadedException
     */
    public GanttDateTimeService(String locale) throws LocaleNotLoadedException {
        super(locale);
    }

    /**
     * <p>
     * Copy of super.formatDate. Only difference is that TimeZone is fixed to
     * GMT.
     * <p>
     * Check if format contains the month name. If it does we manually convert
     * it to the month name since DateTimeFormat.format always uses the current
     * locale and will replace the month name wrong if current locale is
     * different from the locale set for the DateField.
     * 
     * MMMM is converted into long month name, MMM is converted into short month
     * name. '' are added around the name to avoid that DateTimeFormat parses
     * the month name as a pattern.
     * 
     * @param date
     *            The date to convert
     * @param formatStr
     *            The format string that might contain MMM or MMMM
     * @param dateTimeService
     *            Reference to the Vaadin DateTimeService
     * @return
     */
    @Override
    public String formatDate(Date date, String formatStr) {
        /*
         * Format month and day names separately when locale for the
         * DateTimeService is not the same as the browser locale
         */
        formatStr = formatMonthNames(date, formatStr);
        formatStr = formatDayNames(date, formatStr);

        // Format uses the browser locale
        DateTimeFormat format = DateTimeFormat.getFormat(formatStr);

        String result = format.format(date, gmt);

        return result;
    }

    /* copy of super.formatDayNames */
    private String formatDayNames(Date date, String formatStr) {
        if (formatStr.contains("EEEE")) {
            String dayName = getDay(date.getDay());

            if (dayName != null) {
                /*
                 * Replace 4 or more E:s with the quoted day name. Also
                 * concatenate generated string with any other string prepending
                 * or following the EEEE pattern, i.e. 'EEEE'ta ' becomes 'DAYta
                 * ' and not 'DAY''ta ', 'ab'EEEE becomes 'abDAY', 'x'EEEE'y'
                 * becomes 'xDAYy'.
                 */
                formatStr = formatStr.replaceAll("'([E]{4,})'", dayName);
                formatStr = formatStr.replaceAll("([E]{4,})'", "'" + dayName);
                formatStr = formatStr.replaceAll("'([E]{4,})", dayName + "'");
                formatStr = formatStr
                        .replaceAll("[E]{4,}", "'" + dayName + "'");
            }
        }

        if (formatStr.contains("EEE")) {

            String dayName = getShortDay(date.getDay());

            if (dayName != null) {
                /*
                 * Replace 3 or more E:s with the quoted month name. Also
                 * concatenate generated string with any other string prepending
                 * or following the EEE pattern, i.e. 'EEE'ta ' becomes 'DAYta '
                 * and not 'DAY''ta ', 'ab'EEE becomes 'abDAY', 'x'EEE'y'
                 * becomes 'xDAYy'.
                 */
                formatStr = formatStr.replaceAll("'([E]{3,})'", dayName);
                formatStr = formatStr.replaceAll("([E]{3,})'", "'" + dayName);
                formatStr = formatStr.replaceAll("'([E]{3,})", dayName + "'");
                formatStr = formatStr
                        .replaceAll("[E]{3,}", "'" + dayName + "'");
            }
        }

        return formatStr;
    }

    /* copy of super.formatMonthNames */
    private String formatMonthNames(Date date, String formatStr) {
        if (formatStr.contains("MMMM")) {
            String monthName = getMonth(date.getMonth());

            if (monthName != null) {
                /*
                 * Replace 4 or more M:s with the quoted month name. Also
                 * concatenate generated string with any other string prepending
                 * or following the MMMM pattern, i.e. 'MMMM'ta ' becomes
                 * 'MONTHta ' and not 'MONTH''ta ', 'ab'MMMM becomes 'abMONTH',
                 * 'x'MMMM'y' becomes 'xMONTHy'.
                 */
                formatStr = formatStr.replaceAll("'([M]{4,})'", monthName);
                formatStr = formatStr.replaceAll("([M]{4,})'", "'" + monthName);
                formatStr = formatStr.replaceAll("'([M]{4,})", monthName + "'");
                formatStr = formatStr.replaceAll("[M]{4,}", "'" + monthName
                        + "'");
            }
        }

        if (formatStr.contains("MMM")) {

            String monthName = getShortMonth(date.getMonth());

            if (monthName != null) {
                /*
                 * Replace 3 or more M:s with the quoted month name. Also
                 * concatenate generated string with any other string prepending
                 * or following the MMM pattern, i.e. 'MMM'ta ' becomes 'MONTHta
                 * ' and not 'MONTH''ta ', 'ab'MMM becomes 'abMONTH', 'x'MMM'y'
                 * becomes 'xMONTHy'.
                 */
                formatStr = formatStr.replaceAll("'([M]{3,})'", monthName);
                formatStr = formatStr.replaceAll("([M]{3,})'", "'" + monthName);
                formatStr = formatStr.replaceAll("'([M]{3,})", monthName + "'");
                formatStr = formatStr.replaceAll("[M]{3,}", "'" + monthName
                        + "'");
            }
        }

        return formatStr;
    }

}
