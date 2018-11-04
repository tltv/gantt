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

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

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
    private boolean resizable = true;
    private boolean movable = true;

    private long startDate = -1;
    private long endDate = -1;

    private boolean substep;

    public AbstractStep() {
    }

    public AbstractStep(String caption) {
        setCaption(caption);
    }

    public AbstractStep(String caption, CaptionMode captionMode) {
        setCaption(caption);
        setCaptionMode(captionMode);
    }

    public boolean isSubstep() {
        return substep;
    }

    public void setSubstep(boolean substep) {
        this.substep = substep;
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

    public void read(JSONObject json) {
        if (json.containsKey("uid")) {
            setUid(json.get("uid").isString().stringValue());
        }
        if (json.containsKey("caption") && json.get("caption").isNull() == null) {
            setCaption(json.get("caption").isString().stringValue());
        }
        if (json.containsKey("description") && json.get("description").isNull() == null) {
            setDescription(json.get("description").isString().stringValue());
        }
        if (json.containsKey("backgroundColor") && json.get("backgroundColor").isNull() == null) {
            setBackgroundColor(json.get("backgroundColor").isString().stringValue());
        }
        if (json.containsKey("styleName") && json.get("styleName").isNull() == null) {
            setStyleName(json.get("styleName").isString().stringValue());
        }
        if (json.containsKey("identifier") && json.get("identifier").isNull() == null) {
            setIdentifier(Double.valueOf(json.get("identifier").isNumber().doubleValue()).longValue());
        }
        if (json.containsKey("startDate") && json.get("startDate").isNull() == null) {
            setStartDate(Double.valueOf(json.get("startDate").isNumber().doubleValue()).longValue());
        }
        if (json.containsKey("endDate") && json.get("endDate").isNull() == null) {
            setEndDate(Double.valueOf(json.get("endDate").isNumber().doubleValue()).longValue());
        }
        if (json.containsKey("captionMode") && json.get("captionMode").isNull() == null) {
            setCaptionMode(CaptionMode.valueOf(json.get("captionMode").isString().stringValue()));
        }
        if (json.containsKey("resizable") && json.get("resizable").isNull() == null) {
            setResizable(json.get("resizable").isBoolean().booleanValue());
        }
        if (json.containsKey("movable") && json.get("movable").isNull() == null) {
            setMovable(json.get("movable").isBoolean().booleanValue());
        }
        if (json.containsKey("showProgress") && json.get("showProgress").isNull() == null) {
            setShowProgress(json.get("showProgress").isBoolean().booleanValue());
        }
        if (json.containsKey("progress") && json.get("progress").isNull() == null) {
            setProgress(json.get("progress").isNumber().doubleValue());
        }
    }

    /** Returns JSON object with only uid for reference. */
    public JSONObject toJsonReference() {
        JSONObject json = new JSONObject();
        json.put("uid", new JSONString(getUid()));
        return json;
    }

    public JSONObject toJson() {
        JSONObject json = toJsonReference();
        if (getCaption() != null) {
            json.put("caption", new JSONString(getCaption()));
        }
        if (getDescription() != null) {
            json.put("description", new JSONString(getDescription()));
        }
        if (getBackgroundColor() != null) {
            json.put("backgroundColor", new JSONString(getBackgroundColor()));
        }
        if (getStyleName() != null) {
            json.put("styleName", new JSONString(getStyleName()));
        }
        if (getIdentifier() != null) {
            json.put("identifier", new JSONNumber(getIdentifier()));
        }
        json.put("startDate", new JSONNumber(getStartDate()));
        json.put("endDate", new JSONNumber(getEndDate()));
        if (getCaptionMode() != null) {
            json.put("captionMode", new JSONString(getCaptionMode().name()));
        }
        json.put("resizable", JSONBoolean.getInstance(isResizable()));
        json.put("movable", JSONBoolean.getInstance(isMovable()));
        json.put("showProgress", JSONBoolean.getInstance(isShowProgress()));
        json.put("progress", new JSONNumber(getProgress()));
        return json;
    }
}
