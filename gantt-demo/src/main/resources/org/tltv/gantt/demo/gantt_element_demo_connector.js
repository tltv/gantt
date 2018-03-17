// connector for debugging purposes
window.org_tltv_gantt_demo_GanttElementDemo = function() {
	var self = this;
    var element = this.getElement();
    
    element.style.width='100%';
    element.style.height='100%';

    var g = new gantt.GanttElement();
    g.getGanttElement().ganttElement = g;
    element.appendChild(g.getGanttElement());
    
    var steps = [
		{
			uid: "1", // required unique step id
			identifier: 1, // optional ID
			caption: "Step A",
			description: "Description of Step A",
			backgroundColor: "#A8D9FF",
			startDate: new Date('2017-01-01T00:00:00Z').getTime(),
			endDate: new Date('2017-01-31T23:59:59Z').getTime(),
			resizable: true,
			movable: true
		}
	];
    
    g.setState(
			{
				startDate: new Date('2017-01-01T00:00:00Z').getTime(),
				endDate: new Date('2017-12-31T23:59:59Z').getTime(),
				locale: "fi_FI", // must match to UI instance's locale!
				resolution: "Day",
				backgroundGridEnabled: true
			});
    
    g.getGanttElement().steps = steps;
    
    // step click handling
	g.getGanttElement().handleStepClicked = function(stepUid, details) {
		var step = g.getStepByUid(stepUid);
		alert(step.caption + ': ' + details.toString());
	}

	// step move handling
	g.getGanttElement().handleOnMove = function(stepUid, newStepUid, startDate, endDate, details) {
		var step = g.getStepByUid(stepUid);
		if(step.substep) {
			step.startDate = startDate;
			step.endDate = endDate;
			g.updateSubStep(step);
		} else {
			step.startDate = startDate;
			step.endDate = endDate;
			g.updateStep(step);
		}
	}

	// step resize handling
	g.getGanttElement().handleOnResize = function(stepUid, startDate, endDate, details) {
		var step = g.getStepByUid(stepUid);
		step.startDate = startDate;
		step.endDate = endDate;
		g.update(step);
	}

	// step predecessor change handling
	g.getGanttElement().handleOnPredecessorChange = function(newPredecessorStepUid, forTargetStepUid, clearPredecessorForStepUid) {

	}
    
	
	
    this.onStateChange = function () {
        var state = this.getState();
    }
}
