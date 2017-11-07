# Gantt Chart Web Component

Gantt is an UI component add-on created for Vaadin 8 Framework. This projects creates gantt-widget web component based on Polymer library that makes it possible to use Gantt in any web project either by using javacript API or just html elements.

You can read more about Gantt charts in general from here: http://en.wikipedia.org/wiki/Gantt_chart. 

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

Tested to work with following browsers: 
* IE 11
* Edge
* Firefox
* Chrome
* Safari


## Demo

Online demo (Vaadin 8) available @ http://tomivirtanen.app.fi/gantt-demo/

Code can be found under /gantt-demo folder.

## Building and running demo

* git clone <url of the Gantt repository>
* mvn clean install
* cd gantt-element/src/main/resources/org/tltv/gantt/public/
* polymer serve

To see the demo, navigate to http://localhost:8081/


## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Gantt is written by Tomi Virtanen

