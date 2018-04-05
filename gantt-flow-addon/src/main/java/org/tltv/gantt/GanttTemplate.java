package org.tltv.gantt;

import org.tltv.gantt.GanttTemplate.GanttViewModel;
import org.tltv.gantt.model.State;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("gantt-template")
@HtmlImport("frontend://gantt-template.html")
public class GanttTemplate extends PolymerTemplate<GanttViewModel> {

    public GanttTemplate(State initialState) {
        getModel().setState(initialState);

        GanttWidget gantt = new GanttWidget();
        getElement().appendChild(gantt.getElement());
    }

    public static interface GanttViewModel extends TemplateModel {

        State getState();

        void setState(State state);

    }
}
