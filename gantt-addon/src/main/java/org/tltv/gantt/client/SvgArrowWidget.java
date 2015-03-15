package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.event.PointerDownEvent;
import com.vaadin.client.event.PointerDownHandler;

/** SVG implementation of {@link ArrowElement} arrow between two elements. */
public class SvgArrowWidget extends Widget implements ArrowElement {

    public static final String SVG_NS = "http://www.w3.org/2000/svg";

    private Element curve;
    private Element startingPoint;
    private Element endingPoint;

    private int width = 0;
    private int height = 0;

    public SvgArrowWidget() {
        Element predecessorArrow = createSVGElementNS("svg");
        addStyleName(predecessorArrow, "arrow");
        predecessorArrow.getStyle().setPosition(Position.ABSOLUTE);
        predecessorArrow.getStyle().setZIndex(2);
        predecessorArrow.getStyle().setProperty("pointerEvents", "none");

        Element g = createSVGElementNS("g");
        setAttributeNS(g, "stroke", "black");
        setAttributeNS(g, "stroke-width", "1");
        curve = createSVGElementNS("path");
        setAttributeNS(curve, "fill", "none");
        startingPoint = createSVGElementNS("circle");
        setAttributeNS(startingPoint, "stroke-width", "2");
        setAttributeNS(startingPoint, "r", "3");
        setAttributeNS(startingPoint, "fill", "black");
        // endingPoint = createSVGElementNS("polygon");
        endingPoint = createSVGElementNS("circle");
        setAttributeNS(endingPoint, "stroke-width", "2");
        setAttributeNS(endingPoint, "r", "2");
        setAttributeNS(endingPoint, "fill", "black");
        DOM.appendChild(g, curve);
        DOM.appendChild(g, startingPoint);
        DOM.appendChild(g, endingPoint);
        DOM.appendChild(predecessorArrow, g);
        setElement(predecessorArrow);
    }

    private void addStyleName(Element element, String style) {
        String curStyles = element.getAttribute("class");
        if (curStyles == null) {
            curStyles = "";
        }
        if (!curStyles.contains(style)) {
            curStyles += " " + style;
            element.setAttribute("class", curStyles);
        }
    }

    @Override
    public void setWidth(double width) {
        this.width = (int) width;
        setAttributeNS(getElement(), "width", (int) width);
    }

    @Override
    public void setHeight(double height) {
        this.height = (int) height;
        setAttributeNS(getElement(), "height", (int) height);
    }

    @Override
    public void draw(ArrowPositionData d) {
        int y1 = ((d.isFromTop()) ? d.getFromHeightCenter() : height
                - d.getFromHeightCenter());
        int y2 = ((d.isFromTop()) ? height - d.getToHeightCenter() : d
                .getToHeightCenter());

        StringBuilder s = new StringBuilder("M");
        s.append(" " + ((d.isFromLeft()) ? 0 : width)); // x1
        s.append(", " + y1); // y1
        s.append(" C");
        s.append(" " + d.getHalfWidth()); // cx1
        s.append(", " + y1); // cy1
        s.append(", " + d.getHalfWidth()); // cx2
        s.append(", " + y2); // cy2
        s.append(", " + ((d.isFromLeft()) ? width : 0)); // x2
        s.append(", " + y2); // y2

        setAttributeNS(curve, "d", s.toString());

        setAttributeNS(startingPoint, "cx", (d.isFromLeft()) ? 0 : (int) width);
        setAttributeNS(
                startingPoint,
                "cy",
                ((d.isFromTop()) ? d.getFromHeightCenter() : height
                        - d.getFromHeightCenter()));

        int endPointX = (!d.isFromLeft()) ? 0 : (int) width;
        int endPointY = ((!d.isFromTop()) ? d.getFromHeightCenter() : height
                - d.getFromHeightCenter());
        setAttributeNS(endingPoint, "cx", endPointX);
        setAttributeNS(endingPoint, "cy", endPointY);

        // s = new StringBuilder("");
        // s.append(endPointX - 5).append(","); // x1,y1
        // s.append(endPointY - 2).append(" ");
        // s.append(endPointX).append(",");// x2,y2
        // s.append(endPointY).append(" ");
        // s.append(endPointX - 5).append(",");// x3,y3
        // s.append(endPointY + 2).append(" ");
        // setAttributeNS(endingPoint, "points", s.toString());
    }

    private PointerDownHandler msPointerDownHandler = new PointerDownHandler() {

        @Override
        public void onPointerDown(PointerDownEvent event) {
            event.stopPropagation();
        }
    };

    private TouchStartHandler touchStartHandler = new TouchStartHandler() {
        @Override
        public void onTouchStart(TouchStartEvent event) {
            event.stopPropagation();
        }
    };

    private MouseDownHandler mouseDownHandler = new MouseDownHandler() {

        @Override
        public void onMouseDown(MouseDownEvent event) {
            if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
                event.stopPropagation();
            }
        }
    };

    public static Element createSVGElementNS(String tag) {
        return createElementNS(SVG_NS, tag);
    }

    public static native Element createElementNS(String ns, String tag)
    /*-{
        return $doc.createElementNS(ns, tag);
    }-*/;

    public static void setAttributeNS(Element elem, String attr, int value) {
        setAttributeNS(null, elem, attr, "" + value);
    }

    public static void setAttributeNS(Element elem, String attr, String value) {
        setAttributeNS(null, elem, attr, value);
    }

    public static native void setAttributeNS(String uri, Element elem,
            String attr, String value)
    /*-{
        elem.setAttributeNS(uri, attr, value);
    }-*/;

    @Override
    public void setUpEventHandlers(boolean touchSupported,
            boolean msTouchSupported) {
        if (msTouchSupported) {
            // IE10 pointer events (ms-prefixed events)
            // addDomHandler(msPointerDownHandler, PointerDownEvent.getType());
            // addDomHandler(msPointerUpHandler, PointerUpEvent.getType());
            // addDomHandler(msPointerMoveHandler, PointerMoveEvent.getType());
            // addDomHandler(msPointerCancelHandler,
            // PointerCancelEvent.getType());

        } else if (touchSupported) {
            // touch events replaces mouse events
            // addDomHandler(touchStartHandler, TouchStartEvent.getType());
            // addDomHandler(touchEndHandler, TouchEndEvent.getType());
            // addDomHandler(touchMoveHandler, TouchMoveEvent.getType());
            // addDomHandler(touchCancelHandler, TouchCancelEvent.getType());

        } else {
            // addDomHandler(mouseDownHandler, MouseDownEvent.getType());
            // addDomHandler(mouseUpHandler, MouseUpEvent.getType());
            // addDomHandler(mouseMoveHandler, MouseMoveEvent.getType());
        }
    }

}
