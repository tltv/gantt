package org.tltv.gantt.demo.util;

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
    public static Component wrapByUriFragment(String uriragment,
            Component component) {
        if (uriragment == null) {
            return component;
        }
        if (uriragment.contains("tabsheet")) {
            TabSheet tabsheet = new TabSheet();
            tabsheet.setSizeFull();
            Tab tab = tabsheet.addTab(component);
            tab.setCaption("Tabsheet test");
            return tabsheet;
        }
        return component;
    }
}
