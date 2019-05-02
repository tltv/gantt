/*
 * Copyright 2018 Tomi Virtanen
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.model.Resolution;
import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.events.MouseEventDetails.MouseButton;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(Lumo.class)
@Route("")
@SuppressWarnings("serial")
public class DemoUI extends Div {

    private ZoneId defaultZoneId;
    private int newId = 10000;

    public DemoUI() {
        setSizeFull();

        Gantt gantt = new Gantt();

        Step stepA = new Step();
        stepA.setUid("1"); // required unique step id
        stepA.setCaption("Step A");
        stepA.setDescription("Description of Step A");
        stepA.setBackgroundColor("#A8D946");
        stepA.setStartZonedDateTime(LocalDateTime.of(2017, 1, 1, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepA.setEndZonedDateTime(LocalDateTime.of(2017, 1, 29, 23, 59, 59).atZone(getDefaultTimeZone()));
        stepA.setResizable(true);
        stepA.setMovable(true);

        Step stepB = new Step();
        stepB.setUid("2"); // required unique step id
        stepB.setCaption("Step B");
        stepB.setDescription("Description of Step B");
        stepB.setStartZonedDateTime(LocalDateTime.of(2017, 2, 1, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepB.setEndZonedDateTime(LocalDateTime.of(2017, 2, 25, 23, 59, 59).atZone(getDefaultTimeZone()));
        stepB.setResizable(true);
        stepB.setMovable(true);
        stepB.setPredecessor(stepA);

        Step stepC = new Step();
        stepC.setUid("3"); // required unique step id
        stepC.setCaption("Step C");
        stepC.setDescription("Description of Step C");
        stepC.setStartZonedDateTime(LocalDateTime.of(2017, 3, 1, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepC.setEndZonedDateTime(LocalDateTime.of(2017, 3, 25, 23, 59, 59).atZone(getDefaultTimeZone()));
        stepC.setResizable(true);
        stepC.setMovable(true);
        stepC.setPredecessor(stepB);

        Step stepD = new Step();
        stepD.setUid("4"); // required unique step id
        stepD.setCaption("Step D");
        stepD.setDescription("Description of Step D");
        stepD.setStartZonedDateTime(LocalDateTime.of(2017, 1, 15, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepD.setEndZonedDateTime(LocalDateTime.of(2017, 1, 25, 23, 59, 59).atZone(getDefaultTimeZone()));
        stepD.setResizable(true);
        stepD.setMovable(true);

        SubStep subStepA = new SubStep();
        subStepA.setUid("5"); // required unique step id
        subStepA.setCaption("SubStep A");
        subStepA.setBackgroundColor("#A8D946");
        subStepA.setDescription("Description of SubStep A");
        subStepA.setStartZonedDateTime(LocalDateTime.of(2017, 1, 15, 0, 0,
                0).atZone(getDefaultTimeZone()));
        subStepA.setEndZonedDateTime(LocalDateTime.of(2017, 1, 20, 23, 59,
                59).atZone(getDefaultTimeZone()));
        subStepA.setResizable(true);
        subStepA.setMovable(true);
        subStepA.setOwner(stepD);

        SubStep subStepB = new SubStep();
        subStepB.setUid("6"); // required unique step id
        subStepB.setCaption("SubStep B");
        subStepB.setDescription("Description of SubStep B");
        subStepB.setStartZonedDateTime(LocalDateTime.of(2017, 1, 21, 0, 0,
                0).atZone(getDefaultTimeZone()));
        subStepB.setEndZonedDateTime(LocalDateTime.of(2017, 1, 25, 23, 59,
                59).atZone(getDefaultTimeZone()));
        subStepB.setResizable(true);
        subStepB.setMovable(true);
        subStepB.setOwner(stepD);

        gantt.addStep(stepA);
        gantt.addStep(stepB);
        gantt.addStep(stepC);
        gantt.addStep(stepD);
        gantt.addSubSteps(subStepA, subStepB);

        gantt.setStartDateTime(LocalDateTime.of(2017, 1, 1, 0, 0, 0));
        gantt.setEndDateTime(LocalDateTime.of(2018, 1, 1, 0, 0, 0));
        gantt.getSettings().setMovableStepsBetweenRows(true);

        final VerticalLayout layout = new VerticalLayout();
        layout.setClassName("demoContentLayout");
        layout.setMargin(false);
        layout.setSizeFull();
        layout.add(createControls(gantt));
        layout.add(gantt);

        add(layout);

        addEventHandlers(gantt);
    }

    private void addEventHandlers(Gantt gantt) {
        gantt.addStepClickListener(event -> {
            if (event.getDetails().getButton().equals(MouseButton.LEFT)) {
                gantt.remove(event.getTarget());
            } else if (event.getDetails().getButton().equals(MouseButton.RIGHT)) {
                Notification.show("Clicked RIGHT mouse button on " + event.getTarget().getCaption());
            }
        });

        gantt.addMoveListener(event -> {
            Notification.show("Moved " + event.getTarget().getCaption());
        });

        gantt.addResizeListener(event -> {
            Notification.show("Resized " + event.getTarget().getCaption());
        });

        gantt.addPredecessorChangeListener(event -> {
            Notification.show("Predecessor changed for " + event.getStep().getCaption() + " to  "
                    + Optional.ofNullable(event.getPredecessorStep()).map(Step::getCaption).orElse("null"));
        });
    }

    private Div createControls(final Gantt gantt) {
        Div controls = new Div();

        ComboBox<Resolution> resolutionField = new ComboBox<>("Resolution");
        resolutionField.setPreventInvalidInput(true);
        resolutionField.setItems(Resolution.values());
        resolutionField.setValue(gantt.getResolution());
        resolutionField.addValueChangeListener(e -> gantt.setResolution(e.getValue()));

        DatePicker startDateField = new DatePicker(
                LocalDate.of(2017, 1, 1),
                e -> Optional.ofNullable(e.getValue()).ifPresent(date -> gantt.setStartDateTime(date.atStartOfDay())));
        startDateField.setLabel("Start date");

        DatePicker endDateField = new DatePicker(
                LocalDate.of(2018, 1, 1),
                e -> Optional.ofNullable(e.getValue()).ifPresent(date -> gantt.setEndDateTime(date.atStartOfDay())));
        endDateField.setLabel("End date");

        ComboBox<String> timeZoneField = new ComboBox<>("Timezone", getSupportedTimeZoneIds());
        timeZoneField.setWidth("350px");
        timeZoneField.setValue("Default");
        timeZoneField.setItemLabelGenerator(item -> {
            if ("Default".equals(item)) {
                return "Default (" + getDefaultTimeZone().getDisplayName(TextStyle.FULL, UI.getCurrent().getLocale())
                        + ")";
            }
            TimeZone tz = TimeZone.getTimeZone(item);
            return tz.getID() + " (raw offset " + (tz.getRawOffset() / 60000) + "m)";
        });
        timeZoneField.addValueChangeListener(e -> Optional.ofNullable(e.getValue()).ifPresent(zoneId -> {
            if ("Default".equals(zoneId)) {
                gantt.setZoneId(getDefaultTimeZone());
            } else {
                gantt.setZoneId(ZoneId.of(zoneId));
            }
        }));

        ComboBox<Locale> localeField = new ComboBox<>("Locale",
                Stream.of(Locale.getAvailableLocales()).collect(Collectors.toList()));
        localeField.setWidth("350px");
        localeField.setItemLabelGenerator((l) -> l.getDisplayName(UI.getCurrent().getLocale()));
        localeField.setValue(gantt.getLocale());
        localeField.addValueChangeListener(e -> Optional.ofNullable(e.getValue()).ifPresent(l -> gantt.setLocale(l)));

        Button addNewStepBtn = new Button("Add new step", event -> {
            Step step = new Step();
            step.setUid("" + newId++); // required unique step id
            step.setCaption("Step " + step.getUid());
            step.setDescription("Description of Step " + step.getUid());
            step.setStartZonedDateTime(LocalDateTime.of(2017, 1, 15, 0, 0, 0).atZone(getDefaultTimeZone()));
            step.setEndZonedDateTime(LocalDateTime.of(2017, 1, 25, 23, 59, 59).atZone(getDefaultTimeZone()));
            step.setResizable(true);
            step.setMovable(true);
            gantt.addStep(step);
        });

        Button addNewSubStepBtn = new Button("Add new sub step", event -> {
            SubStep substep = new SubStep();
            substep.setUid("" + newId++); // required unique step id
            substep.setCaption("Step " + substep.getUid());
            substep.setDescription("Description of SubStep " + substep.getUid());
            substep.setStartZonedDateTime(LocalDateTime.of(2017, 1, 15, 0, 0, 0).atZone(getDefaultTimeZone()));
            substep.setEndZonedDateTime(LocalDateTime.of(2017, 1, 25, 23, 59, 59).atZone(getDefaultTimeZone()));
            substep.setResizable(true);
            substep.setMovable(true);
            substep.setOwner(gantt.getStep(0));
            gantt.addSubSteps(substep);
        });

        Button changeStepCaption = new Button("Change step caption", event -> {
            gantt.getStep(0).setCaption(gantt.getStep(0).getCaption() + "!");
        });

        controls.add(resolutionField, startDateField, endDateField, localeField, timeZoneField);
        controls.add(addNewStepBtn, addNewSubStepBtn, changeStepCaption);
        return controls;
    }

    private List<String> getSupportedTimeZoneIds() {
        List<String> items = new ArrayList<>();
        items.add("Default");
        items.addAll(Gantt.getSupportedTimeZoneIDs());
        return items;
    }

    private ZoneId getDefaultTimeZone() {
        if (defaultZoneId != null) {
            return defaultZoneId;
        }
        ZoneId zone = ZoneId.systemDefault();
        if (Gantt.getSupportedTimeZoneIDs().contains(zone.getId())) {
            defaultZoneId = zone;
        } else {
            defaultZoneId = ZoneId.of("Europe/Helsinki");
        }
        return defaultZoneId;
    }
}
