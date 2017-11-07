package org.tltv.gantt.client;

import java.util.List;

import org.tltv.gantt.client.shared.Step;

import com.vaadin.polymer.elemental.Function;

public class StepHierarchyHandler {

    private GanttWidget widget;

    public StepHierarchyHandler(GanttWidget widget) {
        this.widget = widget;
    }

    public GanttWidget getWidget() {
        return widget;
    }

    public void setSteps(final List<StepWidget> stepWidgets) {
        int index = 0;
        for (StepWidget sw : stepWidgets) {
            getWidget().setStep(index++, sw, false);
        }
    }

    public void requestRemoveStep(final StepWidget stepWidget) {
        stepWidget.ready(new Function<Object, Object>() {
            @Override
            public Object call(Object args) {
                getWidget().removeStep(stepWidget);
                return null;
            }
        });
    }

    /** Updates all steps predecessor visualizations. */
    public void updateAllStepsPredecessors(List<StepWidget> steps) {
        for (StepWidget c : steps) {
            c.updatePredecessor();
        }
    }

    public void updateRelatedStepsPredecessors(Step targetStep, List<StepWidget> stepWidgets) {
        for (StepWidget stepWidget : stepWidgets) {
            stepWidget.requestUpdatePredecessor(targetStep);
        }
    }

}
