package org.tltv.gantt.client;

import org.tltv.gantt.client.shared.Step;

public interface StepProvider {

    StepWidget getStepWidget(Step target);
}
