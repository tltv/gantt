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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.tltv.gantt.client.ArrowElement;

import com.google.gwt.user.client.rpc.GwtTransient;

public class Step extends AbstractStep {

    private Step predecessor;
    private List<SubStep> subSteps = new LinkedList<SubStep>();

    @GwtTransient
    transient private Set<SubStepObserverProxy> subStepObserver = new HashSet<SubStepObserverProxy>();

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

    /** Get predecessor step for this step. */
    public Step getPredecessor() {
        return predecessor;
    }

    /**
     * Set predecessor step for this step. Predecessor is visualized by 'arrow'
     * between the predecessor step and this step. 'Arrow' will be painted
     * starting from the end of the predecessor step and ends pointing to start
     * of this step. This relation is editable by default if Gantt is not in
     * read-only mode.
     * <p>
     * Visual look of the arrow can be customized by implementing GWT widget
     * which implements {@link ArrowElement} interface.
     * 
     * @param predecessor
     *            Predecesor step
     */
    public void setPredecessor(Step predecessor) {
        this.predecessor = predecessor;
    }

    /** Get unmodifiable list of sub steps in this step. */
    public List<SubStep> getSubSteps() {
        return Collections.unmodifiableList(subSteps);
    }

    /** Add new sub step. */
    public boolean addSubStep(SubStep subStep) {
        if (subStep != null) {
            subStep.setOwner(this);
            boolean added = subSteps.add(subStep);
            if (added && subStepObserver != null) {
                for (SubStepObserverProxy obs : subStepObserver) {
                    obs.onAddSubStep(subStep);
                }
            }
            return added;
        }
        return false;
    }

    /** Add new sub steps. */
    public void addSubSteps(SubStep... subSteps) {
        if (subSteps != null) {
            addSubSteps(Arrays.asList(subSteps));
        }
    }

    /** Add new sub steps. */
    public void addSubSteps(List<SubStep> subSteps) {
        if (subSteps != null) {
            for (SubStep s : subSteps) {
                addSubStep(s);
            }
        }
    }

    /**
     * Remove given sub-step. Sub step is actually identified by its UID
     * {@link AbstractStep#getUid()}.
     */
    public boolean removeSubStep(SubStep subStep) {
        if (subStep == null) {
            return false;
        }
        boolean removed = subSteps.remove(subStep);
        if (removed && subStepObserver != null) {
            for (SubStepObserverProxy obs : subStepObserver) {
                obs.onRemoveSubStep(subStep);
            }
        }
        return removed;
    }

    /** Get unmodifiable list of SubStepObservers. */
    public Set<SubStepObserverProxy> getSubStepObservers() {
        return Collections.unmodifiableSet(subStepObserver);
    }

    /**
     * Add new {@link SubStepObserverProxy} for this step. Observer notifies
     * about structure changes of sub-steps.
     */
    public void addSubStepObserver(SubStepObserverProxy subStepObserver) {
        this.subStepObserver.add(subStepObserver);
    }

    /** Remove {@link SubStepObserverProxy} from this step. */
    public void removeSubStepObserver(SubStepObserverProxy subStepObserver) {
        this.subStepObserver.remove(subStepObserver);
    }

    /** Get smallest sub-step start date from this step. */
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

    /**
     * Get largest sub-step end date from this step.
     */
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
