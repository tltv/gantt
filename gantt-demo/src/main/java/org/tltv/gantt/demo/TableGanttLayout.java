package org.tltv.gantt.demo;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

public class TableGanttLayout extends HorizontalLayout implements GanttListener {

    Gantt gantt;
    Table ganttTable;

    public TableGanttLayout(Gantt gantt) {
        this.gantt = gantt;

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        UI.getCurrent().getPage().getStyles()
                .add(".v-table-row, .v-table-row-odd { height: 30px; }");
        ganttTable = createTableForGantt();

        addComponent(ganttTable);
        addComponent(gantt);
    }

    private Table createTableForGantt() {
        BeanItemContainer<Step> container = new BeanItemContainer<Step>(
                Step.class);

        Table table = new Table(null, container);
        table.setSortEnabled(false);
        table.setBuffered(false);
        table.setSizeFull();
        container.addAll(gantt.getSteps());
        table.setVisibleColumns("caption");

        gantt.setVerticalScrollDelegateTarget(table);
        table.setColumnWidth(null, 500);
        return table;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void stepModified(Step step) {
        if (!ganttTable.containsId(step)) {
            ((BeanItemContainer<Step>) ganttTable.getContainerDataSource())
                    .addBean(step);
        } else {
            ganttTable.refreshRowCache();
        }
    }
}
