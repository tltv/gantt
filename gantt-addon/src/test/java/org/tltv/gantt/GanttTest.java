package org.tltv.gantt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.tltv.gantt.client.shared.GanttUtil;
import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

public class GanttTest {

    @Test
    public void initGanttTest() {
        Gantt gantt = new Gantt();

        Assert.assertEquals(gantt.getStartDate(), null);
        Assert.assertEquals(gantt.getEndDate(), null);
    }

    @Test
    public void removeSteps_withTwoStepsOneHasSubStep_sizeIsZeroAndComponetHierarchyCleaned() {
        Gantt gantt = new Gantt();

        SubStep subStep = new SubStep();
        Step stepWithSubStep = new Step();
        stepWithSubStep.addSubStep(subStep);
        gantt.addStep(stepWithSubStep);
        gantt.addStep(new Step());

        AbstractStepComponent stepWithSubStepComponent = gantt
                .getStepComponent(stepWithSubStep);
        AbstractStepComponent subStepComponent = gantt
                .getStepComponent(subStep);
        Assert.assertNotNull(stepWithSubStepComponent.getParent());
        Assert.assertNotNull(subStepComponent.getParent());

        gantt.removeSteps();
        Assert.assertEquals(0, gantt.getSteps().size());
        Assert.assertNull(stepWithSubStepComponent.getParent());
        Assert.assertNull(subStepComponent.getParent());
        Assert.assertNull(gantt.getStepComponent(stepWithSubStep));
        Assert.assertNull(gantt.getStepComponent(subStep));
    }

    @Test
    public void addEmptyStepAndGetByUid_addAndGetSuccessfully() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime end = new DateTime(2014, 9, 1, 10, 30, 30, 123);

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        List<Step> steps = new ArrayList<Step>();

        Step step = new Step();
        gantt.addStep(step);

        steps.add(step);

        Assert.assertNotNull(step.getUid());

        Step foundStep = (Step) gantt.getStep(step.getUid());

