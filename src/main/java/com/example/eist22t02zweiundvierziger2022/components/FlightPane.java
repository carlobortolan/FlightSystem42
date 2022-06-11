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
    private GridPane gridPane;
    public FlightPane(Flight flight) {
        super();
        this.flight = flight;
        this.gridPane = new GridPane();

        int j =0;
        for(FlightObject flightObject : this.flight.getFlight()) {
//            this.getChildren().addAll(new FlightObjectPane(flightObject, j));
            this.add(new FlightObjectPane(flightObject, j), 0, j++);
        }
        System.out.println("ADDED NEW FLIGHT");
        //
//        int i = 0;
//        if(this.flight.getFlight() != null) {
//            for (FlightObject flightObject : this.flight.getFlight()) {
//                pane.add(new FlightObjectPane(flightObject, i), 0, i++);
//            }
//        }

    }
}
