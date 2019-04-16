package org.tltv.gantt.model;

import org.tltv.gantt.GanttStep;

public class Step extends GanttStep {

    private Step predecessor;

    public Step getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Step predecessor) {
        this.predecessor = predecessor;
    }

}
