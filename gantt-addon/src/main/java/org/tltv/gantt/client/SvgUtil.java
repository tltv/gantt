package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

/** Utility class to create SVG elements. */
public class SvgUtil {

    public static final String SVG_NS = "http://www.w3.org/2000/svg";

    public static Element createSVGElementNS(String tag) {
        return createElementNS(SVG_NS, tag);
    }

    public static native void setAttributeNS(String uri, Element elem,
            String attr, String value)
    /*-{
        elem.setAttributeNS(uri, attr, value);
    }-*/;

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
}
