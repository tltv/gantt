package org.tltv.gantt.demo.util;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.shared.ui.colorpicker.Color;

public class CssColorToColorPickerConverter implements Converter<String, Color> {

    public Color convertToModel(String value) {
        return convertToModel(value, null).getOrThrow((v) -> new RuntimeException("Invalid color value '" + v + "'!"));
    }

    @Override
    public Result<Color> convertToModel(String value, ValueContext context) {
        if (value == null || value.trim().isEmpty()) {
            return Result.ok(Color.WHITE);
        }
        value = value.trim();
        if (!value.startsWith("#") && !value.startsWith("0x")) {
            value = "#" + value;
        }
        try {
            java.awt.Color c = java.awt.Color.decode(value);
            return Result.ok(new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Result.ok(Color.WHITE);
    }

    @Override
    public String convertToPresentation(Color value, ValueContext context) {
        if (value != null) {
            return value.getCSS();
        }
        return null;
    }

}
