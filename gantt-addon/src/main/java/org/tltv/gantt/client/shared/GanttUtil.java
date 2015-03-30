package org.tltv.gantt.client.shared;

import java.util.Date;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

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
    public static int getWeekNumber(Date d, long timezoneOffset,
            int firstDayOfWeek) {
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
        double weekNo = Math
                .ceil((((d.getTime() - yearStart.getTime()) / 86400000.0) + 1.0) / 7.0);
        return (int) weekNo;
    }

    /**
     * Parse computed styles to get precise margin height for the given element.
     * Returns zero if computed styles is not defined.
     * 
     * @param elem
     *            Target element to measure
     */
    public static native double getMarginByComputedStyle(
            com.google.gwt.dom.client.Element elem)
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

    public static native double getBoundingClientRectRight(
            com.google.gwt.dom.client.Element element)
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

    public static native double getBoundingClientRectLeft(
            com.google.gwt.dom.client.Element element)
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

    public static native double getBoundingClientRectWidth(
            com.google.gwt.dom.client.Element element)
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

    public static native double getBoundingClientRectHeight(
            com.google.gwt.dom.client.Element element)
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

    public static native double getBoundingClientRectTop(
            com.google.gwt.dom.client.Element element)
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

    public static native double getBoundingClientRectBottom(
            com.google.gwt.dom.client.Element element)
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

    public static native Element getElementFromPoint(int clientX, int clientY)
    /*-{
        var el = $wnd.document.elementFromPoint(clientX, clientY);
        // Call elementFromPoint two times to make sure IE8 also returns something sensible if the application is running in an iframe
        el = $wnd.document.elementFromPoint(clientX, clientY);
        if(el != null && el.nodeType == 3) {
            el = el.parentNode;
        }
        return el;
    }-*/;
}
