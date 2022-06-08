package model;

import java.time.LocalTime;

public class Details {
    private LocalTime eta;
    private LocalTime delay;
    private int seat;
    private String airline;
    private String plane;
    private int connections;

    public Details(LocalTime eta, LocalTime delay, int seat, String airline, String plane) {
        this.eta = eta;
        this.delay = delay;
        this.seat = seat;
        this.airline = airline;
        this.plane = plane;
        this.connections = 0;
    }

    public Details(LocalTime eta, LocalTime delay, int seat, String airline, String plane, int connections) {
        this.eta = eta;
        this.delay = delay;
        this.seat = seat;
        this.airline = airline;
        this.plane = plane;
        this.connections = connections;
    }
}
