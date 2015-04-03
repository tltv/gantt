package org.tltv.gantt.client.shared;

public interface SubStepObserver {

    void onAddSubStep(SubStep subStep);

    void onRemoveSubStep(SubStep subStep);
}
