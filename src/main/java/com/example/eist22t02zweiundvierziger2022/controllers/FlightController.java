package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.components.AutoCompleteComboBoxListener;
import com.example.eist22t02zweiundvierziger2022.components.FlightPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lufthansa.FlightParser;
import lufthansa.IATA;
import model.Flight;
import model.FlightCollection;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FlightController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button switchButton;
    private String from;
    private String to;
    private String date;
    private boolean directFlightsOnly = false;

    private FlightCollection myFlights = new FlightCollection();
    @FXML
    private ScrollPane myFlightsPane = new ScrollPane();

    @FXML
//    private FlightScrollPane resultPane = new FlightScrollPane();
    private ScrollPane resultPane = new ScrollPane();

    private FlightCollection resultCollection = new FlightCollection();
    @FXML
    private ComboBox<String> fromField = new ComboBox<>();
    @FXML
    private ComboBox<String> toField = new ComboBox<>();
    @FXML
    private DatePicker datePicker;

    public FlightCollection getMyFlights() {
        return myFlights;
    }

    public void updateMyFlights() {
        System.out.println("UPDATING");
        GridPane pane = new GridPane();
        int i = 0;
        if(this.getMyFlights() != null) {

            for (Flight flight : this.getMyFlights().getFlights()) {
                pane.add(new FlightPane(flight, this, false), 0, i++);

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
    }

    public static String readIATA(String input) {
        String split[] = input.split("\\|");
        String iata = input.trim();
        if(split.length >= 3) {
            iata = split[2].trim();
        }
        return iata;
    }


    @FXML
    private void loadFoundflightsview() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("detail-view.fxml")));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void searchForFlights() throws IOException, InterruptedException {

        if (from != null && to != null && date != null) {
            System.out.println("SEARCHING FOR:");
            System.out.println("from = " + FlightController.readIATA(from));
            System.out.println("to = " + FlightController.readIATA(to));
            System.out.println("date = " + date);
            System.out.println("direct = " + directFlightsOnly);

            this.resultCollection = new FlightCollection(FlightParser.fetchFlights(new FlightParser().searchFlight(FlightController.readIATA(from), FlightController.readIATA(to), date, directFlightsOnly ? 1 : 0)));

//            pane.setPrefSize(600, 150);
            GridPane pane = new GridPane();
            int i = 0;
            if(this.resultCollection != null) {

            for (Flight flight : this.resultCollection.getFlights()) {
                FlightPane flightPane = new FlightPane(flight, this, true);
                flightPane.setAlignment(Pos.CENTER);
                pane.add(flightPane, 0, i++);
                System.out.println("ADDED NEW FLIGHT = " + flight.getFlight().get(0).getTrackingNumber() + " DURATION = " + flight.getDuration());

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

            pane.setAlignment(Pos.CENTER);
            this.resultPane.setContent(pane);
            }

        } else {
            System.out.println("SOMETHING WAS NULL!");
        }
    }

    @FXML
    private void switchDestinations() {
        String tmp = this.from;
        this.from = this.to;
        this.to = tmp;
        this.fromField.setValue(from.toUpperCase());
        this.toField.setValue(to.toUpperCase());
    }

    @FXML
    private String getFrom() {
        return from;
    }

    @FXML
    private void setFrom() {
//        this.from = fromField.getCharacters().toString();
        this.from = fromField.getValue().toUpperCase();
        System.out.println("from = " + from);
    }

    @FXML
    private String getTo() {
        return to;
    }

    @FXML
    private void setTo() {
//        this.to = toField.getCharacters().toString();
        this.to = toField.getValue().toUpperCase();
        System.out.println("to = " + to);
    }

    @FXML
    private String getDate() {
        return date;
    }

    @FXML
    private void setDate() {
        String dayOfMonth =  "" + datePicker.getValue().getDayOfMonth();
        if(datePicker.getValue().getDayOfMonth() <= 9) {
            dayOfMonth = "" + 0 + dayOfMonth;
        }
        String month = "" + datePicker.getValue().getMonthValue();
        if(datePicker.getValue().getMonth().getValue() <= 9) {
            month = "" + 0 + month;
        }
        String year = "" + datePicker.getValue().getYear();
        this.date = "" + year + "-" + month + "-" + dayOfMonth;
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
