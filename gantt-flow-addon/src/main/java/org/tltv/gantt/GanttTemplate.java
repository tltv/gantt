package org.tltv.gantt;

import org.tltv.gantt.GanttTemplate.GanttTemplateModel;
import org.tltv.gantt.model.State;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("gantt-template")
@HtmlImport("frontend://gantt-template.html")
public class GanttTemplate extends PolymerTemplate<GanttTemplateModel> {

    public GanttTemplate(State initialState) {
        getModel().setState(initialState);

        GanttWidget gantt = new GanttWidget();
        getElement().appendChild(gantt.getElement());
    }

    public State getState() {
        return getModel().getState();
    }

    public void setState(State state) {
        getModel().setState(state);
    }

    public static interface GanttTemplateModel extends TemplateModel {

        State getState();

        void setState(State state);

    }
}
