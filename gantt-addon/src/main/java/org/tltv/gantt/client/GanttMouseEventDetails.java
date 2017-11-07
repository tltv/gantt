package org.tltv.gantt.client;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;

import com.vaadin.shared.MouseEventDetails;

@Export
@ExportPackage(value = "gantt")
public class GanttMouseEventDetails extends MouseEventDetails {

    public GanttMouseEventDetails() {
    }

    public static GanttMouseEventDetails of(MouseEventDetails details) {
        GanttMouseEventDetails instance = new GanttMouseEventDetails();
        instance.setButton(details.getButton());
        instance.setClientX(details.getClientX());
        instance.setClientY(details.getClientY());
        instance.setAltKey(details.isAltKey());
        instance.setCtrlKey(details.isCtrlKey());
        instance.setMetaKey(details.isMetaKey());
        instance.setShiftKey(details.isShiftKey());
        instance.setType(details.getType());
        instance.setRelativeX(details.getRelativeX());
        instance.setRelativeY(details.getRelativeY());
        return instance;
    }
}
