/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package model;

public class FlightObject {
    private String trackingNumber;
    private City from;
    private City to;
    private Details details;

    public FlightObject(String trackingNumber, City from, City to, Details details) {
        System.out.println("trackingNumber = " + trackingNumber);
        this.trackingNumber = trackingNumber;
        this.from = from;
        this.to = to;
        this.details = details;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}


