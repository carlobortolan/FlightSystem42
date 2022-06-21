package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import com.example.eist22t02zweiundvierziger2022.components.AutoCompleteComboBoxListener;
import com.example.eist22t02zweiundvierziger2022.components.FlightPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import lufthansa.FlightParser;
import lufthansa.IATA;
import model.Flight;
import model.FlightCollection;
import model.POI;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FlightController {

    private String from;
    private String to;
    private String date = LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().indexOf("T"));

    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView flightInstruction;

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
    private Button StartVideo;
    @FXML
    private Button PauseVideo;

    @FXML
    private GridPane favoritesPane;

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
//
//            if (this.favorites.isEmpty()) {
//                System.out.println("EMPTY");
//            } else {

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
    private void startVideo() {
        this.StartVideo.setDisable(true);
        this.PauseVideo.setDisable(false);
        File linktoVideo = new File("src/main/resources/Videos/safety_video.mp4");
        Media video = new Media(linktoVideo.toURI().toString());
        mediaPlayer = new MediaPlayer(video);
        flightInstruction.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    private void pauseVideo() {
        this.PauseVideo.setDisable(true);
        this.StartVideo.setDisable(false);
        mediaPlayer.pause();
    }

    @FXML
    private void restartVideo() {
        mediaPlayer.stop();
        this.PauseVideo.setDisable(true);
        this.StartVideo.setDisable(false);
    }

    @FXML
    private void setDirectFlightsOnly(boolean directFlightsOnly) {
        this.directFlightsOnly = directFlightsOnly;
    }

}
