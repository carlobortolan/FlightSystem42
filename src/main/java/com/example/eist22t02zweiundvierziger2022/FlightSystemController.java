package com.example.eist22t02zweiundvierziger2022;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lufthansa.FlightParser;
import model.Flight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FlightSystemController {

    @FXML
    private AnchorPane flightView;

    private String from;
    private String to;
    private String date;
    private boolean directFlightsOnly = false;

    @FXML
    private Button search;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private DatePicker datePicker;

    @FXML
    private void loadFoundflightsview() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("found_flights.fxml")));
    }

    @FXML
    private void search() throws IOException, InterruptedException {
        
        if(from != null && to != null && date != null) {
        System.out.println("SEARCHING FOR:");
        System.out.println("from = " + from.toUpperCase());
        System.out.println("to = " + to.toUpperCase());
        System.out.println("date = " + date);
        System.out.println("direct = " + directFlightsOnly);

        ArrayList<Flight> flights = FlightParser.fetchFlights(new FlightParser().searchFlight(from.toUpperCase(), to.toUpperCase(), date, directFlightsOnly ? 1 : 0));
//        flightView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
    } else {
            System.out.println("SOMETHING WAS NULL!");
        }
    }

    @FXML
    private String getFrom() {
        return from;
    }

    @FXML
    private void setFrom() {
        this.from = fromField.getCharacters().toString();
        System.out.println("from = " + from);
    }

    @FXML
    private String getTo() {
        return to;
    }

    @FXML
    private void setTo() {
        this.to = toField.getCharacters().toString();
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