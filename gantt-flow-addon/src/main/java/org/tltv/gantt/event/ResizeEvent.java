package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.model.Step;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class ResizeEvent extends ComponentEvent<Gantt> {

    private final Step step;
    private long startDate;
    private long endDate;
    private long previousStartDate;
    private long previousEndDate;

    private final MouseEventDetails details;

    public ResizeEvent(Gantt source, boolean fromClient, Step step, MouseEventDetails details) {
        super(source, fromClient);
        this.step = step;
        this.details = details;
    }

    public Step getStep() {
        return step;
    }

    public MouseEventDetails getDetails() {
        return details;
    }
}
