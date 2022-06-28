/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.eist22t02zweiundvierziger2022.components;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import com.example.eist22t02zweiundvierziger2022.controllers.DetailController;
import com.example.eist22t02zweiundvierziger2022.controllers.FlightController;
import com.example.eist22t02zweiundvierziger2022.controllers.StatusController;
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
import model.POI;

import java.io.IOException;
import java.util.List;

public class FlightPane extends GridPane {
    private Flight flight;
    private Button addButton;
    private Button detailButton;

    private Button statusButton;
    private boolean added;
    private FlightController controller;
    private boolean inSearchView;

    public FlightPane(Flight flight, FlightController flightController, boolean inSearchView, List<POI> favorites) {
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

        this.statusButton = new Button("View status");
        this.statusButton.setOnAction(event -> {
            Parent root;
            try {
                FXMLLoader fxloader = new FXMLLoader(FlightSystemApplication.class.getResource("status-view.fxml"));
                root = fxloader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 320, 400));
                String s = flight.getFrom().getIATA() + "-" + flight.getTo().getIATA() + ": [";
                for (FlightObject f : flight.getFlight()) {
                    s = s.concat(f.getTrackingNumber() + ", ");
                }
                s = s.substring(0, s.lastIndexOf(", "));
                s+= "] STATUS";
                stage.setTitle(s);
                StatusController statusController = fxloader.getController();
                statusController.initialize(flight);
                stage.show();
            }catch(IOException e){
                e.printStackTrace();
            }
        });

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
                FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class
                        .getResource("message.fxml"));
                try {
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 420, 120));
                    stage.setTitle("Notification");
                    StatusController statusController = fxmlLoader.getController();
                    statusController.initialize(flight);
                    if(statusController.getStatus().getText().contains("Delayed")){
                        statusController.getField().setText("This Flight is delayed. Check Flight Status for more Info.");
                        statusController.getField().setMaxWidth(400);
                        stage.show();
                    }
                    if(statusController.getStatus().getText().contains("Cancelled")){
                        statusController.getField().setText("This Flight is cancelled. Check Flight List for alternatives");
                        statusController.getField().setMaxWidth(400);
                        stage.show();
                    }
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
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
                detailController.initialize(flight, favorites);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        FlowPane buttonPane = new FlowPane();

        buttonPane.getChildren().addAll(detailButton);
        addButton.setAlignment(Pos.CENTER_RIGHT);
        buttonPane.getChildren().addAll(addButton);
        buttonPane.getChildren().addAll(statusButton);

        this.add(buttonPane, 0, i);
    }
}
