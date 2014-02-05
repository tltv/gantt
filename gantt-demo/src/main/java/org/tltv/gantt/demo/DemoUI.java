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
package org.tltv.gantt.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.MoveEvent;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.demo.util.Util;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.DateToLongConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("demo")
@Title("Gantt Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.tltv.gantt.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    private Gantt gantt;

    private NativeSelect localeSelect;

    private ClickListener createStepClickListener = new ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {
            Step newStep = new Step();
            Date now = new Date();
            newStep.setStartDate(now.getTime());
            newStep.setEndDate(now.getTime() + (7 * 24 * 3600000));
            openStepEditor(newStep);
        }

    };

    private ValueChangeListener startDateValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            gantt.setStartDate((Date) event.getProperty().getValue());
        }
    };

    private ValueChangeListener endDateValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            gantt.setEndDate((Date) event.getProperty().getValue());
        }
    };

    private ValueChangeListener resolutionValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            gantt.setResolution((org.tltv.gantt.client.shared.Resolution) event
                    .getProperty().getValue());
        }
    };

    private ValueChangeListener localeValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            gantt.setLocale((Locale) event.getProperty().getValue());
        }
    };

    private ValueChangeListener timezoneValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            String tzId = (String) event.getProperty().getValue();
            if ("Default".equals(tzId)) {
                gantt.setTimeZone(null);
                return;
            }
            gantt.setTimeZone(TimeZone.getTimeZone(tzId));
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        createGantt();

        Panel controls = createControls();

        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(controls);
        layout.addComponent(gantt);
        layout.setExpandRatio(gantt, 1);
        setContent(layout);
    }

    private void createGantt() {
        gantt = new Gantt();
        gantt.setWidth(100, Unit.PERCENTAGE);
        gantt.setHeight(500, Unit.PIXELS);
        gantt.setResizableSteps(true);
        gantt.setMovableSteps(true);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        gantt.setStartDate(cal.getTime());
        cal.add(Calendar.YEAR, 1);
        gantt.setEndDate(cal.getTime());

        cal.setTime(new Date());
        Step step1 = new Step("First step");
        step1.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.MONTH, 2);
        step1.setEndDate(cal.getTime().getTime());

        Step step2 = new Step("Second step");
        cal.add(Calendar.DATE, 1);
        step2.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.MONTH, 4);
        step2.setEndDate(cal.getTime().getTime());

        Step step3 = new Step("Third step");
        cal.add(Calendar.DATE, 1);
        step3.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.MONTH, 12);
        step3.setEndDate(cal.getTime().getTime());

        gantt.addStep(step1);
        gantt.addStep(step2);
        gantt.addStep(step3);

        String[] colors = new String[] { "11FF11", "33FF33", "55FF55",
                "77FF77", "99FF99", "BBFFBB", "DDFFDD" };

        cal.setTime(new Date());
        for (int i = 0; i < 10; i++) {
            Step step = new Step("Step " + i);
            step.setStartDate(cal.getTime().getTime());
            cal.add(Calendar.DATE, 14);
            step.setEndDate(cal.getTime().getTime());
            step.setBackgroundColor(colors[i % colors.length]);
            gantt.addStep(step);
        }

        gantt.addClickListener(new Gantt.ClickListener() {

            @Override
            public void onGanttClick(org.tltv.gantt.Gantt.ClickEvent event) {
                openStepEditor(event.getStep());
            }
        });

        gantt.addMoveListener(new Gantt.MoveListener() {

            @Override
            public void onGanttMove(MoveEvent event) {
                Notification.show("Moved " + event.getStep().getCaption()
                        + " to  Start Date: "
                        + new Date(event.getStartDate()).toString()
                        + "End Date: "
                        + new Date(event.getEndDate()).toString());
            }
        });

        gantt.addResizeListener(new Gantt.ResizeListener() {

            @Override
            public void onGanttResize(ResizeEvent event) {
                Notification.show("Resized " + event.getStep().getCaption()
                        + " to  Start Date: "
                        + new Date(event.getStartDate()).toString()
                        + "End Date: "
                        + new Date(event.getEndDate()).toString());
            }
        });
    }

    private Panel createControls() {
        Panel panel = new Panel();
        panel.setWidth(100, Unit.PERCENTAGE);

        HorizontalLayout controls = new HorizontalLayout();
        controls.setSpacing(true);
        controls.setMargin(true);
        panel.setContent(controls);

        DateField start = new DateField("Start date");
        start.setResolution(Resolution.SECOND);
        start.setImmediate(true);
        start.addValueChangeListener(startDateValueChangeListener);
        DateField end = new DateField("End date");
        end.setResolution(Resolution.SECOND);
        end.setImmediate(true);
        end.addValueChangeListener(endDateValueChangeListener);

        Button createStep = new Button("Create New Step...",
                createStepClickListener);

        HorizontalLayout heightAndUnit = new HorizontalLayout(
                Util.createHeightEditor(gantt),
                Util.createHeightUnitEditor(gantt));

        HorizontalLayout widthAndUnit = new HorizontalLayout(
                Util.createWidthEditor(gantt),
                Util.createWidthUnitEditor(gantt));

        NativeSelect reso = new NativeSelect("Resolution");
        reso.setNullSelectionAllowed(false);
        reso.addItem(org.tltv.gantt.client.shared.Resolution.Day);
        reso.addItem(org.tltv.gantt.client.shared.Resolution.Week);
        reso.setValue(gantt.getResolution());
        reso.setImmediate(true);
        reso.addValueChangeListener(resolutionValueChangeListener);

        localeSelect = new NativeSelect("Locale") {
            @Override
            public void attach() {
                super.attach();

                if (getValue() == null) {
                    // use default locale
                    setValue(gantt.getLocale());
                    addValueChangeListener(localeValueChangeListener);
                }
            }
        };
        localeSelect.setNullSelectionAllowed(false);
        for (Locale l : Locale.getAvailableLocales()) {
            localeSelect.addItem(l);
            localeSelect.setItemCaption(l, l.getDisplayName(getLocale()));
        }
        localeSelect.setImmediate(true);

        String[] zones = new String[] { "GMT-0", "GMT-1", "GMT-2", "GMT-3",
                "GMT-4", "GMT-5", "GMT-6", "GMT-7", "GMT-8", "GMT-9", "GMT-10",
                "GMT-11", "GMT-12", "GMT+1", "GMT+2", "GMT+3", "GMT+4",
                "GMT+5", "GMT+6", "GMT+7", "GMT+8", "GMT+9", "GMT+10",
                "GMT+11", "GMT+12", "GMT+13", "GMT+14" };
        NativeSelect timezoneSelect = new NativeSelect("Timezone");
        timezoneSelect.setNullSelectionAllowed(false);
        timezoneSelect.addItem("Default");
        timezoneSelect.setItemCaption("Default", "Default ("
                + TimeZone.getDefault().getDisplayName() + ")");
        for (String timezoneId : zones) {
            TimeZone tz = TimeZone.getTimeZone(timezoneId);
            timezoneSelect.addItem(timezoneId);
            timezoneSelect.setItemCaption(timezoneId,
                    tz.getDisplayName(getLocale()));
        }
        timezoneSelect.setValue("Default");
        timezoneSelect.setImmediate(true);
        timezoneSelect.addValueChangeListener(timezoneValueChangeListener);

        controls.addComponent(start);
        controls.addComponent(end);
        controls.addComponent(reso);
        controls.addComponent(localeSelect);
        controls.addComponent(timezoneSelect);
        controls.addComponent(heightAndUnit);
        controls.addComponent(widthAndUnit);
        controls.addComponent(createStep);
        controls.setComponentAlignment(createStep, Alignment.MIDDLE_LEFT);

        return panel;
    }

    private void openStepEditor(Step step) {
        final Window win = new Window("Step Editor");
        win.center();

        BeanItem<Step> item = new BeanItem<Step>(step);

        final FieldGroup group = new FieldGroup(item);
        group.setBuffered(true);

        TextField captionField = new TextField("Caption");
        captionField.setNullRepresentation("");
        group.bind(captionField, "caption");

        TextField bgField = new TextField("Background color");
        bgField.setNullRepresentation("");
        group.bind(bgField, "backgroundColor");

        DateField startDate = new DateField("Start date");
        startDate.setResolution(Resolution.SECOND);
        startDate.setConverter(new DateToLongConverter());
        group.bind(startDate, "startDate");

        DateField endDate = new DateField("End date");
        endDate.setResolution(Resolution.SECOND);
        endDate.setConverter(new DateToLongConverter());
        group.bind(endDate, "endDate");

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);
        win.setContent(content);

        content.addComponent(captionField);
        content.addComponent(bgField);
        content.addComponent(startDate);
        content.addComponent(endDate);

        HorizontalLayout buttons = new HorizontalLayout();
        content.addComponent(buttons);

        Button ok = new Button("Ok", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    group.commit();
                    Step step = ((BeanItem<Step>) group.getItemDataSource())
                            .getBean();
                    if (!gantt.getSteps().contains(step)) {
                        gantt.addStep(step);
                    }
                    win.close();
                } catch (CommitException e) {
                    Notification.show("Commit failed", e.getMessage(),
                            Type.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
        Button cancel = new Button("Cancel", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                group.discard();
                win.close();
            }
        });
        buttons.addComponent(ok);
        buttons.addComponent(cancel);
        win.setClosable(true);

        getUI().addWindow(win);
    }

}
