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

import static org.tltv.gantt.client.SvgUtil.createSVGElementNS;
import static org.tltv.gantt.client.SvgUtil.setAttributeNS;
import static org.tltv.gantt.client.shared.GanttUtil.getTouchOrMouseClientX;
import static org.tltv.gantt.client.shared.GanttUtil.getTouchOrMouseClientY;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.event.PointerCancelEvent;
import com.vaadin.client.event.PointerCancelHandler;
import com.vaadin.client.event.PointerDownEvent;
import com.vaadin.client.event.PointerDownHandler;
import com.vaadin.client.event.PointerMoveEvent;
import com.vaadin.client.event.PointerMoveHandler;
import com.vaadin.client.event.PointerUpEvent;
import com.vaadin.client.event.PointerUpHandler;

/** SVG implementation of {@link ArrowElement} arrow between two elements. */
public class SvgArrowWidget extends Widget implements ArrowElement {

    public static final String SELECTION_STYLE_NAME = "select-target-step";
    private static final int POINTER_TOUCH_DETECTION_INTERVAL = 100;

    protected boolean readOnly;

    protected Element curve;
    protected Element startingPoint;
    protected Element endingPoint;

    protected Element movePointElement = DOM.createDiv();

    protected int width = 0;
    protected int height = 0;

    protected int margin = 8; // px

    protected ArrowPositionData originalData;
    protected int originalWidth;
    protected int originalHeight;

    protected HandlerRegistration moveRegisteration;
    protected HandlerRegistration touchCancelRegisteration;
    protected ArrowPositionData movingData;
    protected Point capturePoint;
    protected int capturePointScrollTop = 0;
    protected int capturePointScrollLeft = 0;

    protected ArrowChangeHandler handler;

    protected boolean selectPredecessorMode = false;
    protected boolean selectFollowerMode = false;
    protected Element captureElement = null;

    protected int currentPointerEventId = -1;
    protected NativeEvent pendingPointerDownEvent;
    protected Point pointerDownPoint;

    protected Timer pointerTouchStartedTimer = new Timer() {

        @Override
        public void run() {
            handleDownEvent(pendingPointerDownEvent);
            pendingPointerDownEvent = null;
        }
    };

    protected PointerDownHandler msPointerDownHandler = new PointerDownHandler() {

        @Override
        public void onPointerDown(PointerDownEvent event) {
            GWT.log("Starting point touched (pointerDown)!");
            if (currentPointerEventId == -1) {
                currentPointerEventId = event.getPointerId();
            } else {
                event.preventDefault();
                return; // multi-touch not supported
            }
            pointerDownPoint = new Point(
                    getTouchOrMouseClientX(event.getNativeEvent()),
                    getTouchOrMouseClientY(event.getNativeEvent()));
            pendingPointerDownEvent = event.getNativeEvent();
            pointerTouchStartedTimer.schedule(POINTER_TOUCH_DETECTION_INTERVAL);
        }
    };

    protected PointerUpHandler msPointerUpHandler = new PointerUpHandler() {

        @Override
        public void onPointerUp(PointerUpEvent event) {
            pointerTouchStartedTimer.cancel();
            currentPointerEventId = -1;
            event.preventDefault();
        }
    };

    protected TouchStartHandler touchStartHandler = new TouchStartHandler() {
        @Override
        public void onTouchStart(TouchStartEvent event) {
            GWT.log("Starting point touched (touchDown)!");

            handleDownEvent(event.getNativeEvent());
        }
    };

