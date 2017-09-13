package org.tltv.gantt.client;

import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.GanttUtil;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.PolymerWidget;
import com.vaadin.polymer.elemental.Function;

public class AbstractStepWidget extends PolymerWidget {

    public static final String STYLE_BAR = "bar";
    public static final String STYLE_BAR_LABEL = "bar-label";
    public static final String STYLE_INVALID = "invalid";

    protected boolean readOnly;

    protected String extraStyle;
    protected long start = -1;
    protected long end = -1;

    protected ProgressElement progressElement;
    protected AbstractStep step = null;

    protected GanttWidget gantt;
    protected LocaleDataProvider localeDataProvider;

    public boolean waitingForPolymer = true; // TODO can this be removed now?

    private int calculatedHeight = 0;

    @Override
    protected void onDetach() {
        super.onDetach();
    }

    public AbstractStepWidget() {
        super("gantt-step", new SafeHtmlBuilder().toSafeHtml());
        // super("gantt-step", "gantt/gantt-step.html", "");
        addStyleName("bar");

        ready(new Function<Object, Object>() {
            @Override
            public Object call(Object args) {
                GWT.log("AbstractStepWidget() READY");
                waitingForPolymer = false;
                return null;
            }
        });
        // DivElement bar = DivElement.as(DOM.createDiv());
        // bar.setClassName(STYLE_BAR);
        // setElement(bar);
        //
        // caption = DivElement.as(DOM.createDiv());
        // caption.setClassName(STYLE_BAR_LABEL);
        // bar.appendChild(caption);

        // hide by default
        // bar.getStyle().setVisibility(Visibility.HIDDEN);
    }

    @Override
    public void ready(Function<?, ?> f) {
        GanttUtil.whenReady(f, getElement());
    }

    public Element getShadowRoot() {
        return getRootElement(getElement());
    }

    public Element getBar() {
        return getElement();
    }

    public Element getBarCaption() {
        return getInternalBarCaptionElement(getElement());
    }

    public static native Element getInternalBarCaptionElement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.$.barLabel;
    }-*/;

    public static native Element getRootElement(com.google.gwt.dom.client.Element elem)
    /*-{
        return elem.root;
    }-*/;

    public void setGantt(GanttWidget gantt, LocaleDataProvider localeDataProvider) {
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
            getBarCaption().setInnerHTML(step.getCaption());
        } else {
            getBarCaption().setInnerText(step.getCaption());
        }
    }

    protected void updateBackground() {
        getBar().getStyle().setBackgroundColor(step.getBackgroundColor());
    }

    protected void updateStyle() {
        if (!isEmpty(step.getStyleName())) {
            if (!step.getStyleName().equals(extraStyle)) {
                // style name changed. Clear old and add new style.
                if (!isEmpty(extraStyle)) {
                    getBar().removeClassName(extraStyle);
                }
                getBar().addClassName(step.getStyleName());
            }
            extraStyle = step.getStyleName();
        } else if (!isEmpty(extraStyle)) {
            getBar().removeClassName(extraStyle);
            extraStyle = null;
        }
    }

    protected boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    protected void updatePositionAndWidth() {
        gantt.updateBarPercentagePosition(step.getStartDate(), step.getEndDate(), getBar());
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
            getShadowRoot().insertAfter(progressElement.getElement(), getBarCaption());
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
        ready(new Function<Object, Object>() {
            @Override
            public Object call(Object args) {
                if (gantt == null || getElement().getParentNode() == null) {
                    return null;
                }

                getBar().getStyle().clearVisibility();

                if (start != step.getStartDate() || end != step.getEndDate()) {

                    // sanity check
                    if (step.getStartDate() < 0 || step.getEndDate() < 0 || step.getEndDate() <= step.getStartDate()) {
                        getBar().addClassName(STYLE_INVALID);
                    } else {
                        updatePositionAndWidth();
                    }
                }
                return null;
            }
        });
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void add(Widget w) {
        super.add(w, getShadowRoot());
    }

    protected int countNonSubStepChilds() {
        return 1 /* <style> is first */ + ((getBarCaption() != null && getBarCaption().hasParentElement()) ? 1 : 0)
                + ((progressElement != null && progressElement.getElement().hasParentElement()) ? 1 : 0);
    }

    public void registerCalculatedHeight(int height) {
        calculatedHeight = height;
    }

    public void clearCalculatedHeight() {
        calculatedHeight = 0;
    }

    public int getPreviousHeight() {
        return calculatedHeight;
    }

    public boolean isSet() {
        return getPreviousHeight() != 0;
    }
}
