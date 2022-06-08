package common.model;

import java.time.Instant;

public class FlightObject {
//    private Instant departure;
    private String trackingNumber;
    private City from;
    private City to;
    private Details details;

    public FlightObject(String trackingNumber, City from, City to, Details details) {
//        this.departure = departure;
        this.trackingNumber = trackingNumber;
        this.from = from;
        this.to = to;
        this.details = details;
    }

//    public Instant getDeparture() {
//        return departure;
//    }

//    public void setDeparture(Instant date) {
//        this.departure = departure;
//    }

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


