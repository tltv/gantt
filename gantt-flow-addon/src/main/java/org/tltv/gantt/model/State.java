package org.tltv.gantt.model;

public class State {

    private Double startDate;
    private Double endDate;
    private String locale = "en_US";
    private String resolution = "Day";
    private boolean backgroundGridEnabled = true;

    public Double getStartDate() {
        return startDate;
    }

    public void setStartDate(Double startDate) {
        this.startDate = startDate;
    }

    public Double getEndDate() {
        return endDate;
    }

    public void setEndDate(Double endDate) {
        this.endDate = endDate;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isBackgroundGridEnabled() {
        return backgroundGridEnabled;
    }

    public void setBackgroundGridEnabled(boolean backgroundGridEnabled) {
        this.backgroundGridEnabled = backgroundGridEnabled;
    }
}
