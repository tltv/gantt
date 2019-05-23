package de.adito.aditoweb.vaadin.addons.gantt.client.shared;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;

public class StepState extends AbstractComponentState {

    public Step step;

    public List<Connector> subSteps = new LinkedList<Connector>();

}
