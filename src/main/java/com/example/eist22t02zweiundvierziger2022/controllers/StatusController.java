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

package com.example.eist22t02zweiundvierziger2022.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateTimeStringConverter;
import lufthansa.FlightStatus;
import model.Flight;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Delayed;

public class StatusController {

    private Flight flight;

    @FXML
    private TextField status;
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


    public void initialize(Flight flight) {
        this.flight = flight;
        try {
            getStatusInformation();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        if (status.getText().contains("Delayed")) {
            if(infoPane != null) {
                infoPane.setVisible(true);
            }
        }
    }

    private void getStatusInformation() throws IOException, InterruptedException {
        FlightStatus flightStatus = new FlightStatus(flight.getFlight().getFirst());
        flightStatus.parseStatus(flightStatus.getStatus());
        if (this.status != null) {
            this.status.setText(flightStatus.getDetimeStatus());
            LocalDateTime est = flight.getFlight().getFirst().getDetails().getTimeOfDeparture();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
            LocalDateTime act = LocalDateTime.parse(flightStatus.getDeActualTime()
                    .replaceAll("T", "-")
                    .replaceAll(":", "-"), formatter);
            long minutes = ChronoUnit.MINUTES.between(est, act);
            long hours = ChronoUnit.HOURS.between(est, act);
            this.delay.setText(hours + " hours and " + minutes + " minutes");
            this.etd.setText(flightStatus.getDeActualTime().replace("T", ", "));

            this.eta.setText(flight.getFlight().getLast().getDetails()
                    .getEta()
                    .plusHours(hours)
                    .plusMinutes(minutes)
                    .toString()
                    .replace("T", ", "));
        } else {
            this.status = new TextField();
            this.status.setText(flightStatus.getStatus());
        }
    }

    public TextField getStatus() {
        return this.status;
    }

    public TextField getField() {
        return this.field;
    }


}
