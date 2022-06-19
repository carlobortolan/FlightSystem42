package com.example.eist22t02zweiundvierziger2022.controllers;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import model.City;
import model.Flight;
import netscape.javascript.JSObject;

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

    //TODO: CONNECT CONTROLLER TO API
    public void initialize(Flight flight) {

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

        this.showStartButton.setOnAction(e -> {
            browser.getEngine().load("https://www.google.com/maps/place/" + from.getCityName() + ",+" + from.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.showDestinationButton.setOnAction(e -> {
            browser.getEngine().load("https://www.google.com/maps/place/" + to.getCityName() + ",+" + to.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.showAttractionsButton.setOnAction(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/" + to.getCityName() + ",+" + to.getCountry() + "+attractions");
            vBox.getChildren().setAll(browser);
        });
        this.showRestaurantsButton.setOnAction(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/Resaurants+" + to.getCityName() + ",+" + to.getCountry());
            vBox.getChildren().setAll(browser);
        });
        this.showHotelsButton.setOnAction(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/Hotels+" + to.getCityName() + ",+" + to.getCountry());
            vBox.getChildren().setAll(browser);
        });

        System.out.println(from.getCityName() + " - " + to.getCityName());
    }
}
