package common.model;

import java.util.List;

public class City {
    public String getIATA() {
        return IATA;
    }

    private String IATA;
    private String name;
    private String country;
    private String coordinates;
    private Weather weather;
    private List<String> poi;

    public City(String IATA, String name, String country, String coordinates, Weather weather, List<String> poi) {
        this.IATA = IATA;
        this.name = name;
        this.country = country;
        this.coordinates = coordinates;
        this.weather = weather;
        this.poi = poi;
    }
    public City(String IATA) {
        this.IATA = IATA;
        //TODO
//      this.name = name;
//      this.country = country;
//      this.coordinates = coordinates;
//      this.weather = weather;
//      this.poi = poi;
    }
}
