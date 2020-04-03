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
package de.adito.aditoweb.vaadin.addons.gantt.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification.Type;
import de.adito.aditoweb.vaadin.addons.gantt.Gantt;
import de.adito.aditoweb.vaadin.addons.gantt.GanttTimeUtil;
import de.adito.aditoweb.vaadin.addons.gantt.client.shared.AbstractStep;
import de.adito.aditoweb.vaadin.addons.gantt.client.shared.Resolution;
import de.adito.aditoweb.vaadin.addons.gantt.client.shared.Step;
import de.adito.aditoweb.vaadin.addons.gantt.client.shared.SubStep;
import de.adito.aditoweb.vaadin.addons.gantt.demo.util.CssColorToColorPickerConverter;
import de.adito.aditoweb.vaadin.addons.gantt.demo.util.UriFragmentWrapperFactory;
import de.adito.aditoweb.vaadin.addons.gantt.demo.util.Util;

import javax.servlet.annotation.WebServlet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Theme("demo")
@Title("Gantt Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "de.adito.aditoweb.vaadin.addons.gantt.demo.WidgetSet")
    public static class Servlet extends VaadinServlet
    {
    }

    private TimeZone defaultTimeZone;

    private Gantt gantt;

    private NativeSelect<Locale> localeSelect;
    private NativeSelect<Resolution> reso;

    private DateTimeField start;
    private DateTimeField end;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy");

    private HorizontalLayout controls;
    private HorizontalLayout subControls;

    private GanttListener ganttListener;

    private AttachListener ganttAttachListener = new AttachListener()
    {

        @Override
        public void attach(AttachEvent event)
        {
            syncLocale();
        }
    };

    private ClickListener createStepClickListener = new ClickListener()
    {

        @Override
        public void buttonClick(ClickEvent event)
        {
            Step newStep = new Step();
            Date now = new Date();
            newStep.setStartDate(now.getTime());
            newStep.setEndDate(now.getTime() + (7 * 24 * 3600000));
            openStepEditor(newStep);
        }

    };

    private Optional<Registration> startDateValueChangeRegistration;
    private ValueChangeListener<LocalDateTime> startDateValueChangeListener = new ValueChangeListener<LocalDateTime>()
    {

        @Override
        public void valueChange(ValueChangeEvent<LocalDateTime> event)
        {
            gantt.setStartDate(event.getValue());
        }
    };

    private Optional<Registration> endDateValueChangeRegistration;
    private ValueChangeListener<LocalDateTime> endDateValueChangeListener = new ValueChangeListener<LocalDateTime>()
    {

        @Override
        public void valueChange(ValueChangeEvent<LocalDateTime> event)
        {
            gantt.setEndDate(event.getValue());
        }
    };

    private Optional<Registration> resolutionValueChangeRegistration;
    private ValueChangeListener<Resolution> resolutionValueChangeListener = new ValueChangeListener<Resolution>()
    {

        @Override
        public void valueChange(ValueChangeEvent<Resolution> event)
        {
            if (validateResolutionChange(event.getValue()))
            {
                gantt.setResolution(event.getValue());
            }
        }

    };

    private ValueChangeListener<Locale> localeValueChangeListener = new ValueChangeListener<Locale>()
    {

        @Override
        public void valueChange(ValueChangeEvent<Locale> event)
        {
            gantt.setLocale(event.getValue());

            syncLocale();
        }
    };

    private ValueChangeListener<String> timezoneValueChangeListener = new ValueChangeListener<String>()
    {

        @Override
        public void valueChange(ValueChangeEvent<String> event)
        {
            String tzId = event.getValue();
            if ("Default".equals(tzId))
            {
                gantt.setTimeZone(getDefaultTimeZone());
            } else
            {
                gantt.setTimeZone(TimeZone.getTimeZone(tzId));
            }
            syncLocale();
        }
    };

    @Override
    protected void init(VaadinRequest request)
    {
        ganttListener = null;
        createGantt();

        MenuBar menu = controlsMenuBar();
        Panel controls = createControls();

        Component wrapper = UriFragmentWrapperFactory.wrapByUriFragment(UI.getCurrent().getPage().getUriFragment(),
                gantt);
        if (wrapper instanceof GanttListener)
        {
            ganttListener = (GanttListener) wrapper;
        }

        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setMargin(false);
        layout.setSizeFull();
        layout.addComponent(menu);
        layout.addComponent(controls);
        layout.addComponent(wrapper);
        layout.setExpandRatio(wrapper, 1);

        setContent(layout);
    }

    private void createGantt()
    {
        gantt = new Gantt();
        gantt.setWidth(100, Unit.PERCENTAGE);
        gantt.setHeight(500, Unit.PIXELS);
        gantt.setResizableSteps(true);
        gantt.setMovableSteps(true);
        gantt.setYearsVisible(false);
        gantt.addAttachListener(ganttAttachListener);
        gantt.setTimeZone(getDefaultTimeZone());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        gantt.setStartDate(cal.getTime());
        cal.add(Calendar.YEAR, 1);
        gantt.setEndDate(cal.getTime());

        cal.setTime(new Date());

        Step stepGroup = new Step("Cebit");
        stepGroup.setDescription("Description tooltip");
        stepGroup.setGroup(true);
        stepGroup.setBackgroundColor("#145");
        stepGroup.setForegroundColor("#145");
        stepGroup.addStyleName("testphase sdf");
        stepGroup.addStyleNames("sdddf", "dddd");
        stepGroup.removeStyleNames("testphase sdf");

        Step step1 = new Step("First step");
        step1.setDescription("Description tooltip");
        step1.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.WEEK_OF_MONTH, 2);
        step1.setEndDate(cal.getTime().getTime());
        step1.setPredecessor(stepGroup);
        step1.setBackgroundColor("#44444");

        Step step2 = new Step("Verbunden mit First Step");
        step2.setDescription("Description tooltip for second step");
        cal.add(Calendar.DATE, 1);
        step2.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.WEEK_OF_MONTH, 4);
        step2.setEndDate(cal.getTime().getTime());
        step2.setPredecessor(step1);
        step2.setBackgroundColor("#44444");

        Step step3 = new Step("Verbunden mit Root");
        cal.add(Calendar.DATE, 1);
        step3.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.WEEK_OF_MONTH, 2);
        step3.setEndDate(cal.getTime().getTime());
        step3.addStyleName("testphase sdf");
        step3.setStyleName("tres");
        step3.setPredecessor(stepGroup);
        step3.setBackgroundColor("#44444");

        Step step4 = new Step("Verbunden mit Root V2");
        cal.add(Calendar.DATE, 1);
        step4.setStartDate(cal.getTime().getTime());
        cal.add(Calendar.WEEK_OF_MONTH, 2);
        step4.setEndDate(cal.getTime().getTime());
        step4.setPredecessor(stepGroup);
        step4.setBackgroundColor("#44444");

