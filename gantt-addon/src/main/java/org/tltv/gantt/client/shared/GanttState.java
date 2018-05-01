package org.tltv.gantt.client.shared;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;
import com.vaadin.shared.annotations.DelegateToWidget;

public class GanttState extends AbstractComponentState {

    public boolean readOnly = false;

    public String locale;

    public String timeZoneJson;

    public String timeZoneId;

    @DelegateToWidget
    public Resolution resolution = Resolution.Day;

    @DelegateToWidget
    public int firstDayOfRange;

    @DelegateToWidget
    public int firstHourOfRange;

    /** GMT start date */
    @DelegateToWidget
    public Long startDate;

    /** GMT end date */
    @DelegateToWidget
    public Long endDate;

    @DelegateToWidget
    public boolean resizableSteps = true;

    @DelegateToWidget
    public boolean movableSteps = true;

    @DelegateToWidget
    public boolean movableStepsBetweenRows = false;

    @DelegateToWidget
    public boolean monthRowVisible = true;

    @DelegateToWidget
    public boolean yearRowVisible = true;

    @DelegateToWidget
    public boolean backgroundGridEnabled = true;

    @DelegateToWidget
    public String hourFormat;

    @DelegateToWidget
    public String weekFormat;

    @DelegateToWidget
    public String dayFormat;

    @DelegateToWidget
    public String monthFormat;

    @DelegateToWidget
    public String yearFormat;

    @DelegateToWidget
    public boolean defaultContextMenuEnabled = false;

    @DelegateToWidget
    public boolean showCurrentTime = true;

    /** Current date in yyyyMMdd format. */
    @DelegateToWidget
    public String currentDate;

    /** Current hour in HH format. */
    @DelegateToWidget
    public String currentHour;

    @DelegateToWidget
    public Long timestamp;

    public List<Connector> steps = new LinkedList<Connector>();

    public Connector verticalScrollDelegateTarget;

}