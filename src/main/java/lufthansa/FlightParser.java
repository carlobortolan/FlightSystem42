/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz, Luca Mathias
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lufthansa;

import javafx.util.converter.LongStringConverter;
import model.City;
import model.Details;
import model.Flight;
import model.FlightObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.LinkedList;




public class FlightParser {

    private String token;

    public  FlightParser() throws IOException, InterruptedException {
        long lastAsked = new LongStringConverter().fromString(readFromFile(Path.of("time")));
        if(System.currentTimeMillis() - lastAsked > 1.72e+5){
            getToken();
        }
        else this.token = readFromFile(Path.of("key"));
        System.out.println(token);
    }

    public static LinkedList<Flight> fetchFlights(String f) throws IOException {
        if (f.contains("Errors") || f.contains("<!DOCTYPE><html><head><title>")) {
            System.out.println("No flight matched the criteria.");
            throw new IOException("NOT FOUND");
        }

        String[] allFlights = f.split("TotalJourney");
        LinkedList<Flight> fetchedFlights = new LinkedList<>();

        for (String flights : allFlights) {
            System.out.println("INPUT = " + flights);
            if (flights.contains("Duration")) {
                String[] flightConnections = flights.split("}}},");
                String duration = flights.substring(flights.indexOf("\"Duration\":\"") + 12, flights.substring(flights.indexOf("\"Duration\":\"") + 12).indexOf("\"") + flights.substring(0, flights.indexOf("\"Duration\":\"") + 12).length());
                System.out.println("\r\n\r\nDuration = " + duration);

                Flight flight = new Flight();
                for (String input : flightConnections) {
                    System.out.println("INPUT = " + input);
                    try {
//                    if (input.contains("Departure") && input.contains("Arrival") && input.contains("ScheduledTimeLocal") && input.contains("Terminal") && input.contains("AirlineID") && input.contains("FlightNumber") && input.contains("AircraftCode") && input.contains("StopQuantity")) {

                        City from, to;
                        String airlineId, aircraftCode, terminalFrom, terminalTo;
                        LocalDateTime timeOfDeparture, eta;
                        int stops, flightNumber;

                        String fromC = input.substring(input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28, input.substring(input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28).indexOf("\"") + input.substring(0, input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28).length());
                        System.out.println("FROMC = " + fromC);
                        from = City.destinations.get(input.substring(input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28, input.substring(input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28).indexOf("\"") + input.substring(0, input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28).length()));
                        if (from == null) {
                            from = new City(fromC);
                        }
                        System.out.println("From = " + from.getIATA());

                        String toC = input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26, input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).indexOf("\"") + input.substring(0, input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).length());
                        to = City.destinations.get(input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26, input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).indexOf("\"") + input.substring(0, input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).length()));
                        if (to == null) {
                            to = new City(toC);
                        }
                        System.out.println("To = " + to.getIATA());

                        String departure = input.substring(input.indexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34, input.substring(input.indexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34).indexOf("\"") + input.substring(0, input.indexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34).length());
                        System.out.println("TimeOfDeparture = " + departure);
                        int depYear = Integer.parseInt(departure.substring(0, 4));
                        int depMonth = Integer.parseInt(departure.substring(5, 7));
                        int depDay = Integer.parseInt(departure.substring(8, 10));
                        int depHours = Integer.parseInt(departure.substring(11, 13));
                        int depMinutes = Integer.parseInt(departure.substring(14, 16));
                        timeOfDeparture = LocalDateTime.of(depYear, depMonth, depDay, depHours, depMinutes);

                        String arrival = input.substring(input.lastIndexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34, input.substring(input.lastIndexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34).indexOf("\"") + input.substring(0, input.lastIndexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34).length());
                        System.out.println("Eta = " + arrival);
                        int etaYear = Integer.parseInt(arrival.substring(0, 4));
                        int etaMonth = Integer.parseInt(arrival.substring(5, 7));
                        int etaDay = Integer.parseInt(arrival.substring(8, 10));
                        int etaHours = Integer.parseInt(arrival.substring(11, 13));
                        int etaMinutes = Integer.parseInt(arrival.substring(14, 16));
                        eta = LocalDateTime.of(etaYear, etaMonth, etaDay, etaHours, etaMinutes);

                        terminalFrom = input.substring(input.indexOf("\"Terminal\":{\"Name\":\"") + 20, input.substring(input.indexOf("\"Terminal\":{\"Name\":\"") + 20).indexOf("\"") + input.substring(0, input.indexOf("\"Terminal\":{\"Name\":\"") + 20).length());
                        System.out.println("TerminalFrom = " + terminalFrom);

                        terminalTo = input.substring(input.lastIndexOf("\"Terminal\":{\"Name\":\"") + 20, input.substring(input.lastIndexOf("\"Terminal\":{\"Name\":\"") + 20).indexOf("\"") + input.substring(0, input.lastIndexOf("\"Terminal\":{\"Name\":\"") + 20).length());
                        System.out.println("TerminalTo = " + terminalTo);

                        airlineId = input.substring(input.indexOf("\"AirlineID\":\"") + 13, input.substring(input.indexOf("\"AirlineID\":\"") + 13).indexOf("\"") + input.substring(0, input.indexOf("\"AirlineID\":\"") + 13).length());
                        System.out.println("AirLineID = " + airlineId);

                        flightNumber = Integer.parseInt(input.substring(input.indexOf(",\"FlightNumber\":\"") + 17, input.substring(input.indexOf(",\"FlightNumber\":\"") + 17).indexOf("\"") + input.substring(0, input.indexOf(",\"FlightNumber\":\"") + 17).length()));
                        System.out.println("FlightNumber = " + flightNumber);

                        aircraftCode = input.substring(input.indexOf("\"AircraftCode\":\"") + 16, input.substring(input.indexOf("\"AircraftCode\":\"") + 16).indexOf("\"") + input.substring(0, input.indexOf("\"AircraftCode\":\"") + 16).length());
                        System.out.println("AircraftCode = " + aircraftCode);

                        stops = Integer.parseInt(input.substring(input.indexOf("\"Stops\":{\"StopQuantity\":") + 24, input.substring(input.indexOf("\"Stops\":{\"StopQuantity\":") + 24).indexOf("}") + input.substring(0, input.indexOf("\"Stops\":{\"StopQuantity\":") + 24).length()));
                        System.out.println("Stops = " + stops);
                        System.out.println();

                        FlightObject flightObject = new FlightObject(airlineId + flightNumber + "-" + aircraftCode, from, to, new Details(timeOfDeparture, eta, stops, airlineId, aircraftCode, terminalFrom, terminalTo));

                        flight.addFlight(flightObject);
                        flight.setDuration(duration);
                        //                   }
                    } catch (StringIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }

