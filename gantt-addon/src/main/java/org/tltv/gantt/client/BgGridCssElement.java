package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

/**
 * Background Grid CSS implementation.
 * <p>
 * Chrome: css background-size attribute desn't work with decimal numbers. Use
 * {@link BgGridSvgElement} instead.
 * */
public class BgGridCssElement implements BgGridElement {

    private Element container;

    @Override
    public void init(Element container, Element content) {
        this.container = container;
    }

    @Override
    public void hide() {
        container.getStyle().setBackgroundImage("none");
    }

    @Override
    public void setBackgroundSize(String gridBlockWidth,
            double gridBlockWidthPx, int gridBlockHeightPx) {
        container.getStyle().setProperty("backgroundSize",
                gridBlockWidth + " " + gridBlockHeightPx + "px");
    }

    @Override
    public void setBackgroundPosition(String offsetX, String offsetY,
            double posXPx, double posYPx) {
        container.getStyle().setProperty("backgroundPosition",
                offsetX + " " + offsetY);
    }

    @Override
    public boolean isAttached() {
        return false;
    }

    @Override
    public boolean equals(Element element) {
        return false;
    }

    @Override
    public Element getElement() {
        return null;
    }

}
