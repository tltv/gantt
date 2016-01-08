/*
 * Copyright 2016 Tomi Virtanen
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.GanttClientRpc;
import org.tltv.gantt.client.shared.GanttServerRpc;
import org.tltv.gantt.client.shared.GanttState;
import org.tltv.gantt.client.shared.Step;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.LocaleNotLoadedException;
import com.vaadin.client.LocaleService;
import com.vaadin.client.WidgetUtil;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.communication.StateChangeEvent.StateChangeHandler;
import com.vaadin.client.ui.AbstractHasComponentsConnector;
import com.vaadin.client.ui.FocusableScrollPanel;
import com.vaadin.client.ui.VScrollTable;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.client.ui.table.TableConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;

/**
 * Connector for client side GWT {@link GanttWidget} and server side
 * {@link Gantt} Vaadin component.
 * 
 * @author Tltv
 * 
 */
@Connect(Gantt.class)
public class GanttConnector extends AbstractHasComponentsConnector {

    GanttServerRpc rpc = RpcProxy.create(GanttServerRpc.class, this);

    String locale;
    String timeZoneId;
    TimeZone timeZone;
    GanttDateTimeService dateTimeService;
    boolean notifyHeight = false;

    ComponentConnector delegateScrollConnector;
    FocusableScrollPanel delegateScrollPanelTarget;
    VScrollTable delegateScrollTableTarget;
    HandlerRegistration ganttScrollHandlerRegistration;
    HandlerRegistration scrollDelegateHandlerRegistration;
    // flag indicating that scroll is delegating right now
    boolean ganttDelegatingVerticalScroll = false;
    boolean delegatingVerticalScroll = false;

    Timer ganttScrollDelay = new Timer() {

        @Override
        public void run() {
            ganttDelegatingVerticalScroll = false;
        }
    };

    Timer scrollDelay = new Timer() {

        @Override
        public void run() {
            delegatingVerticalScroll = false;
        }
    };

    /**
     * Scroll handler for Gantt component to delegate to other component.
     */
    final ScrollHandler ganttScrollHandler = new ScrollHandler() {

        @Override
        public void onScroll(ScrollEvent event) {
            if (delegatingVerticalScroll) {
                // if other component is scrolling, don't allow this scroll
                // event
                return;
            }
            ganttScrollDelay.cancel();
            ganttDelegatingVerticalScroll = true;
            int scrollTop = getWidget().getScrollContainer().getScrollTop();
            try {
                delegateScrollPanelTarget.setScrollPosition(scrollTop);
            } finally {
                ganttScrollDelay.schedule(20);
            }
        }
    };
    /**
     * Scroll handler for scroll events from other component that Gantt may
     * react to.
     */
    final ScrollHandler scrollDelegateTargetHandler = new ScrollHandler() {

        @Override
        public void onScroll(ScrollEvent event) {
            if (ganttDelegatingVerticalScroll) {
                // if gantt is scrolling, don't allow this scroll event
                return;
            }
            scrollDelay.cancel();
            int scrollPosition = delegateScrollPanelTarget.getScrollPosition();
            delegatingVerticalScroll = true;
            try {
                getWidget().getScrollContainer().setScrollTop(scrollPosition);
            } finally {
                scrollDelay.schedule(20);
            }
        }
    };

