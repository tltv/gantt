package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

/** Element representing an arrow between two elements. SVG implementation. */
public interface ArrowElement {

    void setWidth(double width);

    void setHeight(double height);

    void setTop(int top);

    void setLeft(int left);

    void setReadOnly(boolean readOnly);

    void draw(ArrowPositionData arrowData);

    Element getElement();

    void setUpEventHandlers(boolean touchSupported, boolean msTouchSupported);

    void setArrowChangeHandler(ArrowChangeHandler handler);

    public interface ArrowChangeHandler {
        /**
         * Arrow source/target has changed.
         * 
         * @param startingPointChanged
         *            True when arrow's starting point has changed. False when
         *            ending point has changed.
         * @param event
         *            Original Event which triggered this method call.
         * @return true if change is valid
         */
        boolean onArrowChanged(boolean startingPointChanged, NativeEvent event);
    }
}
