package org.tltv.gantt;

import com.vaadin.flow.component.charts.events.MouseEventDetails;
import com.vaadin.flow.component.charts.events.MouseEventDetails.MouseButton;

import elemental.json.JsonObject;

public class GanttUtil {

    public static MouseEventDetails readMouseEventDetails(JsonObject detailsJson) {
        MouseEventDetails details = new MouseEventDetails();

        Double x = detailsJson.getNumber("relativeX");
        Double y = detailsJson.getNumber("relativeY");
        if (x != null) {
            details.setxValue(x);
        }
        if (y != null) {
            details.setyValue(y);
        }
        details.setAbsoluteX((int) detailsJson.getNumber("clientX_0"));
        details.setAbsoluteY((int) detailsJson.getNumber("clientY_0"));
        String buttonName = detailsJson.getObject("button_0").getString("name_0");
        if (MouseButton.LEFT.name().equalsIgnoreCase(buttonName)) {
            details.setButton(MouseButton.LEFT);
        } else if (MouseButton.RIGHT.name().equalsIgnoreCase(buttonName)) {
            details.setButton(MouseButton.RIGHT);
        } else if (MouseButton.MIDDLE.name().equalsIgnoreCase(buttonName)) {
            details.setButton(MouseButton.MIDDLE);
        }
        details.setAltKey(detailsJson.getBoolean("altKey_0"));
        details.setCtrlKey(detailsJson.getBoolean("ctrlKey_0"));
        details.setMetaKey(detailsJson.getBoolean("metaKey_0"));
        details.setShiftKey(detailsJson.getBoolean("shiftKey_0"));
        return details;
    }
}
