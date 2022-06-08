package lufthansa;

import common.model.City;
import common.model.Details;
import common.model.Flight;
import common.model.FlightObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

//TODO @Fabian
public class FlightParser {

    private String token = "s7j7buk476wue7nyke58q3rz";
    private int restTime;
    private LocalDateTime askedforToken;
    private LocalDateTime askfornewToken;

    public ArrayList<FlightObject> fetchFlights(String input) {
        ArrayList<FlightObject> fetchedFlights = new ArrayList<>();

        System.out.println("\r\ninput = " + input);
        String[] tmp = input.split(",");

        City from, to;
        String airlineId, aircraftCode;
        String timeOfDeparture, eta, duration, delay;
        int stops, flightNumber, terminalFrom, terminalTo;

        //TODO

//        input.substring(input.indexOf("info") + 99, input.substring(input.indexOf("info")).indexOf("endOfInput"));
//
        duration = input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length());
        System.out.println("Duration = " + duration);

        from = new City(input.substring(input.indexOf("\"Flight\":{\"Departure\":{\"AirportCode\":\"") + 38, input.substring(input.indexOf("\"Flight\":{\"Departure\":{\"AirportCode\":\"") + 38).indexOf("\"") + input.substring(0, input.indexOf("\"Flight\":{\"Departure\":{\"AirportCode\":\"") + 38).length()));
        System.out.println("From = " + from.getIATA());

        to = new City(input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26, input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).indexOf("\"") + input.substring(0, input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).length()));
        System.out.println("To = " + to.getIATA());

        timeOfDeparture = input.substring(input.indexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34, input.substring(input.indexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34).indexOf("\"") + input.substring(0, input.indexOf("\"ScheduledTimeLocal\":{\"DateTime\":\"") + 34).length());
        System.out.println("TimeOfDeparture = " + timeOfDeparture);

        terminalFrom =  Integer.parseInt(input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length()));
        System.out.println("TerminalFrom = " + terminalFrom);

        terminalTo =  Integer.parseInt(input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length()));
        System.out.println("TerminalTo = " + terminalTo);

        airlineId = input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length());
        System.out.println("AirLineID = " + airlineId);

        flightNumber= Integer.parseInt(input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length()));
        System.out.println("FlightNumber = " + flightNumber);

        aircraftCode = input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length());
        System.out.println("AircraftCode = " + aircraftCode);

        stops = Integer.parseInt(input.substring(input.indexOf("Duration\":\"") + 11, input.substring(input.indexOf("Duration\":\"") + 11).indexOf("\"") + input.substring(0, input.indexOf("Duration\":\"") + 11).length()));
        System.out.println("Stops = " + stops);

        //
//           /*
//            switch (s) {
//                case "Duration" -> System.out.println("Duration");
//                case "\"Flight\":{\"Departure\":{\"AirportCode\":\"" -> System.out.println("From");
//                case "\"Arrival\":{\"AirportCode\":\"" -> System.out.println("To");
//                case "ScheduledTimeLocal\":{\"DateTime\":\"" -> System.out.println("timeOfDeparture");
//                case "\"Terminal\":{\"Name\":\"" -> System.out.println("Terminal");
//                case "{\"AirlineID\":\"" -> System.out.println("AirLineID");
//                case "\"FlightNumber\":\"410\"}" -> System.out.println("FlightNumber");
//                case "{\"AircraftCode\":\"" -> System.out.println("AircraftCode");
//                case "\"Details\":{\"Stops\":{\"StopQuantity\":}" -> System.out.println("stops");
//
//                // case "{\"AircraftCode\":\"" -> System.out.println("eta");
//                // case "{\"AircraftCode\":\"" -> System.out.println("delay");
//            }
//        }
//        */
//
//
//        fetchedFlights.add(new FlightObject(airlineId + flightNumber, from, to, new Details(timeOfDeparture, eta, delay, stops, airlineId, aircraftCode, terminal)));
//
//        //TODO
//
//        return fetchedFlights;
        return null;
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
        token = response.substring(17, 42);
        restTime = response.substring(78, 84);

        this.token = token;
        this.restTime = Integer.parseInt(restTime);
        this.askedforToken = LocalDateTime.now();
        this.askfornewToken = askedforToken.plusSeconds(Long.parseLong(restTime));

        System.out.println(token);
        System.out.println(response);
        System.out.println(restTime);

    }

    public String searchFlight(String from, String to, String date, int directFlight) throws IOException, InterruptedException {
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

    public int getRestTime() {
        return restTime;
    }

    public LocalDateTime getAskedforToken() {
        return askedforToken;
    }

    public LocalDateTime getAskfornewToken() {
        return askfornewToken;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
/*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.lufthansa.com/v1/operations/schedules/FRA/JFK/2022-06-06?directFlights=0"))
                .header("X-RapidAPI-Host", "lihcode-lufthansa-open-new-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

 */


        FlightParser versuch = new FlightParser();
        String f = versuch.searchFlight("MUC", "JFK", "2022-06-06", 1);

        versuch.fetchFlights(f);
    }
}

