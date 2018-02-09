package org.tltv.gantt;

import org.tltv.gantt.client.shared.SubStep;

public class SubStepComponentFactory {

    public SubStepComponent create(StepComponent stepComponent, SubStep subStep) {
        return new SubStepComponent(stepComponent, subStep);
    }
}
