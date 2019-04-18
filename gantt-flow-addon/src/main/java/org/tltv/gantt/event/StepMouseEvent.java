package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;

import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class StepMouseEvent extends StepEvent {

    private final MouseEventDetails details;

    public StepMouseEvent(Gantt source, boolean fromClient, GanttStep target, MouseEventDetails details) {
        super(source, fromClient, target);
        this.details = details;
    }

    public MouseEventDetails getDetails() {
        return details;
    }
}
