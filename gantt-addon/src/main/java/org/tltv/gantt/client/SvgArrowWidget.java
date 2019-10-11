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

import static org.tltv.gantt.client.SvgUtil.setAttributeNS;
import static org.tltv.gantt.client.shared.GanttUtil.getTouchOrMousePageX;
import static org.tltv.gantt.client.shared.GanttUtil.getTouchOrMousePageY;

import org.tltv.gantt.client.shared.GanttUtil;

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
import com.vaadin.client.event.PointerCancelEvent;
import com.vaadin.client.event.PointerCancelHandler;
import com.vaadin.client.event.PointerDownEvent;
import com.vaadin.client.event.PointerDownHandler;
import com.vaadin.client.event.PointerMoveEvent;
import com.vaadin.client.event.PointerMoveHandler;
import com.vaadin.client.event.PointerUpEvent;
import com.vaadin.client.event.PointerUpHandler;
import com.vaadin.polymer.elemental.Function;

/** SVG implementation of {@link ArrowElement} arrow between two elements. */
public class SvgArrowWidget extends PolymerWidget implements ArrowElement {

    public static final String SELECTION_STYLE_NAME = "select-target-step";
    private static final int POINTER_TOUCH_DETECTION_INTERVAL = 100;

    protected boolean readOnly;

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

    protected PointerDownHandler pointerDownHandler = new PointerDownHandler() {

        @Override
        public void onPointerDown(PointerDownEvent event) {
            GWT.log("Starting point touched (pointerDown)!");
            if (currentPointerEventId == -1) {
                currentPointerEventId = event.getPointerId();
            } else {
                event.preventDefault();
                return; // multi-touch not supported
            }
            pointerDownPoint = new Point(getTouchOrMousePageX(event.getNativeEvent()),
                    getTouchOrMousePageY(event.getNativeEvent()));
            pendingPointerDownEvent = event.getNativeEvent();
            pointerTouchStartedTimer.schedule(POINTER_TOUCH_DETECTION_INTERVAL);
        }
    };

    protected PointerUpHandler pointerUpHandler = new PointerUpHandler() {

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

    protected PointerMoveHandler pointerMoveHandler = new PointerMoveHandler() {

        @Override
        public void onPointerMove(PointerMoveEvent event) {
            GWT.log("SvgArrowWidget.onPointerMove(PointerMoveEvent)");
            if (pointerDownPoint == null) {
                return;
            }
            // do nothing, if touch position has not changed
            if (!(pointerDownPoint.getX() == getTouchOrMousePageX(event.getNativeEvent())
                    && pointerDownPoint.getY() == getTouchOrMousePageY(event.getNativeEvent()))) {
                pointerTouchStartedTimer.cancel();
                handleMove(event.getNativeEvent());
            }
        }
    };

    protected PointerCancelHandler pointerCancelHandler = new PointerCancelHandler() {

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
    public void setUpEventHandlers(boolean touchSupported, boolean msTouchSupported) {
        this.touchSupported = touchSupported;
        this.msTouchSupported = msTouchSupported;
        if (msTouchSupported) {
            addDomHandler(pointerDownHandler, PointerDownEvent.getType());
            addDomHandler(pointerUpHandler, PointerUpEvent.getType());
        } else if (touchSupported) {
            addDomHandler(touchStartHandler, TouchStartEvent.getType());
        } else {
            addDomHandler(mouseDownHandler, MouseDownEvent.getType());
        }
        registerMouseDownAndTouchDownEventListener(getStartPoint());
        registerMouseDownAndTouchDownEventListener(getEndPoint());
    }

    public SvgArrowWidget() {
        super("svg-arrow");
    }

    @Override
    public void ready(Function<?, ?> f) {
        GanttUtil.whenReadyAndConnected(f, getElement());
    }

    public static native Element getShadowRootElement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.root?elem.root:elem;
    }-*/;

    public static native Element getInternalSvgELement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.$.svgElement;
    }-*/;

