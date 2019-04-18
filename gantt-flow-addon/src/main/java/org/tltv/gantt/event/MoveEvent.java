package org.tltv.gantt.event;

import java.time.ZonedDateTime;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;

import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class MoveEvent extends StepMouseEvent {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int stepIndex;
    private ZonedDateTime previousStartDate;
    private ZonedDateTime previousEndDate;
    private int previousStepIndex;

    public MoveEvent(Gantt source, boolean fromClient, GanttStep target, ZonedDateTime startDate, ZonedDateTime endDate,
            int stepIndex,
            ZonedDateTime previousStartDate, ZonedDateTime previousEndDate, int previousStepIndex,
            MouseEventDetails details) {
        super(source, fromClient, target, details);
        this.startDate = startDate;
        this.endDate = endDate;
        this.stepIndex = stepIndex;
        this.previousStartDate = previousStartDate;
        this.previousEndDate = previousEndDate;
        this.previousStepIndex = previousStepIndex;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public ZonedDateTime getPreviousStartDate() {
        return previousStartDate;
    }

    public ZonedDateTime getPreviousEndDate() {
        return previousEndDate;
    }

    public int getPreviousStepIndex() {
        return previousStepIndex;
    }

}
