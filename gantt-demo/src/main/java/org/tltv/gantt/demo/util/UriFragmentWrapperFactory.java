package org.tltv.gantt.demo.util;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.demo.GridGanttLayout;
import org.tltv.gantt.demo.TreeGridGanttLayout;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

public class UriFragmentWrapperFactory {

    /**
     * Wrap the given component into a component identified by the given uri
     * fragment.
     * <p>
     * 'tabsheet' wraps it to Tabsheet component.
     * <p>
     * Returns by default the component itself.
     *
     * @param uriragment
     * @param component
     * @return
     */
    public static Component wrapByUriFragment(String uriragment, Gantt gantt) {
        if (uriragment == null) {
            return gantt;
        }
        if (uriragment.contains("tabsheet")) {
            TabSheet tabsheet = new TabSheet();
            tabsheet.setSizeFull();
            Tab tab = tabsheet.addTab(gantt);
            tab.setCaption("Tabsheet test");
            return tabsheet;

        } else if (uriragment.startsWith("grid")) {
            return new GridGanttLayout(gantt);

        } else if (uriragment.startsWith("treegrid")) {
            return new TreeGridGanttLayout(gantt);
        }

        return gantt;
    }

}
