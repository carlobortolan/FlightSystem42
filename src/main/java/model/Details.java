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

import java.time.LocalDateTime;

public class Details {
    private LocalDateTime timeOfDeparture;
    private LocalDateTime eta;
    private int stops;
    private String airline;
    private String aircraftCode;
    private String terminalFrom;
    private String terminalTo;

    public Details(LocalDateTime timeOfDeparture, LocalDateTime eta, int stops, String airline, String aircraftCode, String terminalFrom, String terminalTo) {
        this.timeOfDeparture = timeOfDeparture;
        this.eta = eta;
        this.stops = stops;
        this.airline = airline;
        this.aircraftCode = aircraftCode;
        this.terminalFrom = terminalFrom;
        this.terminalTo = terminalTo;
    }

    public LocalDateTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public LocalDateTime getEta() {
        return eta;
    }

    public int getStops() {
        return stops;
    }

    public String getAirline() {
        return airline;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public String getTerminalFrom() {
        return terminalFrom;
    }

    public String getTerminalTo() {
        return terminalTo;
    }

    public boolean equals(Details a, Details b) {
        return (a.getAircraftCode().equals(b.getAircraftCode())
                && a.getAirline().equals(b.getAirline())
                && a.getEta().equals(b.getEta())
                && a.getStops() == b.getStops()
                && a.getTerminalTo().equals(b.getTerminalTo())
                && a.getTerminalFrom().equals(b.getTerminalFrom())
                && a.getTimeOfDeparture().equals(b.getTimeOfDeparture()));
    }
}

