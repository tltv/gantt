package org.tltv.gantt.demo;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript(value = { "gantt_element_demo_connector.js" })
public class GanttElementDemo extends AbstractJavaScriptComponent {

    public GanttElementDemo() {
    }

    @Override
    protected GanttElementDemoState getState() {
        return (GanttElementDemoState) super.getState();
    }
}
