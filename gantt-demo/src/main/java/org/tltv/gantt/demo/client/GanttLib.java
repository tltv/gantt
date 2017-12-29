package org.tltv.gantt.demo.client;

import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;

/** GanttLib exporter for debugging. */
public class GanttLib implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ExporterUtil.exportAll();

        onLoad();
    }

    private native void onLoad()
    /*-{
        if ($wnd.ganttLibReady) $wnd.ganttLibReady();
    }-*/;
}
