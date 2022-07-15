/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz, Luca Mathias
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

package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lufthansa.FlightStatus;
import model.Flight;
import model.FlightObject;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

public class StatusController {

    private Flight flight;


    @FXML
    private Pane infoPane;
    @FXML
    private TextField delay;
    @FXML
    private TextField etd;
    @FXML
    private TextField eta;
    @FXML
    private TextField field;

    @FXML
    private Text iataFrom;

    @FXML
    private Text iataTo;

    @FXML
    private Text cityNameFrom;

    @FXML
    private Text cityNameTo;

    @FXML
    private Text orDE;

    @FXML
    private Text exDE;

    @FXML
    private Text acDE;

    @FXML
    private Text teDE;

    @FXML
    private Text orAr;

    @FXML
    private Text exAr;

    @FXML
    private Text acAr;

    @FXML
    private Text teAr;

    @FXML
    private Text flightNr;

    @FXML
    private Text delayTime;

    @FXML
    private Text flightStatus;

    @FXML
    private Text delayinTime;

    @FXML
    private Button openFlightMap;

    @FXML
    private WebView webView;


    public void initialize(Flight flight) {
        this.cityNameFrom.setText(flight.getFrom().getCityName());
        this.cityNameTo.setText(flight.getTo().getCityName());
        this.flight = flight;
        String flightNr = "";
        LinkedList<FlightObject> flightObjects = flight.getFlight();
        for (int i = 0; i < flight.getFlight().size(); i++) {
            FlightObject current = flightObjects.get(i);
            if (flightNr.equals("")) {
                flightNr = current.getTrackingNumber();
            } else {
                flightNr += " | " + current.getTrackingNumber();
            }
        }
        this.flightNr.setText(flightNr);
        try {
            getStatusInformation();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        if (flightStatus.getText().contains("Delayed")) {
            if (infoPane != null) {
                infoPane.setVisible(true);
            }
        }
        openFlightMap.setOnAction(e -> {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("flight-view.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                String s = flight.getFrom().getIATA() + "-" + flight.getTo().getIATA() + ": [";
                for (FlightObject f : flight.getFlight()) {
                    s = s.concat(f.getTrackingNumber() + ", ");
                }
                s = s.substring(0, s.lastIndexOf(", "));
                s += "] MAP";
                stage.setTitle(s);
                stage.setScene(new Scene(root));
                FlightMapController flightMapController = fxmlLoader.getController();
                String flightNrlong = flight.getFlight().get(0).getTrackingNumber();
                String flightNrshort = flightNrlong.substring(0, flightNrlong.indexOf("-"));
                flightMapController.initialize(flightNrshort);
                stage.show();
                ((Node) (e.getSource())).getScene().getWindow().hide();

            } catch (IOException ee) {
                ee.printStackTrace();
            }
        });




    }

    private void getStatusInformation() throws IOException, InterruptedException {
        FlightStatus flightStatus = new FlightStatus(flight.getFlight().getFirst());
        flightStatus.getStatus();
        this.iataFrom.setText(flightStatus.getDeAirportCode());

        this.orDE.setText(flightStatus.getDeorDateTime());
        this.exDE.setText(flightStatus.getDeestDateTime());
        this.acDE.setText(flightStatus.getDeActualTime());
        String terminalDe = "Gate: " + flightStatus.getDeTerminalGate() + " | Terminal: " + flightStatus.getDeTerminalName();
        this.teDE.setText(terminalDe);

        FlightStatus flightStatusAr = new FlightStatus(flight.getFlight().getLast());
        flightStatusAr.getStatus();
        this.iataTo.setText(flightStatusAr.getArAirportCode());
        this.orAr.setText(flightStatusAr.getArScheduledTime());
        this.exAr.setText(flightStatusAr.getArEstimatedTime());
        this.acAr.setText(flightStatusAr.getArActualTime());
        String terminalAr = "Gate: " + flightStatusAr.getArTerminalGate() + " | Terminal: " + flightStatusAr.getArTerminalName();
        this.teAr.setText(terminalAr);
        this.flightStatus.setText(flightStatusAr.getArtimeStatus());

        if(!acDE.equals("-") && flight.getFlight().size() == 1 ){
            openFlightMap.setVisible(true);
        }
        else{
            openFlightMap.setVisible(false);
        }

        this.delayinTime.setVisible(false);
        this.delayTime.setVisible(false);
        if (this.flightStatus.getText().contains("Delayed")) {
            this.delayinTime.setVisible(true);
            this.delayTime.setVisible(true);
            LocalDateTime est = flight.getFlight().getFirst().getDetails().getTimeOfDeparture();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
            LocalDateTime act = LocalDateTime.parse(flightStatus.getDeActualTime()
                    .replaceAll("T", "-")
                    .replaceAll(":", "-"), formatter);
            long minutes = ChronoUnit.MINUTES.between(est, act);
            long hours = 0;
            while (minutes >= 60) {
                hours++;
                minutes -= 60;
            }
            this.delayTime.setText(hours + " hours and " + minutes + " minutes");
        }
    }



    public Text getStatus() {
        return this.flightStatus;
    }


    public TextField getField() {
        return this.field;
    }


}
