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
package org.tltv.gantt.client;

import java.util.Date;
import java.util.Locale;

public interface LocaleDataProvider {

    /**
     * Returns month names in order starting from January.
     * 
     * @return Locale dependent month names
     */
    String[] getMonthNames();

    /**
     * Returns weekdays in order starting from Sunday.
     * 
     * @return Locale dependent weekday names
     */
    String[] getWeekdayNames();

    /**
     * Returns first day of week. Allowed values are 1-7. 1 is Sunday.
     * 
     * @return Integer between 1-7.
     */
    int getFirstDayOfWeek();

    /**
     * Format date to String.
     * 
     * @param date
     *            Date to format
     * @param format
     *            Pattern of the date format. Like MMM or MMMM.
     * @return Formatted date
     */
    String formatDate(Date date, String format);

    /**
     * Returns true, if active locale uses twelve hour clock.
     * 
     * @return true if 12h clock. False if 24h clock.
     */
    boolean isTwelveHourClock();

    /**
     * Get currently active locale id. See {@link Locale#toString()}.
     * 
     * @return Locale
     */
    String getLocale();

    /**
     * Return current timezone offset + daylight saving offset.
     * 
     * @return
     */
    long getTimeZoneOffset();
}
