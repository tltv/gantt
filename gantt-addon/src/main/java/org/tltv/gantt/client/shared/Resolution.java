/*
 * Copyright 2014 Tomi Virtanen
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

/**
 * Resolution of timeline.
 * 
 * @author Tltv
 * 
 */
public enum Resolution {

    /**
     * Day resolution makes the Gantt Chart to represent all dates in the
     * timeline. Suitable for representing few days or several weeks long steps.
     */
    Day,
    /**
     * Week resolution divides the timeline in week blocks making it clearer to
     * represent several months long steps.
     */
    Week,

    /**
     * Hour resolution divides the timeline in hour blocks.
     */
    Hour
}
