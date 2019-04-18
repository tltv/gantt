package org.tltv.gantt.event;

import java.time.ZonedDateTime;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;

import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class ResizeEvent extends StepMouseEvent {

    private final ZonedDateTime startDate;
    private final ZonedDateTime endDate;
    private final ZonedDateTime previousStartDate;
    private final ZonedDateTime previousEndDate;

    public ResizeEvent(Gantt source, boolean fromClient, GanttStep target, ZonedDateTime startDate,
            ZonedDateTime endDate, ZonedDateTime previousStartDate, ZonedDateTime previousEndDate,
            MouseEventDetails details) {
        super(source, fromClient, target, details);
        this.startDate = startDate;
        this.endDate = endDate;
        this.previousStartDate = previousStartDate;
        this.previousEndDate = previousEndDate;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public ZonedDateTime getPreviousStartDate() {
        return previousStartDate;
    }

    public ZonedDateTime getPreviousEndDate() {
        return previousEndDate;
    }
}
