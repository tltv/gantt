package org.tltv.gantt.demo;

public enum Operation {

    ADD("New", "add", false),
    EDIT("Edit", "edit", true);

    private final String nameInTitle;
    private final String nameInText;
    private final boolean deleteEnabled;

    Operation(String nameInTitle, String nameInText,
            boolean deleteEnabled) {
        this.nameInTitle = nameInTitle;
        this.nameInText = nameInText;
        this.deleteEnabled = deleteEnabled;
    }

    public String getNameInTitle() {
        return nameInTitle;
    }

    public String getNameInText() {
        return nameInText;
    }

    public boolean isDeleteEnabled() {
        return deleteEnabled;
    }
}
