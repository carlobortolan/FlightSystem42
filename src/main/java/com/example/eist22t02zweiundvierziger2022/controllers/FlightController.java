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
import com.example.eist22t02zweiundvierziger2022.components.AutoCompleteComboBoxListener;
import com.example.eist22t02zweiundvierziger2022.components.FlightPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lufthansa.FlightParser;
import lufthansa.IATA;
import model.Flight;
import model.FlightCollection;
import model.POI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FlightController {

    private String from;
    private String to;
    private String date = LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().indexOf("T"));

    private boolean directFlightsOnly = false;

    private FlightCollection myFlights = new FlightCollection();
    @FXML
    private ScrollPane myFlightsPane = new ScrollPane();

    @FXML
    private ScrollPane resultPane = new ScrollPane();

    private FlightCollection resultCollection = new FlightCollection();

    private List<POI> favorites = new LinkedList<>();
    @FXML
    private ComboBox<String> fromField = new ComboBox<>();
    @FXML
    private ComboBox<String> toField = new ComboBox<>();
    @FXML
    private DatePicker datePicker;

    @FXML
    private TabPane main;

    @FXML
    private GridPane favoritesPane;

    @FXML
    private GridPane entertainmentPane;

    @FXML
    private GridPane servicePane;

    @FXML
    private Tab MyLocationsTab;

    public FlightCollection getMyFlights() {
        return myFlights;
    }

    public void updateMyFlights() {
        System.out.println("UPDATING");
        GridPane pane = new GridPane();
        int i = 0;
        if (this.getMyFlights() != null) {

            for (Flight flight : this.getMyFlights().getFlights()) {
                pane.add(new FlightPane(flight, this, false, favorites), 0, i++);

                Separator separator = new Separator();
                separator.setOpacity(0);
                pane.add(separator, 0, i++);

                separator = new Separator();
                separator.setOpacity(0);
                pane.add(separator, 0, i++);

                separator = new Separator();
                separator.setOpacity(0);
                pane.add(separator, 0, i++);

                separator = new Separator();
                separator.setOpacity(0);
                pane.add(separator, 0, i++);

                separator = new Separator();
                separator.setOpacity(0);
                pane.add(separator, 0, i++);
            }
            pane.setAlignment(Pos.CENTER);

            this.myFlightsPane.setContent(pane);
        }
    }

    public void addFlight(Flight flight) {
        this.getMyFlights().addFlight(flight);
        System.out.println("added flight = " + flight);
    }

    public boolean removeFlight(Flight flight) {
        System.out.println("removed flight = " + flight);
        return this.getMyFlights().removeFlight(flight);
    }

    public void initialize() throws IOException {
        IATA cities = new IATA();
        cities.finddestinations();
        List<IATA> list = cities.getDestinations();
        ObservableList<String> citynames = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder city_Airport = new StringBuilder();
            city_Airport.append(list.get(i).getCityName())
                    .append(" | ")
                    .append(list.get(i).getAirportName())
                    .append(" | ")
                    .append(list.get(i).getIataCode());
            citynames.add(String.valueOf(city_Airport));
        }
        this.fromField.setItems(citynames);
        this.toField.setItems(citynames);
        new AutoCompleteComboBoxListener<>(fromField);
        new AutoCompleteComboBoxListener<>(toField);
        this.fromField.setPromptText("From");
        this.toField.setOnKeyTyped(e -> this.setFrom());
        this.toField.setPromptText("To");
        this.toField.setOnKeyTyped(e -> this.setTo());

        try {
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("entertainmentTab-view.fxml"));
            root = fxmlLoader.load();
            EntertainmentController entertainmentController = fxmlLoader.getController();
            for (Node node : this.entertainmentPane.getChildren()) {
                node.setVisible(false);
            }
            entertainmentController.initialize();
            this.entertainmentPane.add(root, 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("service-view.fxml"));
            root = fxmlLoader.load();
           ServiceController serviceController = fxmlLoader.getController();
            for (Node node : this.servicePane.getChildren()) {
                node.setVisible(false);
            }
            serviceController.initialize(this);
            this.servicePane.add(root, 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        main.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if (newValue == MyLocationsTab) {
                    updateFavorites();
                }

            }
        });
    }


    public static String readIATA(String input) {
        String split[] = input.split("\\|");
        String iata = input.trim();
        if (split.length >= 3) {
            iata = split[2].trim();
        }
        return iata.toUpperCase();
    }

    @FXML
    private void updateFavorites() {
        try {
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("favoritesTab-view.fxml"));
            root = fxmlLoader.load();

            FavoritesController favoritesController = fxmlLoader.getController();
            favoritesController.initialize(favorites);
            for (Node node : this.favoritesPane.getChildren()) {
                node.setVisible(false);
            }

            this.favoritesPane.add(root, 0, 0);
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void searchForFlights() throws InterruptedException {
        this.setDate();
        if (from != null && to != null && date != null) {
            System.out.println("SEARCHING FOR:");
            System.out.println("from = " + FlightController.readIATA(from));
            System.out.println("to = " + FlightController.readIATA(to));
            System.out.println("date = " + date);
            System.out.println("direct = " + directFlightsOnly);
            try {
                if (from == null || to == null) {
                    throw new IOException("NOT FOUND!");
                } else {
                    this.resultCollection = new FlightCollection(FlightParser.fetchFlights(new FlightParser().searchFlight(FlightController.readIATA(from), FlightController.readIATA(to), date, directFlightsOnly ? 1 : 0)));
                }
            } catch (IOException e) {
                System.out.println("NOT FOUND!");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" FLIGHT NOT FOUND");
                alert.setHeaderText("No flight matched your criteria!");
                alert.setContentText("Searched from: " + from + ", to: " + to + "\r\nMake sure to fill in all information and try again.");
                alert.showAndWait();
            }
            GridPane pane = new GridPane();
            int i = 0;
            if (this.resultCollection != null && this.resultCollection.getFlights() != null && !this.resultCollection.getFlights().isEmpty()) {
                System.out.println("FOUND");
                Separator separator = new Separator();
                separator.setOpacity(0);
                for (Flight flight : this.resultCollection.getFlights()) {
                    FlightPane flightPane = new FlightPane(flight, this, true, favorites);
                    flightPane.setAlignment(Pos.CENTER);
                    pane.add(flightPane, 5, i++);
                    System.out.println("ADDED NEW FLIGHT = " + flight.getFlight().get(0).getTrackingNumber() + " DURATION = " + flight.getDuration());

                    separator = new Separator();
                    separator.setOpacity(0);
                    pane.add(separator, 5, i++);

                    separator = new Separator();
                    separator.setOpacity(0);
                    pane.add(separator, 5, i++);

                    separator = new Separator();
                    separator.setOpacity(0);
                    pane.add(separator, 5, i++);

                    separator = new Separator();
                    separator.setOpacity(0);
                    pane.add(separator, 5, i++);

                    separator = new Separator();
                    separator.setOpacity(0);
                    pane.add(separator, 5, i++);
                }
                pane.setAlignment(Pos.CENTER);

                this.resultPane.setContent(pane);
            }
        } else {
            System.out.println("NOT FOUND!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" FLIGHT NOT FOUND");
            alert.setHeaderText("No flight matched your criteria!");
            alert.setContentText("Searched from: " + from + ", to: " + to + "\r\nMake sure to fill in all information and try again.");

            alert.showAndWait();
        }
    }

    @FXML
    private void switchDestinations() {
        String tmp = this.from;
        this.from = this.to;
        this.to = tmp;
        this.fromField.setValue(from);
        this.toField.setValue(to);

    }

    @FXML
    private String getFrom() {
        return from;
    }

    @FXML
    private void setFrom() {
        this.from = fromField.getValue();
        System.out.println("from = " + from);
    }

    @FXML
    private String getTo() {
        return to;
    }

    @FXML
    private void setTo() {
        this.to = toField.getValue();
        System.out.println("to = " + to);
    }

    @FXML
    private String getDate() {
        return date;
    }

    @FXML
    private void setDate() {
        if (this.datePicker.getValue() != null) {
            String dayOfMonth = "" + datePicker.getValue().getDayOfMonth();
            if (datePicker.getValue().getDayOfMonth() <= 9) {
                dayOfMonth = "" + 0 + dayOfMonth;
            }
            String month = "" + datePicker.getValue().getMonthValue();
            if (datePicker.getValue().getMonth().getValue() <= 9) {
                month = "" + 0 + month;
            }
            String year = "" + datePicker.getValue().getYear();
            this.date = "" + year + "-" + month + "-" + dayOfMonth;
            System.out.println("date = " + date);
        } else {
            this.date = LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().indexOf("T"));
        }
    }

    @FXML
    private void changeIsDirectFlightsOnly() {
        if (this.isDirectFlightsOnly()) {
            this.setDirectFlightsOnly(false);
        } else {
            this.setDirectFlightsOnly(true);
        }
        System.out.println("DirectFlightsOnly = " + directFlightsOnly);
    }

    @FXML
    private boolean isDirectFlightsOnly() {
        return directFlightsOnly;
    }

    @FXML
    private void setDirectFlightsOnly(boolean directFlightsOnly) {
        this.directFlightsOnly = directFlightsOnly;
    }

}
