/*
 * Copyright 2014 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tltv.gantt.client;

import java.util.HashSet;
import java.util.Set;

import org.tltv.gantt.StepComponent;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractHasComponentsConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Connector between GWT StepWidget and Vaadin StepComponent.
 * 
 * @author Tltv
 * 
 */
@Connect(StepComponent.class)
public class StepConnector extends AbstractHasComponentsConnector {

    private GanttWidget gantt;

    @Override
    protected Widget createWidget() {
        return GWT.create(StepWidget.class);
    }

    @Override
    public StepWidget getWidget() {
        return (StepWidget) super.getWidget();
    }

    @Override
    public StepState getState() {
        return (StepState) super.getState();
    }

    @Override
    protected void updateComponentSize() {
        // nop. Component size is handled independently without LayouManager.
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        if (!(getParent() instanceof GanttConnector)) {
            return;
        }

        if (gantt == null) {
            gantt = getGanttConnector().getWidget();
        }

        if (stateChangeEvent.hasPropertyChanged("step")) {
            updatePredecessorWidgetReference();// need to be called before
                                               // setStep
            getWidget().setStep(getState().step);
        }
        if (!getWidget().getElement().hasParentElement()) {
            gantt.addStep(getStepIndex(), getWidget());
        }
        getWidget().updateWidth();

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                getWidget().updatePredecessor();
                GanttConnector ganttConnector = getGanttConnector();
                for (StepWidget stepWidget : ganttConnector.findRelatedSteps(
                        getState().step, ganttConnector.getChildComponents())) {
                    stepWidget.updatePredecessor();
                }
            }
        });
    }

    protected int getStepIndex() {
        return Math.max(0, getGanttConnector().getState().steps.indexOf(this));
    }

    private void updatePredecessorWidgetReference() {

        // check predecessor change and update widget reference if
        // needed.
        Step predecessor = getState().step.getPredecessor();
        Step oldPredecessor = null;
        if (getWidget().getPredecessorStepWidget() != null) {
            oldPredecessor = getWidget().getPredecessorStepWidget().getStep();
        }

        if ((predecessor == null && oldPredecessor != null)
                || (predecessor != null && !predecessor.equals(oldPredecessor))) {
            getWidget().setPredecessorStepWidget(
                    ((GanttConnector) getParent()).getStepWidget(predecessor));
        }
    }

    protected GanttConnector getGanttConnector() {
        return (GanttConnector) getParent();

    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        // nop
    }

    @Override
    public void onConnectorHierarchyChange(
            ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {

        // SubStepConnector handles adding new sub-step.
        // Here we handle removing and other necessary changed related
        // hierarchy.
        Set<SubStepWidget> removed = new HashSet<SubStepWidget>();
        // remove old sub-steps
        for (ComponentConnector c : connectorHierarchyChangeEvent
                .getOldChildren()) {
            if (!getChildComponents().contains(c)) {
                SubStepWidget stepWidget = ((SubStepConnector) c).getWidget();
                getWidget().remove(stepWidget);
                removed.add(stepWidget);
            }
        }

        if (gantt == null) {
            return;
        }
        // update new steps with references to gantt widget and locale data
        // provider.
        for (ComponentConnector c : getChildComponents()) {
            SubStepWidget stepWidget = ((SubStepConnector) c).getWidget();
            if (!connectorHierarchyChangeEvent.getOldChildren().contains(c)) {
                stepWidget.setGantt(gantt, gantt.getLocaleDataProvider());
            }
        }
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo(getState().step.getDescription(),
                getState().errorMessage);
    }

    @Override
    public boolean hasTooltip() {
        // Normally, there is a tooltip if description or errorMessage is set
        StepState state = getState();
        if (state.description != null
                && !state.step.getDescription().equals("")) {
            return true;
        } else if (state.errorMessage != null && !state.errorMessage.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
