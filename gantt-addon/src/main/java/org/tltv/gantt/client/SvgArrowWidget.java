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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.event.PointerDownEvent;
import com.vaadin.client.event.PointerDownHandler;

/** SVG implementation of {@link ArrowElement} arrow between two elements. */
public class SvgArrowWidget extends Widget implements ArrowElement {

    public static final String SVG_NS = "http://www.w3.org/2000/svg";

    private Element curve;
    private Element startingPoint;
    private Element endingPoint;

    private int width = 0;
    private int height = 0;

    public SvgArrowWidget() {
        Element predecessorArrow = createSVGElementNS("svg");
        addStyleName(predecessorArrow, "arrow");
        predecessorArrow.getStyle().setPosition(Position.ABSOLUTE);
        predecessorArrow.getStyle().setZIndex(2);
        predecessorArrow.getStyle().setProperty("pointerEvents", "none");

        Element g = createSVGElementNS("g");
        setAttributeNS(g, "stroke", "black");
        setAttributeNS(g, "stroke-width", "1");
        curve = createSVGElementNS("path");
        addStyleName(curve, "curve-line");
        setAttributeNS(curve, "fill", "none");
        startingPoint = createSVGElementNS("circle");
        startingPoint.getStyle().setProperty("pointerEvents", "visiblePainted");
        addStyleName(startingPoint, "start-p");
        setAttributeNS(startingPoint, "stroke-width", "2");
        setAttributeNS(startingPoint, "r", "4");
        setAttributeNS(startingPoint, "fill", "black");
        endingPoint = createSVGElementNS("circle");
        endingPoint.getStyle().setProperty("pointerEvents", "visiblePainted");
        addStyleName(endingPoint, "end-p");
        setAttributeNS(endingPoint, "stroke-width", "2");
        setAttributeNS(endingPoint, "r", "2");
        setAttributeNS(endingPoint, "fill", "black");
        DOM.appendChild(g, curve);
        DOM.appendChild(g, startingPoint);
        DOM.appendChild(g, endingPoint);
        DOM.appendChild(predecessorArrow, g);
        setElement(predecessorArrow);

    }

    private void addStyleName(Element element, String style) {
        String curStyles = element.getAttribute("class");
        if (curStyles == null) {
            curStyles = "";
        }
        if (!curStyles.contains(style)) {
            curStyles += " " + style;
            element.setAttribute("class", curStyles);
        }
    }

    @Override
    public void setWidth(double width) {
        this.width = (int) width;
        setAttributeNS(getElement(), "width", (int) width);
    }

    @Override
    public void setHeight(double height) {
        this.height = (int) height;
        setAttributeNS(getElement(), "height", (int) height);
    }

    @Override
    public void draw(ArrowPositionData d) {
        int y1 = ((d.isFromTop()) ? d.getFromHeightCenter() : height
                - d.getFromHeightCenter());
        int y2 = ((d.isFromTop()) ? height - d.getToHeightCenter() : d
                .getToHeightCenter());

        StringBuilder s = new StringBuilder("M");
        s.append(" " + ((d.isFromLeft()) ? 0 : width)); // x1
        s.append(", " + y1); // y1
        s.append(" C");
        s.append(" " + d.getHalfWidth()); // cx1
        s.append(", " + y1); // cy1
        s.append(", " + d.getHalfWidth()); // cx2
        s.append(", " + y2); // cy2
        s.append(", " + ((d.isFromLeft()) ? width : 0)); // x2
        s.append(", " + y2); // y2

        setAttributeNS(curve, "d", s.toString());

        setAttributeNS(startingPoint, "cx", (d.isFromLeft()) ? 0 : (int) width);
        setAttributeNS(
                startingPoint,
                "cy",
                ((d.isFromTop()) ? d.getFromHeightCenter() : height
                        - d.getFromHeightCenter()));

        int endPointX = (!d.isFromLeft()) ? 0 : (int) width;
        int endPointY = ((!d.isFromTop()) ? d.getFromHeightCenter() : height
                - d.getFromHeightCenter());
        setAttributeNS(endingPoint, "cx", endPointX);
        setAttributeNS(endingPoint, "cy", endPointY);
    }

    private PointerDownHandler msPointerDownHandler = new PointerDownHandler() {

        @Override
        public void onPointerDown(PointerDownEvent event) {
            GWT.log("Starting point touched (pointerDown)!");
            // TODO
            event.stopPropagation();
        }
    };

    private TouchStartHandler touchStartHandler = new TouchStartHandler() {
        @Override
        public void onTouchStart(TouchStartEvent event) {
            GWT.log("Starting point touched (touchDown)!");
            // TODO
            event.stopPropagation();
        }
    };

    private boolean selectPredecessorMode = false;
    private boolean selectFollowerMode = false;
    private Element captureElement = null;

    private MouseDownHandler mouseDownHandler = new MouseDownHandler() {

        @Override
        public void onMouseDown(MouseDownEvent event) {
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                GWT.log("Starting point Clicked!");

                if (captureElement != null) {
                    GWT.log("Relasing captured point.");
                    Event.releaseCapture(captureElement);
                    // TODO: handle selection of new predecessor/following step.
                    captureElement = null;
                    selectPredecessorMode = false;
                    selectFollowerMode = false;
                    event.stopPropagation();
                    return;
                }

                Element element = event.getNativeEvent().getEventTarget()
                        .cast();
                if (element != null
                        && (element.equals(startingPoint) || element
                                .equals(endingPoint))) {
                    if (element.equals(startingPoint)) {
                        selectPredecessorMode = true;
                    } else if (element.equals(endingPoint)) {
                        selectFollowerMode = true;
                    }
                    GWT.log("Capturing clicked point.");
                    captureElement = element;
                    Event.setCapture(captureElement);
                    event.stopPropagation();

                    // TODO: enable MODE for new predecessor/following step
                    // selection.
                }
            }
        }
    };

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

    @Override
    public void setUpEventHandlers(boolean touchSupported,
            boolean msTouchSupported) {
        if (msTouchSupported) {
            // IE10 pointer events (ms-prefixed events)
            addDomHandler(msPointerDownHandler, PointerDownEvent.getType());
            // addDomHandler(msPointerUpHandler, PointerUpEvent.getType());
            // addDomHandler(msPointerMoveHandler, PointerMoveEvent.getType());
            // addDomHandler(msPointerCancelHandler,
            // PointerCancelEvent.getType());

        } else if (touchSupported) {
            // touch events replaces mouse events
            addDomHandler(touchStartHandler, TouchStartEvent.getType());
            // addDomHandler(touchEndHandler, TouchEndEvent.getType());
            // addDomHandler(touchMoveHandler, TouchMoveEvent.getType());
            // addDomHandler(touchCancelHandler, TouchCancelEvent.getType());

        } else {
            addHandler(mouseDownHandler, MouseDownEvent.getType());
            // addHandler(mouseDownHandler, MouseDownEvent.getType());
            // registerEventListener(getElement());
            // addDomHandler(mouseUpHandler, MouseUpEvent.getType());
            // addDomHandler(mouseMoveHandler, MouseMoveEvent.getType());
        }
        registerEventListener(startingPoint);
        registerEventListener(endingPoint);
    }

    private void registerEventListener(final Element element) {
        Event.sinkEvents(element, Event.MOUSEEVENTS | Event.TOUCHEVENTS);
    }

}
