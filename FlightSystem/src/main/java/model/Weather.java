package model;

public class Weather {
    private double temperature;
    private WeatherType wheatertype;

    public Weather(double temperature, WeatherType weatherType) {
        this.temperature =temperature;
        this.wheatertype = weatherType;
    }
}
