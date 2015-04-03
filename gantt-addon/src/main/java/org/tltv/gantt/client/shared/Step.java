package org.tltv.gantt.client.shared;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Step extends AbstractStep {

    private Step predecessor;
    private List<SubStep> subSteps = new LinkedList<SubStep>();

    public Step() {
    }

    public Step(String caption) {
        super(caption);
    }

    public Step(String caption, CaptionMode captionMode) {
        super(caption, captionMode);
    }

    public Step(String caption, CaptionMode captionMode, SubStep... subSteps) {
        super(caption, captionMode);
        addSubSteps(subSteps);
    }

    public Step getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Step predecessor) {
        this.predecessor = predecessor;
    }

    public List<SubStep> getSubSteps() {
        return Collections.unmodifiableList(subSteps);
    }

    public void addSubStep(SubStep subStep) {
        if (subStep != null) {
            subStep.setOwner(this);
            subSteps.add(subStep);
        }
    }

    public void addSubSteps(SubStep... subSteps) {
        if (subSteps != null) {
            addSubSteps(Arrays.asList(subSteps));
        }
    }

    public void addSubSteps(List<SubStep> subSteps) {
        if (subSteps != null) {
            for (SubStep s : subSteps) {
                addSubStep(s);
            }
        }
    }

    public void removeSubStep(SubStep subStep) {
        subSteps.remove(subStep);
    }

}
