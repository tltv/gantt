package org.tltv.gantt.demo;

import org.tltv.gantt.demo.GanttView.GanttViewModel;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("gantt-view")
@HtmlImport("frontend://gantt-view.html")
// @JavaScript("frontend://EC8353941F89F770CF55286517781E70.cache.js")
public class GanttView extends PolymerTemplate<GanttViewModel> {

    public GanttView() {

        GanttComponent gantt = new GanttComponent();
        getElement().appendChild(gantt.getElement());
    }

    public static interface GanttViewModel extends TemplateModel {

    }
}
