package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

public class ProgressBarElement implements ProgressElement {

    private GanttProgressBar element;

    @Override
    public void init(double progressValue) {
        element = new GanttProgressBar();
        element.addStyleName("bar-progress");
        element.setState(convertToState(progressValue));
    }

    @Override
    public void setProgress(double progress) {
        element.setState(convertToState(progress));
    }

    @Override
    public Element getElement() {
        return element.getElement();
    }

    protected float convertToState(double value) {
        return (float) (value / 100.0);
    }
}
