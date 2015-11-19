package com.schalamcharla.simpletodolistapp.util;

/**
 * Created by schalamcharla on 11/18/15.
 */
public class ViewTask {
    String label;
    String value;

    public ViewTask() {}

    public ViewTask(String label, String value) {
        this.label = label;
        this.value = value;
    }

    // Getters
    public String getLabel() { return this.label; }
    public String getValue() { return this.value; }

    // Setters
    public void setLabel(String label) { this.label = label; }
    public void setValue(String value) { this.value = value; }
}
