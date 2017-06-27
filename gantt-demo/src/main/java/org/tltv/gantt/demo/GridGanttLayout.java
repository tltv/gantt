package org.tltv.gantt.demo;

import java.util.ArrayList;
import java.util.List;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class GridGanttLayout extends HorizontalLayout implements GanttListener {

    Gantt gantt;
    Grid<Step> ganttGrid;
    ListDataProvider<Step> dataProvider;

    public GridGanttLayout(Gantt gantt) {
        this.gantt = gantt;

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        UI.getCurrent().getPage().getStyles().add(".v-grid tr th, .v-grid tr td { height: 37px; }");

        ganttGrid = createGridForGantt();

        addComponent(ganttGrid);
        addComponent(gantt);
        setExpandRatio(gantt, 1);
    }

    private Grid<Step> createGridForGantt() {

        dataProvider = new ListDataProvider<>(new ArrayList<>(gantt.getSteps()));
        Grid<Step> grid = new Grid<>(dataProvider);
        grid.setWidth(400, Unit.PIXELS);
        grid.setHeight(100, Unit.PERCENTAGE);

        Column<Step, ?> c = grid.addColumn(Step::getCaption);
        c.setSortable(false);
        c.setResizable(false);

        gantt.setVerticalScrollDelegateTarget(grid);

        return grid;
    }

    @Override
    public void stepModified(Step step) {
        if (!dataProvider.getItems().contains(step)) {
            dataProvider.getItems().add(step);
        }
        dataProvider.refreshItem(step);
    }

    @Override
    public void stepDeleted(Step step) {
        dataProvider.getItems().remove(step);
        dataProvider.refreshAll();
    }

    @Override
    public void stepMoved(Step step, int newStepIndex, int oldStepIndex) {
        if (oldStepIndex < newStepIndex) {
            newStepIndex--;
        }
        dataProvider.getItems().remove(step);
        ((List<Step>) dataProvider.getItems()).add(newStepIndex, step);
        dataProvider.refreshAll();
    }
}
