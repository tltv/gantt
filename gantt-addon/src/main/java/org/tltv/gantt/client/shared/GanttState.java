package org.tltv.gantt.client.shared;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.Connector;
import com.vaadin.shared.annotations.DelegateToWidget;

public class GanttState extends com.vaadin.shared.AbstractComponentState {

    public String locale;

    public Long timeZoneOffset;

    @DelegateToWidget
    public Resolution resolution = Resolution.Day;

    @DelegateToWidget
    public int firstDayOfRange;

    @DelegateToWidget
    public int firstHourOfRange;

    @DelegateToWidget
    public Long startDate;

    @DelegateToWidget
    public Long endDate;

    @DelegateToWidget
    public boolean resizableSteps = true;

    @DelegateToWidget
    public boolean movableSteps = true;

    @DelegateToWidget
    public boolean monthRowVisible = true;

    @DelegateToWidget
    public boolean yearRowVisible = true;

    @DelegateToWidget
    public String weekFormat;

    @DelegateToWidget
    public String dayFormat;

    @DelegateToWidget
    public String monthFormat;

    @DelegateToWidget
    public String yearFormat;

    public List<Step> steps = new LinkedList<Step>();

    public Connector verticalScrollDelegateTarget;

}