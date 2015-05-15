package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

/** Interface for background grid implementation. */
public interface BgGridElement {

    /**
     * Initialize background grid.
     * <p>
     * Container and/or content are the targets which should have this
     * background grid. Depending on the implementation. Container is the main
     * 'panel' with scroll bars. Content is usually the first child element
     * inside the container, which wraps the actual content elements.
     * 
     * @param container
     *            (Scroll) container element which contains content element.
     * @param content
     *            Content element which is a child of container element.
     */
    void init(Element container, Element content);

    /** Hide background grid. */
    void hide();

    /**
     * Set background grid's block width and height.
     * <p>
     * Should be called <u>before</u>
     * {@link #setBackgroundPosition(String, String, double, double)}.
     */
    void setBackgroundSize(String gridBlockWidth, double gridBlockWidthPx,
            int gridBlockHeightPx);

    /**
     * Set background grid position.
     * <p>
     * Should be called after {@link #setBackgroundSize(String, double, int)}.
     */
    void setBackgroundPosition(String offsetX, String offsetY, double posXPx,
            double posYPx);

    /** Returns true if this is a element attached to container. */
    boolean isAttached();

    /**
     * Return true if given element is same as element representing background
     * element.
     */
    boolean equals(Element element);

    /** Get element representing background. May be null. */
    Element getElement();
}
