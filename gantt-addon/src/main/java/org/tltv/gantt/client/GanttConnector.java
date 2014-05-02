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

import java.util.Date;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.GanttServerRpc;
import org.tltv.gantt.client.shared.GanttState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.LocaleNotLoadedException;
import com.vaadin.client.LocaleService;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;

/**
 * Connector for client side GWT {@link GanttWidget} and server side
 * {@link Gantt} Vaadin component.
 * 
 * @author Tltv
 * 
 */
@Connect(Gantt.class)
public class GanttConnector extends AbstractComponentConnector {

    GanttServerRpc rpc = RpcProxy.create(GanttServerRpc.class, this);

    String locale;
    long timeZoneOffset = 0;
    GanttDateTimeService dateTimeService;
    boolean notifyHeight = false;

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
        public String formatDate(Date date, String formatStr) {
            if (dateTimeService == null) {
                try {
                    dateTimeService = new GanttDateTimeService(getLocale());
                } catch (LocaleNotLoadedException e) {
                    GWT.log("Could not create DateTimeService for the locale "
                            + getLocale(), e);
                    return "";
                }
            }
            return dateTimeService.formatDate(date, formatStr);
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
        public long getTimeZoneOffset() {
            return timeZoneOffset;
        }

    };

    GanttRpc ganttRpc = new GanttRpc() {

        @Override
        public void stepClicked(int index) {
            rpc.stepClicked(index);
        }

        @Override
        public void onMove(int rowIndex, int newRowIndex, long startDate,
                long endDate) {
            rpc.onMove(rowIndex, newRowIndex, startDate, endDate);
        }

        @Override
        public void onResize(int rowIndex, long startDate, long endDate) {
            rpc.onResize(rowIndex, startDate, endDate);
        }
    };

    private int previousHeight = -1;
    private int previousWidth = -1;

    private final ElementResizeListener widgetResizeListener = new ElementResizeListener() {

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
                    }
                });
            }
            if (previousWidth != width) {
                previousWidth = width;
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                    @Override
                    public void execute() {
                        getWidget().notifyWidthChanged(width);
                    }
                });
            }
        }
    };

    public GanttConnector() {
    }

    @Override
    protected void init() {
        super.init();
        getWidget().setBrowserInfo(BrowserInfo.get().isIE(),
                BrowserInfo.get().isIE8(), BrowserInfo.get().isIE9());
        getWidget().setAlwaysCalculatePixelWidths(
                BrowserInfo.get().isSafari() || BrowserInfo.get().isOpera()
                        || BrowserInfo.get().isIE8());
        getWidget().setTouchSupportted(BrowserInfo.get().isTouchDevice());
        getWidget().initWidget(ganttRpc, localeDataProvider);
        getLayoutManager().addElementResizeListener(getWidget().getElement(),
                widgetResizeListener);
    }

    @Override
    public void onUnregister() {
        getLayoutManager().removeElementResizeListener(
                getWidget().getElement(), widgetResizeListener);
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
        timeZoneOffset = getState().timeZoneOffset;
        if (stateChangeEvent.hasPropertyChanged("locale")) {
            dateTimeService = null;
        }

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
        }

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                getWidget().update(getState().steps);
                if (notifyHeight) {
                    getWidget().notifyHeightChanged(previousHeight);
                }
            }
        });
    }
}
