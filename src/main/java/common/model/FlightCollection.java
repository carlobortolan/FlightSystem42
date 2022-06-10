package common.model;

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
}