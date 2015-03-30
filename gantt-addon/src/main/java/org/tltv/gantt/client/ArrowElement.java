package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseDownEvent;

/** Element representing an arrow between two elements. SVG implementation. */
public interface ArrowElement {

    void setWidth(double width);

    void setHeight(double height);

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
         */
        void onArrowChanged(boolean startingPointChanged, MouseDownEvent event);
    }
}
