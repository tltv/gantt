package org.tltv.gantt.demo;

import com.vaadin.ui.HorizontalLayout;

public class GanttElementLayout extends HorizontalLayout {

    public GanttElementLayout() {
        setSizeFull();
        setMargin(false);
        setSpacing(false);

        GanttElementDemo element = new GanttElementDemo();
        addComponent(element);
    }
}
