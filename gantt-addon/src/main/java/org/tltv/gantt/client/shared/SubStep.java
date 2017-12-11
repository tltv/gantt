/*
 * Copyright 2015 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tltv.gantt.client.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;

public class SubStep extends AbstractStep {

    public SubStep() {
        setSubstep(true);
    }

    public SubStep(String caption) {
        super(caption);
    }

    public SubStep(String caption, CaptionMode captionMode) {
        super(caption, captionMode);
    }

    private Step owner;

    public Step getOwner() {
        return owner;
    }

    protected void setOwner(Step owner) {
        this.owner = owner;
    }

    public static SubStep toStep(JavaScriptObject o) {
        SubStep s = new SubStep();
        s.read(new JSONObject(o));
        return s;
    }

    @Override
    public void read(JSONObject json) {
        super.read(json);
        if (json.containsKey("owner")) {
            setOwner(Step.toStep(json.get("owner").isObject().getJavaScriptObject()));
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("substep", JSONBoolean.getInstance(isSubstep()));
        if (getOwner() != null) {
            json.put("owner", getOwner().toJsonReference());
        }
        return json;
    }
}
