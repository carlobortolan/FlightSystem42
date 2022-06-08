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

//    public String getTrackingNumber() {
//        return this.flight.getFirst().getTrackingNumber();
//    }
//    public void setTrackingNumber(String trackingNumber) {
//        this.flight.getFirst().setTrackingNumber(trackingNumber);
//    }

}