package org.tltv.gantt;

import java.util.List;

import org.tltv.gantt.GanttTemplate.GanttTemplateModel;
import org.tltv.gantt.model.Settings;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("gantt-template")
@HtmlImport("frontend://gantt-template.html")
public class GanttTemplate extends PolymerTemplate<GanttTemplateModel> {

    public GanttTemplate(Settings initialSettings) {
        getModel().setSettings(initialSettings);

        GanttWidget gantt = new GanttWidget();
        getElement().appendChild(gantt.getElement());
    }

    public Settings getSettings() {
        return getModel().getSettings();
    }

    public void setSettings(Settings state) {
        getModel().setSettings(state);
    }

    public List<Step> getSteps() {
        return getModel().getSteps();
    }

    public void setSteps(List<Step> steps) {
        getModel().setSteps(steps);
    }

    // TODO events

    public static interface GanttTemplateModel extends TemplateModel {

        Settings getSettings();

        void setSettings(Settings settings);

        @Include({ "uid", "caption", "description", "captionMode", "styleName", "startDate", "endDate",
                "backgroundColor", "progress", "showProgress", "resizable", "movable", "substep", "predecessor.uid" })
        // TODO sub-steps
        List<Step> getSteps();

        void setSteps(List<Step> steps);

    }
}
