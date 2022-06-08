package common.model;

import java.util.LinkedList;

public class Flight {
    private LinkedList<FlightObject> flight;

    public Flight(FlightObject flightObject) {
        this.flight = new LinkedList<>();
        this.flight.add(flightObject);
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

    public void addFlight(FlightObject flightObject) {
        this.flight.add(flightObject);
    }
    public boolean removeFlight(FlightObject flightObject) {
        return this.flight.remove(flightObject);
    }

    public int getStopCount() {
        return this.flight.size()-1;
    }

//    public String getTrackingNumber() {
//        return this.flight.getFirst().getTrackingNumber();
//    }
//    public void setTrackingNumber(String trackingNumber) {
//        this.flight.getFirst().setTrackingNumber(trackingNumber);
//    }

}