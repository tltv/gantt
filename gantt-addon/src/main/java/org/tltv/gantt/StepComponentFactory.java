package org.tltv.gantt;

import org.tltv.gantt.client.shared.Step;

public class StepComponentFactory {

    public StepComponent create(Gantt gantt, Step step) {
        return new StepComponent(gantt, step);
    }
}
