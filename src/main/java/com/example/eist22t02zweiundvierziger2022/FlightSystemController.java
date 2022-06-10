package com.example.eist22t02zweiundvierziger2022;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class FlightSystemController {

    @FXML
    private AnchorPane root;

    @FXML
    private Button search;

    @FXML
    private void loadFoundflightsview() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("found_flights.fxml")));
    }



}