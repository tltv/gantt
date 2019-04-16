package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;
import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.ComponentEvent;

public class ClickEvent extends ComponentEvent<Gantt> {

    private final GanttStep target;
    // TODO click details

    public ClickEvent(Gantt source, boolean fromClient, GanttStep target) {
        super(source, fromClient);
        this.target = target;
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
}
