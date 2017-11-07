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
import org.tltv.gantt.client.shared.SubStep;
import org.tltv.gantt.client.shared.SubStepState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.ContentMode;

@Connect(SubStepComponent.class)
public class SubStepConnector extends AbstractComponentConnector {

    private StepWidget step;

    @Override
    protected Widget createWidget() {
        return new SubStepWidget();
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
    protected void updateComponentSize() {
        // nop. Component size is handled independently without LayouManager.
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        if (!(getParent() instanceof StepConnector)) {
            return;
        }

        if (step == null) {
            step = ((StepConnector) getParent()).getWidget();
            getWidget().setOwner(step);
        }

        step.ready(new Function<Object, Object>() {
            @Override
            public Object call(Object args) {

                final boolean added;
                if (getWidget().getElement().getParentNode() == null) {
                    added = true;
                    step.add(getWidget());
                } else {
                    added = false;
                }

                getWidget().ready(new Function<Object, Object>() {
                    @Override
                    public Object call(Object args) {
                        GWT.log("*** SubStepConnector.onStateChanged READY");
                        if (stateChangeEvent.hasPropertyChanged("step")) {
                            setStep(getState().step);
                        }
                        if (added) {
                            getWidget().getOwner().updateStylesForSubSteps();
                        }
                        getWidget().getElement().setAttribute("start-date", "" + getState().step.getStartDate());
                        getWidget().getElement().setAttribute("end-date", "" + getState().step.getEndDate());
                        // if (stateChangeEvent.hasPropertyChanged("step")) {
                        // getWidget().getOwner().updateOtherSubstepsWidths(getWidget());
                        // }
                        getWidget().updateWidth();
                        return null;
                    }
                });

                return null;
            }
        });

    }

    protected void setStep(SubStep step) {
        getWidget().getBar().setAttribute("background-color", step.getBackgroundColor());
        getWidget().getBar().setAttribute("caption", step.getCaption());
        getWidget().setStep(getState().step);
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo(getState().step.getDescription(), ContentMode.HTML, getState().errorMessage);
    }

    @Override
    public boolean hasTooltip() {
        // Normally, there is a tooltip if description or errorMessage is set
        SubStepState state = getState();
        if (state == null) {
            return false;
        }
        if (state.step != null && state.step.getDescription() != null && !state.step.getDescription().equals("")) {
            return true;
        } else if (state.errorMessage != null && !state.errorMessage.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
