package org.tltv.gantt.client;

import com.google.gwt.dom.client.Element;

public interface ProgressElement {

    public void init(double progressValue);

    public void setProgress(double progress);

    public Element getElement();
}
