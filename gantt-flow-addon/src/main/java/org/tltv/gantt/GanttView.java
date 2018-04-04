package org.tltv.gantt;

import org.tltv.gantt.GanttView.GanttViewModel;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("gantt-view")
@HtmlImport("frontend://gantt-view.html")
public class GanttView extends PolymerTemplate<GanttViewModel> {

    public GanttView() {

        GanttComponent gantt = new GanttComponent();
        getElement().appendChild(gantt.getElement());
    }

    public static interface GanttViewModel extends TemplateModel {

    }
}
