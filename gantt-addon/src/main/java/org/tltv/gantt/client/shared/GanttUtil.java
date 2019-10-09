package org.tltv.gantt.client.shared;

import java.util.Date;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Node;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.json.client.JSONArray;
import com.vaadin.polymer.elemental.Function;

import elemental.html.ClientRect;

public class GanttUtil {

    /**
     * Calculate week number for the given date.
     *
     * @param d
     *            Target date
     * @param timezoneOffset
     *            Time zone offset in milliseconds
     * @param firstDayOfWeek
     *            First day of week. Integer between 1-7. 1 is Sunday.
     * @return
     */
    public static int getWeekNumber(Date d, long timezoneOffset, int firstDayOfWeek) {
        /*
         * Thanks to stackoverflow.com for a easy function to calculate week
         * number. See
         * http://stackoverflow.com/questions/6117814/get-week-of-year
         * -in-javascript-like-in-php
         */
        d = new Date(d.getYear(), d.getMonth(), d.getDate());
        int daysToTursday;
        if (firstDayOfWeek == 1) {
            daysToTursday = 4 - d.getDay();
        } else {
            daysToTursday = 4 - ((d.getDay() == 0) ? 7 : d.getDay());
        }
        d.setDate(d.getDate() + daysToTursday);
        Date yearStart = new Date(d.getYear(), 0, 1);
        double weekNo = Math.ceil((((d.getTime() - yearStart.getTime()) / 86400000.0) + 1.0) / 7.0);
        return (int) weekNo;
    }

    /**
     * Parse computed styles to get precise margin height for the given element.
     * Returns zero if computed styles is not defined.
     *
     * @param elem
     *            Target element to measure
     */
    public static native double getMarginByComputedStyle(com.google.gwt.dom.client.Element elem)
    /*-{
        var cs = elem.ownerDocument.defaultView.getComputedStyle(elem);
        if (cs) {
            size = parseInt(cs.getPropertyValue('margin-top'))
                        + parseInt(cs.getPropertyValue('margin-bottom'));
        } else {
            size = 0;
        }
        return size;
     }-*/;

    public static double getBoundingClientRectRight(elemental.dom.Element element) {
        if (element == null) {
            return 0.0;
        }
        if (element.getBoundingClientRect() != null) {
            return element.getBoundingClientRect().getRight();
        } else {
            return element.getOffsetLeft() + element.getOffsetWidth();
        }
    }

    public static native double getBoundingClientRectRight(com.google.gwt.dom.client.Element element)
    /*-{
        if(!element) {
          return 0.0;
        }
        if (element.getBoundingClientRect) {
          var rect = element.getBoundingClientRect();
          return rect.right;
        } else {
          return element.offsetLeft + element.offsetWidth;
        }
    }-*/;

    public static double getBoundingClientRectLeft(elemental.dom.Element element) {
        if (element == null) {
            return 0.0;
        }
        if (element.getBoundingClientRect() != null) {
            return element.getBoundingClientRect().getLeft();
        } else {
            return element.getOffsetLeft();
        }
    }

    public static native double getBoundingClientRectLeft(com.google.gwt.dom.client.Element element)
    /*-{
        if(!element) {
          return 0.0;
        }
        if (element.getBoundingClientRect) {
          var rect = element.getBoundingClientRect();
          return rect.left;
        } else {
          return element.offsetLeft;
        }
    }-*/;

    public static double getBoundingClientRectWidth(elemental.dom.Element element) {
        if (element == null) {
            return 0.0;
        }
        if (element.getBoundingClientRect() != null) {
            ClientRect rect = element.getBoundingClientRect();
            return rect.getRight() - rect.getLeft();
        } else {
            return element.getOffsetWidth();
        }
    }

    public static native double getBoundingClientRectWidth(com.google.gwt.dom.client.Element element)
    /*-{
        if(!element) {
          return 0.0;
        }
        if (element.getBoundingClientRect) {
          var rect = element.getBoundingClientRect();
          return rect.right - rect.left;
        } else {
          return element.offsetWidth;
        }
    }-*/;