    protected MouseDownHandler mouseDownHandler = new MouseDownHandler() {

        @Override
        public void onMouseDown(MouseDownEvent event) {
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                GWT.log("Starting point Clicked!");

                handleDownEvent(event.getNativeEvent());
            }
        }

    };

    protected MouseMoveHandler mouseMoveHandler = new MouseMoveHandler() {

        @Override
        public void onMouseMove(MouseMoveEvent event) {
            GWT.log("SvgArrowWidget.onMouseMove(MouseMoveEvent)");

            handleMove(event.getNativeEvent());
        }
    };

    protected PointerMoveHandler msPointerMoveHandler = new PointerMoveHandler() {

        @Override
        public void onPointerMove(PointerMoveEvent event) {
            GWT.log("SvgArrowWidget.onPointerMove(PointerMoveEvent)");
            if (pointerDownPoint == null) {
                return;
            }
            // do nothing, if touch position has not changed
            if (!(pointerDownPoint.getX() == getTouchOrMouseClientX(event
                    .getNativeEvent()) && pointerDownPoint.getY() == getTouchOrMouseClientY(event
                    .getNativeEvent()))) {
                pointerTouchStartedTimer.cancel();
                handleMove(event.getNativeEvent());
            }
        }
    };

    protected PointerCancelHandler msPointerCancelHandler = new PointerCancelHandler() {

        @Override
        public void onPointerCancel(PointerCancelEvent event) {
            GWT.log("SvgArrowWidget.onPointerCancel(PointerCancelEvent)");
            pointerTouchStartedTimer.cancel();
            cancelMove(true, null);
        }
    };

    protected TouchMoveHandler touchMoveHandler = new TouchMoveHandler() {
        @Override
        public void onTouchMove(TouchMoveEvent event) {
            GWT.log("SvgArrowWidget.onTouchMove(TouchMoveEvent)");
            if (event.getChangedTouches().length() == 1) {
                handleMove(event.getNativeEvent());
                event.preventDefault();
            }
        }
    };

    protected TouchCancelHandler touchCancelHandler = new TouchCancelHandler() {

        @Override
        public void onTouchCancel(TouchCancelEvent event) {
            GWT.log("SvgArrowWidget.onTouchCancel(TouchCancelEvent)");
            cancelMove(true, null);
        }
    };

    @Override
    public void setUpEventHandlers(boolean touchSupported,
            boolean msTouchSupported) {
        this.touchSupported = touchSupported;
        this.msTouchSupported = msTouchSupported;
        if (msTouchSupported) {
            addDomHandler(msPointerDownHandler, PointerDownEvent.getType());
            addDomHandler(msPointerUpHandler, PointerUpEvent.getType());
        } else if (touchSupported) {
            addDomHandler(touchStartHandler, TouchStartEvent.getType());
        } else {
            addHandler(mouseDownHandler, MouseDownEvent.getType());
        }
        registerMouseDownAndTouchDownEventListener(startingPoint);
        registerMouseDownAndTouchDownEventListener(endingPoint);
    }

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
        setAttributeNS(startingPoint, "r", "7");
        setAttributeNS(startingPoint, "fill", "black");
        endingPoint = createSVGElementNS("circle");
        endingPoint.getStyle().setProperty("pointerEvents", "visiblePainted");
        addStyleName(endingPoint, "end-p");
        setAttributeNS(endingPoint, "stroke-width", "2");
        setAttributeNS(endingPoint, "r", "5");
        setAttributeNS(endingPoint, "fill", "black");
        DOM.appendChild(g, curve);
        DOM.appendChild(g, startingPoint);
        DOM.appendChild(g, endingPoint);
        DOM.appendChild(predecessorArrow, g);
        setElement(predecessorArrow);

    }

    @Override
    public void setWidth(double width) {
        this.width = (int) width;
        setAttributeNS(getElement(), "width", getWidthWithMargin());
    }

    @Override
    public void setHeight(double height) {
        this.height = (int) height;
        setAttributeNS(getElement(), "height", getHeightWithMargin());
    }

    @Override
    public void setTop(int top) {
        getElement().getStyle().setTop(top - getMargin(), Unit.PX);
    }

    @Override
    public void setLeft(int left) {
        getElement().getStyle().setLeft(left - getMargin(), Unit.PX);
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public void draw(ArrowPositionData d) {
        originalData = d;
        startingPoint.getStyle().setVisibility(Visibility.VISIBLE);
        endingPoint.getStyle().setVisibility(Visibility.VISIBLE);
        internalDraw(d);
    }

    public int getMargin() {
        return margin;
    }

    @Override
    public void setArrowChangeHandler(ArrowChangeHandler handler) {
        this.handler = handler;
    }

    protected void addStyleName(Element element, String style) {
        String curStyles = element.getAttribute("class");
        if (curStyles == null) {
            curStyles = "";
        }
        if (!curStyles.contains(style)) {
            curStyles += " " + style;
            element.setAttribute("class", curStyles);
        }
    }

    protected int getWidthWithMargin() {
        return width + (2 * getMargin());
    }

    protected int getHeightWithMargin() {
        return height + (2 * getMargin());
    }

    protected int getHalfMargin() {
        return getMargin() / 2;
    }

    protected void internalDraw(ArrowPositionData d) {
        internalDrawCurve(d);

        setAttributeNS(startingPoint, "cx", d.calcStartPointX() + getMargin());
        setAttributeNS(startingPoint, "cy", d.calcStartPointY() + getMargin());

        int endPointX = d.calcEndPointX();
        int endPointY = d.calcEndPointY();
        setAttributeNS(endingPoint, "cx", endPointX + getMargin());
        setAttributeNS(endingPoint, "cy", endPointY + getMargin());
    }

    protected void internalDrawCurve(ArrowPositionData d) {
        int y1 = ((d.isFromTop()) ? d.getFromHeightCenter() : height
                - d.getFromHeightCenter());
        int y2 = ((d.isFromTop()) ? height - d.getToHeightCenter() : d
                .getToHeightCenter());

        StringBuilder s = new StringBuilder("M");
        s.append(" ").append(
                (d.isFromLeft()) ? getMargin() : width + getMargin()); // x1
        s.append(", ").append(y1 + getMargin()); // y1
        s.append(" C");
        s.append(" ").append(d.getHalfWidth() + getHalfMargin()); // cx1
        s.append(", ").append(y1 + getMargin()); // cy1
        s.append(", ").append(d.getHalfWidth() + getHalfMargin()); // cx2
        s.append(", ").append(y2 + getMargin()); // cy2
        s.append(", ").append(
                (d.isFromLeft()) ? width + getMargin() : getMargin()); // x2
        s.append(", ").append(y2 + getMargin()); // y2

        setAttributeNS(curve, "d", s.toString());
    }

    protected void updateMovingData(Point forPoint) {
        if (selectPredecessorMode) {
            movingData = new ArrowPositionData(createSnapshotElement(forPoint),
                    originalData.getTo());
        } else {
            movingData = new ArrowPositionData(originalData.getFrom(),
                    createSnapshotElement(forPoint));
        }
    }

    protected Element createSnapshotElement(Point point) {
        int deltaX = (int) (point.getX() - capturePoint.getX());
        int deltaY = (int) (point.getY() - capturePoint.getY());
        int scrollDeltaY = getElement().getParentElement().getParentElement()
                .getScrollTop()
                - capturePointScrollTop;
        int scrollDeltaX = getElement().getParentElement().getParentElement()
                .getScrollLeft()
                - capturePointScrollLeft;

        int originalTopPoint = selectPredecessorMode ? originalData
                .calcStartPointY() : originalData.calcEndPointY();
        int originalLeftPoint = selectPredecessorMode ? originalData
                .calcStartPointX() : originalData.calcEndPointX();

        movePointElement.getStyle().setVisibility(Visibility.HIDDEN);
        movePointElement.getStyle().setPosition(Position.ABSOLUTE);
        movePointElement.getStyle().setTop(
                Math.max(0, originalData.getTop() + originalTopPoint + deltaY
                        + scrollDeltaY), Unit.PX);
        movePointElement.getStyle().setLeft(
                Math.max(0, originalData.getLeft() + originalLeftPoint + deltaX
                        + scrollDeltaX), Unit.PX);
        movePointElement.getStyle().setWidth(2, Unit.PX);
        movePointElement.getStyle().setHeight(2, Unit.PX);

        return movePointElement;
    }

    protected void startMoving(NativeEvent event, Element element) {
        if (element.equals(startingPoint)) {
            selectPredecessorMode = true;
            startingPoint.getStyle().setVisibility(Visibility.HIDDEN);
        } else if (element.equals(endingPoint)) {
            selectFollowerMode = true;
            endingPoint.getStyle().setVisibility(Visibility.HIDDEN);
        }
        capturePointScrollTop = getElement().getParentElement()
                .getParentElement().getScrollTop();
        capturePointScrollLeft = getElement().getParentElement()
                .getParentElement().getScrollLeft();
        getParent().getElement().appendChild(movePointElement);
        getElement().getParentElement().addClassName(SELECTION_STYLE_NAME);
        GWT.log("Capturing clicked point.");
        captureElement = getElement();
        Event.setCapture(getElement());
        event.stopPropagation();

        // enable MODE for new predecessor/following step
        // selection.
        addMoveHandler();

        capturePoint = new Point(getTouchOrMouseClientX(event),
                getTouchOrMouseClientY(event));
        originalWidth = width;
        originalHeight = height;
    }

    protected void addMoveHandler() {
        if (msTouchSupported) {
            moveRegisteration = addDomHandler(msPointerMoveHandler,
                    PointerMoveEvent.getType());
            touchCancelRegisteration = addDomHandler(msPointerCancelHandler,
                    PointerCancelEvent.getType());
        } else if (touchSupported) {
            moveRegisteration = addDomHandler(touchMoveHandler,
                    TouchMoveEvent.getType());
            touchCancelRegisteration = addDomHandler(touchCancelHandler,
                    TouchCancelEvent.getType());
        } else {
            moveRegisteration = addDomHandler(mouseMoveHandler,
                    MouseMoveEvent.getType());
        }
    }

    protected void stopMoving(NativeEvent event) {
        cancelMove(false, event);
    }

    protected void cancelMove(boolean forceReset, NativeEvent event) {
        GWT.log("Relasing captured point.");
        if (captureElement != null) {
            Event.releaseCapture(captureElement);
        }
        movePointElement.removeFromParent();
        getElement().getParentElement().removeClassName(SELECTION_STYLE_NAME);
        moveRegisteration.removeHandler();
        if (touchCancelRegisteration != null) {
            touchCancelRegisteration.removeHandler();
        }
        captureElement = null;

        if (forceReset
                || (handler != null && !handler.onArrowChanged(
                        selectPredecessorMode, event))) {
            // cancel
            resetArrow();
        }
        selectPredecessorMode = false;
        selectFollowerMode = false;
        currentPointerEventId = -1;
        pendingPointerDownEvent = null;
    }

    /** MouseTtouch down event handler. */
    protected void handleDownEvent(NativeEvent event) {
        if (isReadOnly()) {
            return;
        }
        if (captureElement != null) {
            stopMoving(event);

            return;
        }

        Element element = event.getEventTarget().cast();
        if (element != null
                && (element.equals(startingPoint) || element
                        .equals(endingPoint))) {
            startMoving(event, element);
        }
    }

    protected void resetArrow() {
        setWidth(originalData.getWidth());
        setHeight(originalData.getHeight());
        setTop((int) originalData.getTop());
        setLeft((int) originalData.getLeft());
        draw(originalData);
        startingPoint.getStyle().setVisibility(Visibility.VISIBLE);
        endingPoint.getStyle().setVisibility(Visibility.VISIBLE);
    }

    protected void handleMove(NativeEvent event) {
        Point movePoint = new Point(getTouchOrMouseClientX(event),
                getTouchOrMouseClientY(event));

        updateMovingData(movePoint);

        setWidth(movingData.getWidth());
        setHeight(movingData.getHeight());
        setTop((int) movingData.getTop());
        setLeft((int) movingData.getLeft());

        startingPoint.getStyle().setVisibility(Visibility.HIDDEN);
        endingPoint.getStyle().setVisibility(Visibility.HIDDEN);

        internalDrawCurve(movingData);
        event.stopPropagation();
    }

    boolean touchSupported;
    boolean msTouchSupported;

    protected void registerMouseDownAndTouchDownEventListener(
            final Element element) {
        Event.sinkEvents(element, Event.ONMOUSEDOWN | Event.ONTOUCHSTART);
    }

}
