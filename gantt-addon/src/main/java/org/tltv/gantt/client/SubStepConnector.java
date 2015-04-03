/*
 * Copyright 2015 Tomi Virtanen
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

import org.tltv.gantt.SubStepComponent;
import org.tltv.gantt.client.shared.SubStepState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(SubStepComponent.class)
public class SubStepConnector extends AbstractComponentConnector {

    private StepWidget step;

    @Override
    protected Widget createWidget() {
        return GWT.create(SubStepWidget.class);
    }

    @Override
    public SubStepWidget getWidget() {
        return (SubStepWidget) super.getWidget();
    }

    @Override
    public SubStepState getState() {
        return (SubStepState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        if (!(getParent() instanceof StepConnector)) {
            return;
        }

        if (step == null) {
            step = ((StepConnector) getParent()).getWidget();
            getWidget().setOwner(step);
        }

        if (stateChangeEvent.hasPropertyChanged("step")) {
            getWidget().setStep(getState().step);
        }
        getWidget().updateWidth();
        if (!getWidget().getElement().hasParentElement()) {
            step.add(getWidget());
        }
    }

}
