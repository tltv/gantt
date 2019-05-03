package org.tltv.gantt;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.function.Supplier;

import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.charts.events.MouseEventDetails;
import com.vaadin.flow.component.charts.events.MouseEventDetails.MouseButton;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import elemental.json.JsonObject;

public class GanttUtil {

    public static MouseEventDetails readMouseEventDetails(JsonObject detailsJson) {
        MouseEventDetails details = new MouseEventDetails();

        Double x = detailsJson.getNumber("relativeX");
        Double y = detailsJson.getNumber("relativeY");
        if (x != null) {
            details.setxValue(x);
        }
        if (y != null) {
            details.setyValue(y);
        }
        details.setAbsoluteX((int) detailsJson.getNumber("clientX_0"));
        details.setAbsoluteY((int) detailsJson.getNumber("clientY_0"));
        String buttonName = detailsJson.getObject("button_0").getString("name_0");
        if (MouseButton.LEFT.name().equalsIgnoreCase(buttonName)) {
            details.setButton(MouseButton.LEFT);
        } else if (MouseButton.RIGHT.name().equalsIgnoreCase(buttonName)) {
            details.setButton(MouseButton.RIGHT);
        } else if (MouseButton.MIDDLE.name().equalsIgnoreCase(buttonName)) {
            details.setButton(MouseButton.MIDDLE);
        }
        details.setAltKey(detailsJson.getBoolean("altKey_0"));
        details.setCtrlKey(detailsJson.getBoolean("ctrlKey_0"));
        details.setMetaKey(detailsJson.getBoolean("metaKey_0"));
        details.setShiftKey(detailsJson.getBoolean("shiftKey_0"));
        return details;
    }

    /** Get smallest sub-step start date from this step. */
    public static double getMinStartDateBySubSteps(Collection<SubStep> subSteps) {
        double min = -1;
        for (SubStep subStep : subSteps) {
            if (min < 0) {
                min = subStep.getStartDate();
            } else {
                min = Math.min(min, subStep.getStartDate());
            }
        }
        return min;
    }

    /** Get largest sub-step end date from this step. */
    public static double getMaxEndDateBySubSteps(Collection<SubStep> subSteps) {
        double max = -1;
        for (SubStep subStep : subSteps) {
            if (max < 0) {
                max = subStep.getEndDate();
            } else {
                max = Math.max(max, subStep.getEndDate());
            }
        }
        return max;
    }

    public static Double toDouble(LocalDate date, Gantt gantt) {
        return toDouble(date, gantt.getZoneId());
    }

    public static Double toDouble(LocalDate date, ZoneId zoneId) {
        if (date == null) {
            return null;
        }
        return (double) date.atStartOfDay(zoneId).toInstant().getEpochSecond() * 1000;
    }

    public static LocalDate toLocalDate(Double date, Gantt gantt) {
        return toLocalDate(date, gantt.getZoneId());
    }

    public static LocalDate toLocalDate(Double date, ZoneId zoneId) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.longValue()).atZone(zoneId).toLocalDate();
    }

    public static Double toDouble(LocalDate date, LocalTime time, Gantt gantt) {
        return toDouble(date, time, gantt.getZoneId());
    }

    public static Double toDouble(LocalDate date, LocalTime time, ZoneId zoneId) {
        if (date == null || time == null) {
            return null;
        }
        return (double) date.atStartOfDay(zoneId).with(time).toInstant().getEpochSecond() * 1000;
    }

    public static LocalTime toLocalTime(Double date, Gantt gantt) {
        return toLocalTime(date, gantt.getZoneId());
    }

    public static LocalTime toLocalTime(Double date, ZoneId zoneId) {
        if (date == null) {
            return null;
        }
        LocalTime time = Instant.ofEpochMilli(date.longValue()).atZone(zoneId).toLocalTime();
        return time;
    }

    public static class LocalDateToDoubleConverter implements Converter<LocalDate, Double> {

        private Supplier<Gantt> ganttSupplier;

        public LocalDateToDoubleConverter(Supplier<Gantt> ganttSupplier) {
            this.ganttSupplier = ganttSupplier;
        }

        @Override
        public Result<Double> convertToModel(LocalDate value, ValueContext context) {
            if (value == null) {
                return Result.ok(null);
            }
            return Result.ok(toDouble(value, ganttSupplier.get()));
        }

        @Override
        public LocalDate convertToPresentation(Double value, ValueContext context) {
            return toLocalDate(value, ganttSupplier.get());
        }
    }

}
