package org.tltv.gantt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.tltv.gantt.client.shared.AbstractStep;

/** Server-side Gantt LocalDateTime utility class. */
public class GanttTimeUtil {

    public static LocalDateTime getStartLocalDateTime(AbstractStep step) {
        return (step.getStartDate() > -1)
                ? LocalDateTime.ofInstant(Instant.ofEpochMilli(step.getStartDate()), ZoneId.systemDefault()) : null;
    }

    public static LocalDateTime getEndLocalDateTime(AbstractStep step) {
        return (step.getEndDate() > -1)
                ? LocalDateTime.ofInstant(Instant.ofEpochMilli(step.getEndDate()), ZoneId.systemDefault()) : null;
    }

    public static void setEndDate(AbstractStep step, Instant instant) {
        step.setEndDate((instant != null) ? Date.from(instant) : null);
    }

    public static void setEndDate(AbstractStep step, LocalDateTime endTime) {
        step.setEndDate(
                (endTime != null) ? Date.from(endTime.atZone(TimeZone.getDefault().toZoneId()).toInstant()) : null);
    }

    public static void setEndDate(AbstractStep step, ZonedDateTime dateTime) {
        step.setEndDate((dateTime != null) ? Date.from(dateTime.toInstant()) : null);
    }

    public static void setStartDate(AbstractStep step, Instant instant) {
        step.setStartDate((instant != null) ? Date.from(instant) : null);
    }

    public static void setStartDate(AbstractStep step, LocalDateTime dateTime) {
        step.setStartDate(
                (dateTime != null) ? Date.from(dateTime.atZone(TimeZone.getDefault().toZoneId()).toInstant()) : null);
    }

    public static void setStartDate(AbstractStep step, ZonedDateTime dateTime) {
        step.setStartDate((dateTime != null) ? Date.from(dateTime.toInstant()) : null);
    }
}
