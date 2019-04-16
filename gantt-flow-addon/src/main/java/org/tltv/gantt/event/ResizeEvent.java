package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.model.Step;

import com.vaadin.flow.component.ComponentEvent;

public class ResizeEvent extends ComponentEvent<Gantt> {

    private final Step step;
    private long startDate;
    private long endDate;
    private long previousStartDate;
    private long previousEndDate;

    public ResizeEvent(Gantt source, boolean fromClient, Step step) {
        super(source, fromClient);
        this.step = step;
    }

    public Step getStep() {
        return step;
    }

}
