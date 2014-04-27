package org.tltv.gantt;

import java.util.TimeZone;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.tltv.gantt.client.shared.Resolution;

public class GanttTest {

    @Test
    public void thisAlwaysPasses() {
        Assert.assertEquals(true, true);
    }

    @Test
    public void initGanttTest() {
        Gantt gantt = new Gantt();

        Assert.assertEquals(gantt.getStartDate(), null);
        Assert.assertEquals(gantt.getEndDate(), null);
    }

    @Test
    public void setDateRangeForDayResolutionAndDefaultTimezoneTest() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);

        DateTime expectedStart = new DateTime(2014, 1, 1, 0, 0, 0, 000);
        DateTime expectedEnd = new DateTime(2015, 1, 1, 23, 59, 59, 999);

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime end = new DateTime(2015, 1, 1, 10, 30, 30, 123);

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getStartDate(), expectedStart.toDate());
        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }

    @Test
    public void setDateRangeForDayResolutionAndGMTm10TimezoneTest() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);
        gantt.setTimeZone(TimeZone.getTimeZone("GMT-10"));

        DateTime expectedStart = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-10")));
        DateTime expectedEnd = new DateTime(2015, 1, 1, 23, 59, 59, 999,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-10")));

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-10")));
        DateTime end = new DateTime(2015, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-10")));

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getStartDate(), expectedStart.toDate());
        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }

    @Test
    public void setDateRangeForHourResolutionAndGMTp10TimezoneTest() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Hour);
        gantt.setTimeZone(TimeZone.getTimeZone("GMT+10"));

        DateTime expectedStart = new DateTime(2014, 1, 1, 10, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+10")));
        DateTime expectedEnd = new DateTime(2015, 1, 1, 10, 59, 59, 999,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+10")));

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+10")));
        DateTime end = new DateTime(2015, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+10")));

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getStartDate(), expectedStart.toDate());
        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }
}