                if (!flight.isEmpty()) fetchedFlights.add(flight);
            }
        }
        return fetchedFlights;
    }

    public void getToken() throws IOException, InterruptedException {

        String[] commands = new String[]{"curl", "https://api.lufthansa.com/v1/oauth/token", "-X", "POST", "-d",
                "client_id=vzr85vbughdj7tbvkqhbabj7", "-d", "client_secret=aGtfzGbDhN54TQPBjFQ6", "-d", "grant_type=client_credentials"};
        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response = response + line;
        }
        String token = "";
        String restTime = "";
        token = response.substring(17, 41);
        System.out.println(token);
        restTime = response.substring(78, 84);

        this.token = token;
        saveToFile(Path.of("key"), token);
        saveToFile(Path.of("time"), new LongStringConverter().toString(System.currentTimeMillis()));

        System.out.println(token);
        System.out.println(response);
    }

    private void saveToFile(Path path, String s) throws IOException {
        Files.writeString(path, s);
    }
    private String readFromFile(Path path) throws IOException {
        return Files.readString(path);
    }


    public String searchFlight(String from, String to, String date, int directFlight) throws IOException, InterruptedException {
        System.out.println(token);
        System.out.println("REQ: from = " + from + ", to = " + to + ", date = " + date + ", directFlight = " + directFlight);
        String[] commands = new String[]{"curl", "-H", "Authorization: Bearer " + token, "-H", "Accept: application/json",
                "https://api.lufthansa.com/v1/operations/schedules/" + from + "/" + to + "/" + date + "?directFlights=" + directFlight};

        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response = response + line;
        }
        String[] array = response.split("TotalJourney");


        for (int i = 1; i < array.length; i++) {
            String[] details = array[i].split("}");
        }
        System.out.println(response);
        return response;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.lufthansa.com/v1/operations/schedules/FRA/JFK/2022-06-06?directFlights=0"))
//                .header("X-RapidAPI-Host", "lihcode-lufthansa-open-new-v1.p.rapidapi.com")
//                .header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());
//
//
//        new FlightParser().getToken();
//        LinkedList<Flight> flights = FlightParser.fetchFlights("{\"ScheduleResource\":{\"Schedule\":[{\"TotalJourney\":{\"Duration\":\"PT9H\"},\"Flight\":{\"Departure\":{\"AirportCode\":\"MUC\",\"ScheduledTimeLocal\":{\"DateTime\":\"2022-06-18T12:35\"},\"Terminal\":{\"Name\":\"2\"}},\"Arrival\":{\"AirportCode\":\"JFK\",\"ScheduledTimeLocal\":{\"DateTime\":\"2022-06-18T15:35\"},\"Terminal\":{\"Name\":\"1\"}},\"MarketingCarrier\":{\"AirlineID\":\"LH\",\"FlightNumber\":\"410\"},\"Equipment\":{\"AircraftCode\":\"346\",\"OnBoardEquipment\":{\"InflightEntertainment\":true,\"Compartment\":[{\"ClassCode\":\"F\",\"ClassDesc\":\"FirstClass\",\"FlyNet\":true,\"SeatPower\":true,\"Usb\":true,\"LiveTv\":true},{\"ClassCode\":\"C\",\"ClassDesc\":\"BusinessClass\",\"FlyNet\":true,\"SeatPower\":true,\"Usb\":true,\"LiveTv\":true},{\"ClassCode\":\"E\",\"ClassDesc\":\"PremiumEconomy\",\"FlyNet\":true,\"SeatPower\":true,\"Usb\":true,\"LiveTv\":true},{\"ClassCode\":\"Y\",\"ClassDesc\":\"Economy\",\"FlyNet\":true,\"SeatPower\":true,\"Usb\":true,\"LiveTv\":true}]}},\"Details\":{\"Stops\":{\"StopQuantity\":0},\"DaysOfOperation\":\"13567\",\"DatePeriod\":{\"Effective\":\"2022-06-13\",\"Expiration\":\"2022-10-29\"}}}}");
//        System.out.println("SIZE = " + flights.size());
//    }
}