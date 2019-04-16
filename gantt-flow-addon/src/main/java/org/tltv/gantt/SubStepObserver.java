package org.tltv.gantt;

import java.util.List;

import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

public interface SubStepObserver {

    List<SubStep> getSubSteps(Step step);

    boolean onAddSubStep(SubStep subStep);

    boolean onRemoveSubStep(SubStep subStep);
}
