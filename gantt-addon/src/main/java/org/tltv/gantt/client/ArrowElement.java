package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

/** Element representing an arrow between two elements. SVG implementation. */
public interface ArrowElement {

    void setWidth(double width);

    void setHeight(double height);

    void draw(ArrowPositionData arrowData);

    Element getElement();

    void setUpEventHandlers(boolean touchSupported, boolean msTouchSupported);
}
