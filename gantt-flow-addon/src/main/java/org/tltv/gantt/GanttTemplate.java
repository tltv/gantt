package org.tltv.gantt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.tltv.gantt.GanttTemplate.GanttTemplateModel;
import org.tltv.gantt.event.ClickEvent;
import org.tltv.gantt.model.Settings;
import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.shared.Registration;
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

    public boolean contains(Step targetStep) {
        return getModel().getSteps().stream().filter(step -> step.getUid().equals(targetStep.getUid())).findFirst()
                .isPresent();
    }

    public boolean contains(SubStep targetSubStep) {
        return getModel().getSubSteps().stream().filter(substep -> substep.getUid().equals(targetSubStep.getUid()))
                .findFirst()
                .isPresent();
    }

    /**
     * Get unmodifiable list of all steps.
     */
    public List<Step> getSteps() {
        return Collections.unmodifiableList(getModel().getSteps());
    }

    public Step addStep(Step step) {
        if (!contains(step)) {
            getModel().getSteps().add(step);
        }
        return step;
    }

    public void addStep(int index, Step step) {
        if (contains(step)) {
            moveStep(index, step);
        } else {
            getModel().getSteps().add(index, step);
        }
    }

    public void moveStep(int toIndex, Step step) {
        if (!contains(step)) {
            return;
        }
        Step targetStep = getModel().getSteps().get(toIndex);
        Step moveStep = step;
        if (targetStep.equals(moveStep)) {
            return;
        }
        getModel().getSteps().removeIf(item -> item.getUid().equals(moveStep.getUid()));
        getModel().getSteps().add(indexOf(targetStep), moveStep);
    }

    /**
     * Moves sub-step to other step. Does nothing, if either is null, or
     * sub-step's owner is null or owner is same as target step.
     */
    public void moveSubStep(SubStep subStep, Step toStep) {
        if (subStep == null || toStep == null || subStep.getOwner() == null || subStep.getOwner().equals(toStep)) {
            return;
        }
        removeSubStep(subStep);
        addSubStep(toStep, subStep);
    }

    public Step getStep(int index) {
        if (index >= 0 && index < getModel().getSteps().size()) {
            return (getModel().getSteps().get(index));
        }
        return null;
    }

    /**
     * Get Step by UID.
     *
     * @param uid
     *            Unique Identifier of Step
     * @return Step with given UID or null if step doesn't exist.
     */
    public Step getStep(String uid) {
        if (uid == null) {
            return null;
        }
        return getModel().getSteps().stream().filter(step -> step.getUid().equals(uid)).findFirst().orElse(null);
    }

    /**
     * Removes all the steps in this Gantt chart.
     */
    public void removeSteps() {
        getModel().getSteps().forEach(this::removeStep);
    }

    public boolean remove(GanttStep target) {
        if (target == null) {
            return false;
        }
        if (target.isSubstep()) {
            return removeSubStep((SubStep) target);
        }
        return removeStep((Step) target);
    }

    /**
     * Remove {@link Step}.
     *
     * @param step
     *            Target Step
     * @return true when target step exists and was removed successfully.
     */
    public boolean removeStep(Step step) {
        List<SubStep> subSteps = getSubSteps(step);
        if (getModel().getSteps().removeIf(item -> item.getUid().equals(step.getUid()))) {
            getModel().getSubSteps().removeAll(subSteps);
            return true;
        }
        return false;
    }

    public List<SubStep> getSubSteps(String stepUid) {
        return getModel().getSubSteps().stream().filter(sub -> sub.getOwner().getUid().equals(stepUid))
                .collect(Collectors.toList());
    }

    public List<SubStep> getSubSteps(Step step) {
        if (step == null) {
            return null;
        }
        return getSubSteps(step.getUid());
    }

    public int getStepIndex(Step step) {
        return indexOf(step);
    }

    protected int indexOf(Step step) {
        return getModel().getSteps().indexOf(step);
    }

    /** Add new sub step to target step. */
    protected boolean addSubStep(Step targetStep, SubStep subStep) {
        if (subStep != null) {
            subStep.setSubstep(true);
            subStep.setOwner(targetStep);
            return onAddSubStep(subStep);
        }
        return false;
    }

    public void addSubSteps(SubStep... subSteps) {
        if (subSteps != null) {
            addSubSteps(Arrays.asList(subSteps));
        }
    }

    public void addSubSteps(List<SubStep> subSteps) {
        subSteps.stream()
                .filter(sub -> sub.getOwner() != null && sub.getOwner().getUid() != null)
                .forEach(subStep -> {
                    Step targetStep = getStep(subStep.getOwner().getUid());
                    if (targetStep == null) {
                        throw new IllegalArgumentException("SubStep.owner.uid is required for SubStep");
                    }
                    addSubStep(targetStep, subStep);
                });
    }

    /** Add new sub steps. */
    public void addStepWithSubSteps(Step targetStep, SubStep... subSteps) {
        if (!contains(targetStep)) {
            addStep(targetStep);
        }
        if (subSteps != null) {
            addStepWithSubSteps(targetStep, Arrays.asList(subSteps));
        }
    }

    /** Add new sub steps. */
    public void addStepWithSubSteps(Step targetStep, List<SubStep> subSteps) {
        if (!contains(targetStep)) {
            addStep(targetStep);
        }
        if (subSteps != null) {
            subSteps.forEach(sub -> addSubStep(targetStep, sub));
        }
    }

    /**
     * Remove given sub-step. Sub step is actually identified by its UID
     * {@link GanttStep#getUid()}.
     */
    public boolean removeSubStep(SubStep subStep) {
        if (subStep == null) {
            return false;
        }
        onRemoveSubStep(subStep);
        return false;
    }

    public boolean onAddSubStep(SubStep subStep) {
        if (!contains(subStep)) {
            return getModel().getSubSteps().add(subStep);
        }
        return false;
    }

    public boolean onRemoveSubStep(SubStep subStep) {
        return getModel().getSubSteps().removeIf(sub -> sub.getUid().equals(subStep.getUid()));
    }

    @ClientCallable
    private void handleStepClicked(String stepUid) {
        fireEvent(new ClickEvent((Gantt) this, true,
                getModel().getSteps().stream().filter(step -> step.getUid().equals(stepUid)).findFirst().orElse(null)));
    }

    @ClientCallable
    private void handleSubStepClicked(String subStepUid) {
        fireEvent(new ClickEvent((Gantt) this, true,
                getModel().getSubSteps().stream().filter(substep -> substep.getUid().equals(subStepUid)).findFirst()
                        .orElse(null)));
    }

    @ClientCallable
    private void handleOnMove(String stepUid, String newStepUid, double startDate, double endDate) {
        // TODO
    }

    @ClientCallable
    private void handleOnResize(String stepUid, double startDate, double endDate) {
        // TODO
    }

    @ClientCallable
    private void handleOnPredecessorChange(String newPredecessorStepUid, String forTargetStepUid,
            String clearPredecessorForStepUid) {
        // TODO
    }

    public Registration addStepClickListener(ComponentEventListener<ClickEvent> listener) {
        return getEventBus().addListener(ClickEvent.class, listener);
    }

    public static interface GanttTemplateModel extends TemplateModel {

        Settings getSettings();

        void setSettings(Settings settings);

        @Include({ "uid", "caption", "description", "captionMode", "styleName", "startDate", "endDate",
                "backgroundColor", "progress", "showProgress", "resizable", "movable", "substep", "predecessor.uid" })
        // TODO substeps (Lists in beans are not supported currently)
        List<Step> getSteps();

        @Include({ "uid", "caption", "description", "captionMode", "styleName", "startDate", "endDate",
                "backgroundColor", "progress", "showProgress", "resizable", "movable", "substep", "owner.uid" })
        List<SubStep> getSubSteps();

        void setSteps(List<Step> steps);

        void setSubSteps(List<Step> subSteps);

        void setHeight(String height);

    }
}
