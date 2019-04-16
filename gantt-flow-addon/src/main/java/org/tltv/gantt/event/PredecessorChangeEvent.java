package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.model.Step;

import com.vaadin.flow.component.ComponentEvent;

public class PredecessorChangeEvent extends ComponentEvent<Gantt> {

    private final Step step;

    public PredecessorChangeEvent(Gantt source, boolean fromClient, Step step) {
        super(source, fromClient);
        this.step = step;
    }

    public Step getStep() {
        return step;
    }

}