//        Step stepWithSubSteps = new Step("Step with sub-steps");
//        stepWithSubSteps.setDescription("Tooltip for Step with sub-steps");
//
//        cal.setTime(new Date(step1.getStartDate()));
//        cal.add(Calendar.DATE, 7);
//
//        SubStep subStep1 = new SubStep("Sub-step A");
//        subStep1.setDescription("Tooltip for Sub-step A");
//        subStep1.setBackgroundColor("A8D9DD");
//        subStep1.setStartDate(step1.getStartDate());
//        subStep1.setEndDate(cal.getTime());
//
//        SubStep subStep2 = new SubStep("Sub-step B");
//        subStep2.setDescription("Tooltip for Sub-step B");
//        subStep2.setBackgroundColor("A8D9BB");
//        subStep2.setStartDate(cal.getTime());
//        cal.add(Calendar.MONTH, 1);
//        subStep2.setEndDate(cal.getTime());
//
//        SubStep subStep3 = new SubStep("Sub-step C");
//        subStep3.setDescription("Tooltip for Sub-step C");
//        subStep3.setBackgroundColor("A8D999");
//        subStep3.setStartDate(cal.getTime());
//        cal.add(Calendar.MONTH, 1);
//        subStep3.setEndDate(step1.getEndDate());
//
//        stepWithSubSteps.addSubStep(subStep1);
//        stepWithSubSteps.addSubStep(subStep2);
//        stepWithSubSteps.addSubStep(subStep3);

        gantt.addStep(stepGroup);
        gantt.addStep(step1);
        gantt.addStep(step2);
        gantt.addStep(step3);
        gantt.addStep(step4);
//        gantt.addStep(stepWithSubSteps);

