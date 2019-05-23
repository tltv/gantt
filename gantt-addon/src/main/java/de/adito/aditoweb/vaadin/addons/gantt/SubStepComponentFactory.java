package de.adito.aditoweb.vaadin.addons.gantt;

import de.adito.aditoweb.vaadin.addons.gantt.client.shared.SubStep;

public class SubStepComponentFactory {

    public SubStepComponent create(StepComponent stepComponent, SubStep subStep) {
        return new SubStepComponent(stepComponent, subStep);
    }
}
