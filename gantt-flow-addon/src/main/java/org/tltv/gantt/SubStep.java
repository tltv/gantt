package org.tltv.gantt;

public class SubStep extends GanttStep {

    private Step owner;

    public Step getOwner() {
        return owner;
    }

    public void setOwner(Step owner) {
        this.owner = owner;
    }
}
