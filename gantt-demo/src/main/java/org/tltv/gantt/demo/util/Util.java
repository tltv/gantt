package org.tltv.gantt.demo.util;

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Util {

    public static TextField createNumberEditor(String caption, float value,
            final Component component, final NumberValueChange valueChange) {
        TextField field = new TextField(caption);
        field.setColumns(5);
        field.setValue("" + value);
        field.setImmediate(true);
        field.addValueChangeListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Object v = event.getProperty().getValue();
                try {
                    float f = Float.parseFloat("" + v);
                    valueChange.onValueChange(f);
                } catch (NumberFormatException e) {
                    Notification
                            .show("Invalid floating number! Format is 123.345");
                }
            }
        });
        return field;
    }

    public static TextField createTextEditor(String caption, String value,
            final Component component, final TextValueChange valueChange) {
        TextField field = new TextField(caption);
        field.setValue("" + value);
        field.setImmediate(true);
        field.addValueChangeListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Object v = event.getProperty().getValue();
                valueChange.onValueChange(String.valueOf(v));
            }
        });
        return field;
    }

    public static TextField createHeightEditor(final Component component) {
        return createNumberEditor("Height", component.getHeight(), component,
                new NumberValueChange() {

                    @Override
                    public void onValueChange(float number) {
                        component.setHeight(number, component.getHeightUnits());
                    }
                });
    }

    public static TextField createWidthEditor(final Component component) {
        return createNumberEditor("Width", component.getWidth(), component,
                new NumberValueChange() {

                    @Override
                    public void onValueChange(float number) {
                        component.setWidth(number, component.getWidthUnits());
                    }
                });
    }

    public static NativeSelect createHeightUnitEditor(final Component component) {
        return createNativeSelectEditor("Height Unit",
                component.getHeightUnits(), Arrays.asList(Unit.values()),
                new SelectValueChange() {

                    @Override
                    public void onValueChange(Object unit) {
                        component.setHeight(component.getHeight(), (Unit) unit);
                    }
                });
    }

    public static NativeSelect createWidthUnitEditor(final Component component) {
        return createNativeSelectEditor("Width Unit",
                component.getWidthUnits(), Arrays.asList(Unit.values()),
                new SelectValueChange() {

                    @Override
                    public void onValueChange(Object unit) {
                        component.setWidth(component.getWidth(), (Unit) unit);
                    }
                });
    }

    public static NativeSelect createNativeSelectEditor(String caption,
            Object value, Collection<?> items,
            final SelectValueChange valueChange) {
        NativeSelect s = new NativeSelect(caption);
        for (Object i : items) {
            s.addItem(i);
            s.setItemCaption(i, String.valueOf(i));
        }
        s.setNullSelectionAllowed(false);
        s.setValue(value);
        s.setImmediate(true);
        s.addValueChangeListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                valueChange.onValueChange(event.getProperty().getValue());
            }
        });
        return s;
    }

    public static void showConfirmationPopup(String msg, final Runnable callback) {
        Window window = new Window();
        window.setModal(true);
        window.center();
        window.setWidth(400, Unit.PIXELS);
        window.setClosable(false);
        window.setResizable(false);

        VerticalLayout content = new VerticalLayout();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);
        content.setMargin(true);

        Label l = new Label(msg);
        content.addComponent(l);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button ok = new Button("OK");
        ok.setData(window);
        ok.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                callback.run();
                ((Window) event.getButton().getData()).close();
            }
        });
        Button cancel = new Button("Cancel");
        cancel.setData(window);
        cancel.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                ((Window) event.getButton().getData()).close();
            }
        });
        buttons.addComponent(ok);
        buttons.addComponent(cancel);
        content.addComponent(buttons);

        window.setContent(content);

        UI.getCurrent().addWindow(window);
    }

    public interface TextValueChange {
        void onValueChange(String value);
    }

    public interface NumberValueChange {
        void onValueChange(float number);
    }

    public interface SelectValueChange {
        void onValueChange(Object value);
    }
}
