package org.tltv.gantt.client.shared;

import java.util.Date;

public class GanttUtil {

    /**
     * Calculate week number for the given date.
     * 
     * @param d
     *            Target date
     * @param timezoneOffset
     *            Time zone offset in milliseconds
     * @param firstDayOfWeek
     *            First day of week. Integer between 1-7. 1 is Sunday.
     * @return
     */
    public static int getWeekNumber(Date d, long timezoneOffset,
            int firstDayOfWeek) {
        /*
         * Thanks to stackoverflow.com for a easy function to calculate week
         * number. See
         * http://stackoverflow.com/questions/6117814/get-week-of-year
         * -in-javascript-like-in-php
         */
        d = new Date(d.getYear(), d.getMonth(), d.getDate());
        int daysToTursday;
        if (firstDayOfWeek == 1) {
            daysToTursday = 4 - d.getDay();
        } else {
            daysToTursday = 4 - ((d.getDay() == 0) ? 7 : d.getDay());
        }
        d.setDate(d.getDate() + daysToTursday);
        Date yearStart = new Date(d.getYear(), 0, 1);
        double weekNo = Math
                .ceil((((d.getTime() - yearStart.getTime()) / 86400000.0) + 1.0) / 7.0);
        return (int) weekNo;
    }
}
