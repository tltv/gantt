package org.tltv.gantt.event;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;

import com.vaadin.flow.component.charts.events.MouseEventDetails;

public class ClickEvent extends StepMouseEvent {

    public ClickEvent(Gantt source, boolean fromClient, GanttStep target, MouseEventDetails details) {
        super(source, fromClient, target, details);
    }

}
