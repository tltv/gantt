# Gantt Chart for Flow

Gantt component for Flow (Vaadin Platform 13+) based on build-in GanttLib library which is exported from Gantt addon for Vaadin 8 (GWT).


List of features that are supported:
* Server side API allows to modify timeline options and step options like start/end date, caption, color and adding/removing steps
* adjustable timeline range
* timeline supports Hour, Day and Week resolutions 
* modify step's start date and end date by dragging and moving the step (move and resize events)
* reorder steps by drag and dropping 
* scrolling enabled horizontally and vertically for a large chart content
* scales the small timeline and content width up to fit in the space available
* background grid
* dynamically modifiable predecessor relations between steps
* sub-steps
* touch support

Tested to work with following browsers: 
* Chrome, Firefox, Edge
  
IE11 will not be supported.


Module descriptions  
* gantt-addon - Gantt addon based on Vaadin 8 GWT
* gantt-element - build-in Polymer element for Gantt
* gantt-exporter - GWT exporter to generate GanttLib library for third-party usage (mainly for Flow addon)
* gantt-flow-addon - Gantt Flow addon using GanttLib library
* gantt-demo - Gantt Demo for Flow 

## Demo

Online demo (Vaadin 8) available @ http://tomivirtanen.app.fi/gantt-demo/

Code can be found under /gantt-demo folder.

Flow demo is not available in public yet. Notice that feature set is a subset from Vaadin 8 version.

## Building and running demo

Gantt Flow addon uses a Javascript library build from Gantt for Vaadin 8 using GWT (./gantt-addon). Library is pre-compiled to gantt-flow-addon module and it is not required to build it (notice that `mvn clean` in root removes pre-compiled files).
 
* git clone <url of the Gantt repository>
* cd gantt-flow-addon
* mvn install
* cd ../gantt-demo
* mvn clean install
* mvn jetty:run

To see the demo, navigate to http://localhost:8080/

To build and run also GanttLib library for gantt-flow-addon, follow these steps:
* git clone <url of the Gantt repository>
* mvn clean
* mvn install
* cd gantt-flow-addon
* mvn clean install
* cd ../gantt-demo
* mvn jetty:run

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Gantt is written by Tomi Virtanen

