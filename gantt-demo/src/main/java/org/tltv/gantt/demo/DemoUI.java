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

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(Lumo.class)
@Route("")
@SuppressWarnings("serial")
public class DemoUI extends Div {

    private ZoneId defaultZoneId;

    public DemoUI() {
        setSizeFull();

        Gantt gantt = new Gantt();

        gantt.setStartDateTime(LocalDateTime.of(2017, 1, 1, 0, 0, 0));
        gantt.setEndDateTime(LocalDateTime.of(2018, 1, 1, 0, 0, 0));

        final VerticalLayout layout = new VerticalLayout();
        layout.setClassName("demoContentLayout");
        layout.setMargin(false);
        layout.setSizeFull();
        layout.add(createControls(gantt));
        layout.add(gantt);

        add(layout);
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

        controls.add(resolutionField, startDateField, endDateField, localeField, timeZoneField);
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
