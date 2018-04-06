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
    public String weekFormat;

    @DelegateToWidget
    public String dayFormat;

    @DelegateToWidget
    public String monthFormat;

    @DelegateToWidget
    public String yearFormat;

    @DelegateToWidget
    public boolean defaultContextMenuEnabled = false;

    public String[] localeDayNames = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
    public String[] localeShortDayNames = { "sun", "mon", "tue", "wed", "thu", "fri", "sat" };
    public String[] localeMonthNames = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };
    public String[] localeShortMonthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec" };
    public int localeFirstDayOfWeek = 0;
    public boolean localeTwelveHourClock = false;

    public List<Connector> steps = new LinkedList<Connector>();

    public Connector verticalScrollDelegateTarget;

}