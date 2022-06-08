package common.model;

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
}
