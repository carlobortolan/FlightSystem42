package com.example.eist22t02zweiundvierziger2022;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class FlightSystemController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button search;

    @FXML
    private ComboBox from;

    @FXML
    private void loadFoundflightsview() throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("found_flights.fxml")));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void autocomboBox(){
        this.from = new ComboBox<String>();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("A");
        list.add("AND");
        list.add("ANDR");
        list.add("ANDRE");
        list.add("B");
        from.setItems(list);
        new AutoCompleteComboBoxListener<>(from);


    }




}