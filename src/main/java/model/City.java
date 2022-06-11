package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class City {
    public String getIATA() {
        return iataCode;
    }
    private String iataCode;
    private String cityName;
    private String airportName;
    private String region;
    private String country;
    private Weather weather;
    public static HashMap<String, City> destinations = new HashMap<>();

    public City(String iataCode, String airportName, String cityName, String country, String region) {
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.cityName = cityName;
        this.country = country;
        this.region = region;
    }
    public City(String iataCode) {
        this.iataCode = iataCode;
        this.airportName = "";
        this.cityName = "";
        this.country = "";
        this.region = "";
    }

    public static void finddestination() throws IOException {
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

            City city = new City(iata, airportName, cityName, countryName, regionName);
            destinations.put(iata, city);

            System.out.println(iata);
        }
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public static HashMap<String, City> getDestinations() {
        return destinations;
    }

    public static void setDestinations(HashMap<String, City> destinations) {
        City.destinations = destinations;
    }

    public static void main(String[] args) throws IOException {
        try {
            City.finddestination();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        City newYork = City.destinations.get("JFK");
        System.out.println(newYork.airportName);
        Weather weather = new Weather();
        weather.getWeather("München");
        System.out.println("Ende");
    }
}
