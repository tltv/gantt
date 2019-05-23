package de.adito.aditoweb.vaadin.addons.gantt;

import de.adito.aditoweb.vaadin.addons.gantt.client.shared.Step;

public class StepComponentFactory {

    public StepComponent create(Gantt gantt, Step step) {
        return new StepComponent(gantt, step);
    }
}
