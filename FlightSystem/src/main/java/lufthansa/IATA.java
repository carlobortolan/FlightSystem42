package lufthansa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

//TODO @Fabian
public class IATA {

    private String city;
    private String airport;
    public static List<IATA> destinations = new LinkedList<>();

    public IATA(String city, String airport) {
        this.city = city;
        this.airport = airport;
    }

    public static void finddestinations() throws IOException {

        Path iataList = Path.of("FlightSystem/src/main/java/lufthansa/IATA LIST");
        String content = Files.readString(iataList);
        String[] cities = content.split("\n");

        for (int i = 0; i < cities.length; i++) {
            String[] citydetails = cities[i].split("\\|");
            String name = citydetails[0].trim();
            String airportCode = "";
            if(citydetails.length > 1) {
                airportCode = citydetails[1].trim();
            }
            IATA destination = new IATA(name, airportCode);
            destinations.add(destination);
        }

        System.out.println(content);

    }

    public static void main(String[] args) throws IOException {
        finddestinations();
        System.out.println("Ende");
    }

}
