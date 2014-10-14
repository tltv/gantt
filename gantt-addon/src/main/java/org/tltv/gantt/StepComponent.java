package org.tltv.gantt;

import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;

import com.vaadin.ui.AbstractComponent;

/**
 * Component representing a Step in the Gantt chart.
 * 
 * @author Tltv
 * 
 */
public class StepComponent extends AbstractComponent {

    public StepComponent(Gantt gantt, Step data) {
        setParent(gantt);
        getState().step = data;
    }

    @Override
    public StepState getState() {
        return (StepState) super.getState();
    }

    @Override
    public StepState getState(boolean markAsDirty) {
        return (StepState) super.getState(markAsDirty);
    }

}
