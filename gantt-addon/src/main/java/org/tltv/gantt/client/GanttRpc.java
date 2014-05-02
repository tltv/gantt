package org.tltv.gantt.client;

public interface GanttRpc {

    void stepClicked(int index);

    void onMove(int rowIndex, int newRowIndex, long startDate, long endDate);

    void onResize(int rowIndex, long startDate, long endDate);
}
