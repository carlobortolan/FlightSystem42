package model;

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

    public List<Flight> getFlights() {
        return flights;
    }

    public boolean contains(Flight flight) {

        for(Flight f : flights) {
            if(f.getFlight().size() == flight.getFlight().size()) {
                int count = 0;
                for(int i = 0; i < f.getFlight().size(); i++) {
                    if(f.getFlight().get(i).getTrackingNumber().equals(flight.getFlight().get(i).getTrackingNumber())
//                            && f.getFlight().get(i).getDetails().equals(flight.getFlight().get(i).getDetails())
                            ) {
                        count++;
                    }
                }
                if(count == f.getFlight().size() && count == flight.getFlight().size()) {
                    return true;
                }
            }
         }
        return false;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
