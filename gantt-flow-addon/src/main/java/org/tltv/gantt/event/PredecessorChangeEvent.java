package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.model.Step;

import com.vaadin.flow.component.ComponentEvent;

public class PredecessorChangeEvent extends ComponentEvent<Gantt> {

    private final Step step;
    private final Step predecessorStep;

    public PredecessorChangeEvent(Gantt source, boolean fromClient, Step step, Step predecessorStep) {
        super(source, fromClient);
        this.step = step;
        this.predecessorStep = predecessorStep;
    }

    public Step getStep() {
        return step;
    }

    public Step getPredecessorStep() {
        return predecessorStep;
    }
}