    public static double getBoundingClientRectHeight(elemental.dom.Element element) {
        if (element == null) {
            return 0.0;
        }
        if (element.getBoundingClientRect() != null) {
            ClientRect rect = element.getBoundingClientRect();
            return rect.getBottom() - rect.getTop();
        } else {
            return element.getOffsetHeight();
        }
    }

    public static native double getBoundingClientRectHeight(com.google.gwt.dom.client.Element element)
    /*-{
        if(!element) {
          return 0.0;
        }
        if (element.getBoundingClientRect) {
          var rect = element.getBoundingClientRect();
          return rect.bottom - rect.top;
        } else {
          return element.offsetHeight;
        }
    }-*/;

    public static double getBoundingClientRectTop(elemental.dom.Element element) {
        if (element == null) {
            return 0.0;
        }
        if (element.getBoundingClientRect() != null) {
            ClientRect rect = element.getBoundingClientRect();
            return rect.getTop();
        } else {
            return element.getOffsetTop();
        }
    }

    public static native double getBoundingClientRectTop(com.google.gwt.dom.client.Element element)
    /*-{
        if(!element) {
          return 0.0;
        }
        if (element.getBoundingClientRect) {
          var rect = element.getBoundingClientRect();
          return rect.top;
        } else {
          return element.offsetTop;
        }
    }-*/;

    public static double getBoundingClientRectBottom(elemental.dom.Element element) {
        if (element == null) {
            return 0.0;
        }
        if (element.getBoundingClientRect() != null) {
            ClientRect rect = element.getBoundingClientRect();
            return rect.getBottom();
        } else {
            return element.getOffsetTop() + element.getOffsetHeight();
        }
    }

    public static native double getBoundingClientRectBottom(com.google.gwt.dom.client.Element element)
    /*-{
        if(!element) {
          return 0.0;
        }
        if (element.getBoundingClientRect) {
          var rect = element.getBoundingClientRect();
          return rect.bottom;
        } else {
          return element.offsetTop + element.offsetHeight;
        }
    }-*/;

    public static boolean isTouchEvent(NativeEvent event) {
        return event.getType().contains("touch");
    }

    public static int getTouchOrMouseClientX(NativeEvent event) {
        if (isTouchEvent(event)) {
            return event.getChangedTouches().get(0).getClientX();
        } else {
            return event.getClientX();
        }
    }

    public static int getTouchOrMouseClientY(NativeEvent event) {
        if (isTouchEvent(event)) {
            return event.getChangedTouches().get(0).getClientY();
        } else {
            return event.getClientY();
        }
    }

    public static int getTouchOrMousePageX(NativeEvent event) {
        if (isTouchEvent(event)) {
            return event.getChangedTouches().get(0).getPageX();
        } else {
            return getElementPageX(event);
        }
    }

    public static int getTouchOrMousePageY(NativeEvent event) {
        if (isTouchEvent(event)) {
            return event.getChangedTouches().get(0).getPageY();
        } else {
            return getElementPageY(event);
        }
    }

    public static int getTouchOrMouseX(NativeEvent event, Element scrollContainer) {
        if (scrollContainer == null) {
            return getTouchOrMousePageX(event);
        } else {
            return getTouchOrMousePageX(event) + scrollContainer.getScrollLeft();
        }
    }

    public static int getTouchOrMouseY(NativeEvent event, Element scrollContainer) {
        if (scrollContainer == null) {
            return getTouchOrMousePageY(event);
        } else {
            return getTouchOrMousePageY(event) + scrollContainer.getScrollTop();
        }
    }

    public static native int getElementPageX(NativeEvent event)
    /*-{
       return event.pageX;
    }-*/;

    public static native int getElementPageY(NativeEvent event)
    /*-{
       return event.pageY;
    }-*/;

