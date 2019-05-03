package org.tltv.gantt;

import com.vaadin.flow.templatemodel.ModelEncoder;

/**
 * Encoder for {@link GanttStep#getEndDate()} that is inclusive in client (model
 * value) and exclusive on presentation (in Flow date fields).
 */
public class DateEncoder implements ModelEncoder<Double, Double> {

    @Override
    public Double encode(Double modelValue) {
        return modelValue + 1;
    }

    @Override
    public Double decode(Double presentationValue) {
        return presentationValue - 1;
    }
}
