package service;

import model.Flight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlightService {
    private final List<Flight> flights;

    public FlightService() {
        this.flights = new ArrayList<>();
    }

    public Flight saveflight(Flight flight) {
        var optionalflight = flights.stream().filter(existingflight -> existingflight.getTrackingNumber().equals(flight.getTrackingNumber())).findFirst();
        if (optionalflight.isEmpty()) {
            flight.setTrackingNumber("test");
            flights.add(flight);
            return flight;
        } else {
            var existingflight = optionalflight.get();
            existingflight.setFrom(flight.getFrom());
            existingflight.setTo(flight.getTo());
            existingflight.setDate(flight.getDate());
            existingflight.setDetails(flight.getDetails());
            return existingflight;
        }
    }

    public void deleteflight(String flightId) {
        this.flights.removeIf(flight -> flight.getTrackingNumber().equals(flightId));
    }

    public List<Flight> getAllflights() {
        return Collections.unmodifiableList(this.flights);
    }
}
