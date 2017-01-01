package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

public interface GanttRpc {

    void stepClicked(String stepUid, NativeEvent event,
            Element relativeToElement);

    void onMove(String stepUid, String newStepUid, long startDate, long endDate);

    void onResize(String stepUid, long startDate, long endDate);

    boolean onStepRelationSelected(StepWidget source,
            boolean startingPointChanged, Element newRelationStepElement);
}
