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

