/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DetailController {

    @FXML
    private Button showMapButton;


    @FXML
    private Text temperature;

    @FXML
    private Text feelslike;

    @FXML
    private Text maxTemp;

    @FXML
    private Text minTemp;

    @FXML
    private Text windDirection;

    @FXML
    private Text windSpeed;

    @FXML
    private Text cityName;

    @FXML
    private Text cityLocation;

    @FXML
    private Text weatherDescription;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private ImageView cityImage;
    @FXML
    private ToggleButton weatherAtStart;
    @FXML
    private ToggleButton weatherAtDestination;

    @FXML
    private Canvas canvas;

    public void initialize(Flight flight, List<POI> favorites) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(new Color(0.96, 0.96, 0.96, 1));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        try {
            this.setBackground(flight.getTo());
            this.initializeWeatherData(flight.getTo());
            this.weatherIcon.setImage(new Image(initializeWeatherImage(flight.getTo()).toURI().toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("CITY " + flight.getTo().getIATA() + " / " + flight.getTo().getCityName() + " not found!");
        }

        this.showMapButton.setOnAction(e -> {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("map-view.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                String s = flight.getFrom().getIATA() + "-" + flight.getTo().getIATA() + ": [";
                for (FlightObject f : flight.getFlight()) {
                    s = s.concat(f.getTrackingNumber() + ", ");
                }
                s = s.substring(0, s.lastIndexOf(", "));
                s += "] MAP";
                stage.setTitle(s);
                stage.setScene(new Scene(root));
                MapController mapController = fxmlLoader.getController();
                mapController.initialize(flight, favorites);
                stage.show();
                ((Node) (e.getSource())).getScene().getWindow().hide();

            } catch (IOException ee) {
                ee.printStackTrace();
            }
        });

        this.weatherAtDestination.setSelected(true);
        this.weatherAtDestination.setOnAction(e -> {
            this.weatherAtDestination.setSelected(true);
            this.weatherAtStart.setSelected(false);
            try {
                this.initializeWeatherData(flight.getTo());
                this.weatherIcon.setImage(new Image(initializeWeatherImage(flight.getTo()).toURI().toString()));
                this.setBackground(flight.getTo());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        this.weatherAtStart.setOnAction(e -> {
            this.weatherAtDestination.setSelected(false);
            this.weatherAtStart.setSelected(true);
            try {
                this.initializeWeatherData(flight.getFrom());
                this.weatherIcon.setImage(new Image(initializeWeatherImage(flight.getFrom()).toURI().toString()));
                this.setBackground(flight.getFrom());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

    }

    public void initializeWeatherData(City city) throws IOException {
        city.setWeather(new Weather().getWeather(city.getCityName()));
        Weather weatherStart = city.getWeather();
        this.weatherDescription.setText(weatherStart.getWeatherDescirption());
        this.temperature.setText(weatherStart.getCurrentTemp());
        this.feelslike.setText(weatherStart.getTempfeelslike());
        this.minTemp.setText(weatherStart.getTemp_min());
        this.maxTemp.setText(weatherStart.getTemp_max());
        this.windDirection.setText(weatherStart.getWindDirection());
        this.windSpeed.setText(weatherStart.getWindSpeed());
    }

    public File initializeWeatherImage(City city) throws IOException {
        String url;
        System.out.println(city.getWeather().getWeatherDescirption());
        switch (city.getWeather().getWeatherDescirption().trim().toLowerCase()) {
            case "clear sky" -> url = "src/main/resources/Images/weather/01d.png";
            case "few clouds" -> url = "src/main/resources/Images/weather/02d.png";
            case "scattered clouds" -> url = "src/main/resources/Images/weather/03d.png";
            case "broken clouds", "overcast clouds" -> url = "src/main/resources/Images/weather/04d.png";
            case "shower rain", "light intensity shower rain", "heavy intensity shower rain", "ragged shower rain", "light intensity drizzle", "drizzle", "heavy intensity drizzle", "light intensity drizzle rain", "drizzle rain", "heavy intensity drizzle rain", "shower rain and drizzle", "heavy shower rain and drizzle", "shower drizzle" ->
                    url = "src/main/resources/Images/weather/09d.png";
            case "rain", "light rain", "moderate rain", "heavy intensity rain", "very heavy rain", "extreme rain" ->
                    url = "src/main/resources/Images/weather/10d.png";
            case "thunderstorm", "thunderstorm with light rain", "thunderstorm with rain", "thunderstorm with heavy rain", "light thunderstorm", "heavy thunderstorm", "ragged thunderstorm", "thunderstorm with light drizzle", "thunderstorm with drizzle", "thunderstorm with heavy drizzle" ->
                    url = "src/main/resources/Images/weather/11d.png";
            case "snow", "light snow", "heavy snow", "sleet", "light shower sleet", "shower sleet", "light rain and snow", "rain and snow", "light shower snow", "shower snow", "Heavy shower snow", "freezing rain" ->
                    url = "src/main/resources/Images/weather/13d.png";
            case "mist", "smoke", "haze", "sand/ dust whirls", "fog", "sand", "dust", "volcanic ash", "squalls", "tornado" ->
                    url = "src/main/resources/Images/weather/50d.png";
            default -> url = "src/main/resources/Images/weather/na.png";
        }
        return new File(url);
    }

    public void setBackground(City city) {
        POI poi = new POI(city);
        String base = "https://maps.googleapis.com/maps/api/place/photo";
        String maxwidth = "3840";
        String maxHeight = "2160";

        List<String> references = poi.getPhoto_reference();
        Image used = null;
        String key = "AIzaSyCFHuvSLicFOEbrNAMgRkOL0HPbVKNLqhU";
        int search = 0;
        if (references.size() > 3) {
            search = 3;
        }
        search = references.size();

        for (int i = 0; i < search; i++) {
            String reference = references.get(i);
            String url = base + "?maxwidth=" + maxwidth + "&maxHeight=" + maxHeight + "&photo_reference=" + reference + "&key=" + key;
            Image background = new Image(url);
            if (used == null) {
                used = background;
            }
            if (used.getWidth() > 737 && used.getWidth() > used.getHeight()) {
                break;
            } else if (background.getWidth() > used.getWidth()) {
                used = background;
            }
        }
        this.cityImage.setImage(used);
        this.cityName.setText(city.getCityName());
        this.cityLocation.setText(city.getCountry());
    }
}
