package de.adito.aditoweb.vaadin.addons.gantt.demo;

import de.adito.aditoweb.vaadin.addons.gantt.client.shared.Step;

public interface GanttListener
{

    void stepModified(Step step);

    void stepDeleted(Step step);

    void stepMoved(Step step, int newStepIndex, int oldStepIndex);
}
