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

import org.tltv.gantt.StepComponent;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Connector between GWT StepWidget and Vaadin StepComponent.
 * 
 * @author Tltv
 * 
 */
@Connect(StepComponent.class)
public class StepConnector extends AbstractComponentConnector {

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
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        if (!(getParent() instanceof GanttConnector)) {
            return;
        }

        if (gantt == null) {
            gantt = ((GanttConnector) getParent()).getWidget();
        }

        if (!getWidget().getElement().hasParentElement()) {
            gantt.addStep(getWidget());
        }
    }
}
