package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

public class ArrowPositionData {

    private final Element from;
    private final Element to;

    int predecessorHeightCenter;
    int predecessorTop;
    int predecesorBottom;
    int predecessorRight;
    int predecessorLeft;
    int thisBottom;
    int thisCenter;

    double top;
    double bottom;
    double height;

    double left;
    double right;
    double width;

    boolean fromTop;
    boolean fromLeft;
    int halfWidth;

    public ArrowPositionData(Element from, Element to) {
        this.from = from;
        this.to = to;

        predecessorHeightCenter = from.getOffsetHeight() / 2;
        predecessorTop = from.getOffsetTop();
        predecesorBottom = from.getOffsetTop() + from.getOffsetHeight();
        predecessorRight = from.getOffsetLeft() + from.getOffsetWidth();
        predecessorLeft = from.getOffsetLeft();
        thisBottom = to.getOffsetTop() + to.getOffsetHeight();
        thisCenter = to.getOffsetHeight() / 2;

        top = Math.min(predecessorTop, to.getOffsetTop());
        bottom = Math.max(predecesorBottom, thisBottom);
        height = bottom - top;

        left = Math.min(predecessorRight, to.getOffsetLeft());
        right = Math.max(predecessorRight, to.getOffsetLeft());
        width = right - left;

        fromTop = predecessorTop <= to.getOffsetTop();
        fromLeft = predecessorRight <= to.getOffsetLeft();
        halfWidth = (int) width / 2;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getTop() {
        return top;
    }

    public double getBottom() {
        return bottom;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public Element getFrom() {
        return from;
    }

    public Element getTo() {
        return to;
    }

    public int getFromHeightCenter() {
        return predecessorHeightCenter;
    }

    public void setPredecessorHeightCenter(int predecessorHeightCenter) {
        this.predecessorHeightCenter = predecessorHeightCenter;
    }

    public int getPredecessorTop() {
        return predecessorTop;
    }

    public void setPredecessorTop(int predecessorTop) {
        this.predecessorTop = predecessorTop;
    }

    public int getPredecesorBottom() {
        return predecesorBottom;
    }

    public void setPredecesorBottom(int predecesorBottom) {
        this.predecesorBottom = predecesorBottom;
    }

    public int getPredecessorRight() {
        return predecessorRight;
    }

    public void setPredecessorRight(int predecessorRight) {
        this.predecessorRight = predecessorRight;
    }

    public int getPredecessorLeft() {
        return predecessorLeft;
    }

    public void setPredecessorLeft(int predecessorLeft) {
        this.predecessorLeft = predecessorLeft;
    }

    public int getThisBottom() {
        return thisBottom;
    }

    public void setThisBottom(int thisBottom) {
        this.thisBottom = thisBottom;
    }

    public int getToHeightCenter() {
        return thisCenter;
    }

    public void setThisCenter(int thisCenter) {
        this.thisCenter = thisCenter;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isFromTop() {
        return fromTop;
    }

    public void setFromTop(boolean fromTop) {
        this.fromTop = fromTop;
    }

    public boolean isFromLeft() {
        return fromLeft;
    }

    public void setFromLeft(boolean fromLeft) {
        this.fromLeft = fromLeft;
    }

    public int getHalfWidth() {
        return halfWidth;
    }

    public void setHalfWidth(int halfWidth) {
        this.halfWidth = halfWidth;
    }

    public int calcStartPointX() {
        return (isFromLeft()) ? 0 : (int) width;
    }

    public int calcStartPointY() {
        return (isFromTop()) ? getFromHeightCenter() : (int) height
                - getFromHeightCenter();
    }

    public int calcEndPointX() {
        return (!isFromLeft()) ? 0 : (int) width;
    }

    public int calcEndPointY() {
        return ((!isFromTop()) ? getFromHeightCenter() : (int) height
                - getFromHeightCenter());
    }

}
