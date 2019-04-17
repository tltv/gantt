package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;
import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class ClickEvent extends ComponentEvent<Gantt> {

    private final GanttStep target;
    private final MouseEventDetails details;

    public ClickEvent(Gantt source, boolean fromClient, GanttStep target, MouseEventDetails details) {
        super(source, fromClient);
        this.target = target;
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

    public MouseEventDetails getMouseDetails() {
        return details;
    }
}
