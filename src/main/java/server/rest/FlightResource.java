//package server.rest;
//
//import common.model.Flight;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import server.service.FlightService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//public class FlightResource {
//
//    private final FlightService flightService;
//
//    public FlightResource(FlightService flightService) {
//        this.flightService = flightService;
//    }
//
//    @GetMapping("flights")
//    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam("secret") String secret) {
//        if (!"SecretKey".equals(secret)) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(flightService.getAllflights());
//    }
//
//    @PostMapping("flights")
//    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
//        if (flight != null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(flightService.saveflight(flight));
//    }
//
//    @PutMapping("flights/{flight}")
//    public ResponseEntity<Flight> updateFlight(@RequestBody Flight updatedFlight, @PathVariable("flight") Flight flight) {
//        if (!updatedFlight.equals(flight)) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(flightService.saveflight(updatedFlight));
//    }
//
//    @DeleteMapping("flights/{flightTrackingNumber}")
//    public ResponseEntity<Void> deleteFlight(@PathVariable("flight") Flight flight) {
//        flightService.deleteflight(flight);
//        return ResponseEntity.noContent().build();
//    }
//}
