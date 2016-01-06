package org.tltv.gantt.client;

import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractStepWidget extends ComplexPanel {

    public static final String STYLE_BAR = "bar";
    public static final String STYLE_BAR_LABEL = "bar-label";
    public static final String STYLE_INVALID = "invalid";

    protected boolean readOnly;
    protected DivElement caption;

    protected String extraStyle;
    protected long start = -1;
    protected long end = -1;

    protected ProgressElement progressElement;
    protected AbstractStep step;

    protected GanttWidget gantt;
    protected LocaleDataProvider localeDataProvider;

    @Override
    protected void onDetach() {
        super.onDetach();
    }

    public AbstractStepWidget() {
        DivElement bar = DivElement.as(DOM.createDiv());
        bar.setClassName(STYLE_BAR);
        setElement(bar);

        caption = DivElement.as(DOM.createDiv());
        caption.setClassName(STYLE_BAR_LABEL);
        bar.appendChild(caption);

        // hide by default
        bar.getStyle().setVisibility(Visibility.HIDDEN);
    }

    public void setGantt(GanttWidget gantt,
            LocaleDataProvider localeDataProvider) {
        this.gantt = gantt;
        setLocaleDataProvider(localeDataProvider);
    }

    public void setLocaleDataProvider(LocaleDataProvider localeDataProvider) {
        this.localeDataProvider = localeDataProvider;
    }

    public LocaleDataProvider getLocaleDataProvider() {
        return localeDataProvider;
    }

    /**
     * Set data source for this widget. Called when {@linkplain StepState} is
     * changed.
     * 
     * @param step
     */
    public void setStep(AbstractStep step) {
        this.step = step;
        updateBackground();
        updateStyle();
        updateCaption();
        updateProgress();
    }

    /**
     * Get state object. read-only. Changes to the Step object on client side
     * are not registered to the server side.
     */
    public AbstractStep getStep() {
        return step;
    }

    protected void updateCaption() {
        if (step.getCaptionMode() == Step.CaptionMode.HTML) {
            caption.setInnerHTML(step.getCaption());
        } else {
            caption.setInnerText(step.getCaption());
        }
    }

    protected void updateBackground() {
        getElement().getStyle().setBackgroundColor(step.getBackgroundColor());
    }

    protected void updateStyle() {
        if (!isEmpty(step.getStyleName())) {
            if (!step.getStyleName().equals(extraStyle)) {
                // style name changed. Clear old and add new style.
                if (!isEmpty(extraStyle)) {
                    getElement().removeClassName(extraStyle);
                }
                getElement().addClassName(step.getStyleName());
            }
        } else if (!isEmpty(extraStyle)) {
            getElement().removeClassName(extraStyle);
        }
    }

    protected boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    protected void updatePositionAndWidth() {
        gantt.updateBarPercentagePosition(step.getStartDate(),
                step.getEndDate(), getElement());
    }

    protected void updateProgress() {
        if (step.isShowProgress()) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    private void showProgress() {
        if (progressElement == null) {
            progressElement = new ProgressBarElement();
            progressElement.init(step.getProgress());
        } else {
            progressElement.setProgress(step.getProgress());
        }
        if (!progressElement.getElement().hasParentElement()) {
            getElement().insertAfter(progressElement.getElement(), caption);
        }
    }

    private void hideProgress() {
        if (progressElement != null && progressElement.getElement() != null) {
            progressElement.getElement().removeFromParent();
        }
    }

    /**
     * Updates width of this widget to match the Gantt chart's timeline.
     */
    public void updateWidth() {
        if (gantt == null || !getElement().hasParentElement()) {
            return;
        }

        getElement().getStyle().clearVisibility();

        if (start != step.getStartDate() || end != step.getEndDate()) {

            // sanity check
            if (step.getStartDate() < 0 || step.getEndDate() < 0
                    || step.getEndDate() <= step.getStartDate()) {
                getElement().addClassName(STYLE_INVALID);
            } else {
                updatePositionAndWidth();
            }
        }
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void add(Widget w) {
        super.add(w, (Element) getElement());
    }

    protected int countNonSubStepChilds() {
        return ((caption != null && caption.hasParentElement()) ? 1 : 0)
                + ((progressElement != null && progressElement.getElement()
                        .hasParentElement()) ? 1 : 0);
    }
}
