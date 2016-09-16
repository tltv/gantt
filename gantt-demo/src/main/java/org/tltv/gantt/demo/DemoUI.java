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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.MoveEvent;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;
import org.tltv.gantt.demo.util.CssColorToColorPickerConverter;
import org.tltv.gantt.demo.util.UriFragmentWrapperFactory;
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
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;

@Theme("demo")
@Title("Gantt Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.tltv.gantt.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    private TimeZone defaultTimeZone;

    private Gantt gantt;

    private NativeSelect localeSelect;
    private NativeSelect reso;

    private DateField start;
    private DateField end;

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MMM dd HH:mm:ss zzz yyyy");

    private HorizontalLayout controls;
    private HorizontalLayout subControls;

    private GanttListener ganttListener;

    private AttachListener ganttAttachListener = new AttachListener() {

        @Override
        public void attach(AttachEvent event) {
            syncLocaleAndTimezone();
        }
    };

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
            org.tltv.gantt.client.shared.Resolution res = (org.tltv.gantt.client.shared.Resolution) event
                    .getProperty().getValue();
            if (validateResolutionChange(res)) {
                gantt.setResolution(res);
            }
        }

    };

    private ValueChangeListener localeValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            gantt.setLocale((Locale) event.getProperty().getValue());

            syncLocaleAndTimezone();
        }
    };

    private ValueChangeListener timezoneValueChangeListener = new ValueChangeListener() {

        @Override
        public void valueChange(ValueChangeEvent event) {
            String tzId = (String) event.getProperty().getValue();
            if ("Default".equals(tzId)) {
                gantt.setTimeZone(getDefaultTimeZone());
            } else {
                gantt.setTimeZone(TimeZone.getTimeZone(tzId));
            }
            syncLocaleAndTimezone();
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        ganttListener = null;
        createGantt();

        MenuBar menu = controlsMenuBar();
        Panel controls = createControls();

        Component wrapper = UriFragmentWrapperFactory.wrapByUriFragment(UI
                .getCurrent().getPage().getUriFragment(), gantt);
        if (wrapper instanceof GanttListener) {
            ganttListener = (GanttListener) wrapper;
        }

        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(menu);
        layout.addComponent(controls);
        layout.addComponent(wrapper);
        layout.setExpandRatio(wrapper, 1);

        setContent(layout);
    }

    private void createGantt() {
        gantt = new Gantt();
        gantt.setWidth(100, Unit.PERCENTAGE);
        gantt.setHeight(500, Unit.PIXELS);
        gantt.setResizableSteps(true);
        gantt.setMovableSteps(true);
        gantt.addAttachListener(ganttAttachListener);
        gantt.setTimeZone(getDefaultTimeZone());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        gantt.setStartDate(cal.getTime());
        cal.add(Calendar.YEAR, 1);
        gantt.setEndDate(cal.getTime());

        cal.setTime(new Date());
        Step step1 = new Step("First step");
        step1.setDescription("Description tooltip");
        step1.setShowProgress(true);
        step1.setProgress(50.0);
        step1.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.MONTH, 2);

        step1.setEndDate(cal.getTime().getTime());

        Step step2 = new Step("Second step");
        step2.setDescription("Description tooltip for second step");
        step2.setShowProgress(true);
        step2.setProgress(15.0);
        cal.add(Calendar.DATE, 1);
        step2.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.MONTH, 4);
        step2.setEndDate(cal.getTime().getTime());
        step2.setPredecessor(step1);

        Step step3 = new Step("Third step");
        step3.setDescription("<b>HTML</b> <i>content</i> is <u>supported</u> in tooltips.");
        cal.add(Calendar.DATE, 1);
        step3.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.MONTH, 12);
        step3.setEndDate(cal.getTime().getTime());
        step3.setPredecessor(step2);

        Step step4 = new Step("Fourth step");
        step4.setDescription("Tooltip is <b>VTooltip</b>. <p>Looks same for all Vaadin components.");
        step4.setStartDate(step2.getStartDate());
        step4.setEndDate(step2.getEndDate());
        step4.setPredecessor(step1);

        Step stepWithSubSteps = new Step("Step with sub-steps");
        stepWithSubSteps.setDescription("Tooltip for Step with sub-steps");

        cal.setTime(new Date(step1.getStartDate()));
        cal.add(Calendar.DATE, 7);

        SubStep subStep1 = new SubStep("Sub-step A");
        subStep1.setDescription("Tooltip for Sub-step A");
        subStep1.setBackgroundColor("A8D9DD");
        subStep1.setStartDate(step1.getStartDate());
        subStep1.setEndDate(cal.getTime());

        SubStep subStep2 = new SubStep("Sub-step B");
        subStep2.setDescription("Tooltip for Sub-step B");
        subStep2.setBackgroundColor("A8D9BB");
        subStep2.setStartDate(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        subStep2.setEndDate(cal.getTime());

        SubStep subStep3 = new SubStep("Sub-step C");
        subStep3.setDescription("Tooltip for Sub-step C");
        subStep3.setBackgroundColor("A8D999");
        subStep3.setStartDate(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        subStep3.setEndDate(step1.getEndDate());

        stepWithSubSteps.addSubStep(subStep1);
        stepWithSubSteps.addSubStep(subStep2);
        stepWithSubSteps.addSubStep(subStep3);

        gantt.addStep(step1);
        gantt.addStep(step2);
        gantt.addStep(step3);
        gantt.addStep(step4);
        gantt.addStep(stepWithSubSteps);

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
                Date start = new Date(event.getStartDate());
                Date end = new Date(event.getEndDate());

                dateFormat.setTimeZone(gantt.getTimeZone());

                Notification.show("Moved " + event.getStep().getCaption()
                        + " to Start Date: " + dateFormat.format(start)
                        + " End Date: " + dateFormat.format(end),
                        Type.TRAY_NOTIFICATION);
            }
        });

        gantt.addResizeListener(new Gantt.ResizeListener() {

            @Override
            public void onGanttResize(ResizeEvent event) {
                Date start = new Date(event.getStartDate());
                Date end = new Date(event.getEndDate());

                dateFormat.setTimeZone(gantt.getTimeZone());

                Notification.show("Resized " + event.getStep().getCaption()
                        + " to Start Date: " + dateFormat.format(start)
                        + " End Date: " + dateFormat.format(end),
                        Type.TRAY_NOTIFICATION);
            }
        });
    }

    private void syncLocaleAndTimezone() {
        start.removeValueChangeListener(startDateValueChangeListener);
        end.removeValueChangeListener(endDateValueChangeListener);
        try {
            start.setLocale(gantt.getLocale());
            start.setTimeZone(gantt.getTimeZone());
            start.setValue(gantt.getStartDate());
            end.setLocale(gantt.getLocale());
            end.setTimeZone(gantt.getTimeZone());
            end.setValue(gantt.getEndDate());
        } finally {
            start.addValueChangeListener(startDateValueChangeListener);
            end.addValueChangeListener(endDateValueChangeListener);
        }
        dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy",
                gantt.getLocale());
    }

    private Panel createControls() {
        Panel panel = new Panel();
        panel.setWidth(100, Unit.PERCENTAGE);

        controls = new HorizontalLayout();
        controls.setSpacing(true);
        controls.setMargin(true);
        panel.setContent(controls);

        subControls = new HorizontalLayout();
        subControls.setSpacing(true);
        subControls.setVisible(false);

        start = createStartDateField();
        end = createEndDateField();

        Button createStep = new Button("Create New Step...",
                createStepClickListener);

        HorizontalLayout heightAndUnit = new HorizontalLayout(
                Util.createHeightEditor(gantt),
                Util.createHeightUnitEditor(gantt));

        HorizontalLayout widthAndUnit = new HorizontalLayout(
                Util.createWidthEditor(gantt),
                Util.createWidthUnitEditor(gantt));

        reso = new NativeSelect("Resolution");
        reso.setNullSelectionAllowed(false);
        reso.addItem(org.tltv.gantt.client.shared.Resolution.Hour);
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

        ComboBox timezoneSelect = new ComboBox("Timezone");
        timezoneSelect.setFilteringMode(FilteringMode.CONTAINS);
        timezoneSelect.setNullSelectionAllowed(false);
        timezoneSelect.addItem("Default");
        timezoneSelect.setItemCaption("Default", "Default ("
                + getDefaultTimeZone().getDisplayName() + ")");
        for (String timezoneId : Util.getSupportedTimeZoneIDs()) {
            TimeZone tz = TimeZone.getTimeZone(timezoneId);
            timezoneSelect.addItem(timezoneId);
            timezoneSelect.setItemCaption(timezoneId, tz.getID()
                    + " (raw offset " + (tz.getRawOffset() / 60000) + "m)");
        }
        timezoneSelect.setValue("Default");
        timezoneSelect.setImmediate(true);
        timezoneSelect.addValueChangeListener(timezoneValueChangeListener);

        final Button toggleSubControlsBtn = new Button("Show More Settings...");
        toggleSubControlsBtn.addStyleName("link");
        toggleSubControlsBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                subControls.setVisible(!subControls.isVisible());
                toggleSubControlsBtn.setCaption(subControls.isVisible() ? "Less Settings..."
                        : "More Settings...");
            }
        });

        controls.addComponent(start);
        controls.addComponent(end);
        controls.addComponent(reso);
        controls.addComponent(subControls);
        controls.addComponent(toggleSubControlsBtn);
        controls.setComponentAlignment(toggleSubControlsBtn,
                Alignment.BOTTOM_CENTER);

        subControls.addComponent(localeSelect);
        subControls.addComponent(timezoneSelect);
        subControls.addComponent(heightAndUnit);
        subControls.addComponent(widthAndUnit);
        subControls.addComponent(createStep);
        subControls.setComponentAlignment(createStep, Alignment.MIDDLE_LEFT);

        return panel;
    }

    private DateField createStartDateField() {
        DateField f = new DateField("Start date");
        f.setResolution(Resolution.SECOND);
        f.setImmediate(true);
        f.addValueChangeListener(startDateValueChangeListener);
        return f;
    }

    private DateField createEndDateField() {
        DateField f = new DateField("End date");
        f.setResolution(Resolution.SECOND);
        f.setImmediate(true);
        f.addValueChangeListener(endDateValueChangeListener);
        return f;
    }

    private boolean validateResolutionChange(
            final org.tltv.gantt.client.shared.Resolution res) {
        long max = 5 * 12 * 4 * 7 * 24 * 3600000L;
        if (res == org.tltv.gantt.client.shared.Resolution.Hour
                && (gantt.getEndDate().getTime() - gantt.getStartDate()
                        .getTime()) > max) {

            // revert to previous resolution
            setResolution(gantt.getResolution());

            // make user to confirm hour resolution, if the timeline range is
            // very long.
            Util.showConfirmationPopup(
                    "Timeline range is a quite long for hour resolution. Rendering may be slow. Continue anyway?",
                    new Runnable() {

                        @Override
                        public void run() {
                            setResolution(res);
                            gantt.setResolution(res);
                        }
                    });
            return false;
        }
        return true;
    }

    private void setResolution(
            org.tltv.gantt.client.shared.Resolution resolution) {
        reso.removeValueChangeListener(resolutionValueChangeListener);
        try {
            reso.setValue(resolution);
        } finally {
            reso.addValueChangeListener(resolutionValueChangeListener);
        }
    }

    private MenuBar controlsMenuBar() {
        MenuBar menu = new MenuBar();
        MenuItem editItem = menu.addItem("Edit", null);
        MenuItem formatItem = menu.addItem("Format", null);
        MenuItem viewItem = menu.addItem("View", null);

        MenuItem item = editItem.addItem("Enabled", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setEnabled(!gantt.isEnabled());
                selectedItem.setChecked(gantt.isEnabled());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isEnabled());

        item = editItem.addItem("Read-only", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setReadOnly(!gantt.isReadOnly());
                selectedItem.setChecked(gantt.isReadOnly());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isReadOnly());

        editItem.addSeparator();
        item = editItem.addItem("Create New Step...", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                createStepClickListener.buttonClick(null);
            }
        });

        item = formatItem.addItem("Set 'MMM' month format", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setTimelineMonthFormat("MMM");
            }
        });
        item = formatItem.addItem("Set 'MMMM' month format", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setTimelineMonthFormat("MMMM");
            }
        });
        item = formatItem.addItem("Set 'yyyy MMMM' month format",
                new Command() {

                    @Override
                    public void menuSelected(MenuItem selectedItem) {
                        gantt.setTimelineMonthFormat("yyyy MMMM");
                    }
                });
        item = formatItem.addItem("Set 'dd.' week format", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setTimelineWeekFormat("dd.");
            }
        });
        item = formatItem.addItem("Set week number week format", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setTimelineWeekFormat(null);
            }
        });
        item = formatItem.addItem(
                "Set 'dd. EEEE' day format for Hour resolution", new Command() {

                    @Override
                    public void menuSelected(MenuItem selectedItem) {
                        gantt.setTimelineDayFormat("dd. EEEE");
                    }
                });

        item = viewItem.addItem("Show Control Panel", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                controls.setVisible(!controls.isVisible());
                selectedItem.setChecked(controls.isVisible());
            }
        });
        item.setCheckable(true);
        item.setChecked(true);

        viewItem.addSeparator();
        item = viewItem.addItem("Show years", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setYearsVisible(!gantt.isYearsVisible());
                selectedItem.setChecked(gantt.isYearsVisible());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isYearsVisible());

        item = viewItem.addItem("Show months", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                gantt.setMonthsVisible(!gantt.isMonthsVisible());
                selectedItem.setChecked(gantt.isMonthsVisible());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isMonthsVisible());

        item = viewItem.addItem("Show Gantt with Table", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("#table");
                getPage().reload();
            }
        });
        item = viewItem.addItem("Show Gantt with TreeTable", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("#treetable");
                getPage().reload();
            }
        });
        item = viewItem.addItem("Show Gantt alone", new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("#");
                getPage().reload();
            }
        });

        return menu;
    }

    private void openStepEditor(AbstractStep step) {
        final Window win = new Window("Step Editor");
        win.setResizable(false);
        win.center();

        final Collection<Component> hidden = new ArrayList<Component>();

        BeanItem<AbstractStep> item = new BeanItem<AbstractStep>(step);

        final FieldGroup group = new FieldGroup(item);
        group.setBuffered(true);

        TextField captionField = new TextField("Caption");
        captionField.setNullRepresentation("");
        group.bind(captionField, "caption");

        TextField descriptionField = new TextField("Description");
        descriptionField.setNullRepresentation("");
        group.bind(descriptionField, "description");
        descriptionField.setVisible(false);
        hidden.add(descriptionField);

        NativeSelect captionMode = new NativeSelect("Caption Mode");
        captionMode.addItem(Step.CaptionMode.TEXT);
        captionMode.addItem(Step.CaptionMode.HTML);
        group.bind(captionMode, "captionMode");
        captionMode.setVisible(false);
        hidden.add(captionMode);

        CheckBox resizable = new CheckBox("Resizable");
        group.bind(resizable, "resizable");
        resizable.setVisible(false);
        hidden.add(resizable);

        CheckBox showProgress = new CheckBox("Show progress");
        group.bind(showProgress, "showProgress");
        showProgress.setVisible(false);
        hidden.add(showProgress);

        Slider progress = new Slider("Progress");
        progress.setWidth(100, Unit.PERCENTAGE);
        group.bind(progress, "progress");
        progress.setVisible(false);
        hidden.add(progress);

        NativeSelect predecessorSelect = new NativeSelect("Predecessor Step");
        predecessorSelect.setWidth(100, Unit.PERCENTAGE);
        fillPredecessorCanditatesToSelect(step, predecessorSelect);
        predecessorSelect.setEnabled(step instanceof Step);
        if (step instanceof Step) {
            group.bind(predecessorSelect, "predecessor");
        }
        predecessorSelect.setVisible(false);
        hidden.add(predecessorSelect);

        final NativeSelect parentStepSelect = new NativeSelect("Parent Step");
        parentStepSelect.setWidth(100, Unit.PERCENTAGE);
        parentStepSelect.setEnabled(false);
        fillParentStepCanditatesToSelect(step, parentStepSelect);
        parentStepSelect.setVisible(false);
        hidden.add(parentStepSelect);

        HorizontalLayout colorLayout = new HorizontalLayout();
        colorLayout.setWidth(100, Unit.PERCENTAGE);
        colorLayout.setVisible(false);
        hidden.add(colorLayout);

        final TextField bgField = new TextField("Background color");
        bgField.setNullRepresentation("");
        group.bind(bgField, "backgroundColor");
        bgField.setEnabled(false);

        final ColorPicker bgColorPicker = new ColorPicker();
        bgColorPicker.setPosition(300, 100);
        bgColorPicker.setColor(new CssColorToColorPickerConverter()
                .convertToModel(step.getBackgroundColor()));
        bgColorPicker.addColorChangeListener(new ColorChangeListener() {
            @Override
            public void colorChanged(ColorChangeEvent event) {
                bgField.setValue(event.getColor().getCSS());
            }
        });

        colorLayout.addComponent(bgField);
        colorLayout.addComponent(bgColorPicker);
        colorLayout.setExpandRatio(bgField, 1);
        colorLayout.setComponentAlignment(bgColorPicker, Alignment.BOTTOM_LEFT);

        DateField startDate = new DateField("Start date");
        startDate.setLocale(gantt.getLocale());
        startDate.setTimeZone(gantt.getTimeZone());
        startDate.setResolution(Resolution.SECOND);
        startDate.setConverter(new DateToLongConverter());
        group.bind(startDate, "startDate");

        DateField endDate = new DateField("End date");
        endDate.setLocale(gantt.getLocale());
        endDate.setTimeZone(gantt.getTimeZone());
        endDate.setResolution(Resolution.SECOND);
        endDate.setConverter(new DateToLongConverter());
        group.bind(endDate, "endDate");

        CheckBox showMore = new CheckBox("Show all settings");
        showMore.addValueChangeListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                for (Component c : hidden) {
                    c.setVisible((Boolean) event.getProperty().getValue());
                }
                win.center();
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);
        win.setContent(content);

        content.addComponent(captionField);
        content.addComponent(captionMode);
        content.addComponent(descriptionField);
        content.addComponent(resizable);
        content.addComponent(showProgress);
        content.addComponent(progress);
        content.addComponent(predecessorSelect);
        content.addComponent(parentStepSelect);
        content.addComponent(colorLayout);
        content.addComponent(startDate);
        content.addComponent(endDate);
        content.addComponent(showMore);

        HorizontalLayout buttons = new HorizontalLayout();
        content.addComponent(buttons);

        Button ok = new Button("Ok", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                commit(win, group, parentStepSelect);
            }

        });
        Button cancel = new Button("Cancel", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                cancel(win, group);
            }
        });
        Button delete = new Button("Delete", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                delete(win, group);
            }

        });
        buttons.addComponent(ok);
        buttons.addComponent(cancel);
        buttons.addComponent(delete);
        win.setClosable(true);

        getUI().addWindow(win);
    }

    private void fillPredecessorCanditatesToSelect(AbstractStep step,
            final NativeSelect predecessorSelect) {
        for (Step stepCanditate : gantt.getSteps()) {
            if (!stepCanditate.equals(step) && stepCanditate instanceof Step) {
                addItemToSelect(predecessorSelect, stepCanditate);
            }
        }
    }

    private void fillParentStepCanditatesToSelect(AbstractStep step,
            final NativeSelect parentStepSelect) {
        if (!gantt.getSteps().contains(step)) {
            // new step
            parentStepSelect.setEnabled(true);
            for (Step parentStepCanditate : gantt.getSteps()) {
                addItemToSelect(parentStepSelect, parentStepCanditate);
                if (step instanceof SubStep) {
                    if (parentStepCanditate.getSubSteps().contains(step)) {
                        parentStepSelect.setValue(parentStepCanditate);
                        parentStepSelect.setEnabled(false);
                        break;
                    }
                }
            }
        }
    }

    private void addItemToSelect(NativeSelect select, AbstractStep step) {
        select.addItem(step);
        select.setItemCaption(step, step.getCaption());
    }

    @SuppressWarnings("unchecked")
    private void commit(final Window win, final FieldGroup group,
            final NativeSelect parentStepSelect) {
        try {
            group.commit();
            AbstractStep step = ((BeanItem<AbstractStep>) group
                    .getItemDataSource()).getBean();
            gantt.markStepDirty(step);
            if (parentStepSelect.isEnabled()
                    && parentStepSelect.getValue() != null) {
                SubStep subStep = addSubStep(parentStepSelect, step);
                step = subStep;
            }
            if (step instanceof Step && !gantt.getSteps().contains(step)) {
                gantt.addStep((Step) step);
            }
            if (ganttListener != null && step instanceof Step) {
                ganttListener.stepModified((Step) step);
            }
            win.close();
        } catch (CommitException e) {
            Notification.show("Commit failed", e.getMessage(),
                    Type.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void cancel(final Window win, final FieldGroup group) {
        group.discard();
        win.close();
    }

    @SuppressWarnings("unchecked")
    private void delete(final Window win, final FieldGroup group) {
        AbstractStep step = ((BeanItem<AbstractStep>) group.getItemDataSource())
                .getBean();
        if (step instanceof SubStep) {
            SubStep substep = (SubStep) step;
            substep.getOwner().removeSubStep(substep);
        } else {
            gantt.removeStep((Step) step);
            if (ganttListener != null) {
                ganttListener.stepDeleted((Step) step);
            }
        }
        win.close();
    }

    private SubStep addSubStep(final NativeSelect parentStepSelect,
            AbstractStep dataSource) {
        SubStep subStep = new SubStep();
        subStep.setCaption(dataSource.getCaption());
        subStep.setCaptionMode(dataSource.getCaptionMode());
        subStep.setStartDate(dataSource.getStartDate());
        subStep.setEndDate(dataSource.getEndDate());
        subStep.setBackgroundColor(dataSource.getBackgroundColor());
        subStep.setDescription(dataSource.getDescription());
        subStep.setProgress(dataSource.getProgress());
        subStep.setShowProgress(dataSource.isShowProgress());
        subStep.setStyleName(dataSource.getStyleName());
        ((Step) parentStepSelect.getValue()).addSubStep(subStep);
        return subStep;
    }

    private TimeZone getDefaultTimeZone() {
        if (defaultTimeZone != null) {
            return defaultTimeZone;
        }
        TimeZone tz = TimeZone.getDefault();
        if (Util.getSupportedTimeZoneIDs().contains(tz.getID())) {
            defaultTimeZone = tz;
        } else {
            defaultTimeZone = TimeZone.getTimeZone("Europe/Helsinki");
        }
        return defaultTimeZone;
    }
}
