package org.tltv.gantt.client.shared;

import com.vaadin.shared.communication.ClientRpc;

public interface GanttClientRpc extends ClientRpc {

    /**
     * Call client to explicitly update scroll delegation target's height.
     */
    void updateDelegateTargetHeight();
}