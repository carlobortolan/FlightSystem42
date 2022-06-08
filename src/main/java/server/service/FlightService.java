package server.service;

import common.model.Flight;
import common.model.FlightObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class FlightService {
    private final List<Flight> flights;

    public FlightService() {
        this.flights = new ArrayList<>();
    }

    public Flight saveflight(Flight flight) {
        var optionalflight = flights.stream().filter(existingflight -> {
            //return existingflight.getTrackingNumber().equals(flight.getTrackingNumber());
            int tmp = 0;
            int i = 0;
            for(FlightObject flightObject : flight.getFlight()) {
                if (flightObject.getTrackingNumber().equals(existingflight.getFlight().get(i++).getTrackingNumber())) {
                    tmp++;
                }
            }
            return tmp == flight.getFlight().size();
        }).findFirst();
        if (optionalflight.isEmpty()) {
            flights.add(flight);
            return flight;
        } else {
            var existingflight = optionalflight.get();
            existingflight.setFlight(flight.getFlight());
            return existingflight;
        }
    }

    //public void deleteflight(String flightId) {
    //    this.flights.removeIf(flight -> {return flight.getFlight().get(0).getTrackingNumber().equals(flightId);});
    //}

    public void deleteflight(String[] flightId) {
        this.flights.removeIf(flight -> {
            int tmp = 0;
            int i = 0;
            for(FlightObject flightObject : flight.getFlight()) {
                if (flightObject.getTrackingNumber().equals(flightId[i++])) {
                    tmp++;
                }
            }
            return tmp == flight.getFlight().size();
        });
    }

    public List<Flight> getAllflights() {
        return Collections.unmodifiableList(this.flights);
    }
}
