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

import java.time.LocalDateTime;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.model.Resolution;
import org.tltv.gantt.model.Settings;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(Lumo.class)
@Route("")
@SuppressWarnings("serial")
public class DemoUI extends Div {

    private Binder<Settings> stateBinder;

    public DemoUI() {
        setSizeFull();

        Gantt gantt = new Gantt();

        gantt.setStartDateTime(LocalDateTime.of(2017, 1, 1, 0, 0, 0));
        gantt.setEndDateTime(LocalDateTime.of(2018, 1, 1, 0, 0, 0));

        stateBinder = new Binder<>();

        final VerticalLayout layout = new VerticalLayout();
        layout.setClassName("demoContentLayout");
        layout.setMargin(false);
        layout.setSizeFull();
        layout.add(createControls(stateBinder, gantt));
        layout.add(gantt);

        add(layout);

        stateBinder.setBean(gantt.getSettings());
    }

    private Div createControls(Binder<Settings> binder, final Gantt gantt) {
        Div controls = new Div();

        ComboBox<Resolution> resolutionField = new ComboBox<>();
        resolutionField.setItems(Resolution.values());
        resolutionField.setValue(gantt.getResolution());
        resolutionField.addValueChangeListener(e -> gantt.setResolution(e.getValue()));

        controls.add(resolutionField);
        return controls;
    }

}
