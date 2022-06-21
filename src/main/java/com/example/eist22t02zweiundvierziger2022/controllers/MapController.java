package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.City;
import model.Flight;
import model.FlightObject;
import model.POI;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapController {
    @FXML
    private VBox vBox;
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

    //TODO: CONNECT CONTROLLER TO API
    public void initialize(Flight flight, List<POI> favorites) {

        WebView browser = new WebView();
        City from = flight.getFrom();
        City to = flight.getTo();
        browser.getEngine().getLoadWorker()
                .stateProperty()
                .addListener((obs, old, neww) -> {
                    if (neww == Worker.State.SUCCEEDED) {
                        JSObject bridge = (JSObject) browser.getEngine().executeScript("window");
                        bridge.setMember("adder", new Adder());
                    }
                });

        browser.getEngine().load("https://www.google.com/maps/dir/" + from.getCityName() + ",+" + from.getCountry() + "/" + to.getCityName() + ",+" + to.getCountry());
        vBox.getChildren().addAll(browser);
        this.saveToFavoritesButton.setDisable(true);

        this.showStartButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(true);
            browser.getEngine().load("https://www.google.com/maps/place/" + from.getCityName() + ",+" + from.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.showDestinationButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(true);
            browser.getEngine().load("https://www.google.com/maps/place/" + to.getCityName() + ",+" + to.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.showAttractionsButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(false);
            browser.getEngine().load("https://www.google.com/maps/search/" + to.getCityName() + ",+" + to.getCountry() + "+attractions");
            vBox.getChildren().setAll(browser);
        });
        this.showRestaurantsButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(true);
            browser.getEngine().load("https://www.google.com/maps/search/Resaurants+" + to.getCityName() + ",+" + to.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.showHotelsButton.setOnAction(e -> {
            this.saveToFavoritesButton.setDisable(true);
            browser.getEngine().load("https://www.google.com/maps/search/Hotels+" + to.getCityName() + ",+" + to.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.saveToFavoritesButton.setOnAction(e -> {
            for (POI poi : favorites) {
                if (poi.getName().equals("HIER STEHT DER LINK")) {
                    favorites.remove(poi);
                    return;
                }
            }
            favorites.add(new POI("HIER STEHT DER LINK"));

        });
        this.showFavoritesButton.setOnAction(e -> {
            for (POI poi : favorites) {
                System.out.println(poi.getName());
            }
            //TODO: FavoritesPane
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
