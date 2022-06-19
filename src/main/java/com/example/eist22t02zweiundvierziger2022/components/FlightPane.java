package com.example.eist22t02zweiundvierziger2022.components;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import com.example.eist22t02zweiundvierziger2022.controllers.DetailController;
import com.example.eist22t02zweiundvierziger2022.controllers.FlightController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Flight;
import model.FlightObject;

import java.io.IOException;

public class FlightPane extends GridPane {
    private Flight flight;
    private Button addButton;
    private Button detailButton;
    private boolean added;
    private FlightController controller;
    private boolean inSearchView;

    public FlightPane(Flight flight, FlightController flightController, boolean inSearchView) {
        super();
        this.inSearchView = inSearchView;

        this.controller = flightController;
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.flight = flight;

        String dur = "Duration: ";

        dur +=  flight.getDuration().replace("PT", "").replace("H", "h, ").replace("M", "min").replace("P", "").replace("DT", "d, ").trim();

        if(dur.endsWith(",")) {
            dur = dur.substring(0, dur.lastIndexOf(","));
        }

        TextField d = new TextField(dur);
        d.setEditable(false);
        this.add(d, 0, 0);

        int i = 1;
        for (FlightObject flightObject : this.flight.getFlight()) {
            this.add(new FlightObjectPane(flightObject, i), 0, i++);
        }

        this.added = controller.getMyFlights().contains(flight);

        if (added || !inSearchView) {
            this.added = true;
            this.addButton = new Button("Remove from my flights");
        } else {
            this.added = false;
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
        this.detailButton.setOnAction(event -> {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("detail-view.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                String s = flight.getFrom().getIATA() + "-" + flight.getTo().getIATA() + ": [";
                for (FlightObject f : flight.getFlight()) {
                    s = s.concat(f.getTrackingNumber() + ", ");
                }
                s = s.substring(0, s.lastIndexOf(", "));
                s+= "] DETAILS";
                stage.setTitle(s);
                stage.setScene(new Scene(root, 700, 485));
                DetailController detailController = fxmlLoader.getController();
                detailController.initialize(flight);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
