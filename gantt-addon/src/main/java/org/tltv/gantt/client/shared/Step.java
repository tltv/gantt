/*
 * Copyright 2015 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tltv.gantt.client.shared;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.GwtTransient;

public class Step extends AbstractStep {

    private Step predecessor;
    private List<SubStep> subSteps = new LinkedList<SubStep>();

    @GwtTransient
    transient private SubStepObserverProxy subStepObserver;

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

    public boolean addSubStep(SubStep subStep) {
        if (subStep != null) {
            subStep.setOwner(this);
            boolean added = subSteps.add(subStep);
            if (added && subStepObserver != null) {
                subStepObserver.onAddSubStep(subStep);
            }
            return added;
        }
        return false;
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

    public boolean removeSubStep(SubStep subStep) {
        if (subStep == null) {
            return false;
        }
        boolean removed = subSteps.remove(subStep);
        if (removed && subStepObserver != null) {
            subStepObserver.onRemoveSubStep(subStep);
        }
        return removed;
    }

    public SubStepObserverProxy getSubStepObserver() {
        return subStepObserver;
    }

    public void setSubStepObserver(SubStepObserverProxy subStepObserver) {
        this.subStepObserver = subStepObserver;
    }

    public long getMinStartDateBySubSteps() {
        Long min = null;
        for (SubStep subStep : getSubSteps()) {
            if (min == null) {
                min = subStep.getStartDate();
            } else {
                min = Math.min(min, subStep.getStartDate());
            }
        }
        return min;
    }

    public long getMaxEndDateBySubSteps() {
        Long max = null;
        for (SubStep subStep : getSubSteps()) {
            if (max == null) {
                max = subStep.getEndDate();
            } else {
                max = Math.max(max, subStep.getEndDate());
            }
        }
        return max;
    }
}
