package client.controller;//package controller;
//
//import model.Flight;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Consumer;
//
//public class FlightController {
//    private final WebClient webClient;
//    private final List<Flight> flights;
//
//    public FlightController() {
//        this.webClient = WebClient.builder()
//                .baseUrl("http://localhost:8080/")
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//        this.flights = new ArrayList<Flight>();
//    }
//
//    public void addNote(Flight flight, Consumer<List<Flight>> flightsConsumer) {
//        webClient.post()
//                .uri("flights")
//                .bodyValue(flight)
//                .retrieve()
//                .bodyToMono(Flight.class)
//                .onErrorStop()
//                .subscribe(newFlight -> {
//                    flights.add(newFlight);
//                    flightsConsumer.accept(flights);
//                });
//    }
//
//    public void editFlight(Flight flight, Consumer<List<Flight>> flightsConsumer) {
//        webClient.put()
//                .uri("flights/" + flight.getTrackingNumber())
//                .bodyValue(flight)
//                .retrieve()
//                .bodyToMono(Flight.class)
//                .onErrorStop()
//                .subscribe(newFlight -> {
//                    flights.replaceAll(oldFlight -> oldFlight.getTrackingNumber().equals(newFlight.getTrackingNumber()) ? newFlight : oldFlight);
//                    flightsConsumer.accept(flights);
//                });
//    }
//
//    public void deleteFlight(Flight flight, Consumer<List<Flight>> flightsConsumer) {
//        webClient.delete()
//                .uri("flights/" + flight.getTrackingNumber())
//                .retrieve()
//                .toBodilessEntity()
//                .onErrorStop()
//                .subscribe(v -> {
//                    flights.remove(flight);
//                    flightsConsumer.accept(flights);
//                });
//    }
//
//    public void getAllFlights(Consumer<List<Flight>> flightsConsumer) {
//        webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("flights")
//                        .queryParam("secret", "SecretKey")
//                        .build())
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<Flight>>() {})
//                .onErrorStop()
//                .subscribe(newFlights -> {
//                    flights.clear();
//                    flights.addAll(newFlights);
//                    flightsConsumer.accept(flights);
//                });
//    }
//}
