package org.tltv.gantt.client.shared;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.annotations.DelegateToWidget;

public class GanttState extends com.vaadin.shared.AbstractComponentState {

    public String locale;

    @DelegateToWidget
    public Resolution resolution = Resolution.Day;

    @DelegateToWidget
    public int firstDayOfRange;

    @DelegateToWidget
    public long startDate = -1;

    @DelegateToWidget
    public long endDate = -1;

    @DelegateToWidget
    public boolean resizableSteps = true;

    @DelegateToWidget
    public boolean movableSteps = true;

    public List<Step> steps = new LinkedList<Step>();

}