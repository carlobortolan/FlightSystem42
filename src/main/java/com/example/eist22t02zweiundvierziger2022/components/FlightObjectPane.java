package com.example.eist22t02zweiundvierziger2022.components;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.FlightObject;
import org.w3c.dom.Text;

import java.io.File;
import java.net.URL;


public class FlightObjectPane extends GridPane {

    public FlightObjectPane(FlightObject flightObject, int j) {
        super();

        GridPane details = new GridPane();
        if(flightObject.getTrackingNumber().contains("LH")) {
            File file = new File("src/main/resources/Images/Lufthansa_Icon.png");
            Image image = new Image(file.toURI().toString());
            ImageView view = new ImageView(image);
            view.setFitWidth(90);
            view.setFitHeight(80);
            this.add(view,0,2);

        }
        else if(flightObject.getTrackingNumber().contains("LX")){
            File file = new File("src/main/resources/Images/Swiss_Icon.png");
            Image image = new Image(file.toURI().toString());
            ImageView view = new ImageView(image);
            view.setFitWidth(90);
            view.setFitHeight(80);
            this.add(view,0,2);
        }
        else if(flightObject.getTrackingNumber().contains("AUA")) {
            File file = new File("src/main/resources/Images/Austrian_Icon.png");
            Image image = new Image(file.toURI().toString());
            ImageView view = new ImageView(image);
            view.setFitWidth(90);
            view.setFitHeight(80);
            this.add(view, 0, 2);
        }
        else if(flightObject.getTrackingNumber().contains("EW")){
            File file = new File("src/main/resources/Images/Eurowings_Icon.png");
            Image image = new Image(file.toURI().toString());
            ImageView view = new ImageView(image);
            view.setFitWidth(90);
            view.setFitHeight(80);
            this.add(view,0,2);
        }
        else if(flightObject.getTrackingNumber().contains("4Y")){
            File file = new File("src/main/resources/Images/Eurowings_Icon.png");
            Image image = new Image(file.toURI().toString());
            ImageView view = new ImageView(image);
            view.setFitWidth(90);
            view.setFitHeight(80);
            this.add(view,0,2);
        }
        else{
            File file = new File("src/main/resources/Images/Sonstige_Icon.png");
            Image image = new Image(file.toURI().toString());
            ImageView view = new ImageView(image);
            view.setFitWidth(90);
            view.setFitHeight(80);
            this.add(view,0,2);
        }



        //From
        TextField from = new TextField("From: " +flightObject.getFrom().getIATA());
        from.setEditable(false);
        details.add(from, 0,1);

        TextField cityNameFrom = new TextField(flightObject.getFrom().getCityName());
        cityNameFrom.setEditable(false);
        details.add(cityNameFrom,1,1);

        TextField terminalFrom = new TextField("Terminal: " + flightObject.getDetails().getTerminalFrom());
        terminalFrom.setEditable(false);
        details.add(terminalFrom,2,1);

        TextField departure = new TextField("Departure time: " +flightObject.getDetails().getTimeOfDeparture());
        departure.setMinWidth(220);
        departure.setEditable(false);
        details.add(departure,3,1,2,1);

        //To

        TextField to = new TextField("To: " + flightObject.getTo().getIATA());
        to.setEditable(false);
        details.add(to, 0,2);

        TextField cityTo = new TextField(flightObject.getTo().getCityName());
        cityTo.setEditable(false);
        details.add(cityTo,1,2);

        TextField terminalTo = new TextField("Terminal: " + flightObject.getDetails().getTerminalTo());
        terminalTo.setEditable(false);
        details.add(terminalTo,2,2);

        TextField arrival = new TextField("Arrival time: " + flightObject.getDetails().getEta());
        arrival.setMinWidth(220);
        arrival.setEditable(false);
        details.add(arrival,3,2,2,1);


        //FlightNumber
        TextField flightNr = new TextField("Flight_Nr: "+ flightObject.getTrackingNumber());
        flightNr.setMaxWidth(220);
        flightNr.setAlignment(Pos.BASELINE_RIGHT);
        flightNr.setEditable(false);
        details.setAlignment(Pos.CENTER);
        details.add(flightNr,3,0);
        this.add(details,1,2);
    }
}