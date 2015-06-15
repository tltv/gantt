package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.ui.VProgressBar;

public class ProgressBarElement implements ProgressElement {

    private VProgressBar element;

    @Override
    public void init(double progressValue) {
        element = new VProgressBar();
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
