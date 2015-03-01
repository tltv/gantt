package org.tltv.gantt.client.shared;

import java.io.Serializable;
import java.util.Date;

public class Step implements Serializable {

    private String uid;
    private CaptionMode captionMode = CaptionMode.TEXT;
    private String styleName;
    private String caption;
    private String backgroundColor = "#A8D9FF";
    private Step predecessor;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public void setStartDate(Date startDate) {
        if (startDate != null) {
            this.startDate = startDate.getTime();
        } else {
            this.startDate = -1;
        }
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = endDate.getTime();
        } else {
            this.endDate = -1;
        }
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Step getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Step predecessor) {
        this.predecessor = predecessor;
    }

    public enum CaptionMode {
        TEXT,
        HTML
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Step other = (Step) obj;
        if (uid == null) {
            if (other.uid != null) {
                return false;
            }
        } else if (!uid.equals(other.uid)) {
            return false;
        }
        return true;
    }

}
