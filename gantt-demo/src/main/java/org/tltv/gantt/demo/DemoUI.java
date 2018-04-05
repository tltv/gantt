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
import java.time.ZoneOffset;

import org.tltv.gantt.GanttTemplate;
import org.tltv.gantt.model.State;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
@SuppressWarnings("serial")
public class DemoUI extends Div {

    public DemoUI() {
        setSizeFull();

        Div controls = new Div();

        State state = new State();
        Double end = (double) LocalDateTime.of(2017, 12, 31, 23, 59, 59).toInstant(ZoneOffset.UTC).toEpochMilli();
        state.setStartDate((double) LocalDateTime.of(2017, 1, 1, 0, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        state.setEndDate(end);

        final VerticalLayout layout = new VerticalLayout();
        layout.setClassName("demoContentLayout");
        layout.setMargin(false);
        layout.setSizeFull();
        layout.add(controls);
        layout.add(new GanttTemplate(state));

        add(layout);
    }

}
