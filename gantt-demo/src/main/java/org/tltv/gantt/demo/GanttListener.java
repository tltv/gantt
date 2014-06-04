package org.tltv.gantt.demo;

import org.tltv.gantt.client.shared.Step;

public interface GanttListener {

    void stepModified(Step step);
}
