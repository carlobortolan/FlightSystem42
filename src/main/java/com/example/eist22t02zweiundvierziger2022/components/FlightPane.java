package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Flight;
import model.FlightObject;

public class FlightPane extends GridPane {
    private Flight flight;
    private Button addButton;
    private Button removeButton;
    private Button detailButton;

    public FlightPane(Flight flight) {
        super();
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.flight = flight;

//        this.add(new TextField("HIER STEHT DIE DURATION"), 0, 0);
        this.add(new TextField("Duration: " + flight.getDuration()), 0, 0);

        int i = 1;
        for (FlightObject flightObject : this.flight.getFlight()) {
            this.add(new FlightObjectPane(flightObject, i), 0, i++);
        }

        this.addButton = new Button("Add to my Flights");
        this.removeButton = new Button("Remove from my Flights");
        this.detailButton = new Button("View details");

        this.add(detailButton, 0, i,1 , 1);
        for(int j = 2; j < 50; j++) {
            Separator separator = new Separator();
            separator.setOpacity(0);
            this.add(separator, j, i);
        }
        this.add(addButton, 50, i, 1, 1);

        System.out.println("ADDED NEW FLIGHT");
    }
}
//
//        int i = 0;
//        if(this.flight.getFlight() != null) {
//            for (FlightObject flightObject : this.flight.getFlight()) {
//                pane.add(new FlightObjectPane(flightObject, i), 0, i++);
//            }
//        }
