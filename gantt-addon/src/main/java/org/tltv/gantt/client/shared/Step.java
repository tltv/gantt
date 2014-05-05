package org.tltv.gantt.client.shared;

import java.io.Serializable;

public class Step implements Serializable {

    private CaptionMode captionMode = CaptionMode.TEXT;
    private String styleName;
    private String caption;
    private String backgroundColor = "#A8D9FF";

    private long startDate = -1;
    private long endDate = -1;

    public Step() {
    }

    public Step(String caption) {
        this.caption = caption;
    }

    public Step(String caption, CaptionMode captionMode) {
        this.caption = caption;
        this.captionMode = captionMode;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCaptionMode(CaptionMode captionMode) {
        this.captionMode = captionMode;
    }

    public CaptionMode getCaptionMode() {
        return captionMode;
    }

    public void setCaption(String caption, CaptionMode captionMode) {
        this.caption = caption;
        this.captionMode = captionMode;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        if (backgroundColor != null && !backgroundColor.trim().isEmpty()
                && !backgroundColor.trim().startsWith("#")) {
            backgroundColor = "#" + backgroundColor;
        }
        this.backgroundColor = backgroundColor;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public enum CaptionMode {
        TEXT,
        HTML
    }
}
