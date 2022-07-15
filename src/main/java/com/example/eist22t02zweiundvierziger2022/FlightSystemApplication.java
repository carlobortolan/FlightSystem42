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

package com.example.eist22t02zweiundvierziger2022;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.City;
import server.ServerApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class FlightSystemApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            City.finddestination();
            FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("application-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            stage.setTitle("FlightSystem42");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());
        } catch (Exception e) {
            FlightSystemApplication.errorAlert(e);
        }
    }

    private static void errorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR :(");
        alert.setHeaderText("FlightSystem42 failed to initialize. Contact our support at 0180-667-22-55 \r\n(or view https://support.microsoft.com/de-de/contactus)");
        alert.setContentText(e.getMessage());


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label errorLabel = new Label("The exception stacktrace was:");
        TextArea errorArea = new TextArea(exceptionText);


        GridPane.setVgrow(errorArea, Priority.ALWAYS);
        GridPane.setHgrow(errorArea, Priority.ALWAYS);

        errorArea.setEditable(false);
        errorArea.setWrapText(true);
        errorArea.setMaxWidth(Double.MAX_VALUE);
        errorArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(errorLabel, 0, 0);
        expContent.add(errorArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        //STARTS SERVER
        ServerApplication.main(args);
        //STARTS APPLICATION
        launch();
    }
}