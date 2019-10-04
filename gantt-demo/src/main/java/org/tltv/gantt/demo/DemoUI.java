/*
 * Copyright 2018 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tltv.gantt.demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.GanttStep;
import org.tltv.gantt.model.Resolution;
import org.tltv.gantt.model.Step;
import org.tltv.gantt.model.SubStep;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.events.MouseEventDetails.MouseButton;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(Lumo.class)
@Route("")
@SuppressWarnings("serial")
public class DemoUI extends Div {

    private ZoneId defaultZoneId;
    private int newId = 10000;

    private StepEditor stepEditor = new StepEditor(this::saveUpdate, this::deleteUpdate);

    private SubStepEditor subStepEditor = new SubStepEditor(this::saveUpdateSubStep, this::deleteUpdate);

    private final Gantt gantt;

    private int year = LocalDateTime.now().getYear();

    public DemoUI() {
        setSizeFull();

        gantt = new Gantt();

        Step stepA = new Step();
        stepA.setUid("1"); // required unique step id
        stepA.setCaption("Step A");
        stepA.setDescription("Description of Step A");
        stepA.setBackgroundColor("#A8D946");
        stepA.setStartZonedDateTime(LocalDateTime.of(year, 1, 1, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepA.setEndZonedDateTime(LocalDateTime.of(year, 1, 30, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepA.setResizable(true);
        stepA.setMovable(true);

        Step stepB = new Step();
        stepB.setUid("2"); // required unique step id
        stepB.setCaption("Step B");
        stepB.setDescription("Description of Step B");
        stepB.setStartZonedDateTime(LocalDateTime.of(year, 2, 1, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepB.setEndZonedDateTime(LocalDateTime.of(year, 2, 26, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepB.setResizable(true);
        stepB.setMovable(true);
        stepB.setPredecessor(stepA);

        Step stepC = new Step();
        stepC.setUid("3"); // required unique step id
        stepC.setCaption("Step C");
        stepC.setDescription("Description of Step C");
        stepC.setStartZonedDateTime(LocalDateTime.of(year, 3, 1, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepC.setEndZonedDateTime(LocalDateTime.of(year, 3, 26, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepC.setResizable(true);
        stepC.setMovable(true);
        stepC.setPredecessor(stepB);

        Step stepD = new Step();
        stepD.setUid("4"); // required unique step id
        stepD.setCaption("Step D");
        stepD.setDescription("Description of Step D");
        stepD.setStartZonedDateTime(LocalDateTime.of(year, 1, 15, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepD.setEndZonedDateTime(LocalDateTime.of(year, 1, 26, 0, 0, 0).atZone(getDefaultTimeZone()));
        stepD.setResizable(true);
        stepD.setMovable(true);

        SubStep subStepA = new SubStep();
        subStepA.setUid("5"); // required unique step id
        subStepA.setCaption("SubStep A");
        subStepA.setBackgroundColor("#A8D946");
        subStepA.setDescription("Description of SubStep A");
        subStepA.setStartZonedDateTime(LocalDateTime.of(year, 1, 15, 0, 0,
                0).atZone(getDefaultTimeZone()));
        subStepA.setEndZonedDateTime(LocalDateTime.of(year, 1, 21, 0, 0,
                0).atZone(getDefaultTimeZone()));
        subStepA.setResizable(true);
        subStepA.setMovable(true);
        subStepA.setOwner(stepD);

        SubStep subStepB = new SubStep();
        subStepB.setUid("6"); // required unique step id
        subStepB.setCaption("SubStep B");
        subStepB.setDescription("Description of SubStep B");
        subStepB.setStartZonedDateTime(LocalDateTime.of(year, 1, 21, 0, 0,
                0).atZone(getDefaultTimeZone()));
        subStepB.setEndZonedDateTime(LocalDateTime.of(year, 1, 26, 0, 0,
                0).atZone(getDefaultTimeZone()));
        subStepB.setResizable(true);
        subStepB.setMovable(true);
        subStepB.setOwner(stepD);

        gantt.addStep(stepA);
        gantt.addStep(stepB);
        gantt.addStep(stepC);
        gantt.addStep(stepD);
        gantt.addSubSteps(subStepA, subStepB);

        gantt.setStartDateTime(LocalDateTime.of(year, 1, 1, 0, 0, 0));
        gantt.setEndDateTime(LocalDateTime.of(year + 1, 1, 1, 0, 0, 0));
        gantt.getSettings().setMovableStepsBetweenRows(true);

        final VerticalLayout layout = new VerticalLayout();
        layout.setClassName("demoContentLayout");
        layout.setMargin(false);
        layout.setSizeFull();
        layout.add(addMenu(gantt));
        layout.add(createControls(gantt));
        layout.add(gantt);

        add(layout);

        addEventHandlers(gantt);
    }

    private void addEventHandlers(Gantt gantt) {
        gantt.addStepClickListener(event -> {
            if (event.getDetails().getButton().equals(MouseButton.LEFT)) {
                if (event.getStep() != null) {
                    openEditorForStep(event.getStep());
                } else if (event.getSubStep() != null) {
                    openEditorForSubStep(event.getSubStep());
                }
            } else if (event.getDetails().getButton().equals(MouseButton.RIGHT)) {
                Notification.show("Clicked RIGHT mouse button on " + event.getTarget().getCaption());
            }
        });

        gantt.addMoveListener(event -> {
            Notification.show("Moved " + event.getTarget().getCaption());
        });

        gantt.addResizeListener(event -> {
            Notification.show("Resized " + event.getTarget().getCaption());
        });

        gantt.addPredecessorChangeListener(event -> {
            Notification.show("Predecessor changed for " + event.getStep().getCaption() + " to  "
                    + Optional.ofNullable(event.getPredecessorStep()).map(Step::getCaption).orElse("null"));
        });
    }

    private Div createControls(final Gantt gantt) {
        Div controls = new Div();

        ComboBox<Resolution> resolutionField = new ComboBox<>("Resolution");
        resolutionField.setPreventInvalidInput(true);
        resolutionField.setItems(Resolution.values());
        resolutionField.setValue(gantt.getResolution());
        resolutionField.addValueChangeListener(e -> gantt.setResolution(e.getValue()));

        DatePicker startDateField = new DatePicker(
                gantt.getStartDateTime().toLocalDate(),
                e -> Optional.ofNullable(e.getValue()).ifPresent(date -> gantt.setStartDateTime(date.atStartOfDay())));
        startDateField.setLabel("Start date");

        DatePicker endDateField = new DatePicker(
                gantt.getEndDateTime().toLocalDate(),
                e -> Optional.ofNullable(e.getValue()).ifPresent(date -> gantt.setEndDateTime(date.atStartOfDay())));
        endDateField.setLabel("End date");

        ComboBox<String> timeZoneField = new ComboBox<>("Timezone", getSupportedTimeZoneIds());
        timeZoneField.setWidth("350px");
        timeZoneField.setValue("Default");
        timeZoneField.setItemLabelGenerator(item -> {
            if ("Default".equals(item)) {
                return "Default (" + getDefaultTimeZone().getDisplayName(TextStyle.FULL, UI.getCurrent().getLocale())
                        + ")";
            }
            TimeZone tz = TimeZone.getTimeZone(item);
            return tz.getID() + " (raw offset " + (tz.getRawOffset() / 60000) + "m)";
        });
        timeZoneField.addValueChangeListener(e -> Optional.ofNullable(e.getValue()).ifPresent(zoneId -> {
            if ("Default".equals(zoneId)) {
                gantt.setZoneId(getDefaultTimeZone());
            } else {
                gantt.setZoneId(ZoneId.of(zoneId));
            }
        }));

        ComboBox<Locale> localeField = new ComboBox<>("Locale",
                Stream.of(Locale.getAvailableLocales()).collect(Collectors.toList()));
        localeField.setWidth("350px");
        localeField.setItemLabelGenerator((l) -> l.getDisplayName(UI.getCurrent().getLocale()));
        localeField.setValue(gantt.getLocale());
        localeField.addValueChangeListener(e -> Optional.ofNullable(e.getValue()).ifPresent(l -> gantt.setLocale(l)));

        Select<String> heightField = new Select<>("Full (100%)", "Fixed (500px)", "Auto");
        heightField.setLabel("Height");
        heightField.setEmptySelectionAllowed(false);
        heightField.setValue("Auto");
        heightField.addValueChangeListener(event -> {
            if ("Full (100%)".equals(event.getValue())) {
                gantt.setHeightFull();
            } else if ("Auto".equals(event.getValue())) {
                gantt.setHeight(null);
            } else {
                gantt.setHeight("500px");
            }
        });

        Button addStepButton = new Button("Add step");
        addStepButton.addClickListener(event -> {
            Step newStep = new Step();
            newStep.setCaption("New Step");
            setDefaults(newStep);
            gantt.addStep(newStep);
        });

        controls.add(resolutionField, startDateField, endDateField, localeField, timeZoneField, heightField,
                addStepButton);
        return controls;
    }

    private Button addMenu(final Gantt gantt) {
        Button menuBtn = new Button("Settings");
        menuBtn.setIcon(VaadinIcon.CARET_DOWN.create());
        menuBtn.setIconAfterText(true);

        ContextMenu menu = new ContextMenu(menuBtn);
        menu.setOpenOnClick(true);

        MenuItem movableStepsBetweenRows = menu.addItem("Movable Steps between rows");
        movableStepsBetweenRows.setCheckable(true);
        movableStepsBetweenRows.setChecked(gantt.getSettings().isMovableStepsBetweenRows());
        movableStepsBetweenRows.addClickListener(
                event -> gantt.getSettings().setMovableStepsBetweenRows(event.getSource().isChecked()));

        MenuItem createStepItem = menu.addItem("Create new step...");
        createStepItem.addClickListener(event -> openEditorForStep(null));

        MenuItem createSubStepItem = menu.addItem("Create new sub step...");
        createSubStepItem.addClickListener(event -> openEditorForSubStep(null));

        return menuBtn;
    }

    private List<String> getSupportedTimeZoneIds() {
        List<String> items = new ArrayList<>();
        items.add("Default");
        items.addAll(Gantt.getSupportedTimeZoneIDs());
        return items;
    }

    private ZoneId getDefaultTimeZone() {
        if (defaultZoneId != null) {
            return defaultZoneId;
        }
        ZoneId zone = ZoneId.systemDefault();
        if (Gantt.getSupportedTimeZoneIDs().contains(zone.getId())) {
            defaultZoneId = zone;
        } else {
            defaultZoneId = ZoneId.of("Europe/Helsinki");
        }
        return defaultZoneId;
    }

    private void openEditorForStep(Step step) {
        if (step == null) {
            Step newStep = new Step();
            newStep.setCaption("New Step");
            setDefaults(newStep);
            stepEditor.open(newStep, Operation.ADD, gantt.getSteps());
        } else {
            stepEditor.open(step, Operation.EDIT, gantt.getSteps());
        }
    }

    private void openEditorForSubStep(SubStep substep) {
        if (substep == null) {
            SubStep newSubStep = new SubStep();
            newSubStep.setCaption("New sub step");
            setDefaults(newSubStep);
            subStepEditor.open(newSubStep, Operation.ADD, gantt.getSteps());
        } else {
            subStepEditor.open(substep, Operation.EDIT, gantt.getSteps());
        }
    }

    private void setDefaults(GanttStep step) {
        step.setStartZonedDateTime(ZonedDateTime.now(gantt.getZoneId()).truncatedTo(ChronoUnit.DAYS));
        step.setEndZonedDateTime(step.getStartZonedDateTime(gantt.getZoneId()).plusDays(15));
    }

    public void saveUpdate(Pair<Step, String> stepAndOwner,
            Operation operation) {
        if (operation == operation.ADD) {
            gantt.addStep(stepAndOwner.getKey());
            Notification.show("Added step " + stepAndOwner.getKey().getCaption());
        } else {
            Notification.show("Updated step " + stepAndOwner.getKey().getCaption());
        }
    }

    public void deleteUpdate(Step step) {
        gantt.removeStep(step);
        Notification.show("Step " + step.getCaption() + " removed");
    }

    public void saveUpdateSubStep(Pair<SubStep, String> substepAndOwner,
            Operation operation) {
        SubStep substep = substepAndOwner.getKey();
        String oldOwnerUid = substepAndOwner.getValue();
        if (operation == operation.ADD) {
            gantt.addSubSteps(substep);
            Notification.show("Added substep " + substep.getCaption());
        } else {
            Notification.show("Updated substep " + substep.getCaption());
            if (!oldOwnerUid.equals(substep.getOwner().getUid())) {
                // refresh previous owner
                gantt.moveSubStep(oldOwnerUid, substep, substep.getOwner());
            }
        }
        gantt.refreshStep(substep.getOwner().getUid());
    }

    public void deleteUpdate(SubStep substep) {
        gantt.removeSubStep(substep);
        Notification.show("SubStep " + substep.getCaption() + " removed");
    }

    public class StepEditor extends GanttStepEditor<Step> {

        private Select<Step> predecessorField = new Select<>();

        public StepEditor(
                BiConsumer<Pair<Step, String>, Operation> itemSaver,
                Consumer<Step> itemDeleter) {
            super("step", itemSaver, itemDeleter);

            createPredecessorField();
        }

        public void open(Step item, Operation operation, List<Step> steps) {
            List<Step> options = new ArrayList<>(steps);
            options.removeIf(opt -> opt.getUid().equals(item.getUid()));
            predecessorField.setItems(options.stream().map(this::unproxyPredecessor).collect(Collectors.toList()));
            super.open(item, operation);
        }

        private Step unproxyPredecessor(Step predecessor) {
            Step step = unproxyPredecessorRef(predecessor);
            step.setCaption(predecessor.getCaption());
            return step;
        }

        private Step unproxyPredecessorRef(Step predecessor) {
            if (predecessor == null) {
                return null;
            }
            Step step = new Step();
            step.setUid(predecessor.getUid());
            return step;
        }

        private void createPredecessorField() {
            predecessorField.setLabel("Predecessor");
            predecessorField.setWidthFull();
            predecessorField.setItemLabelGenerator(step -> Optional.ofNullable(step).map(Step::getCaption).orElse(""));
            predecessorField.setTextRenderer(Step::getCaption);
            predecessorField.setEmptySelectionAllowed(true);
            getFormLayout().add(predecessorField);

            getBinder().forField(predecessorField)
                    .bind(step -> unproxyPredecessorRef(step.getPredecessor()), Step::setPredecessor);
        }
    }

    public class SubStepEditor extends GanttStepEditor<SubStep> {

        private Select<Step> ownerField = new Select<>();

        public SubStepEditor(
                BiConsumer<Pair<SubStep, String>, Operation> itemSaver,
                Consumer<SubStep> itemDeleter) {
            super("substep", itemSaver, itemDeleter);

            createOwnerField();
        }

        @Override
        protected Step getOwnerStep() {
            return getCurrentItem().getOwner();
        }

        public void open(SubStep item, Operation operation, List<Step> steps) {
            List<Step> options = new ArrayList<>(steps);
            options.removeIf(opt -> opt.getUid().equals(item.getUid()));
            ownerField.setItems(options.stream().map(this::unproxyOwner).collect(Collectors.toList()));
            super.open(item, operation);
        }

        private Step unproxyOwner(Step owner) {
            Step step = unproxyOwnerRef(owner);
            step.setCaption(owner.getCaption());
            return step;
        }

        private Step unproxyOwnerRef(Step predecessor) {
            if (predecessor == null) {
                return null;
            }
            Step step = new Step();
            step.setUid(predecessor.getUid());
            return step;
        }

        private void createOwnerField() {
            ownerField.setLabel("Owner");
            ownerField.setWidthFull();
            ownerField.setItemLabelGenerator(step -> Optional.ofNullable(step).map(Step::getCaption).orElse(""));
            ownerField.setTextRenderer(Step::getCaption);
            ownerField.setEmptySelectionAllowed(false);
            ownerField.setRequiredIndicatorVisible(true);
            getFormLayout().add(ownerField);

            getBinder().forField(ownerField)
                    .asRequired()
                    .bind(substep -> unproxyOwnerRef(substep.getOwner()), SubStep::setOwner);
        }
    }

    public class GanttStepEditor<T extends GanttStep> extends Dialog {

        private final H3 titleField = new H3();
        private final Button saveButton = new Button("Save");
        private final Button cancelButton = new Button("Cancel");
        private final Button deleteButton = new Button("Delete");
        private Registration registrationForSave;

        private final FormLayout formLayout = new FormLayout();
        private final HorizontalLayout buttonBar = new HorizontalLayout(saveButton,
                cancelButton, deleteButton);

        private Binder<T> binder = new Binder<>();
        private T currentItem;

        private final String itemType;
        private final BiConsumer<Pair<T, String>, Operation> itemSaver;
        private final Consumer<T> itemDeleter;

        private TextField captionField = new TextField();
        private TextField descriptionField = new TextField();
        private DateTimeField startDateTimeField = new DateTimeField("Start date", () -> gantt);
        private DateTimeField endDateTimeField = new DateTimeField("End date", () -> gantt);

        private TextField colorField = new TextField();
        private Checkbox resizableField = new Checkbox();
        private Checkbox movableField = new Checkbox();

        protected GanttStepEditor(String itemType,
                BiConsumer<Pair<T, String>, Operation> itemSaver, Consumer<T> itemDeleter) {
            this.itemType = itemType;
            this.itemSaver = itemSaver;
            this.itemDeleter = itemDeleter;
            setWidth("500px");

            initTitle();
            initFormLayout();
            initButtonBar();
            setCloseOnEsc(true);
            setCloseOnOutsideClick(false);

            createCaptionField();
            createDateFields();
            createColorField();
            createDescriptionField();
            createResizableField();
            createMovableField();
        }

        private void createResizableField() {
            resizableField.setLabel("Resizable");
            getFormLayout().add(resizableField);
            getBinder().forField(resizableField)
                    .bind(T::isResizable, T::setResizable);
        }

        private void createMovableField() {
            movableField.setLabel("Movable");
            getFormLayout().add(movableField);
            getBinder().forField(movableField)
                    .bind(T::isMovable, T::setMovable);
        }

        private void createCaptionField() {
            captionField.setLabel("Caption");
            captionField.setWidthFull();
            getFormLayout().add(captionField);

            getBinder().forField(captionField)
                    .withConverter(String::trim, String::trim)
                    .bind(T::getCaption, T::setCaption);
        }

        private void createDescriptionField() {
            descriptionField.setLabel("Description");
            descriptionField.setWidthFull();
            getFormLayout().add(descriptionField);

            getBinder().forField(descriptionField)
                    .withConverter(String::trim, String::trim)
                    .bind(T::getDescription, T::setDescription);
        }

        private void createColorField() {
            colorField.setLabel("Background color");
            colorField.setWidthFull();
            getFormLayout().add(colorField);

            getBinder().forField(colorField)
                    .withConverter(String::trim, String::trim)
                    .bind(T::getBackgroundColor, T::setBackgroundColor);
        }

        private void createDateFields() {
            getFormLayout().add(startDateTimeField);
            getFormLayout().add(endDateTimeField);

            getBinder().forField(startDateTimeField)
                    .bind(T::getStartDate, T::setStartDate);
            getBinder().forField(endDateTimeField)
                    .bind(T::getEndDate, T::setEndDate);
        }

        private void initTitle() {
            add(titleField);
        }

        private void initFormLayout() {
            formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                    new FormLayout.ResponsiveStep("25em", 1));
            formLayout.setWidthFull();
            Div div = new Div(formLayout);
            div.setWidthFull();
            div.addClassName("has-padding");
            add(div);
        }

        private void initButtonBar() {
            saveButton.setAutofocus(true);
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            cancelButton.addClickListener(e -> close());
            deleteButton.addClickListener(e -> deleteClicked());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            buttonBar.setClassName("buttons");
            buttonBar.setSpacing(true);
            add(buttonBar);
        }

        protected final FormLayout getFormLayout() {
            return formLayout;
        }

        protected final Binder<T> getBinder() {
            return binder;
        }

        protected final T getCurrentItem() {
            return currentItem;
        }

        public final void open(T item, Operation operation) {
            currentItem = item;
            titleField.setText(operation.getNameInTitle() + " " + itemType);
            if (registrationForSave != null) {
                registrationForSave.remove();
            }
            registrationForSave = saveButton
                    .addClickListener(e -> saveClicked(operation));
            binder.readBean(currentItem);

            deleteButton.setEnabled(operation.isDeleteEnabled());

            open();
        }

        protected Step getOwnerStep() {
            return (Step) currentItem;
        }

        private void saveClicked(Operation operation) {
            Step owner = getOwnerStep();
            boolean isValid = binder.writeBeanIfValid(currentItem);

            if (isValid) {
                itemSaver.accept(Pair.of(currentItem, Optional.ofNullable(owner).map(Step::getUid).orElse(null)),
                        operation);
                close();
            } else {
                BinderValidationStatus<T> status = binder.validate();
            }
        }

        private void deleteClicked() {
            doDelete(currentItem);
        }

        /**
         * Removes the {@code item} from the backend and close the dialog.
         *
         * @param item
         *            the item to delete
         */
        protected void doDelete(T item) {
            itemDeleter.accept(item);
            close();
        }
    }
}
