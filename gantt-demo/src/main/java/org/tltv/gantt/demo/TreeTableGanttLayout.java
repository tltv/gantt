package org.tltv.gantt.demo;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;

public class TreeTableGanttLayout extends HorizontalLayout implements
        GanttListener {

    Gantt gantt;
    TreeTable ganttTable;
    BeanItemContainer<Step> container;

    public TreeTableGanttLayout(Gantt gantt) {
        this.gantt = gantt;

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        UI.getCurrent().getPage().getStyles()
                .add(".v-table-row, .v-table-row-odd { height: 30px; }");
        ganttTable = createTreeTableForGantt();

        addComponent(ganttTable);
        addComponent(gantt);
    }

    private TreeTable createTreeTableForGantt() {
        container = new BeanItemContainer<Step>(Step.class);

        TreeTable table = new TreeTable(null, container);
        table.setBuffered(false);
        table.setSizeFull();
        container.addAll(gantt.getSteps());
        table.setVisibleColumns("caption");

        gantt.setVerticalScrollDelegateTarget(table);
        table.setColumnWidth(null, 500);
        return table;
    }

    @Override
    public void stepModified(Step step) {
        if (!ganttTable.containsId(step)) {
            container.addBean(step);
        } else {
            ganttTable.refreshRowCache();
        }
    }
}
