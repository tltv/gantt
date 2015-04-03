package org.tltv.gantt.client.shared;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.Connector;

public class StepState extends com.vaadin.shared.AbstractComponentState {

    public Step step;

    public List<Connector> subSteps = new LinkedList<Connector>();

}
