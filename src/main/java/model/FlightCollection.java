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
import java.util.List;

public class FlightCollection {


    private List<Flight> flights;

    public FlightCollection() {
        this.flights = new LinkedList<Flight>();
    }

    public FlightCollection(LinkedList<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        return this.flights.remove(flight);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public boolean contains(Flight flight) {

        for (Flight f : flights) {
            if (f.getFlight().size() == flight.getFlight().size()) {
                int count = 0;
                for (int i = 0; i < f.getFlight().size(); i++) {
                    if (f.getFlight().get(i).getTrackingNumber().equals(flight.getFlight().get(i).getTrackingNumber())) {
                        count++;
                    }
                }
                if (count == f.getFlight().size() && count == flight.getFlight().size()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
