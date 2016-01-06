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
package org.tltv.gantt.client.shared;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractStep implements Serializable {

    private Long identifier;
    private String uid;
    private CaptionMode captionMode = CaptionMode.TEXT;
    private String styleName;
    private String caption;
    private String description;
    private String backgroundColor = "#A8D9FF";
    private double progress;
    private boolean showProgress;

    private long startDate = -1;
    private long endDate = -1;

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