        Assert.assertNotNull(foundStep);
        Assert.assertEquals(step, foundStep);
        Assert.assertEquals(step.getUid(), foundStep.getUid());
        Assert.assertTrue(steps.contains(foundStep));
    }

    @Test
    public void addStepWithDatesAndGetByUid_addAndGetSuccessfully() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime end = new DateTime(2014, 9, 1, 10, 30, 30, 123);

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        List<Step> steps = new ArrayList<Step>();

        DateTime expectedStart = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime expectedEnd = new DateTime(2014, 1, 8, 10, 30, 30, 123);

        DateTime stepStart = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime stepEnd = new DateTime(2014, 1, 8, 10, 30, 30, 123);

        Step step = new Step();
        step.setStartDate(stepStart.toDate());
        step.setEndDate(stepEnd.toDate());
        gantt.addStep(step);

        steps.add(step);

        Assert.assertNotNull(step.getUid());

        Step foundStep = (Step) gantt.getStep(step.getUid());

        Assert.assertNotNull(foundStep);
        Assert.assertEquals(step, foundStep);
        Assert.assertEquals(step.getUid(), foundStep.getUid());
        Assert.assertTrue(steps.contains(foundStep));
        Assert.assertEquals(expectedStart.toDate().getTime(),
                foundStep.getStartDate());
        Assert.assertEquals(expectedEnd.toDate().getTime(),
                foundStep.getEndDate());
    }

    @Test
    public void setDateRangeForDayResolutionAndDefaultTimezoneTest_DST_OFFSET_OVER_0() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);

        DateTime expectedStart = new DateTime(2014, 1, 1, 0, 0, 0, 000);
        DateTime expectedEnd = new DateTime(2014, 9, 1, 23, 59, 59, 999);
        Long dst_offset = Long.valueOf(Calendar.getInstance().get(
                Calendar.DST_OFFSET));
        expectedEnd = expectedEnd.minusMillis(dst_offset.intValue());

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime end = new DateTime(2014, 9, 1, 10, 30, 30, 123);

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(expectedStart.toDate(), gantt.getStartDate());
        Assert.assertEquals(expectedEnd.toDate(), gantt.getEndDate());
    }

    @Test
    public void setDateRangeForDayResolutionAndDefaultTimezoneTest_DST_OFFSET_0() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);

        DateTime expectedStart = new DateTime(2014, 1, 1, 0, 0, 0, 000);
        DateTime expectedEnd = new DateTime(2015, 1, 1, 23, 59, 59, 999);
        Long dst_offset = Long.valueOf(Calendar.getInstance().get(
                Calendar.DST_OFFSET));
        expectedEnd = expectedEnd.minusMillis(dst_offset.intValue());

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123);
        DateTime end = new DateTime(2015, 1, 1, 10, 30, 30, 123);

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(expectedStart.toDate(), gantt.getStartDate());
        Assert.assertEquals(expectedEnd.toDate(), gantt.getEndDate());
    }

    @Test
    public void setDateRangeForDayResolutionAndGMTm10TimezoneTest() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);
        gantt.setTimeZone(TimeZone.getTimeZone("Pacific/Honolulu"));

        DateTime expectedStart = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Pacific/Honolulu")));
        DateTime expectedEnd = new DateTime(2015, 1, 1, 23, 59, 59, 999,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Pacific/Honolulu")));

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Pacific/Honolulu")));
        DateTime end = new DateTime(2015, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Pacific/Honolulu")));

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getStartDate(), expectedStart.toDate());
        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }

    @Test
    public void setDateRange_withDayResolutionAndTZEuropeAmsterdam() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Day);
        gantt.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));

        DateTime expectedStart = new DateTime(2015, 4, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));
        DateTime expectedEnd = new DateTime(2015, 4, 30, 23, 59, 59, 999,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));

        DateTime start = new DateTime(2015, 4, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));
        DateTime end = new DateTime(2015, 4, 30, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getStartDate(), expectedStart.toDate());
        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }

    @Test
    public void setDateRange_withDayResolutionAndTZEuropeAmsterdamAndCustomTimezoneOffsetDate() {
        Gantt gantt = new Gantt() {
            @Override
            protected Date getTimezoneOffsetDate() {
                return new DateTime(2015, 4, 1, 0, 0, 0, 000,
                        DateTimeZone.forTimeZone(TimeZone
                                .getTimeZone("Europe/Amsterdam"))).toDate();
            }
        };
        gantt.setResolution(Resolution.Day);
        gantt.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));

        DateTime expectedEnd = new DateTime(2015, 4, 30, 23, 59, 59, 999,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));

        DateTime start = new DateTime(2015, 4, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));
        DateTime end = new DateTime(2015, 4, 30, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Europe/Amsterdam")));

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }

    @Test
    public void setDateRangeForHourResolutionAndGMTp10TimezoneTest() {
        Gantt gantt = new Gantt();
        gantt.setResolution(Resolution.Hour);
        gantt.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));

        DateTime expectedStart = new DateTime(2014, 1, 1, 10, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Australia/Melbourne")));
        DateTime expectedEnd = new DateTime(2015, 1, 1, 10, 59, 59, 999,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Australia/Melbourne")));

        DateTime start = new DateTime(2014, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Australia/Melbourne")));
        DateTime end = new DateTime(2015, 1, 1, 10, 30, 30, 123,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Australia/Melbourne")));

        gantt.setStartDate(start.toDate());
        gantt.setEndDate(end.toDate());

        Assert.assertEquals(gantt.getStartDate(), expectedStart.toDate());
        Assert.assertEquals(gantt.getEndDate(), expectedEnd.toDate());
    }

    @Test
    public void dateAndTimeZoneTest() {
        Locale locale = Locale.GERMANY;

        long tzGMTOffset = getTimezoneOffset(
                TimeZone.getTimeZone("Europe/London"), locale); // gmt+0
        long tzGMTplus3Offset = getTimezoneOffset(
                TimeZone.getTimeZone("Asia/Tehran"), locale); // gmt+3
        long tzGMTplus10Offset = getTimezoneOffset(
                TimeZone.getTimeZone("Australia/Melbourne"), locale); // gmt+10
        long tzGMTminus10Offset = getTimezoneOffset(
                TimeZone.getTimeZone("Pacific/Honolulu"), locale); // gmt-10

        Assert.assertEquals(0, tzGMTOffset);

        Calendar cal = Calendar.getInstance(
                TimeZone.getTimeZone("Australia/Melbourne"), locale);
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        DateTime dateGMT = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London")));

        DateTime dateGMTplus3 = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Tehran")));

        DateTime dateGMTplus10 = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Australia/Melbourne")));

        DateTime dateGMTminus10 = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(TimeZone
                        .getTimeZone("Pacific/Honolulu")));

        long gmtTime = dateGMT.toDate().getTime();
        long gmtp3Time = dateGMTplus3.toDate().getTime();
        long gmtp10Time = dateGMTplus10.toDate().getTime();
        long gmtm10Time = dateGMTminus10.toDate().getTime();

        Assert.assertEquals(cal.getTime().getTime(), gmtp10Time);

        Assert.assertEquals(gmtTime, (gmtp3Time + tzGMTplus3Offset));
        Assert.assertEquals(gmtTime, (gmtp10Time + tzGMTplus10Offset));
        Assert.assertEquals(gmtTime, (gmtm10Time + tzGMTminus10Offset));

        Assert.assertEquals((gmtp3Time + tzGMTplus3Offset),
                (gmtp10Time + tzGMTplus10Offset));
        Assert.assertEquals((gmtp3Time + tzGMTplus3Offset),
                (gmtm10Time + tzGMTminus10Offset));
    }

    @Test
    public void clientWeekNumberCalculation_lastWeekIf2014_weekNumber1() {
        TimeZone timezone = TimeZone.getTimeZone("Asia/Tehran");
        Locale locale = Locale.GERMANY;

        DateTime week1_2014 = new DateTime(2014, 1, 1, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(timezone));

        DateTime week2_2014 = new DateTime(2014, 1, 6, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(timezone));

        DateTime week52_2014 = new DateTime(2014, 12, 28, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(timezone));

        DateTime lastDayOf2014 = new DateTime(2014, 12, 31, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(timezone));

        DateTime week1_2015 = new DateTime(2015, 1, 4, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(timezone));
        DateTime week2_2015 = new DateTime(2015, 1, 5, 0, 0, 0, 000,
                DateTimeZone.forTimeZone(timezone));

        Calendar cal = Calendar.getInstance(timezone, locale);

        long tzOfset = getTimezoneOffset(timezone, locale);

        int weekNumber = GanttUtil.getWeekNumber(new Date(week1_2014.toDate()
                .getTime() + tzOfset), getTimezoneOffset(timezone, locale),
                cal.getFirstDayOfWeek());
        Assert.assertEquals(1, weekNumber);

        weekNumber = GanttUtil.getWeekNumber(new Date(week2_2014.toDate()
                .getTime() + tzOfset), getTimezoneOffset(timezone, locale),
                cal.getFirstDayOfWeek());
        Assert.assertEquals(2, weekNumber);

        weekNumber = GanttUtil.getWeekNumber(lastDayOf2014.toDate(),
                getTimezoneOffset(timezone, locale), cal.getFirstDayOfWeek());
        Assert.assertEquals(1, weekNumber);

        weekNumber = GanttUtil.getWeekNumber(new Date(week52_2014.toDate()
                .getTime() + tzOfset), getTimezoneOffset(timezone, locale),
                cal.getFirstDayOfWeek());
        Assert.assertEquals(52, weekNumber);

        weekNumber = GanttUtil.getWeekNumber(new Date(week1_2015.toDate()
                .getTime() + tzOfset), getTimezoneOffset(timezone, locale),
                cal.getFirstDayOfWeek());
        Assert.assertEquals(1, weekNumber);

        weekNumber = GanttUtil.getWeekNumber(new Date(week2_2015.toDate()
                .getTime() + tzOfset), getTimezoneOffset(timezone, locale),
                cal.getFirstDayOfWeek());
        Assert.assertEquals(2, weekNumber);
    }

    private long getTimezoneOffset(TimeZone timezone, Locale locale) {
        Calendar cal = Calendar.getInstance(timezone, locale);
        return cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET);
    }
}
