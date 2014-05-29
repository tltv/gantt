package org.tltv.gantt.demo.util;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.demo.TableGanttLayout;
import org.tltv.gantt.demo.TreeTableGanttLayout;

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

        } else if (uriragment.contains("table")) {
            return new TableGanttLayout(gantt);

        } else if (uriragment.contains("treetable")) {
            return new TreeTableGanttLayout(gantt);

        }
        return gantt;
    }

}
