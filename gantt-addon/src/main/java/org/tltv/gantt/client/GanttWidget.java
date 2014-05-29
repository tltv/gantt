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

import java.util.Collection;
import java.util.Date;

import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractNativeScrollbar;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.event.PointerCancelEvent;
import com.vaadin.client.event.PointerCancelHandler;
import com.vaadin.client.event.PointerDownEvent;
import com.vaadin.client.event.PointerDownHandler;
import com.vaadin.client.event.PointerMoveEvent;
import com.vaadin.client.event.PointerMoveHandler;
import com.vaadin.client.event.PointerUpEvent;
import com.vaadin.client.event.PointerUpHandler;

/**
 * GWT Gantt chart widget. Includes {@link TimelineWidget} to show timeline, and
 * below the timeline, shows content of the Gantt. Content is freely (position:
 * absolute) positioned steps aligned vertically on top of each others.
 * <p>
 * These steps can be moved and resized freely in the space available, limited
 * only by the timeline's borders.
 * <p>
 * All events are handled via {@link GanttRpc}.
 * <p>
 * Timeline's localization is handled via {@link LocaleDataProvider}.
 * <p>
 * Here are few steps that need to be notified when taking this widget in use. <br/>
 * First of all, after constructing this widget, you need to initialize it by
 * {@link #initWidget(GanttRpc, LocaleDataProvider)} method. But before doing
 * that, if client uses IE, make sure to call
 * {@link #setBrowserInfo(boolean, boolean, boolean)} to let this widget know
 * that. And if client supports touch events, let this widget know that by
 * calling {@link #setTouchSupported(boolean)} method before initWidget.
 * <p>
 * Sample code snippet:
 * 
 * <code>
 * <pre>
 * GanttWidget widget = new GanttWidget();    
 * widget.setIEInfo(isIe(), isIe8(), isIe9());
 * widget.setTouchSupportted(isTouchDevice());
 * widget.initWidget(ganttRpc, localeDataProvider);
 * </pre>
 * </code>
 * <p>
 * After initializing, widget is ready to go. But to let this widget know when
 * it should re-calculate content widths/heights, call either
 * {@link #notifyHeightChanged(int)} or {@link #notifyWidthChanged(int)} methods
 * to do that. This needs to be done explicitly for example when widget's width
 * is 100%, and the parent's width changes due to browser window's resize event.
 * 
 * @author Tltv
 * 
 */
public class GanttWidget extends Widget implements HasEnabled {

    private static final int RESIZE_WIDTH = 10;
    private static final int BAR_MIN_WIDTH = RESIZE_WIDTH;
    private static final int CLICK_INTERVAL = 250;
    private static final int POINTER_TOUCH_DETECTION_INTERVAL = 100;

    private static final String STYLE_GANTT = "gantt";
    private static final String STYLE_GANTT_CONTAINER = "gantt-container";
    private static final String STYLE_GANTT_CONTENT = "gantt-content";
    private static final String STYLE_BAR = "bar";
    private static final String STYLE_BAR_LABEL = "bar-label";
    private static final String STYLE_MOVING = "moving";
    private static final String STYLE_RESIZING = "resizing";
    private static final String STYLE_INVALID = "invalid";
    private static final String STYLE_MOVE_ELEMENT = "mv-el";

    private boolean enabled = true;
    private boolean touchSupported = false;
    private boolean movableSteps;
    private boolean resizableSteps;

    private GanttRpc ganttRpc;
    private LocaleDataProvider localeDataProvider;

    private String locale;
    private Resolution resolution;
    private int firstDayOfRange;
    private int firstHourOfRange;
    private long startDate;
    private long endDate;
    private int minWidth;
    protected double contentHeight;
    protected boolean wasTimelineOverflowingHorizontally = false;
    protected boolean wasContentOverflowingVertically = false;

    protected TimelineWidget timeline;
    protected DivElement container;
    protected DivElement content;
    protected DivElement scrollbarSpacer;

    protected boolean clickOnNextMouseUp = true;
    protected Point movePoint;
    protected boolean resizing = false;
    protected boolean resizingFromLeft = false;
    protected boolean resizingInProgress = false;
    protected boolean moveInProgress = false;
    protected int currentPointerEventId;

    // following variables are set during mouse down event over a bar element,
    // or over it's children element.
    protected Point capturePoint;
    protected String capturePointLeftPercentage;
    protected String capturePointWidthPercentage;
    protected double capturePointLeftPx;
    protected double capturePointWidthPx;
    protected String capturePointBgColor;
    protected Element targetBarElement;

    // this variable is used to memorize the Y origin to scroll the container
    protected int containerScrollStartPosY = -1;

    // additional element that appears when moving or resizing
    protected DivElement moveElement = DivElement.as(DOM.createDiv());

