package org.tltv.gantt.client;

import org.tltv.gantt.client.shared.GanttUtil;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;

public class PolymerWidget extends HTMLPanel {

    public PolymerWidget(JavaScriptObject jselement) {
        super(""); // does nothing
        setElement(Element.as(jselement));
    }

    public PolymerWidget(String tag) {
        super(tag, "");
    }

    /** Initialize polymer element. */
    public PolymerWidget(String tag, String src) {
        super(tag, "");
        Polymer.ensureCustomElement(getElement(), "../gantt-widget.html");
    }

    public PolymerWidget(String tag, SafeHtml safeHtml) {
        super(tag, safeHtml.asString());
    }

    public void ready(Function<?, ?> f) {
        GanttUtil.whenReady(f, getElement());
    }

    /**
     * @param attributes
     *            a list of attributes or attribute:values pairs. examples:
     *            setAttributes("foo bar") setAttributes("foo:bar ; hello:bye")
     */
    public void setAttributes(String attributes) {
        for (String attr : attributes.trim().replace(" *([;:]) *", "$1").split("[; ]+")) {
            MatchResult e = RegExp.compile(" *([\\w-]+)( *: *)?(.*)? *").exec(attr);

            if (e.getGroup(3) != null) {
                getElement().setAttribute(e.getGroup(1), e.getGroup(3));
            } else {
                setBooleanAttribute(e.getGroup(1), true);
            }
        }
    }

    public void setBooleanAttribute(String name, boolean value) {
        if (value) {
            getElement().setAttribute(name, "");
        } else {
            getElement().removeAttribute(name);
        }
    }
}
