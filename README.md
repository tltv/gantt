[![Published on Vaadin  Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/gantt)
[![Stars on Vaadin Directory](https://img.shields.io/vaadin-directory/star/gantt.svg)](https://vaadin.com/directory/component/gantt)

# Gantt Chart Add-on for Vaadin 8

Gantt is an UI component add-on for Vaadin 8.

You can read more about Gantt charts in general from here: http://en.wikipedia.org/wiki/Gantt_chart. 

Download latest release jar from http://vaadin.com/directory#addon/gantt

Please use version 0.9.5 with Vaadin Framework 7.

List of features that are supported:
* Server side API allows to modify timeline options and step options like start/end date, caption, color and adding/removing steps
* adjustable timeline range
* timeline supports Hour, Day and Week resolutions 
* modify step's start date and end date by dragging and moving the step (move and resize events)
* reorder steps by drag and dropping 
* scrolling enabled horizontally and vertically for a large chart content
* scales the small timeline and content width up to fit in the space available
* Vaadin Table or Grid can be used as a row header, vertical scrolling is mirrored to the table/grid and vice versa
* background grid
* dynamically modifiable predecessor relations between steps
* sub-steps
* tooltips for steps
* touch support
* supports Valo theme
* current time indicator

Technical details:
* server side class name is Gantt, it extends AbstractComponent
* client side connector class name is GanttConnector
* client side widgets (pure GWT) are 
	* GanttWidget: main widget that GanttConnector uses, extends ComplexPanel
	* TimelineWidget: standalone widget representing timeline.
	* AbstractStepWidget: extends ComplexPanel
		* StepWidget
		* SubStepWidget
	* SvgArrowWidget

Tested to work with following browsers: 
* IE 11
* Edge
* Firefox
* Chrome
* Safari


## Demo

Online demo available @ http://tomivirtanen.app.fi/gantt-demo/

Code can be found under /gantt-demo folder.

## Building and running demo

* git clone <url of the Gantt repository>
* mvn clean install
* cd gantt-demo
* mvn jetty:run

To see the demo, navigate to http://localhost:8080/gantt-demo


## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Gantt is written by Tomi Virtanen

