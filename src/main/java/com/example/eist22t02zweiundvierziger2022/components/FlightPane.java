package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Flight;
import model.FlightObject;

public class FlightPane extends GridPane {
    private Flight flight;
    public FlightPane(Flight flight, int i) {
        super();
        this.flight = flight;
//        this.setLayoutX(75);
//        this.setLayoutY(75*i + 75);
        int j =0;
        for(FlightObject flightObject : this.flight.getFlight()) {
            this.getChildren().addAll(new FlightObjectPane(flightObject, j++));
//            getItems().addAll(new FlightObjectPane(flightObject, j++));
        }
        System.out.println("ADDED NEW FLIGHT");
    }
}
