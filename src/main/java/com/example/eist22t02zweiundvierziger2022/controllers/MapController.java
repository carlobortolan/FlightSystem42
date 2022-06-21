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
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.City;
import model.Flight;
import model.FlightObject;
import model.POI;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.List;

public class MapController {


    @FXML
    private Button showStartButton;
    @FXML
    private Button showDestinationButton;
    @FXML
    private Button showAttractionsButton;
    @FXML
    private Button showRestaurantsButton;
    @FXML
    private Button showHotelsButton;
    @FXML
    private Button saveToFavoritesButton;
    @FXML
    private Button showFavoritesButton;

    @FXML
    private WebView webView;

    public void initialize(Flight flight, List<POI> favorites) {

        City from = flight.getFrom();
        City to = flight.getTo();
        webView.getEngine().getLoadWorker()
                .stateProperty()
                .addListener((obs, old, neww) -> {
                    if (neww == Worker.State.SUCCEEDED) {
                        JSObject bridge = (JSObject) webView.getEngine().executeScript("window");
                        bridge.setMember("adder", new Adder());
                    }
                });

        webView.getEngine().load("https://www.google.com/maps/dir/" + from.getCityName() + ",+" + from.getCountry() + "/" + to.getCityName() + ",+" + to.getCountry());

        this.saveToFavoritesButton.setDisable(true);

        this.showStartButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(true);
            webView.getEngine().load("https://www.google.com/maps/place/" + from.getCityName() + ",+" + from.getCountry());

        });
        this.showDestinationButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(true);
            webView.getEngine().load("https://www.google.com/maps/place/" + to.getCityName() + ",+" + to.getCountry());
        });
        this.showAttractionsButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(false);
            webView.getEngine().load("https://www.google.com/maps/search/" + to.getCityName() + ",+" + to.getCountry() + "+attractions");

        });
        this.showRestaurantsButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(false);
            webView.getEngine().load("https://www.google.com/maps/search/Resaurants+" + to.getCityName() + ",+" + to.getCountry());

        });
        this.showHotelsButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(false);
            webView.getEngine().load("https://www.google.com/maps/search/Hotels+" + to.getCityName() + ",+" + to.getCountry());

        });
        this.saveToFavoritesButton.setOnAction(e -> {

            POI p = new POI(webView.getEngine().getLocation());

            for (POI poi : favorites) {
                if (poi.getName().equals(p.getName())) {
                    return;
                }
            }
            favorites.add(p);
        });
        this.showFavoritesButton.setOnAction(e -> {
            Parent root;
            try {

                if (favorites.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" NO FAVORITES FOUND");
                    alert.setHeaderText("Your favorite list is empty!");
                    alert.setContentText("Make sure to add your favorite places in the map-window and try again.");
                    alert.showAndWait();
                    return;
                }

                FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("favorites-view.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                String s = flight.getFrom().getIATA() + "-" + flight.getTo().getIATA() + ": [";
                for (FlightObject f : flight.getFlight()) {
                    s = s.concat(f.getTrackingNumber() + ", ");
                }
                s = s.substring(0, s.lastIndexOf(", "));
                s += "] FAVORITES";
                stage.setTitle(s);
                stage.setScene(new Scene(root));

                FavoritesController favoritesController = fxmlLoader.getController();
                favoritesController.initialize(favorites);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        System.out.println(from.getCityName() + " - " + to.getCityName());
    }
}