    final StateChangeHandler scrollDelegateTargetStateChangeHandler = new StateChangeHandler() {

        @Override
        public void onStateChanged(StateChangeEvent stateChangeEvent) {
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                @Override
                public void execute() {
                    adjustDelegateTargetHeightLazily();
                }
            });
        }
    };

    ElementResizeListener scrollDelegateTargetResizeListener = new ElementResizeListener() {

        @Override
        public void onElementResize(ElementResizeEvent e) {
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                @Override
                public void execute() {
                    adjustDelegateTargetHeightLazily();
                }
            });
        }
    };

    Timer lazyAdjustDelegateTargetHeight = new Timer() {

        @Override
        public void run() {
            updateDelegateTargetHeight();
        }
    };

    LocaleDataProvider localeDataProvider = new LocaleDataProvider() {

        @Override
        public String[] getWeekdayNames() {
            try {
                return LocaleService.getDayNames(locale);
            } catch (LocaleNotLoadedException e) {
                GWT.log(e.getMessage(), e);
            }
            // return default
            return new String[] { "sunday", "monday", "tuesday", "wednesday",
                    "thursday", "friday", "saturday" };
        }

        @Override
        public String[] getMonthNames() {
            try {
                return LocaleService.getMonthNames(locale);
            } catch (LocaleNotLoadedException e) {
                GWT.log(e.getMessage(), e);
            }
            // return default
            return new String[] { "January", "February", "March", "April",
                    "May", "June", "July", "August", "September", "October",
                    "November", "December" };
        }

        @Override
        public int getFirstDayOfWeek() {
            try {
                // Gantt uses 1-based index, just as the server-side Java
                // Locale does. Vaadin locale state has 0-based value.
                return LocaleService.getFirstDayOfWeek(locale) + 1;
            } catch (LocaleNotLoadedException e) {
                GWT.log(e.getMessage(), e);
            }
            // return default
            return 1; // sunday
        }

        @Override
        public String formatDate(Date zonedDate, String formatStr) {
            if (dateTimeService == null) {
                try {
                    dateTimeService = new GanttDateTimeService(getLocale());
                } catch (LocaleNotLoadedException e) {
                    GWT.log("Could not create DateTimeService for the locale "
                            + getLocale(), e);
                    return "";
                }
            }
            return dateTimeService.formatDate(zonedDate, formatStr,
                    getTimeZone());
        }

        @Override
        public String formatDate(Date zonedDate, DateTimeFormat formatter) {
            return formatter.format(zonedDate, getTimeZone());
        }

        @Override
        public boolean isTwelveHourClock() {
            try {
                return LocaleService.isTwelveHourClock(locale);
            } catch (LocaleNotLoadedException e) {
                GWT.log(e.getMessage(), e);
            }
            return false;
        }

        @Override
        public String getLocale() {
            return locale;
        }

        @Override
        public long getTimeZoneOffset(Date zonedDate) {
            int offset = -getTimeZone().getOffset(zonedDate) * 60000;
            return offset;
        }

        @Override
        public TimeZone getTimeZone() {
            return timeZone;
        }

        @Override
        public long getDaylightAdjustment(Date zonedDate) {
            return getTimeZone().getDaylightAdjustment(zonedDate) * 60000;
        }

    };

    GanttRpc ganttRpc = new GanttRpc() {

        @Override
        public void stepClicked(String stepUid) {
            rpc.stepClicked(stepUid);
        }

        @Override
        public void onMove(String stepUid, String newStepUid, long startDate,
                long endDate) {
            rpc.onMove(stepUid, newStepUid, startDate, endDate);
        }

        @Override
        public void onResize(String stepUid, long startDate, long endDate) {
            rpc.onResize(stepUid, startDate, endDate);
        }

        @Override
        public boolean onStepRelationSelected(StepWidget source,
                boolean startingPointChanged, Element newRelationStepElement) {
            StepWidget sw = findStepWidgetByElement(newRelationStepElement);
            if (sw == null) {
                return false;
            }

            if (startingPointChanged) {
                // source is target (sw is related to source).
                // sw is new predecessor.

                if (sw.getStep().equals(source.getStep().getPredecessor())) {
                    return false;
                } else if (sw.getStep().equals(source.getStep())) {
                    // remove predecessor
                    rpc.onPredecessorChanged(null, source.getStep().getUid(),
                            source.getStep().getUid());
                    return true;
                }
                rpc.onPredecessorChanged(sw.getStep().getUid(), source
                        .getStep().getUid(), null);
            } else {
                // source is original target (sw is new target)

                if (sw.getStep().equals(source.getStep())) {
                    return false;
                } else if (sw.getStep().equals(
                        source.getStep().getPredecessor())) {
                    // remove predecessor
                    rpc.onPredecessorChanged(null, source.getStep().getUid(),
                            source.getStep().getUid());
                    return true;
                }
                rpc.onPredecessorChanged(source.getStep().getPredecessor()
                        .getUid(), sw.getStep().getUid(), source.getStep()
                        .getUid());
            }
            return true;
        }
    };

    GanttClientRpc ganttClientRpc = new GanttClientRpc() {

        @Override
        public void updateDelegateTargetHeight() {
            GanttConnector.this.adjustDelegateTargetHeightLazily();
        }
    };

    int previousHeight = -1;
    int previousWidth = -1;

    final ElementResizeListener widgetResizeListener = new ElementResizeListener() {

        @Override
        public void onElementResize(ElementResizeEvent e) {
            final int height = e.getElement().getClientHeight();
            final int width = e.getElement().getClientWidth();
            if (previousHeight != height) {
                previousHeight = height;

                Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                    @Override
                    public void execute() {
                        getWidget().notifyHeightChanged(height);
                        updateDelegateTargetHeight();
                    }
                });
            }
            if (previousWidth != width) {
                previousWidth = width;
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                    @Override
                    public void execute() {
                        getWidget().notifyWidthChanged(width);
                        updateAllStepsPredecessors();
                        updateDelegateTargetHeight();
                    }
                });
            }
        }
    };

    public GanttConnector() {
        registerRpc(GanttClientRpc.class, ganttClientRpc);
    }

    @Override
    protected void init() {
        super.init();
        BrowserInfo info = BrowserInfo.get();
        getWidget()
                .setBrowserInfo(info.isIE(), info.isChrome(), info.isSafari(),
                        info.isWebkit(), info.getBrowserMajorVersion());
        // If background grid is not needed, ie9 works without
        // setting alwaysCalculatePixelWidths flag to true.
        getWidget().setAlwaysCalculatePixelWidths(
                info.isSafari() || info.isOpera() || info.isIE8()
                        || info.isIE9());
        getWidget().setTouchSupported(info.isTouchDevice());
        getWidget().initWidget(ganttRpc, localeDataProvider);
        getLayoutManager().addElementResizeListener(getWidget().getElement(),
                widgetResizeListener);
    }

    @Override
    public void onUnregister() {
        getLayoutManager().removeElementResizeListener(
                getWidget().getElement(), widgetResizeListener);
        unRegisterScrollDelegateHandlers();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(GanttWidget.class);
    }

    @Override
    public GanttWidget getWidget() {
        return (GanttWidget) super.getWidget();
    }

    @Override
    public GanttState getState() {
        return (GanttState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        locale = getState().locale;
        timeZoneId = getState().timeZoneId;
        if (stateChangeEvent.hasPropertyChanged("locale")) {
            dateTimeService = null;
        }
        if (stateChangeEvent.hasPropertyChanged("timeZoneId")) {
            if (getState().timeZoneJson != null) {
                timeZone = TimeZone.createTimeZone(getState().timeZoneJson);
            } else {
                timeZone = TimeZone.createTimeZone(0);
            }
        }

        final boolean changeHasInpactToSteps = stateChangeEvent
                .hasPropertyChanged("resolution")
                || stateChangeEvent.hasPropertyChanged("startDate")
                || stateChangeEvent.hasPropertyChanged("endDate");

        if (stateChangeEvent.hasPropertyChanged("monthRowVisible")
                || stateChangeEvent.hasPropertyChanged("yearRowVisible")
                || stateChangeEvent.hasPropertyChanged("monthFormat")
                || stateChangeEvent.hasPropertyChanged("yearFormat")
                || stateChangeEvent.hasPropertyChanged("weekFormat")
                || stateChangeEvent.hasPropertyChanged("dayFormat")) {
            notifyHeight = !stateChangeEvent.isInitialStateChange();
            getWidget().setForceUpdateTimeline();
        }
        if (!notifyHeight && stateChangeEvent.hasPropertyChanged("resolution")) {
            notifyHeight = !stateChangeEvent.isInitialStateChange();
        }

        if (stateChangeEvent.hasPropertyChanged("readOnly")) {
            getWidget().setMovableSteps(
                    !getState().readOnly && getState().movableSteps);
            getWidget().setResizableSteps(
                    !getState().readOnly && getState().resizableSteps);
            for (StepWidget s : getSteps()) {
                s.setReadOnly(getState().readOnly);
            }
        }

        if (stateChangeEvent.hasPropertyChanged("verticalScrollDelegateTarget")) {
            handleVerticalScrollDelegateTargetChange();
        }

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                getWidget().update(getSteps());
                if (notifyHeight) {
                    getWidget().notifyHeightChanged(previousHeight);
                }
                if (changeHasInpactToSteps) {
                    updateAllStepsPredecessors();
                }
                updateVerticalScrollDelegation();
                adjustDelegateTargetHeightLazily();
            }
        });
    }

    protected List<StepWidget> getSteps() {
        List<StepWidget> steps = new ArrayList<StepWidget>();
        for (Connector sc : getState().steps) {
            steps.add(((StepConnector) sc).getWidget());
        }
        return steps;
    }

    protected StepWidget findStepWidgetByElement(Element target) {
        for (Widget w : getSteps()) {
            if (w.getElement().isOrHasChild(target)) {
                if (w instanceof StepWidget) {
                    return (StepWidget) w;
                }
            }
        }
        return null;
    }

    protected Map<Step, StepWidget> getStepsMap() {
        Map<Step, StepWidget> steps = new HashMap<Step, StepWidget>();
        StepWidget stepWidget;
        for (Connector sc : getState().steps) {
            stepWidget = ((StepConnector) sc).getWidget();
            steps.put(((StepConnector) sc).getState().step, stepWidget);
        }
        return steps;
    }

    void handleVerticalScrollDelegateTargetChange() {
        Connector c = getState().verticalScrollDelegateTarget;
        unRegisterScrollDelegateHandlers();

        delegateScrollConnector = null;
        delegateScrollTableTarget = null;
        delegateScrollPanelTarget = null;
        if (c instanceof TableConnector) {
            delegateScrollConnector = (TableConnector) c;
            VScrollTable scrolltable = ((TableConnector) c).getWidget();
            delegateScrollTableTarget = scrolltable;
            delegateScrollPanelTarget = scrolltable.scrollBodyPanel;
            registerScrollDelegateHandlers();
        }
    }

    void unRegisterScrollDelegateHandlers() {
        if (scrollDelegateHandlerRegistration != null) {
            scrollDelegateHandlerRegistration.removeHandler();
        }
        if (ganttScrollHandlerRegistration != null) {
            ganttScrollHandlerRegistration.removeHandler();
        }
        if (delegateScrollConnector != null) {
            delegateScrollConnector
                    .removeStateChangeHandler(scrollDelegateTargetStateChangeHandler);
        }
        if (delegateScrollTableTarget != null) {
            getLayoutManager().removeElementResizeListener(
                    delegateScrollTableTarget.getElement(),
                    scrollDelegateTargetResizeListener);
        }
    }

    void registerScrollDelegateHandlers() {
        delegateScrollConnector
                .addStateChangeHandler(scrollDelegateTargetStateChangeHandler);
        getLayoutManager().addElementResizeListener(
                delegateScrollTableTarget.getElement(),
                scrollDelegateTargetResizeListener);
    }

    void updateVerticalScrollDelegation() {
        if (delegateScrollPanelTarget == null) {
            return; // scroll delegation is not set
        }
        // register scroll handler to Gantt widget
        ganttScrollHandlerRegistration = getWidget().addDomHandler(
                ganttScrollHandler, ScrollEvent.getType());

        // register a scroll handler to 'delegation' scroll panel.
        scrollDelegateHandlerRegistration = delegateScrollPanelTarget
                .addScrollHandler(scrollDelegateTargetHandler);

        // add detach listener to unregister scroll handler when its detached.
        delegateScrollPanelTarget.addAttachHandler(new Handler() {

            @Override
            public void onAttachOrDetach(AttachEvent event) {
                if (!event.isAttached()
                        && scrollDelegateHandlerRegistration != null) {
                    scrollDelegateHandlerRegistration.removeHandler();
                }
            }
        });
    }

    void updateDelegateTargetHeight() {
        if (delegateScrollTableTarget == null) {
            return;
        }

        int headerHeight = 0;
        if (delegateScrollTableTarget.tHead != null) {
            // update table header height to match the Gantt widget's header
            // height
            int border = WidgetUtil
                    .measureVerticalBorder(delegateScrollTableTarget.tHead
                            .getElement());
            headerHeight = getWidget().getTimelineHeight();

            delegateScrollTableTarget.tHead.setHeight(Math.max(0, headerHeight
                    - border)
                    + "px");
        }

        int border = WidgetUtil.measureVerticalBorder(delegateScrollPanelTarget
                .getElement());
        // Adjust table's scroll container height to match the Gantt widget's
        // scroll container height.
        int newTableScrollContainerHeight = getWidget()
                .getScrollContainerHeight();
        boolean tableHorScrollbarVisible = border >= WidgetUtil
                .getNativeScrollbarSize();
        if (getWidget().isContentOverflowingHorizontally()) {
            getWidget().hideHorizontalScrollbarSpacer();
            if (tableHorScrollbarVisible) {
                newTableScrollContainerHeight += WidgetUtil
                        .getNativeScrollbarSize();
            }
        } else {
            if (tableHorScrollbarVisible) {
                getWidget().showHorizontalScrollbarSpacer();
            } else {
                getWidget().hideHorizontalScrollbarSpacer();
            }
        }

        delegateScrollPanelTarget.setHeight(Math.max(0,
                newTableScrollContainerHeight) + "px");

        getLayoutManager().setNeedsMeasure(
                (ComponentConnector) getState().verticalScrollDelegateTarget);
    }

    void adjustDelegateTargetHeightLazily() {
        lazyAdjustDelegateTargetHeight.cancel();
        // delay must be more than VScrollTable widget's lazy column adjusting.
        lazyAdjustDelegateTargetHeight.schedule(350);
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
    }

    @Override
    public void onConnectorHierarchyChange(
            ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {

        // StepConnector handles adding new step.
        // Here we handle removing and other necessary changed related
        // hierarchy.
        Set<StepWidget> predecessorRemoved = new HashSet<StepWidget>();
        // remove old steps
        for (ComponentConnector c : connectorHierarchyChangeEvent
                .getOldChildren()) {
            if (!getChildComponents().contains(c)) {
                StepWidget stepWidget = ((StepConnector) c).getWidget();
                getWidget().removeStep(stepWidget);
                predecessorRemoved.add(stepWidget);
            }
        }

        Map<Step, StepWidget> steps = getStepsMap();

        // update new steps with references to gantt widget and locale data
        // provider.
        for (ComponentConnector c : getChildComponents()) {
            StepWidget stepWidget = ((StepConnector) c).getWidget();
            if (!connectorHierarchyChangeEvent.getOldChildren().contains(c)) {
                stepWidget.setGantt(getWidget(), localeDataProvider);
            }

            Step predecessor = ((StepConnector) c).getState().step
                    .getPredecessor();
            if (predecessor != null && !predecessorRemoved.contains(stepWidget)) {
                stepWidget.setPredecessorStepWidget(steps.get(predecessor));
            } else {
                stepWidget.setPredecessorStepWidget(null);
            }
        }

        deferredUpdateAllStepsPredecessors();
    }

    /** Updates all steps predecessor visualizations. */
    public void updateAllStepsPredecessors() {
        for (ComponentConnector c : getChildComponents()) {
            StepWidget stepWidget = ((StepConnector) c).getWidget();
            stepWidget.updatePredecessor();
        }
    }

    private void deferredUpdateAllStepsPredecessors() {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                updateAllStepsPredecessors();
            }
        });
    }

    /**
     * Return {@link StepWidget} objects that are related to the given
     * StepWidget. Via {@link Step#getPredecessor()} for example.
     */
    public Set<StepWidget> findRelatedSteps(Step targetStep,
            List<ComponentConnector> stepConnectors) {
        Set<StepWidget> widgets = new HashSet<StepWidget>();
        for (ComponentConnector con : stepConnectors) {
            StepWidget stepWidget = ((StepConnector) con).getWidget();
            if (targetStep.equals(stepWidget.getStep().getPredecessor())) {
                widgets.add(stepWidget);
            }
        }
        return widgets;
    }

    public StepWidget getStepWidget(Step target) {
        return getStepsMap().get(target);
    }
}
