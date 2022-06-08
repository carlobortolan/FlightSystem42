package common.model;

import java.util.List;

public class City {
    private String name;
    private String country;
    private String coordinates;
    private Weather weather;
    private List<String> poi;

    public City(String name, String country, String coordinates, Weather weather, List<String> poi) {
        this.name = name;
        this.country = country;
        this.coordinates = coordinates;
        this.weather = weather;
        this.poi = poi;
    }

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
