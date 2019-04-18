package org.tltv.gantt.client;

import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.StepState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.elemental.Function;

public class AbstractStepWidget extends PolymerWidget {

    public static final String STYLE_BAR = "bar";
    public static final String STYLE_BAR_LABEL = "bar-label";
    public static final String STYLE_INVALID = "invalid";

    protected boolean readOnly;

    protected String extraStyle;

    protected ProgressElement progressElement;
    protected AbstractStep step = null;

    protected GanttWidget gantt;
    protected LocaleDataProvider localeDataProvider;

    private int calculatedHeight = 0;

    @Override
    protected void onDetach() {
        super.onDetach();
    }

    public AbstractStepWidget(JavaScriptObject jselement) {
        super(jselement);
        doInit();
    }

    public AbstractStepWidget() {
        super("gantt-step");
        doInit();
    }

    protected void doInit() {
        addStyleName("bar");

        ready(new Function<Object, Object>() {
            @Override
            public Object call(Object args) {
                registerPositionCalculator(getElement(), new Function<Object, Object[]>() {
                    @Override
                    public Object call(Object[] arg) {
                        if (gantt == null || arg == null) {
                            return "0px";
                        }
                        Long start = Long.valueOf(String.valueOf(arg[0]));
                        Long end = Long.valueOf(String.valueOf(arg[1]));
                        return getLeftPositionPercentageStringForDate(start, end);
                    }
                });
                registerWidthCalculator(getElement(), new Function<Object, Object[]>() {
                    @Override
                    public Object call(Object[] arg) {
                        if (gantt == null || arg == null) {
                            return "0px";
                        }
                        Long start = Long.valueOf(String.valueOf(arg[0]));
                        Long end = Long.valueOf(String.valueOf(arg[1]));
                        return getWidthPercentageStringForDateInterval(start, end);
                    }
                });
                GWT.log("AbstractStepWidget() READY");
                return null;
            }
        });
    }

    public String getLeftPositionPercentageStringForDate(Long start, Long end) {
        return gantt.getLeftPositionPercentageStringForDate(start);
    }

    public String getWidthPercentageStringForDateInterval(Long start, Long end) {
        return gantt.getWidthPercentageStringForDateInterval(start, end);
    }

    public static native void registerPositionCalculator(Element elem, Function f)
    /*-{
        return elem.registerPositionCalculator(f);
    }-*/;

    public static native void registerWidthCalculator(Element elem, Function f)
    /*-{
        return elem.registerWidthCalculator(f);
    }-*/;

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
        updateStyle();
        updateProgress();
    }

    /**
     * Get state object. read-only. Changes to the Step object on client side
     * are not registered to the server side.
     */
    public AbstractStep getStep() {
        return step;
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
        if (gantt == null || step == null || getElement().getParentNode() == null) {
            return;
        }

        getBar().getStyle().clearLeft();
        getBar().getStyle().clearWidth();
        getBar().getStyle().clearVisibility();

        // sanity check
        if (step.getStartDate() < 0 || step.getEndDate() < 0 || step.getEndDate() <= step.getStartDate()) {
            getBar().addClassName(STYLE_INVALID);
        } else {
            getBar().removeClassName(STYLE_INVALID);
            updateStepCustomStyle(getBar(),
                    getLeftPositionPercentageStringForDate(getStep().getStartDate(), getStep().getEndDate()),
                    getWidthPercentageStringForDateInterval(getStep().getStartDate(), getStep().getEndDate()));
        }
    }

    public static native void updateStepCustomStyle(Element e, String left, String width)
    /*-{
        e.updateStyles({
          '--gantt-step-left': left,
          '--gantt-step-width': width,
        });
    }-*/;

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
