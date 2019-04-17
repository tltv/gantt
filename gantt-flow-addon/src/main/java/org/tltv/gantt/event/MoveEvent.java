package org.tltv.gantt.event;

import java.time.ZonedDateTime;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;
import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class MoveEvent extends ComponentEvent<Gantt> {

    private final GanttStep target;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int stepIndex;
    private ZonedDateTime previousStartDate;
    private ZonedDateTime previousEndDate;
    private int previousStepIndex;

    private final MouseEventDetails details;

    public MoveEvent(Gantt source, boolean fromClient, GanttStep target, ZonedDateTime startDate, ZonedDateTime endDate,
            int stepIndex,
            ZonedDateTime previousStartDate, ZonedDateTime previousEndDate, int previousStepIndex,
            MouseEventDetails details) {
        super(source, fromClient);
        this.target = target;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stepIndex = stepIndex;
        this.previousStartDate = previousStartDate;
        this.previousEndDate = previousEndDate;
        this.previousStepIndex = previousStepIndex;
        this.details = details;
    }

    public GanttStep getTarget() {
        return target;
    }

    public Step getStep() {
        return (!target.isSubstep()) ? (Step) target : null;
    }

    public SubStep getSubStep() {
        return (target.isSubstep()) ? (SubStep) target : null;
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

    public MouseEventDetails getMouseDetails() {
        return details;
    }
}
