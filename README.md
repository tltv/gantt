# Gantt Chart Add-on for Vaadin 7

Gantt is an UI component add-on for Vaadin 7.

This simple Gantt Chart (http://en.wikipedia.org/wiki/Gantt_chart) has a minimal feature set implemented. 

List of features that are supported:
* Server side API gives you tools to change timeline options and step options like start/end date, caption, color and adding/removing steps
* timeline supports Day and Week resolutions 
* allows you to modify step's start date and end date by drag and dropping the step (move and resize events)
* scrolling enabled horizontally and vertically for a large chart content
* scales the small timeline and content width up to fit in the space available
* touch support (experimental)

Techincal details:
* server side class name is Gantt, it extends AbstractComponent
* client side connector class name is GanttConnector
* client side widgets (pure GWT) are 
	* GantWidget, which is the main widget that connector uses 
	* TimelineWidget, which is a standalone widget.

## Building and running demo

* git clone <url of the Gantt repository>
* mvn clean install
* cd gantt-demo
* mvn jetty:run

To see the demo, navigate to http://localhost:8080/gantt-demo


## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Gantt is written by Tomi Virtanen

