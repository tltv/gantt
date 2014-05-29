package org.tltv.gantt.demo;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.UI;

public class TableGanttLayout extends HorizontalLayout {

    Gantt gantt;

    public TableGanttLayout(Gantt gantt) {
        this.gantt = gantt;

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        UI.getCurrent().getPage().getStyles()
                .add(".v-table-row, .v-table-row-odd { height: 30px; }");

        addComponent(createTableForGantt());
        addComponent(gantt);
    }

    private Table createTableForGantt() {
        Table table = new Table();
        table.setSizeFull();
        table.setRowHeaderMode(RowHeaderMode.EXPLICIT);
        for (Step step : gantt.getSteps()) {
            table.addItem(step);
            table.setItemCaption(step, step.getCaption());
        }
        gantt.setVerticalScrollDelegateTarget(table);
        table.setColumnWidth(null, 500);
        return table;
    }
}
