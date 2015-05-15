package org.tltv.gantt.client;

import static org.tltv.gantt.client.SvgUtil.createSVGElementNS;
import static org.tltv.gantt.client.SvgUtil.setAttributeNS;

import com.google.gwt.dom.client.Element;

/**
 * Background Grid SVG implementation.
 */
public class BgGridSvgElement extends BgGridCssElement implements BgGridElement {

    public static final String STYLE_BG_GRID = "bg-grid";

    private Element svgElement;
    private Element content;
    private Element pattern;
    private Element path;

    private double gridBlockWidthPx;
    private double gridBlockHeightPx;

    @Override
    public void init(Element container, Element content) {
        this.content = content;

        svgElement = createSVGElementNS("svg");
        setAttributeNS(svgElement, "width", "110%");
        setAttributeNS(svgElement, "height", "110%");
        setAttributeNS(svgElement, "preserveAspectRatio", "none");

        Element defs = createSVGElementNS("defs");
        svgElement.appendChild(defs);

        pattern = createSVGElementNS("pattern");
        setAttributeNS(pattern, "id", "bggrid-pattern");
        setAttributeNS(pattern, "patternUnits", "userSpaceOnUse");
        setAttributeNS(pattern, "x", "0");
        setAttributeNS(pattern, "y", "0");
        defs.appendChild(pattern);

        Element rect = createSVGElementNS("rect");
        setAttributeNS(rect, "width", "100%");
        setAttributeNS(rect, "height", "100%");
        setAttributeNS(rect, "fill", "#ffffff");
        pattern.appendChild(rect);

        path = createSVGElementNS("path");
        setAttributeNS(path, "fill-opacity", "0");
        setAttributeNS(path, "stroke-width", "1");
        setAttributeNS(path, "stroke", "#cccccc");
        pattern.appendChild(path);

        Element rect2 = createSVGElementNS("rect");
        setAttributeNS(rect2, "width", "100%");
        setAttributeNS(rect2, "height", "100%");
        setAttributeNS(rect2, "fill", "url(#bggrid-pattern)");
        svgElement.appendChild(rect2);

        setAttributeNS(svgElement, "class", STYLE_BG_GRID);

        appendToContainer(svgElement);
    }

    private void appendToContainer(Element svgElement) {
        content.insertFirst(svgElement);
    }

    @Override
    public void hide() {
        svgElement.removeFromParent();
    }

    @Override
    public void setBackgroundSize(String gridBlockWidth,
            double gridBlockWidthPx, int gridBlockHeightPx) {
        this.gridBlockWidthPx = gridBlockWidthPx;
        this.gridBlockHeightPx = gridBlockHeightPx;

        setAttributeNS(pattern, "width", "" + gridBlockWidthPx);
        setAttributeNS(pattern, "height", "" + gridBlockHeightPx);

        setAttributeNS(path, "d", "M0," + gridBlockHeightPx + " h"
                + gridBlockWidthPx + " v0 h0 v-" + gridBlockHeightPx);
    }

    @Override
    public void setBackgroundPosition(String offsetX, String offsetY,
            double offsetXPx, double offsetYPx) {
        double offX = 0;
        if (offsetXPx == 0 || offsetXPx == gridBlockWidthPx) {
            offX = 0;
        } else {
            offX = -(gridBlockWidthPx - offsetXPx - 1);
        }
        setAttributeNS(svgElement, "style", "margin-left: " + offX + "px;"
                + "margin-top: " + -(gridBlockHeightPx - offsetYPx) + "px;");
    }

    @Override
    public boolean isAttached() {
        return svgElement.hasParentElement();
    }

    @Override
    public boolean equals(Element element) {
        if (element == null) {
            return false;
        }
        return svgElement.equals(element);
    }

    @Override
    public Element getElement() {
        return svgElement;
    }
}
