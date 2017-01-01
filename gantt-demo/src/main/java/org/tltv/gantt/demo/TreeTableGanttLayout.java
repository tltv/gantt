package org.tltv.gantt.demo;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.mutable.MutableInt;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.CollapseListener;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;
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

        UI.getCurrent()
                .getPage()
                .getStyles()
                .add(".v-table-table tr td.v-table-cell-content { height: 36px; }");
        UI.getCurrent()
                .getPage()
                .getStyles()
                .add(".v-table-table tr:first-child td.v-table-cell-content { height: 37px; }");

        ganttTable = createTreeTableForGantt();

        addComponent(ganttTable);
        addComponent(gantt);
    }

    private TreeTable createTreeTableForGantt() {
        container = new BeanItemContainer<Step>(Step.class);

        final TreeTable table = new TreeTable(null, container);
        table.setBuffered(false);
        table.setSizeFull();
        container.addAll(gantt.getSteps());

        // create hierarchy just for demonstration purpose
        table.setChildrenAllowed(gantt.getSteps().get(0), true);
        table.setChildrenAllowed(gantt.getSteps().get(1), true);
        table.setParent(gantt.getSteps().get(1), gantt.getSteps().get(0));
        table.setParent(gantt.getSteps().get(2), gantt.getSteps().get(1));
        table.setVisibleColumns("caption");
        table.setCollapsed(gantt.getSteps().get(0), false);
        table.setCollapsed(gantt.getSteps().get(1), false);
        table.addCollapseListener(new CollapseListener() {

            @Override
            public void nodeCollapse(CollapseEvent event) {
                removeChildStepRecursively(table, event.getItemId());
            }
        });
        table.addExpandListener(new ExpandListener() {

            @Override
            public void nodeExpand(ExpandEvent event) {
                addChildStepRecursively(table, event.getItemId(),
                        new MutableInt());
            }
        });

        gantt.setVerticalScrollDelegateTarget(table);
        table.setColumnWidth(null, 500);
        return table;
    }

    /**
     * Add all child steps directed by the TreeTable's hierarchical data source.
     */
    private void addChildStepRecursively(TreeTable table, Object itemId,
            MutableInt index) {
        if (!table.hasChildren(itemId)) {
            return;
        }
        if (index.getValue() == 0) {
            index.setValue(gantt.getSteps().indexOf(itemId) + 1);
        }
        Collection<?> childs = new HashSet<Object>(table.getChildren(itemId));
        for (Object child : childs) {
            if (child instanceof Step) {
                gantt.addStep(index.getValue(), (Step) child);
                index.increment();
                addChildStepRecursively(table, child, index);
            }
        }
    }

    /**
     * Remove all child steps directed by the TreeTable's hierarchical data
     * source.
     */
    private void removeChildStepRecursively(TreeTable table, Object itemId) {
        if (table.hasChildren(itemId)) {
            for (Object child : new HashSet<Object>(table.getChildren(itemId))) {
                if (child instanceof Step) {
                    gantt.removeStep((Step) child);
                    removeChildStepRecursively(table, child);
                }
            }
        }
    }

    @Override
    public void stepModified(Step step) {
        if (!ganttTable.containsId(step)) {
            container.addBean(step);
        } else {
            ganttTable.refreshRowCache();
        }
    }

    @Override
    public void stepDeleted(Step step) {
        container.removeItem(step);
    }

    @Override
    public void stepMoved(Step step, int newStepIndex, int oldStepIndex) {
        // move to new position
        if (oldStepIndex < newStepIndex) {
            newStepIndex--;
        }
        ganttTable.removeItem(step);
        container.addItemAt(newStepIndex, step);
    }
}
