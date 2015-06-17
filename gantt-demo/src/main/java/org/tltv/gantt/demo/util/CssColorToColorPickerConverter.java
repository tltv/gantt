package org.tltv.gantt.demo.util;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.shared.ui.colorpicker.Color;

public class CssColorToColorPickerConverter implements Converter<String, Color> {

    public Color convertToModel(String value)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        return convertToModel(value, getModelType(), null);
    }

    @Override
    public Color convertToModel(String value,
            Class<? extends Color> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        if (value == null || value.trim().isEmpty()) {
            return Color.WHITE;
        }
        value = value.trim();
        if (!value.startsWith("#") && !value.startsWith("0x")) {
            value = "#" + value;
        }
        try {
            java.awt.Color c = java.awt.Color.decode(value);
            return new Color(c.getRed(), c.getGreen(), c.getBlue(),
                    c.getAlpha());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Color.WHITE;
    }

    @Override
    public String convertToPresentation(Color value,
            Class<? extends String> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        if (value != null) {
            return value.getCSS();
        }
        return null;
    }

    @Override
    public Class<Color> getModelType() {
        return Color.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

}