    public static native Element getElementFromPoint(Node shadowRoot, int clientX, int clientY)
    /*-{
        var el;
        if(shadowRoot) {
            el = shadowRoot.elementFromPoint(clientX, clientY);
        } else {
            el = $wnd.document.elementFromPoint(clientX, clientY);
        }
        if(el != null && el.nodeType == 3) {
            el = el.parentNode;
        }
        return el;
    }-*/;

    /**
     * Returns daylight saving adjustment (in minutes) for normalized date.
     * Normalized date is zoned date without DST.
     *
     * @param normalDate
     *            Date representing target time zone without DST.
     * @param targetTimeZone
     * @return Daylight saving adjustment in minutes
     */
    public static native int getDaylightAdjustmentForNormalDate(Long normalDate, TimeZone targetTimeZone)
    /*-{
        if (targetTimeZone.@com.google.gwt.i18n.client.TimeZone::transitionPoints == null) {
            return 0;
        }
        var timeInHours = normalDate / 1000 / 3600;
        var index = 0;
        while (index < targetTimeZone.@com.google.gwt.i18n.client.TimeZone::transitionPoints.length
                && timeInHours >= (targetTimeZone.@com.google.gwt.i18n.client.TimeZone::transitionPoints[index]
                - ( ((index == 0) ? 0 : targetTimeZone.@com.google.gwt.i18n.client.TimeZone::adjustments[index - 1]) / 60) )) {
            ++index;
        }
        return (index == 0) ? 0 : targetTimeZone.@com.google.gwt.i18n.client.TimeZone::adjustments[index - 1];
    }-*/;

    /**
     * Executes a function after all imports have been loaded and when the
     * passed element is ready to use. For browsers not supporting html imports,
     * it loads the webcomponentsjs polyfill.
     */
    public static native void whenReady(Function f, Element e)
    /*-{

        function nextTimeout(delayms) {
           setTimeout(function() {
                if (@com.vaadin.polymer.Polymer::isRegisteredElement(*)(e) && e.readyAndConnected) {
                  if (f) f(e);
                } else {
                    nextTimeout(10);
                }
              }, delayms);
        }

        function registered() {
          if (e) {
              nextTimeout(0);
          } else {
              if (f) f();
          }
        }
        function done() {
            $wnd.HTMLImports.whenReady(registered);
        }
        function loadPolyfill() {
            var s = $doc.createElement('script');
            s.src = @com.vaadin.polymer.Polymer::absoluteHref(*)
                        ('webcomponentsjs/webcomponents-lite.min.js');
            s.onreadystatechange = s.onload = done;
            $doc.head.appendChild(s);
        }
        if (!$wnd.HTMLImports) {
            if (@com.vaadin.polymer.Polymer::hasHtmlImports) {
                registered();
            } else {
                loadPolyfill();
            }
        } else {
           done();
        }
    }-*/;

    public static native void whenReadyAndConnected(Function f, Element e)
    /*-{

        function nextTimeout(delayms) {
           setTimeout(function() {
                if (e.readyAndConnected) {
                  if (f) f(e);
                } else {
                    nextTimeout(10);
                }
              }, delayms);
        }

        function registered() {
          if (e) {
              nextTimeout(0);
          } else {
              if (f) f();
          }
        }
    
        if (e.readyAndConnected) {
            if (f) f(e);
        } else {
            registered();
        }
    }-*/;

    public static native void deferred(Function f, Function<Boolean, ?> test)
    /*-{

        function nextTimeout(delayms) {
           setTimeout(function() {
                if (test()) {
                  if (f) f();
                } else {
                    nextTimeout(10);
                }
              }, delayms);
        }
        nextTimeout(0);
    }-*/;

    public static native Element getEventTarget(NativeEvent event)
    /*-{
        if(event.path) {
            return event.path[0];
        }
        return event.target;
    }-*/;

    public static native Element getHost(Node node)
    /*-{
        if(node.host) {
            return node.host;
        }
        return null;
    }-*/;

    public static String[] toStringArray(JSONArray array) {
        String[] value = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            value[i] = array.get(i).isString().stringValue();
        }
        return value;
    }
}
