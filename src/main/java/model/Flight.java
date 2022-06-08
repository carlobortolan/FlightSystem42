package model;

import java.time.Instant;

public class Flight {
    private Instant date;
    private String trackingNumber;
    private City from;
    private City to;
    private Details details;

    public Flight(Instant date, String trackingNumber, City from, City to, Details details) {
        this.date = date;
        this.trackingNumber = trackingNumber;
        this.from = from;
        this.to = to;
        this.details = details;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
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
