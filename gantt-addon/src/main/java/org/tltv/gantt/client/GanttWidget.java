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

import static org.tltv.gantt.client.shared.GanttUtil.getBoundingClientRectWidth;
import static org.tltv.gantt.client.shared.GanttUtil.getMarginByComputedStyle;
import static org.tltv.gantt.client.shared.GanttUtil.getTouchOrMouseClientX;
import static org.tltv.gantt.client.shared.GanttUtil.getTouchOrMouseClientY;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tltv.gantt.client.shared.GanttUtil;
import org.tltv.gantt.client.shared.Resolution;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractNativeScrollbar;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;
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
 * Here are few steps that need to be notified when taking this widget in use.
 * <br>
 * First of all, after constructing this widget, you need to initialize it by
 * {@link #initWidget(GanttRpc, LocaleDataProvider)} method. But before doing
 * that, make sure to call
 * {@link #setBrowserInfo(boolean, boolean, boolean, boolean, int)} to let this
 * widget know some details of the browser. And if client supports touch events,
 * let this widget know that by calling {@link #setTouchSupported(boolean)}
 * method before initWidget.
 * <p>
 * Sample code snippet:
 *
 * <pre>
 * GanttWidget widget = new GanttWidget();
 * widget.setBrowserInfo(isIe(), isChrome(), isSafari(), isWebkit(), getMajorBrowserVersion());
 * widget.setTouchSupportted(isTouchDevice());
 * widget.initWidget(ganttRpc, localeDataProvider);
 * </pre>
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
public class GanttWidget extends ComplexPanel implements HasEnabled, HasWidgets {

    private static final int RESIZE_WIDTH = 10;
    private static final int BAR_MIN_WIDTH = RESIZE_WIDTH;
    private static final int CLICK_INTERVAL = 250;
    /*
     * Time mouse-up is ignored after mouse-down to detect double-click.
     */
    private static final int DOUBLECLICK_DETECTION_INTERVAL = 250;
    private static final int POINTER_TOUCH_DETECTION_INTERVAL = 100;

    private static final String STYLE_GANTT = "gantt";
    private static final String STYLE_GANTT_CONTAINER = "gantt-container";
    private static final String STYLE_GANTT_CONTENT = "gantt-content";
    private static final String STYLE_MOVING = "moving";
    private static final String STYLE_RESIZING = "resizing";
    private static final String STYLE_MOVE_ELEMENT = "mv-el";
    private static final String STYLE_NOW_ELEMENT = "now-el";

    private HandlerRegistration pointerDownHandlerRegistration;
    private HandlerRegistration pointerUpHandlerRegistration;
    private HandlerRegistration pointerMoveHandlerRegistration;
    private HandlerRegistration pointerCancelHandlerRegistration;

    private HandlerRegistration touchStartHandlerRegistration;
    private HandlerRegistration touchEndHandlerRegistration;
    private HandlerRegistration touchMoveHandlerRegistration;
    private HandlerRegistration touchCancelHandlerRegistration;

    private HandlerRegistration scrollHandlerRegistration;
    private HandlerRegistration mouseMoveHandlerRegistration;
    private HandlerRegistration mouseDownHandlerRegistration;
    private HandlerRegistration mouseUpHandlerRegistration;
    private HandlerRegistration mouseDblClickHandlerRegistration;

    private HandlerRegistration contextMenuHandlerRegistration;

    private WidgetCollection children = new WidgetCollection(this);

    private boolean enabled = true;
    private boolean touchSupported = false;
    private boolean movableSteps;
    private boolean movableStepsBetweenRows;
    private boolean resizableSteps;
    private boolean backgroundGridEnabled;
    private boolean defaultContextMenuEnabled = false;
    private boolean showCurrentTime;
    private String currentDate;
    private String currentHour;
    private Long timestamp;

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
    protected BgGridElement bgGrid;

    /* Extra elements inside the content. */
    protected Set<Widget> extraContentElements = new HashSet<Widget>();

    protected boolean clickOnNextMouseUp = true;
    protected boolean secondaryClickOnNextMouseUp = true;
    protected boolean insideDoubleClickDetectionInterval = false;
    protected int numberOfMouseClicksDetected = 0;
    protected NativeEvent previousMouseUpEvent;
    protected Element previousMouseUpBarElement;
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
    protected double capturePointTopPx;
    protected double capturePointAbsTopPx;
    protected double capturePointWidthPx;
    protected String capturePointBgColor;
    protected Element targetBarElement;

    // these variables are used to memorize the Y and Y origin to scroll the
    // container (with touchStart/touchEnd/touchMove events).
    protected int containerScrollStartPosY = -1;
    protected int containerScrollStartPosX = -1;

    // additional element that appears when moving or resizing
    protected DivElement moveElement = DivElement.as(DOM.createDiv());

    // element that points current time in timeline.
    protected DivElement nowElement = DivElement.as(DOM.createDiv());

    // updates current time indicator
    private Timer currentTimeTimer = new Timer() {

        @Override
        public void run() {
            if (isShowCurrentTime()) {
                updateNowElement(true);
                currentTimeTimer.schedule(5 * 60000);
            }
        }
    };

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

    /*
     * When this timer is started, all clicks (mouse down+up) will be postponed
     * until timer executes, but only if double-click event is not fired during
     * that time. This also means that with very slow double-click speeds double
     * clicks are never registered.
     */
    private Timer doubleClickDetectionMaxTimer = new Timer() {

        @Override
        public void run() {
            GWT.log("doubleClickDetectionMaxTimer.run()");

            boolean doFireClick = numberOfMouseClicksDetected > 0 && previousMouseUpEvent != null
                    && previousMouseUpBarElement != null && !isMoveOrResizingInProgress();
            NativeEvent targetEvent = previousMouseUpEvent;
            Element targetElement = previousMouseUpBarElement;
            cancelDoubleClickDetection();
            if (doFireClick) {
                fireClickRpc(targetElement, targetEvent);
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

    private int previousContainerScrollLeft = 0;
    private int previousContainerScrollTop = 0;
    private ScrollHandler scrollHandler = new ScrollHandler() {

        @Override
        public void onScroll(ScrollEvent event) {
            final Element element = event.getNativeEvent().getEventTarget().cast();
            if (element != container) {
                return;
            }
            AnimationScheduler.get().requestAnimationFrame(new AnimationCallback() {

                @Override
                public void execute(double timestamp) {
                    int sl = container.getScrollLeft();
                    int st = container.getScrollTop();
                    if (sl != previousContainerScrollLeft) {
                        timeline.setScrollLeft(sl);
                        previousContainerScrollLeft = sl;
                    }
                    if (st != previousContainerScrollTop) {
                        previousContainerScrollTop = st;
                    }
                }
            });
        }
    };

    private DoubleClickHandler doubleClickHandler = new DoubleClickHandler() {

        @Override
        public void onDoubleClick(DoubleClickEvent event) {
            GWT.log("onDoubleClick(DoubleClickEvent)");
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                doubleClickDetectionMaxTimer.cancel();
                if (!insideDoubleClickDetectionInterval && numberOfMouseClicksDetected < 2) {
                    return; // ignore double-click
                }
                if (targetBarElement != null) {
                    disableClickOnNextMouseUp();
                    targetBarElement = null;
                }
                Element bar = getBar(event.getNativeEvent());
                if (bar != null && numberOfMouseClicksDetected > 1) {
                    fireClickRpc(bar, event.getNativeEvent());
                }
                cancelDoubleClickDetection();
            }
        }
    };

    private MouseDownHandler mouseDownHandler = new MouseDownHandler() {

        @Override
        public void onMouseDown(MouseDownEvent event) {
            GWT.log("onMouseDown(MouseDownEvent)");
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                GanttWidget.this.onTouchOrMouseDown(event.getNativeEvent());
            } else {
                secondaryClickOnNextMouseUp = true;
                new Timer() {

                    @Override
                    public void run() {
                        secondaryClickOnNextMouseUp = false;
                    }
                }.schedule(CLICK_INTERVAL);
                event.stopPropagation();
            }
        }
    };

    private MouseUpHandler mouseUpHandler = new MouseUpHandler() {

        @Override
        public void onMouseUp(MouseUpEvent event) {
            GWT.log("onMouseUp(MouseUpEvent)");
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                GanttWidget.this.onTouchOrMouseUp(event.getNativeEvent());

            } else {
                if (secondaryClickOnNextMouseUp) {
                    Element bar = getBar(event.getNativeEvent());
                    if (bar != null && isEnabled()) {
                        getRpc().stepClicked(getStepUid(bar), event.getNativeEvent(), bar);
                    }
                }
                secondaryClickOnNextMouseUp = true;
            }
        }
    };

    private ContextMenuHandler contextMenuHandler = new ContextMenuHandler() {

        @Override
        public void onContextMenu(ContextMenuEvent event) {
            if (!defaultContextMenuEnabled) {
                event.preventDefault();
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
            GWT.log("onPointerDown(PointerDownEvent)");

            if (currentPointerEventId == -1) {
                currentPointerEventId = event.getPointerId();
            } else {
                event.preventDefault();
                return; // multi-touch not supported
            }
            pendingPointerDownEvent = event.getNativeEvent();
            capturePoint = new Point(getTouchOrMouseClientX(event.getNativeEvent()),
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
            movePoint = new Point(getTouchOrMouseClientX(event.getNativeEvent()),
                    getTouchOrMouseClientY(event.getNativeEvent()));

            // do nothing, if touch position has not changed
            if (!(capturePoint.getX() == movePoint.getX() && capturePoint.getY() == movePoint.getY())) {
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
                JavaScriptObject target = event.getNativeEvent().getEventTarget().cast();
                containerScrollStartPosY = -1;
                containerScrollStartPosX = -1;

                if (target == container || target == content || (!isMovableSteps())) {
                    boolean preventDefaultAndReturn = false;
                    // store x,y position for 'manual' vertical scrolling
                    if (isContentOverflowingVertically()) {
                        containerScrollStartPosY = container.getScrollTop() + event.getTouches().get(0).getPageY();
                        preventDefaultAndReturn = true;
                    }
                    if (isContentOverflowingHorizontally()) {
                        containerScrollStartPosX = container.getScrollLeft() + event.getTouches().get(0).getPageX();
                        preventDefaultAndReturn = true;
                    }
                    if (preventDefaultAndReturn) {
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
            containerScrollStartPosX = -1;
            GanttWidget.this.onTouchOrMouseUp(event.getNativeEvent());
            event.preventDefault();
        }
    };

    private TouchMoveHandler touchMoveHandler = new TouchMoveHandler() {
        @Override
        public void onTouchMove(TouchMoveEvent event) {
            if (event.getChangedTouches().length() == 1) {
                boolean preventDefaultAndReturn = false;
                // did we intend to scroll the container?
                // apply 'manual' vertical scrolling
                if (containerScrollStartPosY != -1) {
                    container.setScrollTop(containerScrollStartPosY - event.getChangedTouches().get(0).getPageY());
                    preventDefaultAndReturn = true;
                }
                if (containerScrollStartPosX != -1) {
                    container.setScrollLeft(containerScrollStartPosX - event.getChangedTouches().get(0).getPageX());
                    preventDefaultAndReturn = true;
                }
                if (preventDefaultAndReturn) {
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
            containerScrollStartPosX = -1;
            onCancelTouch(event.getNativeEvent());
        }
    };

    private boolean ie, chrome, safari, webkit;

    public GanttWidget() {

        setElement(DivElement.as(DOM.createDiv()));
        setStyleName(STYLE_GANTT);

        nowElement.setClassName(STYLE_NOW_ELEMENT);

        moveElement.setClassName(STYLE_MOVE_ELEMENT);
        // not visible by default
        moveElement.getStyle().setDisplay(Display.NONE);

        timeline = GWT.create(TimelineWidget.class);

        container = DivElement.as(DOM.createDiv());
        container.setClassName(STYLE_GANTT_CONTAINER);

        content = DivElement.as(DOM.createDiv());
        content.setClassName(STYLE_GANTT_CONTENT);
        container.appendChild(content);

        content.appendChild(moveElement);
        content.appendChild(nowElement);

        scrollbarSpacer = DivElement.as(DOM.createDiv());
        scrollbarSpacer.getStyle().setHeight(AbstractNativeScrollbar.getNativeScrollbarHeight(), Unit.PX);
        scrollbarSpacer.getStyle().setDisplay(Display.NONE);

        getElement().appendChild(timeline.getElement());
        getElement().appendChild(container);
        getElement().appendChild(scrollbarSpacer);
    }

    public void initWidget(GanttRpc ganttRpc, LocaleDataProvider localeDataProvider) {
        setRpc(ganttRpc);
        setLocaleDataProvider(localeDataProvider);
        resetListeners();
    }

    /**
     * Add new StepWidget into content area.
     *
     * @param stepIndex
     *            Index of step (0 based) (not element index in container)
     * @param widget
     * @param updateAffectedSteps
     *            Updates position of affected steps. Usually it means steps
     *            below the target.
     */
    public void addStep(int stepIndex, StepWidget stepWidget, boolean updateAffectedSteps) {
        DivElement bar = DivElement.as(stepWidget.getElement());

        boolean newStep = !bar.hasParentElement();
        boolean moving = !newStep && getStepIndex(stepWidget) != stepIndex;
        boolean insertDOM = newStep || moving;

        if (insertDOM) {
            insert(stepIndex + getAdditonalContentElementCount(), stepWidget);
        }

        deferredUpdateStepTop(stepIndex, updateAffectedSteps, bar, insertDOM);

        if (newStep) {
            registerBarEventListener(bar);
        }
    }

    private void deferredUpdateStepTop(int stepIndex, boolean updateAffectedSteps, DivElement bar, boolean insertDOM) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                updateStepTop(stepIndex, updateAffectedSteps, bar, insertDOM);
            }
        });
    }

    private void updateStepTop(int stepIndex, boolean updateAffectedSteps, DivElement bar, boolean insertDOM) {
        // Update top
        int stepsInContainer = getChildren().size() - getAdditionalWidgetContentElementCount();
        int indexInWidgetContainer = stepIndex + getAdditionalWidgetContentElementCount();
        // bar height should be defined in css
        int height = getElementHeightWithMargin(bar);

        if (stepIndex == 0) {
            bar.getStyle().setTop(0, Unit.PX);
            if (updateAffectedSteps) {
                updateTopForAllStepsBelow(indexInWidgetContainer + 1, height);
            }
        } else if (stepIndex < stepsInContainer) {
            // update top by the previous step top + step height.
            // Requires that previous steps top is already correct.
            int prevWidgetIndex = indexInWidgetContainer - 1;
            Widget w = getWidget(prevWidgetIndex);
            if (w instanceof StepWidget) {
                double top = parseSize(w.getElement().getStyle().getTop(), "px");
                top += getElementHeightWithMargin(w.getElement());
                bar.getStyle().setTop(top, Unit.PX);

                if (updateAffectedSteps) {
                    updateTopForAllStepsBelow(indexInWidgetContainer + 1, height);
                }
            }
        }

        if (insertDOM) {
            contentHeight += height;
        }
    }

    /**
     * Remove Widget from the content area.
     *
     * @param widget
     */
    public void removeStep(Widget widget) {
        remove(widget);
    }

    /**
     * Update Gantt chart's timeline and content for the given steps. This won't
     * add any steps, but will update the content widths and heights.
     *
     * @param steps
     */
    public void update(List<StepWidget> steps) {
        if (startDate < 0 || endDate < 0 || startDate >= endDate) {
            GWT.log("Invalid start and end dates. Gantt chart can't be rendered. Start: " + startDate + ", End: "
                    + endDate);
            return;
        }

        content.getStyle().setHeight(contentHeight, Unit.PX);

        GWT.log("GanttWidget's active TimeZone: " + getLocaleDataProvider().getTimeZone().getID() + " (raw offset: "
                + getLocaleDataProvider().getTimeZone().getStandardOffset() + ")");

        timeline.setCurrentDateAndTime(isShowCurrentTime(), getCurrentDate(), getCurrentHour(), getTimestamp());
        // tell timeline to notice vertical scrollbar before updating it
        timeline.setNoticeVerticalScrollbarWidth(isContentOverflowingVertically());
        timeline.update(resolution, startDate, endDate, firstDayOfRange, firstHourOfRange, localeDataProvider);
        setContentMinWidth(timeline.getMinWidth());
        internalHandleWidthChange();

        updateStepWidths(steps);

        wasTimelineOverflowingHorizontally = timeline.isTimelineOverflowingHorizontally();

        updateCurrentTime();
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
            if (!"".equals(getElement().getStyle().getHeight())) {
                container.getStyle().setHeight(height - getTimelineHeight() - getHorizontalScrollbarSpacerHeight(),
                        Unit.PX);
            } else {
                // if the component has undefined height also set undefined
                // height to the container
                container.getStyle().setHeight(-1, Unit.PX);
            }

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
            if (timeline.isAlwaysCalculatePixelWidths() || wasTimelineOverflowingHorizontally != overflow) {
                // scrollbar has just appeared/disappeared
                wasTimelineOverflowingHorizontally = overflow;
                if (!wasTimelineOverflowingHorizontally) {
                    timeline.setScrollLeft(0);
                }
            }
            internalHandleWidthChange();
        }
    }

    protected void internalHandleWidthChange() {
        timeline.updateWidths();
        updateContentWidth();
        updateContainerStyle();
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
     * Reset listeners.
     */
    public void resetListeners() {
        Event.sinkEvents(container, Event.ONSCROLL | Event.ONCONTEXTMENU);

        if (contextMenuHandlerRegistration == null) {
            contextMenuHandlerRegistration = addDomHandler(contextMenuHandler, ContextMenuEvent.getType());
        }

        if (scrollHandlerRegistration == null) {
            scrollHandlerRegistration = addHandler(scrollHandler, ScrollEvent.getType());
        }
        if (isMsTouchSupported()) {
            // IE10 pointer events (ms-prefixed events)
            if (pointerDownHandlerRegistration == null) {
                pointerDownHandlerRegistration = addDomHandler(msPointerDownHandler, PointerDownEvent.getType());
            }
            if (pointerUpHandlerRegistration == null) {
                pointerUpHandlerRegistration = addDomHandler(msPointerUpHandler, PointerUpEvent.getType());
            }
            if (pointerMoveHandlerRegistration == null) {
                pointerMoveHandlerRegistration = addDomHandler(msPointerMoveHandler, PointerMoveEvent.getType());
            }
            if (pointerCancelHandlerRegistration == null) {
                pointerCancelHandlerRegistration = addHandler(msPointerCancelHandler, PointerCancelEvent.getType());
            }
        } else if (touchSupported) {
            // touch events replaces mouse events
            if (touchStartHandlerRegistration == null) {
                touchStartHandlerRegistration = addDomHandler(touchStartHandler, TouchStartEvent.getType());
            }
            if (touchEndHandlerRegistration == null) {
                touchEndHandlerRegistration = addDomHandler(touchEndHandler, TouchEndEvent.getType());
            }
            if (touchMoveHandlerRegistration == null) {
                touchMoveHandlerRegistration = addDomHandler(touchMoveHandler, TouchMoveEvent.getType());
            }
            if (touchCancelHandlerRegistration == null) {
                touchCancelHandlerRegistration = addHandler(touchCancelHandler, TouchCancelEvent.getType());
            }

        } else {
            if (mouseDblClickHandlerRegistration == null) {
                mouseDblClickHandlerRegistration = addDomHandler(doubleClickHandler, DoubleClickEvent.getType());
            }
            if (mouseDownHandlerRegistration == null) {
                mouseDownHandlerRegistration = addDomHandler(mouseDownHandler, MouseDownEvent.getType());
            }
            if (mouseUpHandlerRegistration == null) {
                mouseUpHandlerRegistration = addDomHandler(mouseUpHandler, MouseUpEvent.getType());
            }
            if (isMovableSteps() || isResizableSteps()) {
                if (mouseMoveHandlerRegistration == null) {
                    mouseMoveHandlerRegistration = addDomHandler(mouseMoveHandler, MouseMoveEvent.getType());
                }
            } else if (mouseMoveHandlerRegistration != null) {
                mouseMoveHandlerRegistration.removeHandler();
                mouseMoveHandlerRegistration = null;
            }
        }
    }

    /**
     * Return true if background grid is enabled.
     *
     * @return True if background grid is enabled.
     */
    public boolean isBackgroundGridEnabled() {
        return backgroundGridEnabled;
    }

    /**
     * Set background grid enabled. Next time the widget is painted, the grid is
     * shown on the background of the container.
     *
     * @param backgroundGridEnabled
     *            True sets background grid enabled.
     */
    public void setBackgroundGridEnabled(boolean backgroundGridEnabled) {
        this.backgroundGridEnabled = backgroundGridEnabled;
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
     * Are touch events supported.
     *
     * @return True if touch events are supported.
     */
    public boolean isTouchSupported() {
        return touchSupported;
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
     * Enable or disable movable step's -feature.
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

    /**
     * Returns true if the widget is enabled the steps are movable and the steps
     * are movable between rows.
     *
     * @return
     */
    public boolean isMovableStepsBetweenRows() {
        return isMovableSteps() && movableStepsBetweenRows;
    }

    /**
     * Returns true if the widget is enabled the steps are movable and the steps
     * are movable between rows.
     *
     * @return
     */
    public boolean isMovableStepsBetweenRows(Element bar) {
        return isMovableStepsBetweenRows()/* && !isSubBar(bar) */;
    }

    /**
     * Enable or disable movable steps between lines feature
     *
     * @param movableStepsBetweenRows
     *            True makes steps movable between lines
     */
    public void setMovableStepsBetweenRows(boolean movableStepsBetweenRows) {
        this.movableStepsBetweenRows = movableStepsBetweenRows;
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

    public void setHourFormat(String hourFormat) {
        timeline.setHourFormat(hourFormat);
    }

    public String getHourFormat() {
        return timeline.getHourFormat();
    }

    public boolean isDefaultContextMenuEnabled() {
        return defaultContextMenuEnabled;
    }

    public void setDefaultContextMenuEnabled(boolean defaultContextMenuEnabled) {
        this.defaultContextMenuEnabled = defaultContextMenuEnabled;
    }

    public void setShowCurrentTime(boolean showCurrentTime) {
        this.showCurrentTime = showCurrentTime;
    }

    public boolean isShowCurrentTime() {
        return showCurrentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(String currentHour) {
        this.currentHour = currentHour;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
     * Notify which browser this widget should optimize to. Usually just one
     * argument is true.
     *
     * @param ie
     * @param chrome
     * @param safari
     * @param webkit
     * @param majorVersion
     */
    public void setBrowserInfo(boolean ie, boolean chrome, boolean safari, boolean webkit, int majorVersion) {
        this.ie = ie;
        this.chrome = chrome;
        this.safari = safari;
        this.webkit = webkit;
        timeline.setBrowserInfo(ie, majorVersion);
    }

    /**
     * @see TimelineWidget#setAlwaysCalculatePixelWidths(boolean)
     * @param calcPx
     */
    public void setAlwaysCalculatePixelWidths(boolean calcPx) {
        timeline.setAlwaysCalculatePixelWidths(calcPx);
    }

    /**
     * Sets timeline's force update flag up. Next
     * {@link TimelineWidget#update(Resolution, long, long, int, int, LocaleDataProvider)}
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
            return getScrollContainer().getClientHeight() + scrollbarSpacer.getClientHeight();
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

    public void updateBarPercentagePosition(long startDate, long endDate, long ownerStartDate, long ownerEndDate,
            Element bar) {
        double ownerStepWidth = GanttUtil.getBoundingClientRectWidth(bar.getParentElement());
        String sLeft = timeline.getLeftPositionPercentageStringForDate(startDate, ownerStepWidth, ownerStartDate,
                ownerEndDate);
        bar.getStyle().setProperty("left", sLeft);

        double range = ownerEndDate - ownerStartDate;
        String sWidth = timeline.getWidthPercentageStringForDateInterval(endDate - startDate, range);
        bar.getStyle().setProperty("width", sWidth);
    }

    public void updateBarPercentagePosition(long startDate, long endDate, Element bar) {
        String sLeft = timeline.getLeftPositionPercentageStringForDate(startDate, getContentWidth());
        bar.getStyle().setProperty("left", sLeft);

        String sWidth = timeline.getWidthPercentageStringForDateInterval(endDate - startDate);
        bar.getStyle().setProperty("width", sWidth);
    }

    /**
     * Register and add Widget inside the content.
     */
    public void registerContentElement(Widget widget) {
        if (extraContentElements.add(widget)) {
            insertFirst(widget);
        }
    }

    /**
     * Unregister and remove element from the content.
     */
    public void unregisterContentElement(Widget widget) {
        if (widget != null) {
            extraContentElements.remove(widget);
            widget.removeFromParent();
        }
    }

    public native boolean isMsTouchSupported()
    /*-{
       return !!(navigator.maxTouchPoints > 0);
    }-*/;

    /**
     * Get current step index if widget element is attached to DOM. Otherwise
     * return -1.
     */
    public int getStepIndex(StepWidget stepWidget) {
        if (stepWidget != null && stepWidget.getElement().hasParentElement()) {
            int widgetIndex = getWidgetIndex(stepWidget);
            return widgetIndex - getAdditionalWidgetContentElementCount();
        }
        return -1;
    }

    @Override
    public void add(Widget w) {
        super.add(w, content);
    }

    /**
     * @param child
     *            Child widget.
     * @param container
     *            Parent element.
     * @param beforeIndex
     *            Target index of element in DOM.
     * @param domInsert
     *            true: Insert at specific position. false: append at the end.
     */
    @Override
    protected void insert(Widget child, Element container, int beforeIndex, boolean domInsert) {
        GWT.log("Count content elements: " + content.getChildCount() + " ("
                + getAdditionalNonWidgetContentElementCount() + " non-widget non-step elements, "
                + (getAdditonalContentElementCount() - getAdditionalNonWidgetContentElementCount())
                + " non-step widgets.)");

        // Validate index; adjust if the widget is already a child of this
        // panel.
        int adjustedBeforeStepIndex = adjustIndex(child, beforeIndex - getAdditionalNonWidgetContentElementCount())
                - getAdditionalWidgetContentElementCount();

        // Detach new child. Might also remove additional widgets like
        // predecessor arrows. May affect contentHeight.
        child.removeFromParent();

        // Logical attach.
        getChildren().insert(child, adjustedBeforeStepIndex + getAdditionalWidgetContentElementCount());

        // Physical attach.
        if (domInsert) {
            DOM.insertChild(container, child.getElement(), adjustedBeforeStepIndex + getAdditonalContentElementCount());
        } else {
            DOM.appendChild(container, child.getElement());
        }

        // Adopt.
        adopt(child);
    }

    public void insert(int beforeIndex, Widget w) {
        insert(w, content, beforeIndex, true);
    }

    public void insertFirst(Widget child) {
        // Detach new child.
        child.removeFromParent();

        // Logical attach.
        getChildren().insert(child, 0);

        // Physical attach.
        content.insertFirst(child.getElement());

        // Adopt.
        adopt(child);
    }

    @Override
    public boolean remove(Widget w) {
        if (!(w instanceof StepWidget)) {
            return super.remove(w);
        } else {
            int startIndex = getWidgetIndex(w);
            int height = getElementHeightWithMargin(w.getElement());
            contentHeight -= height;

            if ((startIndex = removeAndReturnIndex(w)) >= 0) {
                updateTopForAllStepsBelow(startIndex, -height);

                // update content height
                content.getStyle().setHeight(contentHeight, Unit.PX);
                return true;
            }
            return false;
        }
    }

    public int removeAndReturnIndex(Widget w) {
        int index = -1;
        // Validate.
        if (w.getParent() != this) {
            return index;
        }
        // Orphan.
        try {
            orphan(w);
        } finally {
            index = getWidgetIndex(w);

            // Physical detach.
            Element elem = w.getElement();
            content.removeChild(elem);

            // Logical detach.
            getChildren().remove(w);
        }
        return index;
    }

    /**
     * Update step widths based on the timeline. Timeline's width have to be
     * final at this point.
     *
     * @param steps
     */
    protected void updateStepWidths(Collection<StepWidget> steps) {
        for (StepWidget step : steps) {
            step.updateWidth();
        }
    }

    protected Element getBar(NativeEvent event) {
        Element element = event.getEventTarget().cast();
        if (element == null || isSvg(element)) {
            return null;
        }
        Element parent = element;
        while (parent.getParentElement() != null && parent.getParentElement() != content && !isBar(parent)) {
            parent = parent.getParentElement();
        }
        if (isBar(parent)) {
            return parent;
        }
        return null;
    }

    protected boolean isBgElement(Element target) {
        return bgGrid != null && bgGrid.equals(target);
    }

    private boolean isSvg(Element element) {
        // safety check to avoid calling non existing functions
        if (!isPartOfSvg(element)) {
            return element.hasTagName("svg");
        }
        return true;
    }

    private static native boolean isPartOfSvg(Element element)
    /*-{
        if(element.ownerSVGElement) {
            return true;
        }
        return false;
    }-*/;

    protected boolean isBar(Element element) {
        if (isSvg(element)) {
            return false;
        }
        return element.hasClassName(AbstractStepWidget.STYLE_BAR);
    }

    protected boolean isSubBar(Element element) {
        if (isSvg(element)) {
            return false;
        }
        return element.hasClassName(SubStepWidget.STYLE_SUB_BAR);
    }

    protected boolean hasSubBars(Element element) {
        if (isSvg(element)) {
            return false;
        }
        return element.hasClassName(StepWidget.STYLE_HAS_SUB_STEPS);
    }

    /**
     * This is called when target bar element is moved successfully. Element's
     * CSS attributes 'left' and 'width' are updated (unit in pixels).
     *
     * @param bar
     *            Moved Bar element
     * @param y
     */
    protected void moveCompleted(Element bar, int y, NativeEvent event) {
        double deltay = y - capturePoint.getY();
        GWT.log("Position delta y: " + deltay + "px" + " capture point y is " + capturePoint.getY());

        Element newPosition = findStepElement(bar, (int) capturePointAbsTopPx,
                (int) (capturePointAbsTopPx + getElementHeightWithMargin(bar)), y, deltay);
        internalMoveOrResizeCompleted(bar, newPosition, true, event);
    }

    /**
     * This is called when target bar is resized successfully. Element's CSS
     * attributes 'left' and 'width' are updated (unit in pixels).
     *
     * @param bar
     *            Resized Bar element
     */
    protected void resizingCompleted(Element bar, NativeEvent event) {
        internalMoveOrResizeCompleted(bar, null, false, event);
    }

    protected void onTouchOrMouseDown(NativeEvent event) {
        if (targetBarElement != null && isMoveOrResizingInProgress()) {
            // discard previous 'operation'.
            resetBarPosition(targetBarElement);
            stopDrag(event);
            return;
        }
        Element bar = getBar(event);
        if (bar == null) {
            return;
        }

        targetBarElement = bar;
        capturePoint = new Point(getTouchOrMouseClientX(event), getTouchOrMouseClientY(event));
        movePoint = new Point(getTouchOrMouseClientX(event), getTouchOrMouseClientY(event));

        capturePointLeftPercentage = bar.getStyle().getProperty("left");
        capturePointWidthPercentage = bar.getStyle().getProperty("width");
        capturePointLeftPx = bar.getOffsetLeft();
        capturePointTopPx = bar.getOffsetTop();
        capturePointAbsTopPx = bar.getAbsoluteTop();
        capturePointWidthPx = bar.getClientWidth();
        capturePointBgColor = bar.getStyle().getBackgroundColor();

        if (detectResizing(bar)) {
            resizing = true;
            resizingFromLeft = isResizingLeft(bar);
        } else {
            resizing = false;
        }

        disallowClickTimer.schedule(CLICK_INTERVAL);
        insideDoubleClickDetectionInterval = true;
        doubleClickDetectionMaxTimer.schedule(DOUBLECLICK_DETECTION_INTERVAL);

        event.stopPropagation();
    }

    protected void onTouchOrMouseUp(NativeEvent event) {
        if (targetBarElement == null) {
            return;
        }
        Element bar = getBar(event);

        disallowClickTimer.cancel();

        if (bar == targetBarElement && isClickOnNextMouseup()) {
            clickOnNextMouseUp = true;
            if (insideDoubleClickDetectionInterval) {
                numberOfMouseClicksDetected++;
                previousMouseUpEvent = event;
                previousMouseUpBarElement = bar;
                // event handling postponed until doubleClickDetectionTimer
                // runs.
            } else {
                fireClickRpc(bar, event);
            }

        } else {
            clickOnNextMouseUp = true;
            bar = targetBarElement;

            if (resizing) {
                removeResizingStyles(bar);
                if (resizingInProgress) {
                    resizingCompleted(bar, event);
                } else {
                    resetBarPosition(bar);
                }
            } else if (isMovableStep(bar)) {
                // moving in progress
                removeMovingStyles(bar);
                if (moveInProgress) {
                    moveCompleted(bar, getTouchOrMouseClientY(event), event);
                } else {
                    resetBarPosition(bar);
                }
            }
            bar.getStyle().setBackgroundColor(capturePointBgColor);
        }

        stopDrag(event);
    }

    protected void fireClickRpc(Element bar, NativeEvent event) {
        if (isEnabled()) {
            getRpc().stepClicked(getStepUid(bar), event, bar);
        }
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
            movePoint = new Point(getTouchOrMouseClientX(event), getTouchOrMouseClientY(event));
            showResizingPointer(bar, detectResizing(bar));
        }

        if (targetBarElement == null) {
            return false;
        }
        bar = targetBarElement;

        doubleClickDetectionMaxTimer.cancel();
        disallowClickTimer.cancel();
        clickOnNextMouseUp = false;
        cancelDoubleClickDetection();

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
        } else if (isMovableStep(bar)) {
            updateMoveInProgressFlag(bar, deltax, deltay);
            updateBarMovingPosition(bar, deltax);
            addMovingStyles(bar);
            bar.getStyle().clearBackgroundColor();
            if (isMovableStepsBetweenRows(bar)) {
                updateBarYPosition(bar, deltay);
            }
        }

        // event.stopPropagation();
        return true;
    }

    protected void updateMoveInProgressFlag(Element bar, double deltax, double deltay) {
        moveInProgress = deltax != 0.0 || (isMovableStepsBetweenRows(bar) && Math.abs(deltay) > 3);
    }

    /**
     * Helper method to find Step element by given starting point and y-position
     * and delta-y. Starting point is there to optimize performance a bit as
     * there's no need to iterate through every single step element.
     *
     * @param startFromBar
     *            Starting point element
     * @param newY
     *            target y-axis position
     * @param deltay
     *            delta-y relative to starting point element.
     * @return Step element at y-axis position. May be same element as given
     *         startFromBar element.
     */
    protected Element findStepElement(Element startFromBar, int startTopY, int startBottomY, int newY, double deltay) {
        boolean subStep = isSubBar(startFromBar);
        if (subStep) {
            startFromBar = startFromBar.getParentElement();
        }

        if (isBetween(newY, startTopY, startBottomY)) {
            GWT.log("findStepElement returns same: Y " + newY + " between " + startTopY + "-" + startBottomY);
            return startFromBar;
        }
        int startIndex = getChildIndex(content, startFromBar);
        Element barCanditate;
        int i = startIndex;
        if (deltay > 0) {
            i++;
            for (; i < content.getChildCount(); i++) {
                barCanditate = Element.as(content.getChild(i));
                if (isBetween(newY, barCanditate.getAbsoluteTop(), barCanditate.getAbsoluteBottom())) {
                    if (!subStep && i == (startIndex + 1)) {
                        // moving directly over the following step will be
                        // ignored (if not sub-step).
                        return startFromBar;
                    }
                    return barCanditate;
                }
            }
        } else if (deltay < 0) {
            i--;
            for (; i >= getAdditonalContentElementCount(); i--) {
                barCanditate = Element.as(content.getChild(i));
                if (isBetween(newY, barCanditate.getAbsoluteTop(), barCanditate.getAbsoluteBottom())) {
                    return barCanditate;
                }
            }
        }
        return startFromBar;
    }

    /**
     * Get UID for the given Step element. Or null if StepWidget for the element
     * doesn't exist in container.
     */
    protected String getStepUid(Element stepElement) {
        if (isSubBar(stepElement)) {
            return getSubStepUid(stepElement);
        }

        // get widget by index known by this ComplexPanel.
        StepWidget widget = getStepWidget(stepElement);
        if (widget != null) {
            return widget.getStep().getUid();
        }
        return null;
    }

    protected String getSubStepUid(Element subStepElement) {
        Element stepElement = subStepElement.getParentElement();
        StepWidget widget = getStepWidget(stepElement);
        if (widget != null) {
            return widget.getStepUidBySubStepElement(subStepElement);
        }
        return null;
    }

    protected SubStepWidget getSubStepWidget(Element subStepElement) {
        Element stepElement = subStepElement.getParentElement();
        StepWidget widget = getStepWidget(stepElement);
        if (widget != null) {
            return widget.getSubStepWidgetByElement(subStepElement);
        }
        return null;
    }

    protected StepWidget getStepWidget(Element stepElement) {
        Widget widget = getWidget(getChildIndex(content, stepElement) - getAdditionalNonWidgetContentElementCount());
        if (widget instanceof StepWidget) {
            return (StepWidget) widget;
        }
        return null;
    }

    protected AbstractStepWidget getAbstractStepWidget(Element stepElement) {
        if (isSubBar(stepElement)) {
            return getSubStepWidget(stepElement);
        }
        return getStepWidget(stepElement);
    }

    protected BgGridElement createBackgroundGrid() {
        // implementation may be overridden via deferred binding. There is
        // BgGridSvgElement alternative available and also used by Chrome.
        BgGridElement grid = GWT.create(BgGridCssElement.class);
        grid.init(container, content);
        return grid;
    }

    private void updateTopForAllStepsBelow(int startIndex, int delta) {
        GWT.log("Updating top for all steps below index " + startIndex + ". Delta y: " + delta + "px");
        // update top for all elements below
        Element elementBelow;
        for (int i = startIndex; i < getChildren().size(); i++) {
            elementBelow = getWidget(i).getElement();
            double top = parseSize(elementBelow.getStyle().getTop(), "px");
            elementBelow.getStyle().setTop(top + delta, Unit.PX);
        }
    }

    private double calculateBackgroundGridWidth() {
        return timeline.calculateTimelineWidth();
    }

    private void updateContainerStyle() {
        if (bgGrid == null) {
            bgGrid = createBackgroundGrid();
        }
        if (!isBackgroundGridEnabled()) {
            bgGrid.hide();
            return;
        }
        // Container element has a background image that is positioned, sized
        // and repeated to fill the whole container with a nice grid background.

        // Update 'background-size' in container element to match the background
        // grid's cell width and height to match with the timeline and rows.
        // Update also 'background-position' in container to match the first
        // resolution element width in the timeline, IF it's not same as all
        // other resolution element widths.
        int resDivElementCount = timeline.getResolutionDiv().getChildCount();
        if (resDivElementCount == 0) {
            return;
        }
        Element secondResolutionBlock = null;
        Double firstResolutionBlockWidth = timeline.getFirstResolutionElementWidth();
        if (firstResolutionBlockWidth == null) {
            return;
        }
        Double secondResolutionBlockWidth = null;
        if (resDivElementCount > 2) {
            secondResolutionBlock = Element.as(timeline.getResolutionDiv().getChild(1));
            secondResolutionBlockWidth = getBoundingClientRectWidth(secondResolutionBlock);
        }

        boolean contentOverflowingHorizontally = isContentOverflowingHorizontally();

        boolean adjustBgPosition = secondResolutionBlockWidth != null
                && !firstResolutionBlockWidth.equals(secondResolutionBlockWidth);
        double gridBlockWidthPx = 0.0;
        if (!adjustBgPosition) {
            gridBlockWidthPx = firstResolutionBlockWidth;
        } else {
            gridBlockWidthPx = secondResolutionBlockWidth;
        }

        updateContainerBackgroundSize(contentOverflowingHorizontally, gridBlockWidthPx);

        updateContainerBackgroundPosition(firstResolutionBlockWidth, contentOverflowingHorizontally, gridBlockWidthPx,
                adjustBgPosition);
    }

    private void updateContainerBackgroundSize(boolean contentOverflowingHorizontally, double gridBlockWidthPx) {
        String gridBlockWidth = null;
        if (contentOverflowingHorizontally || useAlwaysPxSizeInBackground()) {
            gridBlockWidth = timeline.toCssCalcOrNumberString(gridBlockWidthPx, "px");
        } else {
            double contentWidth = getBoundingClientRectWidth(content);
            gridBlockWidth = timeline.toCssCalcOrNumberString((100.0 / contentWidth) * gridBlockWidthPx, "%");
        }

        int gridBlockHeightPx = getBgGridCellHeight();
        bgGrid.setBackgroundSize(gridBlockWidth, gridBlockWidthPx, gridBlockHeightPx);
    }

    private void updateContainerBackgroundPosition(double firstResolutionBlockWidth,
            boolean contentOverflowingHorizontally, double gridBlockWidthPx, boolean adjustBgPosition) {
        if (adjustBgPosition) {
            double realBgPosXPx = firstResolutionBlockWidth - 1.0;

            if (useAlwaysPxSizeInBackground() || contentOverflowingHorizontally) {
                bgGrid.setBackgroundPosition(timeline.toCssCalcOrNumberString(realBgPosXPx, "px"), "0px", realBgPosXPx,
                        0);
            } else {
                double timelineWidth = calculateBackgroundGridWidth();
                double relativeBgAreaWidth = timelineWidth - gridBlockWidthPx;
                double bgPosX = (100.0 / relativeBgAreaWidth) * realBgPosXPx;
                bgGrid.setBackgroundPosition(timeline.toCssCalcOrNumberString(bgPosX, "%"), "0px", realBgPosXPx, 0);
            }
        } else {
            bgGrid.setBackgroundPosition("-1px", "0", -1, 0);
        }
    }

    private boolean useAlwaysPxSizeInBackground() {
        return ie || chrome || safari || webkit;
    }

    private int getBgGridCellHeight() {
        int gridBlockHeightPx = 0;
        int firstStepIndex = getAdditonalContentElementCount();
        if (firstStepIndex < content.getChildCount()) {
            Element firstBar = Element.as(content.getChild(firstStepIndex));
            gridBlockHeightPx = getElementHeightWithMargin(firstBar);
        }
        return gridBlockHeightPx;
    }

    private void updateContentWidth() {
        if (timeline.isAlwaysCalculatePixelWidths()) {
            content.getStyle().setWidth(timeline.getResolutionWidth(), Unit.PX);
        }
    }

    private int getElementHeightWithMargin(Element div) {
        int height = (int) Math.round(GanttUtil.getBoundingClientRectHeight(div));
        double marginHeight = 0;
        marginHeight = getMarginByComputedStyle(div);
        return height + (int) Math.round(marginHeight);
    }

    private boolean isBetween(int v, int min, int max) {
        return v >= min && v <= max;
    }

    private void updateMoveElementFor(Element target) {
        if (target == null) {
            moveElement.getStyle().setDisplay(Display.NONE);
        }
        moveElement.getStyle().clearDisplay();

        String styleLeft = target.getStyle().getLeft();
        // user capturePointLeftPx as default
        double left = capturePointLeftPx;
        if (styleLeft != null && styleLeft.length() > 2 && styleLeft.endsWith("px")) {
            // if target's 'left' is pixel value like '123px', use that.
            // When target is already moved, then it's using pixel values. If
            // it's not moved yet, it may use percentage value.
            left = parseSize(styleLeft, "px");
        }
        if (isSubBar(target)) {
            left += target.getParentElement().getOffsetLeft();
        }
        moveElement.getStyle().setProperty("left", left + "px");
        moveElement.getStyle().setProperty("width", target.getClientWidth() + "px");
    }

    private void hideMoveElement() {
        moveElement.getStyle().setDisplay(Display.NONE);
    }

    private void updateNowElement(boolean syncWithServerTime) {
        if (timeline == null || nowElement == null) {
            return;
        }
        if (timeline.isTimelineOverflowingHorizontally()) {
            nowElement.getStyle().setLeft(timeline.getLeftPositionForDate(timeline.getNow()), Unit.PX);
        } else {
            nowElement.getStyle().setLeft(timeline.getLeftPositionPercentageForDate(timeline.getNow(),
                    getContentWidth()), Unit.PCT);
        }
        if (syncWithServerTime) {
            ganttRpc.requestCurrentTime();
        }
    }

    private void updateCurrentTime() {
        currentTimeTimer.cancel();
        if (isShowCurrentTime()) {
            updateNowElement(false);
            currentTimeTimer.schedule(5 * 60000);
            showNowElement();
        } else {
            hideNowElement();
        }
    }

    private void hideNowElement() {
        nowElement.getStyle().setDisplay(Display.NONE);
    }

    private void showNowElement() {
        nowElement.getStyle().clearDisplay();
    }

    private void internalMoveOrResizeCompleted(Element bar, Element newPosition, boolean move, NativeEvent event) {
        String stepUid = getStepUid(bar);
        String newStepUid = stepUid;
        if (newPosition != null && bar != newPosition) {
            newStepUid = getStepUid(newPosition);
        }

        boolean subBar = isSubBar(bar);
        long ownerStartDate = 0;
        long ownerEndDate = 0;
        double left = parseSize(bar.getStyle().getLeft(), "px");
        if (subBar) {
            double ownerLeft = bar.getParentElement().getOffsetLeft();
            left += ownerLeft;
            ownerStartDate = timeline.getDateForLeftPosition(ownerLeft);
            ownerLeft += GanttUtil.getBoundingClientRectWidth(bar.getParentElement());
            ownerEndDate = timeline.getDateForLeftPosition(ownerLeft);
        }
        long startDate = timeline.getDateForLeftPosition(left);
        left += GanttUtil.getBoundingClientRectWidth(bar);
        long endDate = timeline.getDateForLeftPosition(left);
        // update left-position to percentage (so that it scales again)
        if (subBar) {
            updateBarPercentagePosition(startDate, endDate, ownerStartDate, ownerEndDate, bar);
        } else {
            updateBarPercentagePosition(startDate, endDate, bar);
        }

        if (move) {
            if (isMovableStepsBetweenRows() && stepUid == newStepUid) {
                resetBarYPosition(bar);
            }
            getRpc().onMove(stepUid, newStepUid, startDate, endDate, event, bar);
        } else {
            getRpc().onResize(stepUid, startDate, endDate, event, bar);
        }
    }

    private void registerBarEventListener(final DivElement bar) {
        Event.sinkEvents(bar, Event.ONSCROLL | Event.MOUSEEVENTS | Event.TOUCHEVENTS);
    }

    private boolean isBar(NativeEvent event) {
        Element element = getBar(event);
        return element != null;
    }

    private double parseSize(String size, String suffix) {
        if (size == null || size.length() == 0 || "0".equals(size) || "0.0".equals(size)) {
            return 0;
        }
        return Double.parseDouble(size.substring(0, size.length() - suffix.length()));
    }

    private int getStepIndex(Element parent, Element child) {
        // return child index minus additional elements in the content
        return getChildIndex(parent, child) - getAdditonalContentElementCount();
    }

    private int getAdditonalContentElementCount() {
        // moveElement and background element inside the content is noticed.
        // extraContentElements are also noticed.
        return getAdditionalNonWidgetContentElementCount() + extraContentElements.size();
    }

    private int isMoveElementAttached() {
        return moveElement.hasParentElement() ? 1 : 0;
    }

    private int isNowElementAttached() {
        return nowElement.hasParentElement() ? 1 : 0;
    }

    private int getAdditionalNonWidgetContentElementCount() {
        return isNowElementAttached() + isMoveElementAttached() + isBgGridAttached();
    }

    private int getAdditionalWidgetContentElementCount() {
        return extraContentElements.size();
    }

    /**
     * Return the number of background grid related elements in the content. May
     * return 0...n, depending on which browser is used.
     */
    private int isBgGridAttached() {
        return (bgGrid != null && bgGrid.isAttached()) ? 1 : 0;
    }

    private int getChildIndex(Element parent, Element child) {
        return DOM.getChildIndex(com.google.gwt.dom.client.Element.as(parent),
                com.google.gwt.dom.client.Element.as(child));
    }

    private boolean detectResizing(Element bar) {
        return isResizableStep(bar) && !hasSubBars(bar) && (isResizingLeft(bar) || isResizingRight(bar));
    }

    private boolean isMoveOrResizingInProgress() {
        return moveInProgress || resizingInProgress;
    }

    private boolean isResizableStep(Element bar) {
        if (!isResizableSteps()) {
            return false;
        }
        AbstractStepWidget step = getAbstractStepWidget(bar);
        return step != null && step.getStep() != null && step.getStep().isResizable();
    }

    private boolean isMovableStep(Element bar) {
        if (!isMovableSteps()) {
            return false;
        }
        AbstractStepWidget step = getAbstractStepWidget(bar);
        return step != null && step.getStep() != null && step.getStep().isMovable();
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

    private void updateBarYPosition(Element bar, double deltay) {
        int barHeight = getElementHeightWithMargin(bar);
        double offsetY = 0; // offset from content top edge
        if (isSubBar(bar)) {
            Element stepElement = bar.getParentElement();
            offsetY = parseSize(stepElement.getStyle().getTop(), "px");
        }
        double barTop = parseSize(bar.getStyle().getTop(), "px") + offsetY;
        double movementFromTop = capturePointTopPx + offsetY + deltay;
        double deltaTop = movementFromTop - barTop;
        double maxDeltaUp = capturePoint.getY() - capturePointAbsTopPx;
        double maxDeltaDown = barHeight - maxDeltaUp;

        if (deltaTop <= (-1 * maxDeltaUp)) {
            // move up
            if ((barTop - barHeight) >= 0) {
                bar.getStyle().setTop(barTop - barHeight - offsetY, Unit.PX);
            }
        } else if (deltaTop >= maxDeltaDown) {
            // move down
            bar.getStyle().setTop(barTop + barHeight - offsetY, Unit.PX);
        }
    }

    private void resetBarPosition(Element bar) {
        bar.getStyle().setBackgroundColor(capturePointBgColor);
        bar.getStyle().setProperty("left", capturePointLeftPercentage);
        bar.getStyle().setProperty("width", capturePointWidthPercentage);
        resetBarYPosition(bar);
    }

    private void resetBarYPosition(Element bar) {
        bar.getStyle().setTop(capturePointTopPx, Unit.PX);
    }

    private void disableClickOnNextMouseUp() {
        clickOnNextMouseUp = false;
    }

    private void cancelDoubleClickDetection() {
        insideDoubleClickDetectionInterval = false;
        numberOfMouseClicksDetected = 0;
        previousMouseUpEvent = null;
        previousMouseUpBarElement = null;
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

    private double getContentWidth() {
        return getBoundingClientRectWidth(content);
    }

}