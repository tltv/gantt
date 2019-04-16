package org.tltv.gantt;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public abstract class GanttStep {

    private Integer identifier;
    private String uid = "";
    private String captionMode = "TEXT";
    private String styleName = "";
    private String caption = "";
    private String description = "";
    private String backgroundColor = "#A8D9FF";
    private double progress;
    private boolean showProgress;
    private boolean resizable = true;
    private boolean movable = true;
    private double startDate = -1;
    private double endDate = -1;
    private boolean substep;

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCaptionMode() {
        return captionMode;
    }

    public void setCaptionMode(String captionMode) {
        this.captionMode = captionMode;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public double getStartDate() {
        return startDate;
    }

    public void setStartDate(double startDate) {
        this.startDate = startDate;
    }

    public double getEndDate() {
        return endDate;
    }

    public void setEndDate(double endDate) {
        this.endDate = endDate;
    }

    public boolean isSubstep() {
        return substep;
    }

    public void setSubstep(boolean substep) {
        this.substep = substep;
    }

    public void setStartZonedDateTime(ZonedDateTime date) {
        setStartDate(Date.from(date.toInstant()).getTime());
    }

    public void setEndZonedDateTime(ZonedDateTime date) {
        setEndDate(Date.from(date.toInstant()).getTime());
    }

    @Override
    public int hashCode() {
        return uid != null ? Objects.hash(uid) : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(this instanceof GanttStep) || !(obj instanceof GanttStep)) {
            return false;
        }
        GanttStep other = (GanttStep) obj;
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
