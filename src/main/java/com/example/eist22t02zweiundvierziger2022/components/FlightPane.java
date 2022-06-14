package com.example.eist22t02zweiundvierziger2022.components;

import com.example.eist22t02zweiundvierziger2022.controllers.FlightController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Flight;
import model.FlightObject;

public class FlightPane extends GridPane {
    private Flight flight;
    private Button addButton;
    private Button detailButton;
    private boolean added = false;
    private FlightController controller;
    private boolean inSearchView;

    public FlightPane(Flight flight, FlightController flightController, boolean inSearchView) {
        super();
        this.inSearchView = inSearchView;

        this.controller = flightController;
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.flight = flight;

        TextField d = new TextField  ("Duration: " + flight.getDuration());
        d.setEditable(false);
        this.add(d, 0, 0);

        int i = 1;
        for (FlightObject flightObject : this.flight.getFlight()) {
            this.add(new FlightObjectPane(flightObject, i), 0, i++);
        }

        this.added = controller.getMyFlights().contains(flight);

        if(added || !inSearchView) {
            this.added = true;
            this.addButton = new Button("Remove from my flights");
        } else {
            this.added  = false;
            this.addButton = new Button("Add to my flights");
        }

        this.addButton.setOnAction(e -> {
            if (this.added) {
                this.added = false;
                this.addButton.setText("Add to my flights");
                this.controller.removeFlight(flight);
                this.controller.updateMyFlights();
            } else {
                this.added = true;
                this.addButton.setText("Remove from my flights");
                this.controller.addFlight(flight);
                this.controller.updateMyFlights();
            }
        });


        this.detailButton = new Button("View details");



        FlowPane buttonPane = new FlowPane();

        buttonPane.getChildren().addAll(detailButton);

//        for (int j = 0; j < 55; j++) {
//            Separator separator = new Separator();
//            separator.setOpacity(0);
//            buttonPane.getChildren().add(separator);
//        }
        addButton.setAlignment(Pos.CENTER_RIGHT);
        buttonPane.getChildren().addAll(addButton);

        this.add(buttonPane, 0, i);

//        System.out.println("ADDED NEW FLIGHT");
    }
}
