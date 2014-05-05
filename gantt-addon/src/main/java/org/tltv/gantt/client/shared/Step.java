package org.tltv.gantt.client.shared;

import java.io.Serializable;

public class Step implements Serializable {
		private CaptionStyle captionStyle = CaptionStyle.TEXT;
    private String caption;
    private String backgroundColor = "#A8D9FF";

    private long startDate = -1;
    private long endDate = -1;

    public Step() {}

    public Step(String caption) {
        this.caption = caption;
    }

		public Step(String caption, CaptionStyle captionStyle) {
		    this.caption = caption;
		    this.captionStyle = captionStyle;
		}

    public String getCaption() {
        return caption;
    }

		public void setCaptionStyle(CaptionStyle captionStyle) {
		    this.captionStyle = captionStyle;
		}

		public CaptionStyle getCaptionStyle() {
		    return captionStyle;
		}

    public void setCaption(String caption) {
        this.caption = caption;
    }

		public void setCaption(String caption, CaptionStyle captionStyle) {
		    this.caption = caption;
		    this.captionStyle = captionStyle;
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

		public enum CaptionStyle {
			TEXT, HTML
		}
}
