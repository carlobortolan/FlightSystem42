package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.FlightObject;


public class FlightObjectPane extends GridPane {

    public FlightObjectPane(FlightObject flightObject, int j) {
        super();
        this.setGridLinesVisible(true);
        if(flightObject.getTrackingNumber().contains("LH")) {
//            this.add(new ImageView("lufthansaLogo.png"), 0, 0, 3, 3);
        }
        else{
//            this.add(new ImageView("Flight_Icon.png"),0,0,3,3);
        }

        //From
        this.add(new TextField("From: " +flightObject.getFrom().getIATA()), 3,1);
        this.add(new TextField(flightObject.getFrom().getCityName()),4,1);
        this.add(new TextField("Terminal: " + flightObject.getDetails().getTerminalFrom()),5,1);
        this.add(new TextField("Departure time: " + flightObject.getDetails().getTimeOfDeparture()),6,1);

        //To
        this.add(new TextField("To: " + flightObject.getTo().getIATA()), 3,2);
        this.add(new TextField(flightObject.getTo().getCityName()),4,2);
        this.add(new TextField("Terminal: " + flightObject.getDetails().getTerminalTo()),5,2);
        this.add(new TextField("Arrival time: " + flightObject.getDetails().getEta()),6,2);

        //FlightNumber
        this.add(new TextField("Flight_Nr: "+ flightObject.getTrackingNumber()),0,7);

    }
}