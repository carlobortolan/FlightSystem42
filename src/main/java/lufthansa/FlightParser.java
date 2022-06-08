package lufthansa;

import common.model.City;
import common.model.Flight;

import java.util.ArrayList;
import java.util.List;

//TODO @Carlo
public class FlightParser {
    private List<Flight> fetchedFlights;
    public static ArrayList<Flight> fetchFlights(String input) {
        ArrayList<Flight> fetchedFlights = new ArrayList<>();

        String[] tmp = input.split(",");

        City from, to;
        String trackingNumber, airline, plane;
        int connections;


        for(String s : tmp) {

        }

        return fetchedFlights;
    }
}
