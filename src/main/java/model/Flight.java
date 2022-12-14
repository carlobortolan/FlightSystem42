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

import java.util.LinkedList;

public class Flight {
    private LinkedList<FlightObject> flight;
    private String duration;

    public Flight() {
        this.flight = new LinkedList<>();
    }

    public void addFlight(FlightObject flightObject) {
        this.flight.add(flightObject);
    }

    public boolean removeFlight(FlightObject flightObject) {
        return this.flight.remove(flightObject);
    }

    public int getStopCount() {
        return this.flight.size() - 1;
    }

    public boolean equals(Flight a, Flight b) {
        if (a.getFlight().size() == b.getFlight().size()) {
            return false;
        }

        for (int i = 0; i < a.getFlight().size(); i++) {
            if (!a.getFlight().get(i).getTrackingNumber().equals(b.getFlight().get(i).getTrackingNumber())) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return this.flight.isEmpty();
    }

    public LinkedList<FlightObject> getFlight() {
        return flight;
    }

    public void setFlight(LinkedList<FlightObject> flight) {
        this.flight = flight;
    }

    public City getFrom() {
        return this.flight.getFirst().getFrom();
    }

    public void setFrom(City from) {
        this.flight.getFirst().setFrom(from);
    }

    public City getTo() {
        return this.flight.getLast().getTo();
    }

    public void setTo(City to) {
        this.flight.getLast().setTo(to);
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

//    public String getTrackingNumber() {
//        return this.flight.getFirst().getTrackingNumber();
//    }
//    public void setTrackingNumber(String trackingNumber) {
//        this.flight.getFirst().setTrackingNumber(trackingNumber);
//    }

}