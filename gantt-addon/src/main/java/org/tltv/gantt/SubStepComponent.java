package org.tltv.gantt;

import java.util.UUID;

import org.tltv.gantt.client.shared.SubStep;
import org.tltv.gantt.client.shared.SubStepState;

import com.vaadin.ui.AbstractComponent;

/**
 * Component representing a Sub-Step in the Gantt chart.
 * 
 * @author Tltv
 * 
 */
public class SubStepComponent extends AbstractComponent {

    public SubStepComponent(StepComponent stepComponent, SubStep data) {
        if (data.getUid() == null) {
            data.setUid(UUID.randomUUID().toString());
        }
        setParent(stepComponent);
        getState().step = data;
    }

    @Override
    public SubStepState getState() {
        return (SubStepState) super.getState();
    }

    @Override
    public SubStepState getState(boolean markAsDirty) {
        return (SubStepState) super.getState(markAsDirty);
    }

}