    // click is actually detected by 'mouseup'/'mousedown' events, not with
    // 'onclick'. click event is not used. disallowClickTimer helps to detect
    // click during a short interval. And disallowing it after a short interval.
    private Timer disallowClickTimer = new Timer() {

        @Override
        public void run() {
            disableClickOnNextMouseUp();
            if (isMovableSteps() && !isResizingInProgress()) {
                addMovingStyles(targetBarElement);
            }
        }
    };

    private NativeEvent pendingPointerDownEvent;
    private Timer pointerTouchStartedTimer = new Timer() {

        @Override
        public void run() {
            GanttWidget.this.onTouchOrMouseDown(pendingPointerDownEvent);
            pendingPointerDownEvent = null;
        }
    };

    private ScrollHandler scrollHandler = new ScrollHandler() {

        @Override
        public void onScroll(ScrollEvent event) {
            Element element = event.getNativeEvent().getEventTarget().cast();
            if (element != container) {
                return;
            }
            timeline.setScrollLeft(container.getScrollLeft());
        }
    };

    private MouseDownHandler mouseDownHandler = new MouseDownHandler() {

        @Override
        public void onMouseDown(MouseDownEvent event) {
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                GanttWidget.this.onTouchOrMouseDown(event.getNativeEvent());
            }
        }
    };

    private MouseUpHandler mouseUpHandler = new MouseUpHandler() {

        @Override
        public void onMouseUp(MouseUpEvent event) {
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                GanttWidget.this.onTouchOrMouseUp(event.getNativeEvent());
            }
        }
    };

    private MouseMoveHandler mouseMoveHandler = new MouseMoveHandler() {

        @Override
        public void onMouseMove(MouseMoveEvent event) {
            GanttWidget.this.onTouchOrMouseMove(event.getNativeEvent());
            event.preventDefault();
        }
    };

    private PointerDownHandler msPointerDownHandler = new PointerDownHandler() {

        @Override
        public void onPointerDown(PointerDownEvent event) {
            if (currentPointerEventId == -1) {
                currentPointerEventId = event.getPointerId();
            } else {
                event.preventDefault();
                return; // multi-touch not supported
            }
            pendingPointerDownEvent = event.getNativeEvent();
            capturePoint = new Point(
                    getTouchOrMouseClientX(event.getNativeEvent()),
                    getTouchOrMouseClientY(event.getNativeEvent()));
            pointerTouchStartedTimer.schedule(POINTER_TOUCH_DETECTION_INTERVAL);
            event.preventDefault();
        }
    };

    private PointerUpHandler msPointerUpHandler = new PointerUpHandler() {

        @Override
        public void onPointerUp(PointerUpEvent event) {
            currentPointerEventId = -1;
            pointerTouchStartedTimer.cancel();
            pendingPointerDownEvent = null;
            GanttWidget.this.onTouchOrMouseUp(event.getNativeEvent());
            event.preventDefault();
        }
    };

    private PointerMoveHandler msPointerMoveHandler = new PointerMoveHandler() {

        @Override
        public void onPointerMove(PointerMoveEvent event) {
            if (capturePoint == null) {
                return;
            }
            movePoint = new Point(
                    getTouchOrMouseClientX(event.getNativeEvent()),
                    getTouchOrMouseClientY(event.getNativeEvent()));

            // do nothing, if touch position has not changed
            if (!(capturePoint.getX() == movePoint.getX() && capturePoint
                    .getY() == movePoint.getY())) {
                GanttWidget.this.onTouchOrMouseMove(event.getNativeEvent());
            }
        }
    };

    private PointerCancelHandler msPointerCancelHandler = new PointerCancelHandler() {

        @Override
        public void onPointerCancel(PointerCancelEvent event) {
            currentPointerEventId = -1;
            pointerTouchStartedTimer.cancel();
            pendingPointerDownEvent = null;
            onCancelTouch(event.getNativeEvent());
        }
    };

    private TouchStartHandler touchStartHandler = new TouchStartHandler() {
        @Override
        public void onTouchStart(TouchStartEvent event) {
            if (event.getTargetTouches().length() == 1) {
                JavaScriptObject target = event.getNativeEvent()
                        .getEventTarget().cast();
                containerScrollStartPosY = -1;

                if (target == container || target == content
                        || (!isMovableSteps())) {
                    if (isContentOverflowingVertically()) {
                        // store position for 'manual' vertical scrolling
                        containerScrollStartPosY = container.getScrollTop()
                                + event.getTouches().get(0).getPageY();
                        event.preventDefault();
                        return;
                    }
                }
                GanttWidget.this.onTouchOrMouseDown(event.getNativeEvent());
            }
            event.preventDefault();
        }
    };

    private TouchEndHandler touchEndHandler = new TouchEndHandler() {

        @Override
        public void onTouchEnd(TouchEndEvent event) {
            containerScrollStartPosY = -1;
            GanttWidget.this.onTouchOrMouseUp(event.getNativeEvent());
            event.preventDefault();
        }
    };

    private TouchMoveHandler touchMoveHandler = new TouchMoveHandler() {
        @Override
        public void onTouchMove(TouchMoveEvent event) {
            if (event.getChangedTouches().length() == 1) {
                // did we intend to scroll the container?
                if (containerScrollStartPosY != -1) {
                    // apply 'manual' vertical scrolling
                    container.setScrollTop(containerScrollStartPosY
                            - event.getChangedTouches().get(0).getPageY());
                    event.preventDefault();
                    return;
                }
                if (GanttWidget.this.onTouchOrMouseMove(event.getNativeEvent())) {
                    event.preventDefault();
                }
            }
        }
    };

    private TouchCancelHandler touchCancelHandler = new TouchCancelHandler() {

        @Override
        public void onTouchCancel(TouchCancelEvent event) {
            containerScrollStartPosY = -1;
            onCancelTouch(event.getNativeEvent());
        }
    };

    public GanttWidget() {

        setElement(DivElement.as(DOM.createDiv()));
        setStyleName(STYLE_GANTT);

        moveElement.setClassName(STYLE_MOVE_ELEMENT);
        // not visible by default
        moveElement.getStyle().setDisplay(Display.NONE);

        timeline = GWT.create(TimelineWidget.class);

        container = DivElement.as(DOM.createDiv());
        container.setClassName(STYLE_GANTT_CONTAINER);

        content = DivElement.as(DOM.createDiv());
        content.setClassName(STYLE_GANTT_CONTENT);
        container.appendChild(content);

        scrollbarSpacer = DivElement.as(DOM.createDiv());
        scrollbarSpacer.getStyle().setHeight(
                AbstractNativeScrollbar.getNativeScrollbarHeight(), Unit.PX);
        scrollbarSpacer.getStyle().setDisplay(Display.NONE);

        getElement().appendChild(timeline.getElement());
        getElement().appendChild(container);
        getElement().appendChild(scrollbarSpacer);
    }

    public void initWidget(GanttRpc ganttRpc,
            LocaleDataProvider localeDataProvider) {
        setRpc(ganttRpc);
        setLocaleDataProvider(localeDataProvider);
        initListeners();
    }

    /**
     * Update Gantt chart's timeline and content with the given steps.
     * 
     * @param steps
     */
    public void update(Collection<Step> steps) {
        clearContent();

        if (startDate < 0 || endDate < 0 || startDate >= endDate) {
            GWT.log("Invalid start and end dates. Gantt chart can't be rendered. Start: "
                    + startDate + ", End: " + endDate);
            return;
        }
        contentHeight = 0; // reset content height
        for (Step step : steps) {
            contentHeight = addStep(contentHeight, step);
        }
        content.getStyle().setHeight(contentHeight, Unit.PX);

        long offset = getLocaleDataProvider().getTimeZoneOffset();
        GWT.log("GanttWidget's active TimeZone offset: " + offset);

        // tell timeline to notice vertical scrollbar before updating it
        timeline.setNoticeVerticalScrollbarWidth(isContentOverflowingVertically());
        timeline.update(resolution, startDate + offset, endDate + offset,
                firstDayOfRange, firstHourOfRange, localeDataProvider);
        setContentMinWidth(timeline.getMinWidth());
        updateContentWidth();

        updateStepWidths(steps);

        wasTimelineOverflowingHorizontally = timeline
                .isTimelineOverflowingHorizontally();
    }

    private void updateContentWidth() {
        if (timeline.isAlwaysCalculatePixelWidths()) {
            content.getStyle().setWidth(timeline.getResolutionWidth(), Unit.PX);
        }
    }

    /**
     * Set minimum width for the content element.
     * 
     * @param minWidth
     *            Minimum width in pixels.
     */
    public void setContentMinWidth(int minWidth) {
        this.minWidth = minWidth;
        content.getStyle().setProperty("minWidth", this.minWidth + "px");
    }

    /**
     * Return minimal width of the content.
     * 
     * @return Minimum width in pixels.
     */
    public int getMinWidth() {
        return minWidth;
    }

    /**
     * Get current currently active timeline {@link Resolution}.
     * 
     * @return resolution enum
     */
    public Resolution getResolution() {
        return resolution;
    }

    /**
     * Set Gantt's timeline resolution.
     * 
     * @param resolution
     *            New timeline resolution.
     */
    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    /**
     * Get timeline's start date. Date value should follow specification in
     * {@link Date#getTime()}.
     * 
     * @return Start date in milliseconds.
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Set timeline's start date. Date value should follow specification in
     * {@link Date#getTime()}.
     * 
     * @param startDate
     *            New start date in milliseconds.
     */
    public void setStartDate(Long startDate) {
        this.startDate = (startDate != null) ? startDate : 0;
    }

    /**
     * Get timeline's end date. Date value should follow specification in
     * {@link Date#getTime()}.
     * 
     * @return End date in milliseconds.
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Set timeline's end date. Date value should follow specification in
     * {@link Date#getTime()}.
     * 
     * @param endDate
     *            New end date in milliseconds.
     */
    public void setEndDate(Long endDate) {
        this.endDate = (endDate != null) ? endDate : 0;
    }

    /**
     * Set first day of timeline's date range. Value is between 1-7, where 1 is
     * SUNDAY.
     * 
     * @param firstDayOfRange
     *            Value between 1-7.
     */
    public void setFirstDayOfRange(int firstDayOfRange) {
        this.firstDayOfRange = firstDayOfRange;
    }

    public void setFirstHourOfRange(int firstHourOfRange) {
        this.firstHourOfRange = firstHourOfRange;
    }

    /**
     * Notify Gantt widget that height has changed. Delegates necessary changes
     * to child elements.
     * 
     * @param height
     *            New height in pixels
     */
    public void notifyHeightChanged(int height) {
        if (container != null && timeline != null) {
            container.getStyle().setHeight(
                    height - getTimelineHeight()
                            - getHorizontalScrollbarSpacerHeight(), Unit.PX);

            boolean overflow = isContentOverflowingVertically();
            if (wasContentOverflowingVertically != overflow) {
                wasContentOverflowingVertically = overflow;
                timeline.setNoticeVerticalScrollbarWidth(overflow);
                // width has changed due to vertical scrollbar
                // appearing/disappearing
                internalHandleWidthChange();
            }
        }
    }

    /**
     * Get Timeline widget height.
     * 
     * @return
     */
    public int getTimelineHeight() {
        if (timeline != null) {
            return timeline.getElement().getClientHeight();
        }
        return 0;
    }

    /**
     * Notify Gantt widget that width has changed. Delegates necessary changes
     * to child elements.
     * 
     * @param width
     *            New width in pixels
     */
    public void notifyWidthChanged(int width) {
        if (timeline != null) {

            boolean overflow = timeline.checkTimelineOverflowingHorizontally();
            if (timeline.isAlwaysCalculatePixelWidths()
                    || wasTimelineOverflowingHorizontally != overflow) {
                // scrollbar has just appeared/disappeared
                wasTimelineOverflowingHorizontally = overflow;
                if (!wasTimelineOverflowingHorizontally) {
                    timeline.setScrollLeft(0);
                }
                internalHandleWidthChange();
            }
        }
    }

    protected void internalHandleWidthChange() {
        timeline.updateWidths();
        updateContentWidth();
    }

    /**
     * Set RPC implementation that is used to communicate with the server.
     * 
     * @param ganttRpc
     *            GanttRpc
     */
    public void setRpc(GanttRpc ganttRpc) {
        this.ganttRpc = ganttRpc;
    }

    /**
     * Get RPC implementation that is used to communicate with the server.
     * 
     * @return GanttRpc
     */
    public GanttRpc getRpc() {
        return ganttRpc;
    }

    /**
     * Initialize listeners.
     */
    protected void initListeners() {
        Event.sinkEvents(container, Event.ONSCROLL);

        addDomHandler(scrollHandler, ScrollEvent.getType());
        if (isMsTouchSupported()) {
            // IE10 pointer events (ms-prefixed events)
            addDomHandler(msPointerDownHandler, PointerDownEvent.getType());
            addDomHandler(msPointerUpHandler, PointerUpEvent.getType());
            addDomHandler(msPointerMoveHandler, PointerMoveEvent.getType());
            addDomHandler(msPointerCancelHandler, PointerCancelEvent.getType());

        } else if (touchSupported) {
            // touch events replaces mouse events
            addDomHandler(touchStartHandler, TouchStartEvent.getType());
            addDomHandler(touchEndHandler, TouchEndEvent.getType());
            addDomHandler(touchMoveHandler, TouchMoveEvent.getType());
            addDomHandler(touchCancelHandler, TouchCancelEvent.getType());

        } else {
            addDomHandler(mouseDownHandler, MouseDownEvent.getType());
            addDomHandler(mouseUpHandler, MouseUpEvent.getType());
            addDomHandler(mouseMoveHandler, MouseMoveEvent.getType());
        }
    }

    /**
     * Enable or disable touch support.
     * 
     * @param touchSupported
     *            True enables touch support.
     */
    public void setTouchSupported(boolean touchSupported) {
        this.touchSupported = touchSupported;
    }

    /**
     * Enable or disable resizable steps.
     * 
     * @param resizableSteps
     *            True enables step resizing.
     */
    public void setResizableSteps(boolean resizableSteps) {
        this.resizableSteps = resizableSteps;
    }

    /**
     * Returns true if widget is enabled and steps are resizable.
     * 
     * @return
     */
    public boolean isResizableSteps() {
        return isEnabled() && resizableSteps;
    }

    /**
     * Enable of disable movable step's -feature.
     * 
     * @param movableSteps
     *            True makes steps movable.
     */
    public void setMovableSteps(boolean movableSteps) {
        this.movableSteps = movableSteps;
    }

    /**
     * Returns true if widget is enabled and steps are movable.
     * 
     * @return
     */
    public boolean isMovableSteps() {
        return isEnabled() && movableSteps;
    }

    public boolean isMonthRowVisible() {
        return timeline.isMonthRowVisible();
    }

    public void setMonthRowVisible(boolean monthRowVisible) {
        timeline.setMonthRowVisible(monthRowVisible);
    }

    public boolean isYearRowVisible() {
        return timeline.isYearRowVisible();
    }

    public void setYearRowVisible(boolean yearRowVisible) {
        timeline.setYearRowVisible(yearRowVisible);
    }

    public String getMonthFormat() {
        return timeline.getMonthFormat();
    }

    public void setMonthFormat(String monthFormat) {
        timeline.setMonthFormat(monthFormat);
    }

    public void setYearFormat(String yearFormat) {
        timeline.setYearFormat(yearFormat);
    }

    public String getYearFormat() {
        return timeline.getYearFormat();
    }

    public void setWeekFormat(String weekFormat) {
        timeline.setWeekFormat(weekFormat);
    }

    public void setDayFormat(String dayFormat) {
        timeline.setDayFormat(dayFormat);
    }

    /**
     * Set LocaleDataProvider that is used to provide translations of months and
     * weekdays.
     * 
     * @param localeDataProvider
     */
    public void setLocaleDataProvider(LocaleDataProvider localeDataProvider) {
        this.localeDataProvider = localeDataProvider;
    }

    public LocaleDataProvider getLocaleDataProvider() {
        return localeDataProvider;
    }

    /**
     * Notify this widget if IE is used, and which version.
     * 
     * @param ie
     * @param ie8
     * @param ie9
     */
    public void setBrowserInfo(boolean ie, boolean ie8, boolean ie9) {
        timeline.setBrowserInfo(ie, ie8, ie9);
    }

    /**
     * @see {@link TimelineWidget#setAlwaysCalculatePixelWidths(boolean)}
     * @param calcPx
     */
    public void setAlwaysCalculatePixelWidths(boolean calcPx) {
        timeline.setAlwaysCalculatePixelWidths(calcPx);
    }

    /**
     * Sets timeline's force update flag up. Next
     * {@link TimelineWidget#update(Resolution, long, long, int, LocaleDataProvider)}
     * call knows then to update everything.
     */
    public void setForceUpdateTimeline() {
        if (timeline == null) {
            return;
        }

        timeline.setForceUpdate();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Element getScrollContainer() {
        return container;
    }

    /**
     * Get height of the scroll container. Includes the horizontal scrollbar
     * spacer.
     * 
     * @return
     */
    public int getScrollContainerHeight() {
        if (scrollbarSpacer.getStyle().getDisplay().isEmpty()) {
            return getScrollContainer().getClientHeight()
                    + scrollbarSpacer.getClientHeight();
        }
        return getScrollContainer().getClientHeight();
    }

    /**
     * Return true, if content is overflowing vertically. This means also that
     * vertical scroll bar is visible.
     * 
     * @return
     */
    public boolean isContentOverflowingVertically() {
        if (content == null || container == null) {
            return false;
        }
        return content.getClientHeight() > container.getClientHeight();
    }

    /**
     * Return true, if content is overflowing horizontally. This means also that
     * horizontal scroll bar is visible.
     * 
     * @return
     */
    public boolean isContentOverflowingHorizontally() {
        // state of horizontal overflow is handled by timeline widget
        if (content == null || container == null || timeline == null) {
            return false;
        }
        return timeline.isTimelineOverflowingHorizontally();
    }

    /**
     * Show empty spacing in horizontal scrollbar's position.
     */
    public void showHorizontalScrollbarSpacer() {
        if (!scrollbarSpacer.getStyle().getDisplay().isEmpty()) {
            scrollbarSpacer.getStyle().clearDisplay();
            notifyHeightChanged(getOffsetHeight());
        }
    }

    /**
     * Hide empty spacing in horizontal scrollbar's position.
     */
    public void hideHorizontalScrollbarSpacer() {
        if (scrollbarSpacer.getStyle().getDisplay().isEmpty()) {
            scrollbarSpacer.getStyle().setDisplay(Display.NONE);
            notifyHeightChanged(getOffsetHeight());
        }
    }

    public native boolean isMsTouchSupported()
    /*-{
       return !!navigator.msMaxTouchPoints;
    }-*/;

    public static boolean isTouchEvent(NativeEvent event) {
        return event.getType().contains("touch");
    }

    public static int getTouchOrMouseClientX(NativeEvent event) {
        if (isTouchEvent(event)) {
            return event.getChangedTouches().get(0).getClientX();
        } else {
            return event.getClientX();
        }
    }

    public static int getTouchOrMouseClientY(NativeEvent event) {
        if (isTouchEvent(event)) {
            return event.getChangedTouches().get(0).getClientY();
        } else {
            return event.getClientY();
        }
    }

    /**
     * Add new Step. Returns new height of the content.
     * 
     * @param currentHeight
     * @param step
     * @return New height of the content.
     */
    protected double addStep(double currentHeight, Step step) {
        DivElement bar = DivElement.as(DOM.createDiv());
        bar.setClassName(STYLE_BAR);
        bar.getStyle().setBackgroundColor(step.getBackgroundColor());

        if (step.getStyleName() != null
                && !step.getStyleName().trim().isEmpty()) {
            bar.addClassName(step.getStyleName());
        }

        DivElement caption = DivElement.as(DOM.createDiv());
        caption.setClassName(STYLE_BAR_LABEL);

        if (step.getCaptionMode() == Step.CaptionMode.HTML) {
            caption.setInnerHTML(step.getCaption());
        } else {
            caption.setInnerText(step.getCaption());
        }

        bar.appendChild(caption);

        content.appendChild(bar);

        // bar height should be defined in css
        int height = bar.getClientHeight();
        bar.getStyle().setTop(currentHeight, Unit.PX);
        currentHeight += height;

        registerBarEventListener(bar);
        return currentHeight;
    }

    /**
     * Update step widths based on the timeline. Timeline's width have to be
     * final at this point.
     * 
     * @param steps
     */
    protected void updateStepWidths(Collection<Step> steps) {
        int index = getAdditonalContentElementCount();
        Element bar;
        for (Step step : steps) {
            bar = Element.as(content.getChild(index));

            // sanity check
            if (step.getStartDate() < 0 || step.getEndDate() < 0
                    || step.getEndDate() <= step.getStartDate()) {
                bar.addClassName(STYLE_INVALID);
            } else {
                long offset = getLocaleDataProvider().getTimeZoneOffset();
                updateBarPercentagePosition(step.getStartDate() + offset,
                        step.getEndDate() + offset, bar);
            }
            index++;
        }
    }

    protected Element getBar(NativeEvent event) {
        Element element = event.getEventTarget().cast();
        if (element == null) {
            return null;
        }
        Element parent = element;
        while (parent.getParentElement() != null
                && parent.getParentElement() != content) {
            parent = parent.getParentElement();
        }
        if (parent.getParentElement() == content) {
            return parent;
        }
        return null;
    }

    /**
     * This is called when target bar element is moved successfully. Element's
     * CSS attributes 'left' and 'width' are updated (unit in pixels).
     * 
     * @param bar
     *            Moved Bar element
     * @param y
     */
    protected void moveCompleted(Element bar, int y) {
        double deltay = y - capturePoint.getY();
        GWT.log("Position delta y: " + deltay + "px");

        Element newPosition = findStepElement(bar, y, deltay);
        internalMoveOrResizeCompleted(bar, newPosition, true);
    }

    /**
     * This is called when target bar is resized successfully. Element's CSS
     * attributes 'left' and 'width' are updated (unit in pixels).
     * 
     * @param bar
     *            Resized Bar element
     */
    protected void resizingCompleted(Element bar) {
        internalMoveOrResizeCompleted(bar, null, false);
    }

    protected void onTouchOrMouseDown(NativeEvent event) {
        Element bar = getBar(event);
        if (bar == null) {
            return;
        }

        Event.setCapture(bar); // can't trust this one, unfortunately
        targetBarElement = bar;
        capturePoint = new Point(getTouchOrMouseClientX(event),
                getTouchOrMouseClientY(event));
        movePoint = new Point(getTouchOrMouseClientX(event),
                getTouchOrMouseClientY(event));

        capturePointLeftPercentage = bar.getStyle().getProperty("left");
        capturePointWidthPercentage = bar.getStyle().getProperty("width");
        capturePointLeftPx = bar.getOffsetLeft();
        capturePointWidthPx = bar.getClientWidth();
        capturePointBgColor = bar.getStyle().getBackgroundColor();

        if (detectResizing(bar)) {
            resizing = true;
            resizingFromLeft = isResizingLeft(bar);
        } else {
            resizing = false;
        }

        disallowClickTimer.schedule(CLICK_INTERVAL);

        event.stopPropagation();
    }

    protected void onTouchOrMouseUp(NativeEvent event) {
        if (targetBarElement == null) {
            return;
        }
        Element bar = getBar(event);

        disallowClickTimer.cancel();
        Event.releaseCapture(bar);
        if (bar == targetBarElement && isClickOnNextMouseup()) {
            clickOnNextMouseUp = true;
            if (isEnabled()) {
                getRpc().stepClicked(getStepIndex(content, bar));
            }

        } else {
            clickOnNextMouseUp = true;
            bar = targetBarElement;

            if (resizing) {
                removeResizingStyles(bar);
                if (resizingInProgress) {
                    resizingCompleted(bar);
                } else {
                    resetBarPosition(bar);
                }
            } else if (isMovableSteps()) {
                // moving in progress
                removeMovingStyles(bar);
                if (moveInProgress) {
                    moveCompleted(bar, getTouchOrMouseClientY(event));
                } else {
                    resetBarPosition(bar);
                }
            }
            bar.getStyle().setBackgroundColor(capturePointBgColor);
        }

        stopDrag(event);
    }

    protected void stopDrag(NativeEvent event) {
        hideMoveElement();

        targetBarElement = null;
        capturePoint = null;
        resizing = false;
        resizingInProgress = false;
        moveInProgress = false;

        event.stopPropagation();
    }

    protected void onCancelTouch(NativeEvent event) {
        if (targetBarElement == null) {
            return;
        }
        resetBarPosition(targetBarElement);
        stopDrag(event);
    }

    /**
     * Handle step's move event.
     * 
     * @param event
     *            NativeEvent
     * @return True, if this event was handled and had effect on step.
     */
    protected boolean onTouchOrMouseMove(NativeEvent event) {
        Element bar = getBar(event);
        if (bar != null) {
            movePoint = new Point(getTouchOrMouseClientX(event),
                    getTouchOrMouseClientY(event));
            showResizingPointer(bar, detectResizing(bar));
        }

        if (targetBarElement == null) {
            return false;
        }
        bar = targetBarElement;

        disallowClickTimer.cancel();
        clickOnNextMouseUp = false;

        // calculate delta x and y by original position and the current one.
        double deltax = getTouchOrMouseClientX(event) - capturePoint.getX();
        double deltay = getTouchOrMouseClientY(event) - capturePoint.getY();

        GWT.log("Position delta x: " + deltax + "px");

        if (resizing) {
            resizingInProgress = deltax != 0.0;
            if (resizingFromLeft) {
                updateBarResizingLeft(bar, deltax);
            } else {
                updateBarResizingRight(bar, deltax);
            }
            addResizingStyles(bar);
            bar.getStyle().clearBackgroundColor();
        } else if (isMovableSteps()) {
            updateMoveInProgressFlag(deltax, deltay);
            updateBarMovingPosition(bar, deltax);
            addMovingStyles(bar);
            bar.getStyle().clearBackgroundColor();
        }

        event.stopPropagation();
        return true;
    }

    protected void updateMoveInProgressFlag(double deltax, double deltay) {
        moveInProgress = deltax != 0.0;
    }

    /**
     * Helper method to find Step element by given starting point and y-position
     * and delta-y. Starting point is there to optimize performance a bit as
     * there's no need to iterate through every single step element.
     * 
     * @param startFromBar
     *            Starting point element
     * @param y
     *            target y-axis position
     * @param deltay
     *            delta-y relative to starting point element.
     * @return Step element at y-axis position. May be same element as given
     *         startFromBar element.
     */
    protected Element findStepElement(Element startFromBar, int y, double deltay) {
        if (isBetween(y, startFromBar.getAbsoluteTop(),
                startFromBar.getAbsoluteBottom())) {
            return startFromBar;
        }
        int startIndex = getChildIndex(content, startFromBar);
        Element barCanditate;
        int i = startIndex;
        if (deltay > 0) {
            i++;
            for (; i < content.getChildCount(); i++) {
                barCanditate = Element.as(content.getChild(i));
                if (isBetween(y, barCanditate.getAbsoluteTop(),
                        barCanditate.getAbsoluteBottom())) {
                    return barCanditate;
                }
            }
        } else if (deltay < 0) {
            i--;
            for (; i >= getAdditonalContentElementCount(); i--) {
                barCanditate = Element.as(content.getChild(i));
                if (isBetween(y, barCanditate.getAbsoluteTop(),
                        barCanditate.getAbsoluteBottom())) {
                    return barCanditate;
                }
            }
        }
        return startFromBar;
    }

    private boolean isBetween(int v, int min, int max) {
        return v >= min && v <= max;
    }

    private void updateMoveElementFor(Element target) {
        if (target == null) {
            moveElement.getStyle().setDisplay(Display.NONE);
        }
        moveElement.getStyle().clearDisplay();

        moveElement.getStyle().setProperty("left", target.getStyle().getLeft());
        moveElement.getStyle().setProperty("width",
                target.getStyle().getWidth());
    }

    private void hideMoveElement() {
        moveElement.getStyle().setDisplay(Display.NONE);
    }

    private void internalMoveOrResizeCompleted(Element bar,
            Element newPosition, boolean move) {
        int barIndex = getStepIndex(content, bar);
        int newBarIndex = barIndex;
        if (newPosition != null && bar != newPosition) {
            newBarIndex = getStepIndex(content, newPosition);
        }

        double left = parseSize(bar.getStyle().getLeft(), "px");
        long startDate = timeline.getDateForLeftPosition(left);
        left += bar.getClientWidth();
        long endDate = timeline.getDateForLeftPosition(left);
        // update left-position to percentage (so that it scales again)
        updateBarPercentagePosition(startDate, endDate, bar);

        if (barIndex < 0) {
            GWT.log("RPC call cancelled. Invalid child element index: "
                    + barIndex);
            return;
        }

        long offset = getLocaleDataProvider().getTimeZoneOffset();
        if (move) {
            getRpc().onMove(barIndex, newBarIndex, startDate - offset,
                    endDate - offset);
        } else {
            getRpc().onResize(barIndex, startDate - offset, endDate - offset);
        }
    }

    private void updateBarPercentagePosition(long startDate, long endDate,
            Element bar) {
        String sLeft = timeline
                .getLeftPositionPercentageStringForDate(startDate);
        bar.getStyle().setProperty("left", sLeft);

        String sWidth = timeline
                .getWidthPercentageStringForDateInterval(endDate - startDate);
        bar.getStyle().setProperty("width", sWidth);
    }

    private void registerBarEventListener(final DivElement bar) {
        Event.sinkEvents(bar, Event.ONSCROLL | Event.MOUSEEVENTS
                | Event.TOUCHEVENTS);
    }

    private boolean isBar(NativeEvent event) {
        Element element = getBar(event);
        return element != null;
    }

    private double parseSize(String size, String suffix) {
        if (size == null || size.length() == 0 || "0".equals(size)
                || "0.0".equals(size)) {
            return 0;
        }
        return Double.parseDouble(size.substring(0,
                size.length() - suffix.length()));
    }

    private int getStepIndex(Element parent, Element child) {
        // return child index minus additional elements in the content
        return getChildIndex(parent, child) - getAdditonalContentElementCount();
    }

    private int getAdditonalContentElementCount() {
        return 1;
    }

    private int getChildIndex(Element parent, Element child) {
        return DOM.getChildIndex(com.google.gwt.dom.client.Element.as(parent),
                com.google.gwt.dom.client.Element.as(child));
    }

    private boolean detectResizing(Element bar) {
        return isResizableSteps()
                && (isResizingLeft(bar) || isResizingRight(bar));
    }

    private boolean isResizingLeft(Element bar) {
        if (movePoint.getX() <= (bar.getAbsoluteLeft() + RESIZE_WIDTH)) {
            return true;
        }
        return false;
    }

    private boolean isResizingRight(Element bar) {
        if (movePoint.getX() >= (bar.getAbsoluteRight() + -RESIZE_WIDTH)) {
            return true;
        }
        return false;
    }

    private void addResizingStyles(Element bar) {
        bar.addClassName(STYLE_RESIZING);
        updateMoveElementFor(bar);
    }

    private void removeResizingStyles(Element bar) {
        bar.removeClassName(STYLE_RESIZING);
    }

    private void showResizingPointer(Element bar, boolean showPointer) {
        if (showPointer) {
            bar.getStyle().setCursor(Cursor.E_RESIZE);
        } else {
            bar.getStyle().clearCursor();
        }
    }

    private void addMovingStyles(Element bar) {
        if (bar == null) {
            return;
        }
        bar.addClassName(STYLE_MOVING);
        updateMoveElementFor(bar);
    }

    private void removeMovingStyles(Element bar) {
        bar.removeClassName(STYLE_MOVING);
    }

    private void updateBarResizingRight(Element bar, double deltax) {
        double newWidth = capturePointWidthPx + deltax;
        if (newWidth >= BAR_MIN_WIDTH) {
            bar.getStyle().setLeft(capturePointLeftPx, Unit.PX);
            bar.getStyle().setWidth(newWidth, Unit.PX);
        }
    }

    private void updateBarResizingLeft(Element bar, double deltax) {
        double newLeft = capturePointLeftPx + deltax;
        double newWidth = capturePointWidthPx - deltax;
        if (newWidth >= BAR_MIN_WIDTH) {
            bar.getStyle().setLeft(newLeft, Unit.PX);
            bar.getStyle().setWidth(newWidth, Unit.PX);
        }
    }

    private void updateBarMovingPosition(Element bar, double deltax) {
        bar.getStyle().setLeft(capturePointLeftPx + deltax, Unit.PX);
    }

    private void resetBarPosition(Element bar) {
        bar.getStyle().setProperty("left", capturePointLeftPercentage);
        bar.getStyle().setProperty("width", capturePointWidthPercentage);
    }

    private void clearContent() {
        while (content.hasChildNodes()) {
            content.getLastChild().removeFromParent();
        }
        content.appendChild(moveElement);
    }

    private void disableClickOnNextMouseUp() {
        clickOnNextMouseUp = false;
    }

    private boolean isClickOnNextMouseup() {
        return clickOnNextMouseUp;
    }

    private boolean isResizingInProgress() {
        return resizingInProgress;
    }

    private int getHorizontalScrollbarSpacerHeight() {
        if (scrollbarSpacer.getStyle().getDisplay().isEmpty()) {
            return scrollbarSpacer.getClientHeight();
        }
        return 0;
    }
}