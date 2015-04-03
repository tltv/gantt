package org.tltv.gantt.client.shared;

import com.vaadin.shared.communication.ServerRpc;

public interface GanttServerRpc extends ServerRpc {

    public void stepClicked(String stepUid);

    public void onMove(String stepUid, String newStepUid, long startDate,
            long endDate);

    public void onResize(String stepUid, long startDate, long endDate);

    public void onPredecessorChanged(String newPredecessorStepUid,
            String forTargetStepUid, String clearPredecessorForStepUid);
}
