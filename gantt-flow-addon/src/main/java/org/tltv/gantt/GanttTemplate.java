package org.tltv.gantt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tltv.gantt.GanttTemplate.GanttTemplateModel;
import org.tltv.gantt.event.ClickEvent;
import org.tltv.gantt.event.MoveEvent;
import org.tltv.gantt.event.PredecessorChangeEvent;
import org.tltv.gantt.event.ResizeEvent;
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

import elemental.json.JsonObject;

@Tag("gantt-template")
@HtmlImport("frontend://gantt-template.html")
public class GanttTemplate extends PolymerTemplate<GanttTemplateModel> {

    private final Logger log = LoggerFactory.getLogger(GanttTemplate.class);

    protected ZoneId zoneId = ZoneId.systemDefault();

    public GanttTemplate(Settings initialSettings) {
        getModel().setSettings(initialSettings);

        GanttWidget gantt = new GanttWidget();
        getElement().appendChild(gantt.getElement());
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    /**
     * Get Gantt's {@link Settings} model object. Object is part of template
     * model state. Changes in this object will be reflected to client
     * (browser).
     */
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
            getModel().getSteps().add(ensureUID(step));
        }
        return step;
    }

    public void addStep(int index, Step step) {
        if (contains(step)) {
            moveStep(index, step);
        } else {
            getModel().getSteps().add(index, ensureUID(step));
        }
    }

    public void moveStep(int toIndex, Step step) {
        if (!contains(step)) {
            return;
        }
        Step targetStep = getModel().getSteps().get(toIndex);
        Step moveStep = step;
        if (targetStep.getUid().equals(moveStep.getUid())) {
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
     * Get SubStep by UID.
     *
     * @param uid
     *            Unique Identifier of SubStep
     * @return SubStep with given UID or null if substep doesn't exist.
     */
    public SubStep getSubStep(String uid) {
        if (uid == null) {
            return null;
        }
        return getModel().getSubSteps().stream().filter(step -> step.getUid().equals(uid)).findFirst().orElse(null);
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
            return getModel().getSubSteps().add(ensureUID(subStep));
        }
        return false;
    }

    public boolean onRemoveSubStep(SubStep subStep) {
        return getModel().getSubSteps().removeIf(sub -> sub.getUid().equals(subStep.getUid()));
    }

    @ClientCallable
    private void handleStepClicked(String stepUid, JsonObject detailsJson) {
        log.debug("Step {} clicked with details: {}", stepUid, detailsJson);
        fireEvent(new ClickEvent((Gantt) this, true,
                getModel().getSteps().stream().filter(step -> step.getUid().equals(stepUid)).findFirst().orElse(null),
                GanttUtil.readMouseEventDetails(detailsJson)));
    }

    @ClientCallable
    private void handleSubStepClicked(String subStepUid, JsonObject detailsJson) {
        log.debug("SubStep {} clicked with details: {}", subStepUid, detailsJson);
        fireEvent(new ClickEvent((Gantt) this, true,
                getModel().getSubSteps().stream().filter(substep -> substep.getUid().equals(subStepUid)).findFirst()
                        .orElse(null),
                GanttUtil.readMouseEventDetails(detailsJson)));
    }

    @ClientCallable
    private void handleOnMove(String stepUid, String newStepUid, double startDate, double endDate,
            JsonObject detailsJson) {
        GanttStep step = getStep(stepUid);
        if (step == null) {
            step = getSubStep(stepUid);
        }
        GanttStep newStep = getStep(newStepUid);
        if (newStep == null) {
            newStep = getSubStep(newStepUid);
        }
        int previousStepIndex = (step instanceof Step) ? getStepIndex((Step) step)
                : getStepIndex(getStep(((SubStep) step).getOwner().getUid()));
        long previousStartDate = (long) step.getStartDate();
        long previousEndDate = (long) step.getEndDate();
        ZonedDateTime previousZonedStartDate = step.getStartZonedDateTime(getZoneId());
        ZonedDateTime previousZonedEndDate = step.getEndZonedDateTime(getZoneId());
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        log.debug("Moving step {} from [{} - {}] to [{} - {}]", stepUid, previousZonedStartDate, previousZonedEndDate,
                step.getStartZonedDateTime(getZoneId()), step.getEndZonedDateTime(getZoneId()));
        int newStepIndex;
        if (getSettings().isMovableStepsBetweenRows() && newStep instanceof Step) {
            newStepIndex = getStepIndex((Step) newStep);
            if (step instanceof Step) {
                log.debug("Moving step {} from row index {} to {}", stepUid, previousStepIndex, newStepIndex);
                // move to new row
                moveStep(newStepIndex, (Step) step);
            } else {
                log.debug("Moving substep {} from row index {} to {}", stepUid, previousStepIndex, newStepIndex);
                // move sub-step to new owner
                moveSubStep((SubStep) step, (Step) newStep);
            }
        } else {
            newStepIndex = previousStepIndex;
        }
        moveDatesByOwnerStep(step, previousStartDate, previousEndDate);
        adjustDatesByAbstractStep(step);
        fireEvent(new MoveEvent((Gantt) this, true, step,
                step.getStartZonedDateTime(getZoneId()), step.getEndZonedDateTime(getZoneId()),
                newStepIndex, previousZonedStartDate,
                previousZonedEndDate, previousStepIndex,
                GanttUtil.readMouseEventDetails(detailsJson)));
    }

    @ClientCallable
    private void handleOnResize(String stepUid, double startDate, double endDate, JsonObject detailsJson) {
        GanttStep step = getStep(stepUid);
        if (step == null) {
            step = getSubStep(stepUid);
        }
        long previousStartDate = (long) step.getStartDate();
        long previousEndDate = (long) step.getEndDate();
        ZonedDateTime previousZonedStartDate = step.getStartZonedDateTime(getZoneId());
        ZonedDateTime previousZonedEndDate = step.getEndZonedDateTime(getZoneId());
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        log.debug("Resizing step {} from [{} - {}] to [{} - {}]", stepUid, previousZonedStartDate, previousZonedEndDate,
                step.getStartZonedDateTime(getZoneId()), step.getEndZonedDateTime(getZoneId()));
        resizeDatesByOwnerStep(step, previousStartDate, previousEndDate);
        adjustDatesByAbstractStep(step);
        fireEvent(new ResizeEvent((Gantt) this, true, step,
                step.getStartZonedDateTime(getZoneId()), step.getEndZonedDateTime(getZoneId()),
                previousZonedStartDate, previousZonedEndDate,
                GanttUtil.readMouseEventDetails(detailsJson)));
    }

    @ClientCallable
    private void handleOnPredecessorChange(String newPredecessorStepUid, String forTargetStepUid,
            String clearPredecessorForStepUid) {
        Step newPredecessorStep = getStep(newPredecessorStepUid);
        Step forTargetStep = getStep(forTargetStepUid);
        Step clearPredecessorForStep = getStep(clearPredecessorForStepUid);
        if (forTargetStep != null) {
            forTargetStep.setPredecessor(newPredecessorStep);
            fireEvent(new PredecessorChangeEvent((Gantt) this, true, forTargetStep, newPredecessorStep));
        }
        if (clearPredecessorForStep != null) {
            clearPredecessorForStep.setPredecessor(null);
            fireEvent(new PredecessorChangeEvent((Gantt) this, true, clearPredecessorForStep, null));
        }
    }

    public Registration addStepClickListener(ComponentEventListener<ClickEvent> listener) {
        return getEventBus().addListener(ClickEvent.class, listener);
    }

    public Registration addMoveListener(ComponentEventListener<MoveEvent> listener) {
        return getEventBus().addListener(MoveEvent.class, listener);
    }

    public Registration addResizeListener(ComponentEventListener<ResizeEvent> listener) {
        return getEventBus().addListener(ResizeEvent.class, listener);
    }

    public Registration addPredecessorChangeListener(ComponentEventListener<PredecessorChangeEvent> listener) {
        return getEventBus().addListener(PredecessorChangeEvent.class, listener);
    }

    protected void moveDatesByOwnerStep(GanttStep step, long previousStartDate, long previousEndDate) {
        if (!(step instanceof Step)) {
            return;
        }
        Step s = (Step) step;

        long startDelta = ((long) step.getStartDate()) - previousStartDate;
        long endDelta = ((long) step.getEndDate()) - previousEndDate;
        List<SubStep> subSteps = getSubSteps(s);
        if (!subSteps.isEmpty()) {
            for (SubStep sub : subSteps) {
                if (startDelta != 0) {
                    sub.setStartDate(sub.getStartDate() + startDelta);
                }
                if (endDelta != 0) {
                    sub.setEndDate(sub.getEndDate() + endDelta);
                }
            }
        }
    }

    protected void adjustDatesByAbstractStep(GanttStep step) {
        Step owner = null;
        if (step instanceof SubStep) {
            owner = ajustDatesBySubStep(step);

        } else if (step instanceof Step) {
            owner = (Step) step;
        }

        if (owner != null) {
            // adjust owner start/end dates to fit with all sub-steps.
            List<SubStep> subSteps = getSubSteps(owner);
            if (!subSteps.isEmpty()) {
                if (owner.getStartDate() < 0 || GanttUtil.getMinStartDateBySubSteps(subSteps) != owner.getStartDate()) {
                    owner.setStartDate(GanttUtil.getMinStartDateBySubSteps(subSteps));
                }
                if (owner.getEndDate() < 0 || GanttUtil.getMaxEndDateBySubSteps(subSteps) != owner.getEndDate()) {
                    owner.setEndDate(GanttUtil.getMaxEndDateBySubSteps(subSteps));
                }
            }
        }
    }

    protected Step ajustDatesBySubStep(GanttStep step) {
        // adjust parent step start/end date:
        SubStep subStep = (SubStep) step;
        Step owner = getStep(subStep.getOwner().getUid());
        // Cut owner step's start/end date to fit sub-steps in.
        if (owner.getStartDate() < 0 || subStep.getStartDate() < owner.getStartDate()) {
            owner.setStartDate(subStep.getStartDate());
        }
        if (owner.getEndDate() < 0 || subStep.getEndDate() > owner.getEndDate()) {
            owner.setEndDate(subStep.getEndDate());
        }
        return owner;
    }

    protected void resizeDatesByOwnerStep(GanttStep step, long previousStartDate, long previousEndDate) {
        // may be overridden to handle resizing parent step
    }

    /**
     * Ensures that given step has UID. If not, then generates one.
     */
    protected <T extends GanttStep> T ensureUID(T step) {
        if (step == null) {
            return null;
        }
        if (step.getUid() == null) {
            step.setUid(UUID.randomUUID().toString());
        }
        return step;
    }

    public static interface GanttTemplateModel extends TemplateModel {

        Settings getSettings();

        void setSettings(Settings settings);

        @Include({ "uid", "caption", "description", "captionMode", "styleName", "startDate", "endDate",
                "backgroundColor", "progress", "showProgress", "resizable", "movable", "substep", "predecessor.uid" })
        List<Step> getSteps();

        /*
         * Substeps used to be part of Step, but as bean Lists in beans are not
         * supported at this time (2019 April), all substeps are stored in here.
         * Be warned that client still uses Step.subSteps and will populate it
         * back for each step based on this property.
         */
        // TODO consider moving substeps back to Step when Lists in Lists is
        // supported.
        @Include({ "uid", "caption", "description", "captionMode", "styleName", "startDate", "endDate",
                "backgroundColor", "progress", "showProgress", "resizable", "movable", "substep", "owner.uid" })
        List<SubStep> getSubSteps();

        void setSteps(List<Step> steps);

        void setSubSteps(List<Step> subSteps);

        void setHeight(String height);

    }
}
