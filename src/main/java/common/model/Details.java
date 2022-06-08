package common.model;

import java.time.LocalTime;

public class Details {
    private String timeOfDeparture;
    private String eta;
    private String duration;
    private String delay;
    private int stops;
    private String airline;
    private String aircraftCode;
    private int terminalFrom;
    private int terminalTo;

    public Details(String timeOfDeparture, String eta, String delay, int stops, String airline, String aircraftCode, int terminalFrom, int terminalTo) {
        this.timeOfDeparture = timeOfDeparture;
        this.eta = eta;
        this.delay = delay;
        this.stops = stops;
        this.airline = airline;
        this.aircraftCode =aircraftCode;
        this.terminalFrom = terminalFrom;
        this.terminalTo = terminalTo;
    }
    public Details(String timeOfDeparture, String eta, String delay, String airline, String aircraftCode, int terminalFrom, int terminalTo) {
        this.timeOfDeparture = timeOfDeparture;
        this.eta = eta;
        this.delay = delay;
        this.stops = 0;
        this.airline = airline;
        this.aircraftCode =aircraftCode;
        this.terminalFrom = terminalFrom;
        this.terminalTo = terminalTo;
    }
}
