package lufthansa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

//TODO @Fabian
public class IATA {
    private String iataCode;
    private String airportName;
    private String cityName;
    private String country;
    private String region;
    public static List<IATA> destinations = new LinkedList<>();


    public IATA(String iataCode, String airportName, String cityName, String country, String region) {
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.cityName = cityName;
        this.country = country;
        this.region = region;
    }
    public IATA(){
    }

    private void finddestinations() throws IOException {
        Path iataList = Path.of("src/main/java/lufthansa/IATA LIST");
        String content = Files.readString(iataList);
        String[] cities = content.split("\n");

        for (int i = 0; i < cities.length; i++) {
            String[] details = cities[i].split("\t");
            if (details.length < 2) {
                continue;
            }
            String iata = details[0].trim().substring(0, 3);
            String airportName = details[2].trim();
            String cityName;
            String regionName = "-";
            String countryName = "-";
            String[] location = details[3].split(",");
            cityName = location[0].trim();

            if (location.length > 2) {
                regionName = location[1].trim();
                countryName = location[2].trim();
            }
            if (location.length == 2) {
                countryName = location[1].trim();
            }

            IATA city = new IATA(iata, airportName, cityName, countryName, regionName);
            destinations.add(city);

        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Ende");
    }

}
