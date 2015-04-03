package org.tltv.gantt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;
import org.tltv.gantt.client.shared.SubStep;
import org.tltv.gantt.client.shared.SubStepObserver;
import org.tltv.gantt.client.shared.SubStepObserverProxy;

import com.vaadin.shared.Connector;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

/**
 * Component representing a Step in the Gantt chart.
 * 
 * @author Tltv
 * 
 */
public class StepComponent extends AbstractComponent implements HasComponents,
        SubStepObserver {

    protected final Map<SubStep, SubStepComponent> subStepMap = new HashMap<SubStep, SubStepComponent>();

    public StepComponent(Gantt gantt, Step data) {
        if (data.getUid() == null) {
            data.setUid(UUID.randomUUID().toString());
        }
        setParent(gantt);
        getState().step = data;
        for (SubStep subStep : data.getSubSteps()) {
            onAddSubStep(subStep);
        }
        data.setSubStepObserver(new SubStepObserverProxy(this));
    }

    @Override
    public StepState getState() {
        return (StepState) super.getState();
    }

    @Override
    public StepState getState(boolean markAsDirty) {
        return (StepState) super.getState(markAsDirty);
    }

    public void addSubStep(StepComponent stepComponent, SubStep subStep) {
        SubStepComponent component = createSubStepComponent(stepComponent,
                subStep);
        getState().subSteps.add(component);

    }

    protected SubStepComponent createSubStepComponent(
            StepComponent stepComponent, SubStep subStep) {
        return new SubStepComponent(stepComponent, subStep);
    }

    @Override
    public Iterator<Component> iterator() {
        List<Component> l = new ArrayList<Component>();
        for (Connector c : getState(false).subSteps) {
            l.add((Component) c);
        }
        return l.iterator();
    }

    @Override
    public void onAddSubStep(SubStep subStep) {
        SubStepComponent component = createSubStepComponent(this, subStep);
        getState(true).subSteps.add(component);
        subStepMap.put(subStep, component);
    }

    @Override
    public void onRemoveSubStep(SubStep subStep) {
        getState(true).subSteps.add(subStepMap.get(subStep));
        subStepMap.remove(subStep);
    }

}
