package org.tltv.gantt;

import java.util.List;

public class Step extends GanttStep {

    private Step predecessor;

    private List<SubStep> subSteps;

    public Step getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Step predecessor) {
        this.predecessor = predecessor;
    }

    public List<SubStep> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(List<SubStep> subSteps) {
        this.subSteps = subSteps;
    }
}
