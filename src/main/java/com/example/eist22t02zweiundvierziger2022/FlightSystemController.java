package com.example.eist22t02zweiundvierziger2022;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lufthansa.FlightParser;
import lufthansa.IATA;
import model.Flight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightSystemController {

    @FXML
    private AnchorPane rootPane;
    private String from;
    private String to;
    private String date;
    private boolean directFlightsOnly = false;

    @FXML
    private ComboBox<String> fromField = new ComboBox<>();
    @FXML
    private ComboBox<String> toField = new ComboBox<>();
    @FXML
    private DatePicker datePicker;


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
    }
    public static String readIATA(String input){
        String split[] = input.split("\\|");
        String iata = split[2].trim();
        return iata;
    }


    @FXML
    private void loadFoundflightsview() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("found_flights.fxml")));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void search() throws IOException, InterruptedException {

        if(from != null && to != null && date != null) {
            System.out.println("SEARCHING FOR:");
            System.out.println("from = " + FlightSystemController.readIATA(from));
            System.out.println("to = " + FlightSystemController.readIATA(to));
            System.out.println("date = " + date);
            System.out.println("direct = " + directFlightsOnly);

            ArrayList<Flight> flights = FlightParser.fetchFlights(new FlightParser().searchFlight(FlightSystemController.readIATA(from), FlightSystemController.readIATA(to), date, directFlightsOnly ? 1 : 0));
//        flightView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        } else {
            System.out.println("SOMETHING WAS NULL!");
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
//        this.from = fromField.getCharacters().toString();
        this.from = fromField.getValue();
        System.out.println("from = " + from);
    }

    @FXML
    private String getTo() {
        return to;
    }

    @FXML
    private void setTo() {
//        this.to = toField.getCharacters().toString();
        this.to = toField.getValue();
        System.out.println("to = " + to);
    }

    @FXML
    private String getDate() {
        return date;
    }

    @FXML
    private void setDate() {
        int dayOfMonth = datePicker.getValue().getDayOfMonth();
        int month = datePicker.getValue().getMonthValue();
        int year = datePicker.getValue().getYear();
        this.date = "" + dayOfMonth + "-" + month + "-" + year;
        System.out.println("date = " + date);
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