    public static native Element getInternalCusrveLineELement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.$.curveLine;
    }-*/;

    public static native Element getInternalStartPointElement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.$.startPoint;
    }-*/;

    public static native Element getInternalEndPointELement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.$.endPoint;
    }-*/;

    protected Element getSvg() {
        return getInternalSvgELement(getElement());
    }

    protected Element getCurve() {
        return getInternalCusrveLineELement(getElement());
    }

    protected Element getStartPoint() {
        return getInternalStartPointElement(getElement());
    }

    protected Element getEndPoint() {
        return getInternalEndPointELement(getElement());
    }

    @Override
    protected void onDetach() {
        if (isAttached()) {
            // Only call onDetach for attached widget. Otherwise GanttWidget's
            // content widget container may throw IllegalStateException on
            // detach when this widget is already detached explicitly by
            // GanttWidget.unregisterContentElement(SvgArrowWidget).
            super.onDetach();
        }
    }

    @Override
    public void setWidth(double width) {
        this.width = (int) width;
        setAttributes("width:" + getWidthWithMargin());
        // setAttributeNS(getSvg(), "width", getWidthWithMargin());
    }

    @Override
    public void setHeight(double height) {
        this.height = (int) height;
        setAttributes("height:" + getHeightWithMargin());
        // setAttributeNS(getSvg(), "height", getHeightWithMargin());
    }

    @Override
    public void setTop(int top) {
        getSvg().getStyle().setTop(top - getMargin(), Unit.PX);
    }

    @Override
    public void setLeft(int left) {
        getSvg().getStyle().setLeft(left - getMargin(), Unit.PX);
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
        getStartPoint().getStyle().setVisibility(Visibility.VISIBLE);
        getEndPoint().getStyle().setVisibility(Visibility.VISIBLE);
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

        Element startingPoint = getStartPoint();
        setAttributeNS(startingPoint, "cx", d.calcStartPointX() + getMargin());
        setAttributeNS(startingPoint, "cy", d.calcStartPointY() + getMargin());

        int endPointX = d.calcEndPointX();
        int endPointY = d.calcEndPointY();
        Element endingPoint = getEndPoint();
        setAttributeNS(endingPoint, "cx", endPointX + getMargin());
        setAttributeNS(endingPoint, "cy", endPointY + getMargin());
    }

    protected void internalDrawCurve(ArrowPositionData d) {
        int y1 = ((d.isFromTop()) ? d.getFromHeightCenter() : height - d.getFromHeightCenter());
        int y2 = ((d.isFromTop()) ? height - d.getToHeightCenter() : d.getToHeightCenter());

        StringBuilder s = new StringBuilder("M");
        s.append(" ").append((d.isFromLeft()) ? getMargin() : width + getMargin()); // x1
        s.append(", ").append(y1 + getMargin()); // y1
        s.append(" C");
        s.append(" ").append(d.getHalfWidth() + getHalfMargin()); // cx1
        s.append(", ").append(y1 + getMargin()); // cy1
        s.append(", ").append(d.getHalfWidth() + getHalfMargin()); // cx2
        s.append(", ").append(y2 + getMargin()); // cy2
        s.append(", ").append((d.isFromLeft()) ? width + getMargin() : getMargin()); // x2
        s.append(", ").append(y2 + getMargin()); // y2

        setAttributeNS(getCurve(), "d", s.toString());
    }

    protected void updateMovingData(Point forPoint) {
        if (selectPredecessorMode) {
            movingData = new ArrowPositionData(createSnapshotElement(forPoint), originalData.getTo());
        } else {
            movingData = new ArrowPositionData(originalData.getFrom(), createSnapshotElement(forPoint));
        }
    }

    protected Element createSnapshotElement(Point point) {
        int deltaX = (int) (point.getX() - capturePoint.getX());
        int deltaY = (int) (point.getY() - capturePoint.getY());
        int scrollDeltaY = getElement().getParentElement().getParentElement().getScrollTop() - capturePointScrollTop;
        int scrollDeltaX = getElement().getParentElement().getParentElement().getScrollLeft() - capturePointScrollLeft;

        int originalTopPoint = selectPredecessorMode ? originalData.calcStartPointY() : originalData.calcEndPointY();
        int originalLeftPoint = selectPredecessorMode ? originalData.calcStartPointX() : originalData.calcEndPointX();

        movePointElement.getStyle().setVisibility(Visibility.HIDDEN);
        movePointElement.getStyle().setPosition(Position.ABSOLUTE);
        movePointElement.getStyle()
                .setTop(Math.max(0, originalData.getTop() + originalTopPoint + deltaY + scrollDeltaY), Unit.PX);
        movePointElement.getStyle()
                .setLeft(Math.max(0, originalData.getLeft() + originalLeftPoint + deltaX + scrollDeltaX), Unit.PX);
        movePointElement.getStyle().setWidth(2, Unit.PX);
        movePointElement.getStyle().setHeight(2, Unit.PX);

        return movePointElement;
    }

    protected void startMoving(NativeEvent event, Element element) {
        Element startingPoint = getStartPoint();
        Element endingPoint = getEndPoint();
        if (element.equals(startingPoint)) {
            selectPredecessorMode = true;
            startingPoint.getStyle().setVisibility(Visibility.HIDDEN);
        } else if (element.equals(endingPoint)) {
            selectFollowerMode = true;
            endingPoint.getStyle().setVisibility(Visibility.HIDDEN);
        }
        capturePointScrollTop = getElement().getParentElement().getParentElement().getScrollTop();
        capturePointScrollLeft = getElement().getParentElement().getParentElement().getScrollLeft();
        getShadowRootElement(getParent().getElement()).appendChild(movePointElement);
        getElement().getParentElement().addClassName(SELECTION_STYLE_NAME);
        GWT.log("Capturing clicked point.");
        captureElement = getElement();
        Event.setCapture(getElement());
        event.stopPropagation();

        // enable MODE for new predecessor/following step
        // selection.
        addMoveHandler();

        capturePoint = new Point(getTouchOrMousePageX(event), getTouchOrMousePageY(event));
        originalWidth = width;
        originalHeight = height;
    }

    protected void addMoveHandler() {
        if (msTouchSupported) {
            moveRegisteration = addDomHandler(pointerMoveHandler, PointerMoveEvent.getType());
            touchCancelRegisteration = addDomHandler(pointerCancelHandler, PointerCancelEvent.getType());
        } else if (touchSupported) {
            moveRegisteration = addDomHandler(touchMoveHandler, TouchMoveEvent.getType());
            touchCancelRegisteration = addDomHandler(touchCancelHandler, TouchCancelEvent.getType());
        } else {
            moveRegisteration = addDomHandler(mouseMoveHandler, MouseMoveEvent.getType());
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

        if (forceReset || (handler != null && !handler.onArrowChanged(selectPredecessorMode, event))) {
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

        Element element = GanttUtil.getEventTarget(event);
        if (element != null && (element.equals(getStartPoint()) || element.equals(getEndPoint()))) {
            startMoving(event, element);
        }
    }

    protected void resetArrow() {
        setWidth(originalData.getWidth());
        setHeight(originalData.getHeight());
        setTop((int) originalData.getTop());
        setLeft((int) originalData.getLeft());
        draw(originalData);
        getStartPoint().getStyle().setVisibility(Visibility.VISIBLE);
        getEndPoint().getStyle().setVisibility(Visibility.VISIBLE);
    }

    protected void handleMove(NativeEvent event) {
        Point movePoint = new Point(getTouchOrMousePageX(event), getTouchOrMousePageY(event));

        updateMovingData(movePoint);

        setWidth(movingData.getWidth());
        setHeight(movingData.getHeight());
        setTop((int) movingData.getTop());
        setLeft((int) movingData.getLeft());

        getStartPoint().getStyle().setVisibility(Visibility.HIDDEN);
        getEndPoint().getStyle().setVisibility(Visibility.HIDDEN);

        internalDrawCurve(movingData);
        event.stopPropagation();
    }

    boolean touchSupported;
    boolean msTouchSupported;

    protected void registerMouseDownAndTouchDownEventListener(final Element element) {
        Event.sinkEvents(element, Event.ONMOUSEDOWN | Event.ONTOUCHSTART);
    }

    @Override
    public void whenReady(Function<?, ?> op) {
        ready(op);
    }

}
