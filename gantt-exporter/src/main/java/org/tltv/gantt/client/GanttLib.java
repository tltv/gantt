package org.tltv.gantt.client;

import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;

public class GanttLib implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ExporterUtil.exportAll();

        onLoad();
    }

    private native void onLoad()
    /*-{
        if ($wnd.myInit) $wnd.myInit();
    }-*/;
}