//        String[] colors = new String[] { "11FF11", "33FF33", "55FF55", "77FF77", "99FF99", "BBFFBB", "DDFFDD" };
//
//        cal.setTime(new Date());
//        for (int i = 0; i < 10; i++) {
//            Step step = new Step("Step " + i);
//            step.setStartDate(cal.getTime().getTime());
//            cal.add(Calendar.DATE, 14);
//            step.setEndDate(cal.getTime().getTime());
//            step.setBackgroundColor(colors[i % colors.length]);
//            gantt.addStep(step);
//        }

        gantt.addClickListener(new Gantt.ClickListener()
        {

            @Override
            public void onGanttClick(Gantt.ClickEvent event)
            {
                if (event.getDetails().isDoubleClick())
                {
                    Notification.show(String.format("Double Click on Step %s", event.getStep().getCaption()),
                            Type.TRAY_NOTIFICATION);
                }
                if (MouseButton.RIGHT.equals(event.getDetails().getButton()))
                {
                    Notification.show(String.format("Right Click on Step %s", event.getStep().getCaption()),
                            Type.TRAY_NOTIFICATION);
                } else
                {
                    openStepEditor(event.getStep());
                }
            }
        });

        gantt.addMoveListener(new Gantt.MoveListener()
        {

            @Override
            public void onGanttMove(Gantt.MoveEvent event)
            {
                Date start = new Date(event.getStartDate());
                Date end = new Date(event.getEndDate());

                dateFormat.setTimeZone(gantt.getTimeZone());

                String message = "Moved " + event.getStep().getCaption() + " to Start Date: " + dateFormat.format(start)
                        + " End Date: " + dateFormat.format(end);

                if (gantt.isMovableStepsBetweenRows() && event.getStepIndex() != event.getPreviousStepIndex())
                {
                    message += " New Step index: " + event.getStepIndex();

                    if (ganttListener != null && event.getStep() instanceof Step)
                    {
                        ganttListener.stepMoved((Step) event.getStep(), event.getStepIndex(),
                                event.getPreviousStepIndex());
                    }
                }

                showNotificationWithMousedetails(message, event.getDetails());
            }
        });

        gantt.addResizeListener(new Gantt.ResizeListener()
        {

            @Override
            public void onGanttResize(Gantt.ResizeEvent event)
            {
                Date start = new Date(event.getStartDate());
                Date end = new Date(event.getEndDate());

                dateFormat.setTimeZone(gantt.getTimeZone());

                showNotificationWithMousedetails("Resized " + event.getStep().getCaption() + " to Start Date: "
                        + dateFormat.format(start) + " End Date: " + dateFormat.format(end), event.getDetails());
            }
        });
    }

    private void showNotificationWithMousedetails(String msg, MouseEventDetails details)
    {
        String detailsTxt = "";
        if (details.isCtrlKey())
        {
            detailsTxt = "(Ctrl down) ";
        }
        Notification.show(detailsTxt + msg, Type.TRAY_NOTIFICATION);
    }

    private void syncLocale()
    {
        startDateValueChangeRegistration.ifPresent(Registration::remove);
        endDateValueChangeRegistration.ifPresent(Registration::remove);
        try
        {
            start.setLocale(gantt.getLocale());
            start.setValue(gantt.getStartLocalDateTime());
            end.setLocale(gantt.getLocale());
            end.setValue(gantt.getEndLocalDateTime());
        } finally
        {
            startDateValueChangeRegistration = Optional.of(start.addValueChangeListener(startDateValueChangeListener));
            endDateValueChangeRegistration = Optional.of(end.addValueChangeListener(endDateValueChangeListener));
        }
        dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy", gantt.getLocale());
    }

    private Panel createControls()
    {
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

        Button createStep = new Button("Create New Step...", createStepClickListener);

        HorizontalLayout heightAndUnit = new HorizontalLayout(Util.createHeightEditor(gantt),
                Util.createHeightUnitEditor(gantt));

        HorizontalLayout widthAndUnit = new HorizontalLayout(Util.createWidthEditor(gantt),
                Util.createWidthUnitEditor(gantt));

        reso = new NativeSelect<Resolution>("Resolution");
        reso.setEmptySelectionAllowed(false);
        reso.setItems(Resolution.Hour, Resolution.Day,
                Resolution.Week);
        reso.setValue(gantt.getResolution());
        resolutionValueChangeRegistration = Optional.of(reso.addValueChangeListener(resolutionValueChangeListener));

        localeSelect = new NativeSelect<Locale>("Locale")
        {
            @Override
            public void attach()
            {
                super.attach();

                if (getValue() == null)
                {
                    // use default locale
                    setValue(gantt.getLocale());
                    addValueChangeListener(localeValueChangeListener);
                }
            }
        };
        localeSelect.setEmptySelectionAllowed(false);
        localeSelect.setItems(Locale.getAvailableLocales());
        localeSelect.setItemCaptionGenerator((l) -> l.getDisplayName(getLocale()));

        ComboBox<String> timezoneSelect = new ComboBox<String>("Timezone");
        timezoneSelect.setWidth(300, Unit.PIXELS);
        timezoneSelect.setEmptySelectionAllowed(false);
        timezoneSelect.setItemCaptionGenerator(new ItemCaptionGenerator<String>()
        {

            @Override
            public String apply(String item)
            {
                if ("Default".equals(item))
                {
                    return "Default (" + getDefaultTimeZone().getDisplayName() + ")";
                }
                TimeZone tz = TimeZone.getTimeZone(item);
                return tz.getID() + " (raw offset " + (tz.getRawOffset() / 60000) + "m)";
            }
        });
        List<String> items = new ArrayList<>();
        items.add("Default");
        items.addAll(Gantt.getSupportedTimeZoneIDs());
        timezoneSelect.setItems((caption, fltr) -> caption.contains(fltr), items);
        timezoneSelect.setValue("Default");
        timezoneSelect.addValueChangeListener(timezoneValueChangeListener);

        final Button toggleSubControlsBtn = new Button("Show More Settings...");
        toggleSubControlsBtn.addStyleName("link");
        toggleSubControlsBtn.addClickListener(new ClickListener()
        {

            @Override
            public void buttonClick(ClickEvent event)
            {
                subControls.setVisible(!subControls.isVisible());
                toggleSubControlsBtn.setCaption(subControls.isVisible() ? "Less Settings..." : "More Settings...");
            }
        });

        controls.addComponent(start);
        controls.addComponent(end);
        controls.addComponent(reso);
        controls.addComponent(subControls);
        controls.addComponent(toggleSubControlsBtn);
        controls.setComponentAlignment(toggleSubControlsBtn, Alignment.BOTTOM_CENTER);

        subControls.addComponent(localeSelect);
        subControls.addComponent(timezoneSelect);
        subControls.addComponent(heightAndUnit);
        subControls.addComponent(widthAndUnit);
        subControls.addComponent(createStep);
        subControls.setComponentAlignment(createStep, Alignment.MIDDLE_LEFT);

        return panel;
    }

    private DateTimeField createStartDateField()
    {
        DateTimeField f = new DateTimeField("Start date");
        f.setResolution(DateTimeResolution.SECOND);
        startDateValueChangeRegistration = Optional.of(f.addValueChangeListener(startDateValueChangeListener));
        return f;
    }

    private DateTimeField createEndDateField()
    {
        DateTimeField f = new DateTimeField("End date");
        f.setResolution(DateTimeResolution.SECOND);
        endDateValueChangeRegistration = Optional.of(f.addValueChangeListener(endDateValueChangeListener));
        return f;
    }

    private boolean validateResolutionChange(final Resolution res)
    {
        long max = 5 * 12 * 4 * 7 * 24 * 3600000L;
        if (res ==Resolution.Hour
                && (gantt.getEndDate().getTime() - gantt.getStartDate().getTime()) > max)
        {

            // revert to previous resolution
            setResolution(gantt.getResolution());

            // make user to confirm hour resolution, if the timeline range is
            // very long.
            Util.showConfirmationPopup(
                    "Timeline range is a quite long for hour resolution. Rendering may be slow. Continue anyway?",
                    new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            setResolution(res);
                            gantt.setResolution(res);
                        }
                    });
            return false;
        }
        return true;
    }

    private void setResolution(Resolution resolution)
    {
        resolutionValueChangeRegistration.ifPresent(Registration::remove);
        try
        {
            reso.setValue(resolution);
        } finally
        {
            resolutionValueChangeRegistration = Optional.of(reso.addValueChangeListener(resolutionValueChangeListener));
        }
    }

    private MenuBar controlsMenuBar()
    {
        MenuBar menu = new MenuBar();
        MenuItem editItem = menu.addItem("Edit", null);
        MenuItem formatItem = menu.addItem("Format", null);
        MenuItem viewItem = menu.addItem("View", null);

        MenuItem item = editItem.addItem("Enabled", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setEnabled(!gantt.isEnabled());
                selectedItem.setChecked(gantt.isEnabled());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isEnabled());

        item = editItem.addItem("Read-only", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setReadOnly(!gantt.isReadOnly());
                selectedItem.setChecked(gantt.isReadOnly());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isReadOnly());

        editItem.addSeparator();
        item = editItem.addItem("Movable Steps", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setMovableSteps(!gantt.isMovable());
                selectedItem.setChecked(gantt.isMovable());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isMovable());

        item = editItem.addItem("Movable Steps between rows", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setMovableStepsBetweenRows(!gantt.isMovableStepsBetweenRows());
                selectedItem.setChecked(gantt.isMovableStepsBetweenRows());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isMovableStepsBetweenRows());

        item = editItem.addItem("Resizable Steps", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setResizableSteps(!gantt.isResizableSteps());
                selectedItem.setChecked(gantt.isResizableSteps());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isResizableSteps());

        item = editItem.addItem("Disable Default Context Menu", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setDefaultContextMenuEnabled(!gantt.isDefaultContextMenuEnabled());
                selectedItem.setChecked(!gantt.isDefaultContextMenuEnabled());
            }
        });
        item.setCheckable(true);
        item.setChecked(!gantt.isDefaultContextMenuEnabled());

        editItem.addSeparator();
        item = editItem.addItem("Create New Step...", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                createStepClickListener.buttonClick(null);
            }
        });

        item = formatItem.addItem("Set 'MMM' month format", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineMonthFormat("MMM");
            }
        });
        item = formatItem.addItem("Set 'MMMM' month format", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineMonthFormat("MMMM");
            }
        });
        item = formatItem.addItem("Set 'yyyy MMMM' month format", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineMonthFormat("yyyy MMMM");
            }
        });
        item = formatItem.addItem("Set 'dd.' week format", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineWeekFormat("dd.");
            }
        });
        item = formatItem.addItem("Set week number week format", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineWeekFormat(null);
            }
        });
        item = formatItem.addItem("Set 'dd. EEEE' day format for Hour resolution", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineDayFormat("dd. EEEE");
            }
        });
        item = formatItem.addItem("Set 'k' hour format for Hour resolution", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setTimelineHourFormat("k");
            }
        });

        item = viewItem.addItem("Show Control Panel", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                controls.setVisible(!controls.isVisible());
                selectedItem.setChecked(controls.isVisible());
            }
        });
        item.setCheckable(true);
        item.setChecked(true);

        viewItem.addSeparator();
        item = viewItem.addItem("Show years", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setYearsVisible(!gantt.isYearsVisible());
                selectedItem.setChecked(gantt.isYearsVisible());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isYearsVisible());

        item = viewItem.addItem("Show months", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setMonthsVisible(!gantt.isMonthsVisible());
                selectedItem.setChecked(gantt.isMonthsVisible());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isMonthsVisible());

        item = viewItem.addItem("Show current time pointer", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                gantt.setShowCurrentTime(!gantt.isShowCurrentTime());
                selectedItem.setChecked(gantt.isShowCurrentTime());
            }
        });
        item.setCheckable(true);
        item.setChecked(gantt.isShowCurrentTime());

        item = viewItem.addItem("Show Gantt with Grid", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                getPage().setLocation("#grid");
                getPage().reload();
            }
        });
        item = viewItem.addItem("Show Gantt with TreeGrid", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                getPage().setLocation("#treegrid");
                getPage().reload();
            }
        });
        item = viewItem.addItem("Show Gantt alone", new Command()
        {

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                getPage().setLocation("#");
                getPage().reload();
            }
        });

        return menu;
    }

    private <T extends AbstractStep> void openStepEditor(T step)
    {
        final Window win = new Window("Step Editor");
        win.setResizable(false);
        win.center();

        final Collection<Component> hidden = new ArrayList<Component>();
        Binder<T> binder = new Binder<T>((Class<T>) step.getClass());
        binder.setBean(step);

        TextField captionField = new TextField("Caption");
        binder.bind(captionField, "caption");

        TextField descriptionField = new TextField("Description");
        binder.bind(descriptionField, "description");
        descriptionField.setVisible(false);
        hidden.add(descriptionField);

        TextField styleField = new TextField("Style name");
        binder.bind(styleField, "styleName");
        styleField.setVisible(false);
        hidden.add(styleField);

        NativeSelect<Step.CaptionMode> captionMode = new NativeSelect<Step.CaptionMode>("Caption Mode");
        captionMode.setItems(Step.CaptionMode.TEXT, Step.CaptionMode.HTML);
        binder.bind(captionMode, "captionMode");
        captionMode.setVisible(false);
        hidden.add(captionMode);

        CheckBox resizable = new CheckBox("Resizable");
        binder.bind(resizable, "resizable");
        resizable.setVisible(false);
        hidden.add(resizable);

        CheckBox showProgress = new CheckBox("Show progress");
        binder.bind(showProgress, "showProgress");
        showProgress.setVisible(false);
        hidden.add(showProgress);

        Slider progress = new Slider("Progress");
        progress.setWidth(100, Unit.PERCENTAGE);
        binder.bind(progress, "progress");
        progress.setVisible(false);
        hidden.add(progress);

        NativeSelect<AbstractStep> predecessorSelect = new NativeSelect<AbstractStep>("Predecessor Step");
        predecessorSelect.setWidth(100, Unit.PERCENTAGE);
        predecessorSelect.setItemCaptionGenerator(i -> i.getCaption());
        fillPredecessorCanditatesToSelect(step, predecessorSelect);
        predecessorSelect.setEnabled(step instanceof Step);
        if (step instanceof Step)
        {
            binder.bind(predecessorSelect, "predecessor");
        }
        predecessorSelect.setVisible(false);
        hidden.add(predecessorSelect);

        final NativeSelect<Step> parentStepSelect = new NativeSelect<Step>("Parent Step");
        parentStepSelect.setWidth(100, Unit.PERCENTAGE);
        parentStepSelect.setItemCaptionGenerator(i -> i.getCaption());
        parentStepSelect.setEnabled(false);
        fillParentStepCanditatesToSelect(step, parentStepSelect);
        parentStepSelect.setVisible(false);
        hidden.add(parentStepSelect);

        HorizontalLayout colorLayout = new HorizontalLayout();
        colorLayout.setWidth(100, Unit.PERCENTAGE);
        colorLayout.setVisible(false);
        hidden.add(colorLayout);

        final TextField bgField = new TextField("Background color");
        binder.bind(bgField, "backgroundColor");
        bgField.setEnabled(false);

        final ColorPicker bgColorPicker = new ColorPicker();
        bgColorPicker.setPosition(300, 100);
        bgColorPicker.setValue(new CssColorToColorPickerConverter().convertToModel(step.getBackgroundColor()));
        bgColorPicker.addValueChangeListener(e -> bgField.setValue(e.getValue().getCSS()));

        colorLayout.addComponent(bgField);
        colorLayout.addComponent(bgColorPicker);
        colorLayout.setExpandRatio(bgField, 1);
        colorLayout.setComponentAlignment(bgColorPicker, Alignment.BOTTOM_LEFT);

        DateTimeField startDate = new DateTimeField("Start date");
        startDate.setLocale(gantt.getLocale());
        startDate.setResolution(DateTimeResolution.SECOND);
        binder.bind(startDate, (s) -> GanttTimeUtil.getStartLocalDateTime(s),
                (s, v) -> GanttTimeUtil.setStartDate(s, v));

        DateTimeField endDate = new DateTimeField("End date");
        endDate.setLocale(gantt.getLocale());
        endDate.setResolution(DateTimeResolution.SECOND);
        binder.bind(endDate, (s) -> GanttTimeUtil.getEndLocalDateTime(s), (s, v) -> GanttTimeUtil.setEndDate(s, v));

        CheckBox showMore = new CheckBox("Show all settings");
        showMore.addValueChangeListener(new ValueChangeListener<Boolean>()
        {

            @Override
            public void valueChange(ValueChangeEvent<Boolean> event)
            {
                for (Component c : hidden)
                {
                    c.setVisible(event.getValue());
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
        content.addComponent(styleField);
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

        Button ok = new Button("Ok", new ClickListener()
        {

            @Override
            public void buttonClick(ClickEvent event)
            {
                commit(win, binder, parentStepSelect);
            }

        });
        Button cancel = new Button("Cancel", new ClickListener()
        {

            @Override
            public void buttonClick(ClickEvent event)
            {
                cancel(win, binder);
            }
        });
        Button delete = new Button("Delete", new ClickListener()
        {

            @Override
            public void buttonClick(ClickEvent event)
            {
                delete(win, binder);
            }

        });
        buttons.addComponent(ok);
        buttons.addComponent(cancel);
        buttons.addComponent(delete);
        win.setClosable(true);

        getUI().addWindow(win);
    }

    private void fillPredecessorCanditatesToSelect(AbstractStep step,
                                                   final NativeSelect<AbstractStep> predecessorSelect)
    {
        List<AbstractStep> items = new ArrayList<>();
        for (Step stepCanditate : gantt.getSteps())
        {
            if (!stepCanditate.equals(step) && stepCanditate instanceof Step)
            {
                items.add(stepCanditate);
            }
        }
        predecessorSelect.setItems(items);
    }

    private void fillParentStepCanditatesToSelect(AbstractStep step, final NativeSelect<Step> parentStepSelect)
    {
        if (!gantt.getSteps().contains(step))
        {
            // new step
            parentStepSelect.setEnabled(true);
            List<Step> items = new ArrayList<>();
            for (Step parentStepCanditate : gantt.getSteps())
            {
                items.add(parentStepCanditate);
                if (step instanceof SubStep)
                {
                    if (parentStepCanditate.getSubSteps().contains(step))
                    {
                        parentStepSelect.setValue(parentStepCanditate);
                        parentStepSelect.setEnabled(false);
                        break;
                    }
                }
            }
            parentStepSelect.setItems(items);
        }
    }

    private void commit(final Window win, final Binder<? extends AbstractStep> binder,
                        final NativeSelect<Step> parentStepSelect)
    {
        AbstractStep step = binder.getBean();
        gantt.markStepDirty(step);
        if (parentStepSelect.isEnabled() && parentStepSelect.getValue() != null)
        {
            SubStep subStep = addSubStep(parentStepSelect, step);
            step = subStep;
        }
        if (step instanceof Step && !gantt.getSteps().contains(step))
        {
            gantt.addStep((Step) step);
        }
        if (ganttListener != null && step instanceof Step)
        {
            ganttListener.stepModified((Step) step);
        }
        win.close();
    }

    private void cancel(final Window win, final Binder<? extends AbstractStep> binder)
    {
        win.close();
    }

    private void delete(final Window win, final Binder<? extends AbstractStep> binder)
    {
        AbstractStep step = binder.getBean();
        if (step instanceof SubStep)
        {
            SubStep substep = (SubStep) step;
            substep.getOwner().removeSubStep(substep);
        } else
        {
            gantt.removeStep((Step) step);
            if (ganttListener != null)
            {
                ganttListener.stepDeleted((Step) step);
            }
        }
        win.close();
    }

    private SubStep addSubStep(final NativeSelect parentStepSelect, AbstractStep dataSource) {
        SubStep subStep = new SubStep();
        subStep.setCaption(dataSource.getCaption());
        subStep.setCaptionMode(dataSource.getCaptionMode());
        subStep.setStartDate(dataSource.getStartDate());
        subStep.setEndDate(dataSource.getEndDate());
        subStep.setBackgroundColor(dataSource.getBackgroundColor());
        subStep.setForegroundColor(dataSource.getForegroundColor());
        subStep.setStyleNames(dataSource.getStyleNames());
        subStep.setRemoveStyleNames(dataSource.getRemoveStyleNames());
        subStep.setDescription(dataSource.getDescription());
        subStep.setProgress(dataSource.getProgress());
        subStep.setShowProgress(dataSource.isShowProgress());
        subStep.setStyleName(dataSource.getStyleName());
        ((Step) parentStepSelect.getValue()).addSubStep(subStep);
        return subStep;
    }

    private TimeZone getDefaultTimeZone()
    {
        if (defaultTimeZone != null)
        {
            return defaultTimeZone;
        }
        TimeZone tz = TimeZone.getDefault();
        if (Gantt.getSupportedTimeZoneIDs().contains(tz.getID()))
        {
            defaultTimeZone = tz;
        } else
        {
            defaultTimeZone = TimeZone.getTimeZone("Europe/Helsinki");
        }
        return defaultTimeZone;
    }
}
