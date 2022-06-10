package lufthansa;

import model.City;
import model.Details;
import model.Flight;
import model.FlightObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

//TODO @Fabian
public class FlightParser {

    private String token = "s7j7buk476wue7nyke58q3rz";
    private int restTime;
    private LocalDateTime askedforToken;
    private LocalDateTime askfornewToken;

    public static ArrayList<Flight> fetchFlights(String f) {

        if (f.contains("Errors")) {
            System.out.println("No flight matched the criteria.");
            return null;
        }

        String[] allFlights = f.split("TotalJourney");
        ArrayList<Flight> fetchedFlights = new ArrayList<>();

        for (String flights : allFlights) {

            String[] flightConnections = flights.split("}}},");

            String duration = flights.substring(flights.indexOf("\"Duration\":\"") + 14, flights.substring(flights.indexOf("\"Duration\":\"") + 14).indexOf("\"") + flights.substring(0, flights.indexOf("\"Duration\":\"") + 14).length());
            System.out.println("\r\n\r\nDuration = " + duration);

            for (String input : flightConnections) {
                Flight flight = new Flight();

                if (input.contains("Departure") && input.contains("Arrival") && input.contains("ScheduledTimeLocal") && input.contains("Terminal") && input.contains("AirlineID") && input.contains("FlightNumber") && input.contains("AircraftCode") && input.contains("StopQuantity")) {

                    City from, to;
                    String airlineId, aircraftCode, terminalFrom, terminalTo;
                    LocalDateTime timeOfDeparture, eta;
                    int stops, flightNumber;

                    from = new City(input.substring(input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28, input.substring(input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28).indexOf("\"") + input.substring(0, input.indexOf("\"Departure\":{\"AirportCode\":\"") + 28).length()));
                    System.out.println("From = " + from.getIATA());

                    to = new City(input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26, input.substring(input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).indexOf("\"") + input.substring(0, input.indexOf("\"Arrival\":{\"AirportCode\":\"") + 26).length()));
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

        FlightParser.fetchFlights(new FlightParser().searchFlight("FRA", "JFK", "2022-08-08", 0));
    }
}