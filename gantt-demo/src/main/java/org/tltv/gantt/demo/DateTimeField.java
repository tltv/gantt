package org.tltv.gantt.demo;

import java.util.function.Supplier;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttUtil;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;

public class DateTimeField extends CustomField<Double> {

    private TimePicker timePicker;
    private DatePicker datePicker;

    private Supplier<Gantt> ganttSupplier;

    public DateTimeField(String label, Supplier<Gantt> ganttSupplier) {
        this.ganttSupplier = ganttSupplier;

        datePicker = new DatePicker();
        datePicker.setLabel(label);
        datePicker.setRequired(true);
        datePicker.setLocale(UI.getCurrent().getLocale());
        datePicker.addValueChangeListener(event -> updateValue());

        timePicker = new TimePicker();
        timePicker.setLabel("Time");
        timePicker.setRequired(true);
        timePicker.setLocale(UI.getCurrent().getLocale());
        timePicker.addValueChangeListener(event -> updateValue());

        add(new HorizontalLayout(datePicker, timePicker));
    }

    @Override
    protected Double generateModelValue() {
        return GanttUtil.toDouble(datePicker.getValue(), timePicker.getValue(), ganttSupplier.get());
    }

    @Override
    protected void setPresentationValue(Double value) {
        datePicker.setValue(GanttUtil.toLocalDate(value, ganttSupplier.get()));
        timePicker.setValue(GanttUtil.toLocalTime(value, ganttSupplier.get()));
    }

}