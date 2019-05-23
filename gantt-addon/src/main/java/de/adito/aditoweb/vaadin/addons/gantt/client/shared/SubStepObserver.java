package de.adito.aditoweb.vaadin.addons.gantt.client.shared;

public interface SubStepObserver {

    void onAddSubStep(SubStep subStep);

    void onRemoveSubStep(SubStep subStep);
}
