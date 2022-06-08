package rest;

import model.Flight;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FlightService;

import java.util.List;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class FlightResource {

    private final FlightService flightService;

    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("flights")
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam("secret") String secret) {
        if (!"SecretKey".equals(secret)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightService.getAllflights());
    }

    @PostMapping("flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        if (flight.getTrackingNumber()!= null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightService.saveflight(flight));
    }

    @PutMapping("flights/{flightTrackingNumber}")
    public ResponseEntity<Flight> updateNote(@RequestBody Flight updatedFlight, @PathVariable("flightTrackingNumber") String flightTrackingNumber) {
        if (!updatedFlight.getTrackingNumber().equals(flightTrackingNumber)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightService.saveflight(updatedFlight));
    }

    @DeleteMapping("flights/{flightTrackingNumber}")
    public ResponseEntity<Void> deleteFlight(@PathVariable("flightTrackingNumber") String flightTrackingNumber) {
        flightService.deleteflight(flightTrackingNumber);
        return ResponseEntity.noContent().build();
    }
}
