package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

public interface GanttRpc {

    void stepClicked(int index);

    void onMove(int rowIndex, int newRowIndex, long startDate, long endDate);

    void onResize(int rowIndex, long startDate, long endDate);

    void onStepRelationSelected(StepWidget source,
            boolean startingPointChanged, Element newRelationStepElement);
}
