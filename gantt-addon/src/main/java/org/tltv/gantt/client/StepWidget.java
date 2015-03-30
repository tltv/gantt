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

import org.tltv.gantt.client.ArrowElement.ArrowChangeHandler;
import org.tltv.gantt.client.shared.GanttUtil;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget representing a one Step in the Gantt chart.
 * 
 * @author Tltv
 * 
 */
public class StepWidget extends Widget {

    private static final String STYLE_BAR = "bar";
    private static final String STYLE_BAR_LABEL = "bar-label";
    private static final String STYLE_INVALID = "invalid";

    private DivElement caption;

    private String extraStyle;
    private long start = -1;
    private long end = -1;

    private Step step;

    private StepWidget predecessorStepWidget;

    private GanttWidget gantt;
    private LocaleDataProvider localeDataProvider;

    private ArrowElement predecessorArrow;

    private ArrowChangeHandler arrowChangeHandler = new ArrowChangeHandler() {

        @Override
        public void onArrowChanged(boolean startingPointChanged,
                MouseDownEvent event) {
            Element target = GanttUtil.getElementFromPoint(
                    GanttUtil.getTouchOrMouseClientX(event.getNativeEvent()),
                    GanttUtil.getTouchOrMouseClientY(event.getNativeEvent()));
            if (target != null && !getElement().isOrHasChild(target)) {
                gantt.getRpc().onStepRelationSelected(StepWidget.this,
                        startingPointChanged, target);
            }
        }
    };

    @Override
    protected void onDetach() {
        if (gantt != null && predecessorArrow != null) {
            gantt.unregisterContentElement((Widget) predecessorArrow);
        }
        super.onDetach();
    }

    public StepWidget() {
        DivElement bar = DivElement.as(DOM.createDiv());
        bar.setClassName(STYLE_BAR);
        setElement(bar);

        caption = DivElement.as(DOM.createDiv());
        caption.setClassName(STYLE_BAR_LABEL);
        bar.appendChild(caption);

        // hide by default
        bar.getStyle().setVisibility(Visibility.HIDDEN);
    }

    public void setGantt(GanttWidget gantt,
            LocaleDataProvider localeDataProvider) {
        this.gantt = gantt;
        setLocaleDataProvider(localeDataProvider);
    }

    public void setLocaleDataProvider(LocaleDataProvider localeDataProvider) {
        this.localeDataProvider = localeDataProvider;
    }

    public LocaleDataProvider getLocaleDataProvider() {
        return localeDataProvider;
    }

    /**
     * Set data source for this widget. Called when {@linkplain StepState#state}
     * is changed.
     * 
     * @param step
     */
    public void setStep(Step step) {
        this.step = step;
        updateBackground();
        updateStyle();
        updateCaption();
    }

    /**
     * Get state object. read-only. Changes to the Step object on client side
     * are not registered to the server side.
     */
    public Step getStep() {
        return step;
    }

    public StepWidget getPredecessorStepWidget() {
        return predecessorStepWidget;
    }

    public void setPredecessorStepWidget(StepWidget predecessorStepWidget) {
        this.predecessorStepWidget = predecessorStepWidget;
    }

    public void updatePredecessor() {
        createPredecessorElements();

        if (predecessorStepWidget == null) {
            return;
        }

        ArrowPositionData data = new ArrowPositionData(
                getPredecessorStepWidget().getElement(), getElement());

        predecessorArrow.setWidth(data.getWidth());
        predecessorArrow.setHeight(data.getHeight());
        predecessorArrow.getElement().getStyle()
                .setTop((int) data.getTop(), Unit.PX);
        predecessorArrow.getElement().getStyle()
                .setLeft((int) data.getLeft(), Unit.PX);

        predecessorArrow.draw(data);
    }

    public ArrowElement createArrowWidget() {
        return new SvgArrowWidget();
    }

    protected void createPredecessorElements() {
        if (predecessorStepWidget == null) {
            if (predecessorArrow != null) {
                gantt.unregisterContentElement((Widget) predecessorArrow);
            }
        } else {
            if (predecessorArrow == null) {
                predecessorArrow = createArrowWidget();
                predecessorArrow.setUpEventHandlers(gantt.isTouchSupported(),
                        gantt.isMsTouchSupported());
                predecessorArrow.setArrowChangeHandler(arrowChangeHandler);
            }
            gantt.registerContentElement((Widget) predecessorArrow);
        }
    }

    protected void updateCaption() {
        if (step.getCaptionMode() == Step.CaptionMode.HTML) {
            caption.setInnerHTML(step.getCaption());
        } else {
            caption.setInnerText(step.getCaption());
        }
    }

    protected void updateBackground() {
        getElement().getStyle().setBackgroundColor(step.getBackgroundColor());
    }

    protected void updateStyle() {
        if (!isEmpty(step.getStyleName())) {
            if (!step.getStyleName().equals(extraStyle)) {
                // style name changed. Clear old and add new style.
                if (!isEmpty(extraStyle)) {
                    getElement().removeClassName(extraStyle);
                }
                getElement().addClassName(step.getStyleName());
            }
        } else if (!isEmpty(extraStyle)) {
            getElement().removeClassName(extraStyle);
        }
    }

    protected boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    /**
     * Updates width of this widget to match the Gantt chart's timeline.
     */
    public void updateWidth() {
        if (gantt == null || !getElement().hasParentElement()) {
            return;
        }

        getElement().getStyle().clearVisibility();

        if (start != step.getStartDate() || end != step.getEndDate()) {

            // sanity check
            if (step.getStartDate() < 0 || step.getEndDate() < 0
                    || step.getEndDate() <= step.getStartDate()) {
                getElement().addClassName(STYLE_INVALID);
            } else {
                long offset = 0;
                if (getLocaleDataProvider() != null) {
                    offset = getLocaleDataProvider().getTimeZoneOffset();
                }
                gantt.updateBarPercentagePosition(step.getStartDate() + offset,
                        step.getEndDate() + offset, getElement());
            }

        }
    }

    public Widget getPredecessorArrowWidget() {
        return (Widget) predecessorArrow;
    }

}
