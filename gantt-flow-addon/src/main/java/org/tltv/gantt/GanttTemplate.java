package org.tltv.gantt;

import org.tltv.gantt.GanttTemplate.GanttTemplateModel;
import org.tltv.gantt.model.Settings;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
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

    public static interface GanttTemplateModel extends TemplateModel {

        Settings getSettings();

        void setSettings(Settings settings);

    }
}
