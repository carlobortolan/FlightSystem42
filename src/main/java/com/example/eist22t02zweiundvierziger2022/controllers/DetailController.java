package com.example.eist22t02zweiundvierziger2022.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.City;
import model.Flight;
import model.Weather;

import java.io.File;
import java.io.IOException;

public class DetailController {
    @FXML
    private Button showMapButton;

    @FXML
    private TextArea weatherStart;

    @FXML
    private TextArea weatherDestination;

    @FXML
    private ImageView imageStart;

    @FXML
    private ImageView imageDestination;

    public void initialize(Flight flight) {
        try {
            this.weatherStart.setText(this.initializeWeatherData(flight.getFrom()));
            this.imageStart.setImage(new Image(initializeWeatherImage(flight.getFrom()).toURI().toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("CITY " + flight.getFrom().getIATA() + " not found!");
            this.weatherStart.setText("no weather data available");
        }
        try {
            this.weatherDestination.setText(this.initializeWeatherData(flight.getTo()));
            this.imageDestination.setImage(new Image(initializeWeatherImage(flight.getTo()).toURI().toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("CITY " + flight.getFrom().getIATA() + " not found!");
            this.weatherStart.setText("no weather data available");
        }
    }

    public String initializeWeatherData(City city) throws IOException {
        city.setWeather(new Weather().getWeather(city.getCityName()));
        Weather weatherStart = city.getWeather();
        return "[ " + weatherStart.getCoordinates() + "]" +
                "\r\nWeather: " + weatherStart.getWeatherDescirption()
                + "\r\nTemperature: " + weatherStart.getCurrentTemp()
                + "\r\nTemperature feels like: " + weatherStart.getTempfeelslike()
                + "\r\nMinimum temperature: " + weatherStart.getTemp_min()
                + "\r\nMaximum temperature: " + weatherStart.getTemp_max()
                + "\r\nWind direction: " + weatherStart.getWindDirection()
                + "\r\nWind speed: " + weatherStart.getWindSpeed();
    }

    public File initializeWeatherImage(City city) throws IOException {
        String url;
        System.out.println(city.getWeather().getWeatherDescirption());
        switch (city.getWeather().getWeatherDescirption().trim()) {
            case "clear sky" -> url = "src/main/resources/Images/weather/01d.png";
            case "few clouds" -> url = "src/main/resources/Images/weather/02d.png";
            case "scattered clouds" -> url = "src/main/resources/Images/weather/03d.png";
            case "broken clouds" -> url = "src/main/resources/Images/weather/04d.png";
            case "shower rain" -> url = "src/main/resources/Images/weather/09d.png";
            case "rain" -> url = "src/main/resources/Images/weather/10d.png";
            case "thunderstorm" -> url = "src/main/resources/Images/weather/11d.png";
            case "snow" -> url = "src/main/resources/Images/weather/13d.png";
            case "mist" -> url = "src/main/resources/Images/weather/50d.png";
            default -> url = "src/main/resources/Images/weather/na.png";
        }

        return new File(url);
    }
}
