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

import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget representing a one Step in the Gantt chart.
 * 
 * @author Tltv
 * 
 */
public class StepWidget extends Widget {

    public static final String SVG_NS = "http://www.w3.org/2000/svg";

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

    /*
     * predecessorArrowSvg element is always the first child of this widget, if
     * it exist.
     */
    private Element predecessorArrowSvg;
    private Element line;

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

        double top = Math.min(
                predecessorStepWidget.getElement().getOffsetTop(), getElement()
                        .getOffsetTop());
        double bottom = Math.max(predecessorStepWidget.getElement()
                .getOffsetTop()
                + predecessorStepWidget.getElement().getOffsetHeight(),
                getElement().getOffsetTop() + getElement().getOffsetHeight());
        double height = bottom - top;

        double left = Math.min(predecessorStepWidget.getElement()
                .getOffsetLeft()
                + predecessorStepWidget.getElement().getOffsetWidth(),
                getElement().getOffsetLeft());
        double right = Math.max(predecessorStepWidget.getElement()
                .getOffsetLeft(), getElement().getOffsetLeft());
        double width = right - left;

        setAttributeNS(predecessorArrowSvg, "width", (int) width);
        setAttributeNS(predecessorArrowSvg, "height", (int) height);
        predecessorArrowSvg.getStyle().setTop((int) top, Unit.PX);
        predecessorArrowSvg.getStyle().setLeft((int) left, Unit.PX);

        setAttributeNS(line, "x1", "" + 0);
        setAttributeNS(line, "x2", "" + width);
        setAttributeNS(line, "y1", "" + 0);
        setAttributeNS(line, "y2", "" + height);
    }

    protected void createPredecessorElements() {
        if (predecessorStepWidget == null) {
            if (predecessorArrowSvg != null) {
                gantt.unregisterContentElement(predecessorArrowSvg);
            }
        } else {
            if (predecessorArrowSvg == null) {
                predecessorArrowSvg = createSVGElementNS("svg");
                predecessorArrowSvg.getStyle().setPosition(Position.ABSOLUTE);
                Element g = createSVGElementNS("g");
                setAttributeNS(g, "stroke", "black");
                setAttributeNS(g, "stroke-width", "1");
                line = createSVGElementNS("line");
                DOM.appendChild(g, line);
                DOM.appendChild(predecessorArrowSvg, g);
            }
            gantt.registerContentElement(predecessorArrowSvg);
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

    public static Element createSVGElementNS(String tag) {
        return createElementNS(SVG_NS, tag);
    }

    public static native Element createElementNS(String ns, String tag)
    /*-{
        return $doc.createElementNS(ns, tag);
    }-*/;

    public static void setAttributeNS(Element elem, String attr, int value) {
        setAttributeNS(null, elem, attr, "" + value);
    }

    public static void setAttributeNS(Element elem, String attr, String value) {
        setAttributeNS(null, elem, attr, value);
    }

    public static native void setAttributeNS(String uri, Element elem,
            String attr, String value)
    /*-{
        elem.setAttributeNS(uri, attr, value);
    }-*/;
}
