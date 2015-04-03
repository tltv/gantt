package org.tltv.gantt.client.shared;

public class SubStepObserverProxy {

    transient private SubStepObserver observer;

    public SubStepObserverProxy() {
    }

    public SubStepObserverProxy(SubStepObserver observer) {
        this.observer = observer;
    }

    public void onAddSubStep(SubStep subStep) {
        observer.onAddSubStep(subStep);
    }

    public void onRemoveSubStep(SubStep subStep) {
        observer.onRemoveSubStep(subStep);
    }
}
