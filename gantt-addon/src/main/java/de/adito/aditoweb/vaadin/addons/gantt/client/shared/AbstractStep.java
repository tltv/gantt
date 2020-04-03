/*
 * Copyright 2015 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.adito.aditoweb.vaadin.addons.gantt.client.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractStep implements Serializable {

    private Long identifier;
    private String uid;
    private CaptionMode captionMode = CaptionMode.TEXT;
    private String styleName;
    private ArrayList<String> styleNames = new ArrayList<>();
    private ArrayList<String> removeStyleNames = new ArrayList<>();
    private String caption;
    private String description;
    private String backgroundColor = "#A8D9FF";
    private String foregroundColor = "#ffffff";
    private int height;
    private double progress;
    private boolean showProgress;
    private boolean resizable = true;
    private boolean movable = true;

    private long startDate = -1;
    private long endDate = -1;
    private boolean isGroup = false;

    public AbstractStep() {
    }

    public AbstractStep(String caption) {
        setCaption(caption);
    }

    public AbstractStep(String caption, CaptionMode captionMode) {
        setCaption(caption);
        setCaptionMode(captionMode);
    }

    /** Application specific Optional identifier. */
    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    /**
     * Unique identifier for this step. This is auto-generated for new steps
     * when not set explicitly.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set unique identifier for this step. It's not recommended to set this
     * explicitly as it will be auto-generated when step is added first time
     * into Gantt and UID is not already set.
     */
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
        if (backgroundColor != null && !backgroundColor.trim().isEmpty() && !backgroundColor.trim().startsWith("#")) {
            backgroundColor = "#" + backgroundColor;
        }
        this.backgroundColor = backgroundColor;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        if (foregroundColor != null && !foregroundColor.trim().isEmpty() && !foregroundColor.trim().startsWith("#")) {
            foregroundColor = "#" + foregroundColor;
        }
        this.foregroundColor = foregroundColor;
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

    public boolean isStartDateUndefined() {
        return startDate < 0;
    }

    public boolean isEndDateUndefined() {
        return endDate < 0;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public ArrayList<String> getStyleNames() {
        return styleNames;
    }

    public void setStyleNames(ArrayList<String> styleNames) {
        this.styleNames = styleNames;
    }

    public void addStyleName(String style) {
        styleNames.add(style);
    }

    public void addStyleNames(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
    }

    public ArrayList<String> getRemoveStyleNames() {
        return removeStyleNames;
    }

    public void setRemoveStyleNames(ArrayList<String> removeStyleNames) {
        this.removeStyleNames = removeStyleNames;
    }

    public void removeStyleName(String style) {
        removeStyleNames.add(style);
    }

    public void removeStyleNames(String... styles) {
        for (String style : styles) {
            removeStyleName(style);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProgress() {
        return progress;
    }

    /**
     * Set progress between 0-100%.
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    /** Show or hide progress bar inside the step. */
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGroup()
    {
        return isGroup;
    }

    public void setGroup(boolean pIsGroup)//todo rename
    {
        this.isGroup = pIsGroup;
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
        if (!(this instanceof AbstractStep) || !(obj instanceof AbstractStep)) {
            return false;
        }
        AbstractStep other = (AbstractStep) obj;
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
