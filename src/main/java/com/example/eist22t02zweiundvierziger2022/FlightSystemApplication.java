package com.example.eist22t02zweiundvierziger2022;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lufthansa.IATA;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FlightSystemApplication extends Application {

    ComboBox<String> from = new ComboBox<>();
    ComboBox<String> to = new ComboBox<>();


    @Override
        public void start(Stage stage) throws IOException {
        initialize();
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-Test.fxml")));
        root.getChildren().add(to);
        root.getChildren().add(from);
            Scene scene = new Scene(root, 1920, 1080);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

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
            this.from.setItems(citynames);
            this.to.setItems(citynames);
            new AutoCompleteComboBoxListener<>(from);
            new AutoCompleteComboBoxListener<>(to);
            this.from.setLayoutX(325);
            this.from.setLayoutY(439);
            this.from.setPrefHeight(43);
            this.from.setPrefWidth(226);
            this.from.setPromptText("From");
            this.to.setLayoutX(641);
            this.to.setLayoutY(439);
            this.to.setPrefHeight(43);
            this.to.setPrefWidth(226);
            this.to.setPromptText("To");
        }
        public static String readIATA(String input){
        String split[] = input.split("\\|");
        String iata = split[2].trim();
        System.out.println(iata);
        return iata;
        }


        public static void main(String[] args) {
            launch();
        }
    }