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
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import server.Client;

import java.io.File;
import java.time.LocalDateTime;

public class ServiceController {
    private final Client client = new Client();
    @FXML
    private TextArea surveyTextArea;
    @FXML
    private TextField surveyFlightNumberTextField;
    @FXML
    private TextField surveyNameTextField;

    @FXML
    private Button switchButton;
    @FXML
    private GridPane drinkPane;
    @FXML
    private GridPane surveyPane;
    @FXML
    private ImageView background;

    private final String[] drinkNames = new String[]{"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9"};


    public void initialize() {
        this.drinkPane.setVisible(false);
        File file = new File("src/main/resources/Images/Service/surveyBackground.png");
        Image image = new Image(file.toURI().toString());
        this.background.setImage(image);
        d1.setOnMouseEntered(e -> {
            d1.setCursor(Cursor.HAND);
        });
        d2.setOnMouseEntered(e -> {
            d2.setCursor(Cursor.HAND);
        });
        d3.setOnMouseEntered(e -> {
            d3.setCursor(Cursor.HAND);
        });
        d4.setOnMouseEntered(e -> {
            d4.setCursor(Cursor.HAND);
        });
        d5.setOnMouseEntered(e -> {
            d5.setCursor(Cursor.HAND);
        });
        d6.setOnMouseEntered(e -> {
            d6.setCursor(Cursor.HAND);
        });
        d7.setOnMouseEntered(e -> {
            d7.setCursor(Cursor.HAND);
        });
        d8.setOnMouseEntered(e -> {
            d8.setCursor(Cursor.HAND);
        });
        d9.setOnMouseEntered(e -> {
            d9.setCursor(Cursor.HAND);
        });

        d1.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d1.jpg").toURI().toString()));
        d2.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d2.jpg").toURI().toString()));
        d3.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d3.jpg").toURI().toString()));
        d4.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d4.jpg").toURI().toString()));
        d5.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d5.jpg").toURI().toString()));
        d6.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d6.jpg").toURI().toString()));
        d7.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d7.jpg").toURI().toString()));
        d8.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d8.jpg").toURI().toString()));
        d9.setImage(new Image(new File("src/main/resources/Images/Service/drinks/d9.jpg").toURI().toString()));
    }

    @FXML
    public void sendSurvey() {
        String content = surveyTextArea.getText();
        String flightNumber = surveyFlightNumberTextField.getText();
        String date = LocalDateTime.now().toString();
        String name = surveyNameTextField.getText();

        if (content == null || flightNumber == null || surveyTextArea.getText().isBlank() || surveyFlightNumberTextField.getText().isBlank()) {
            System.out.println("SURVEY WAS NULL");
            System.out.println("NOT SENT!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("SURVEY NOT READY");
            alert.setHeaderText("Information missing");
            alert.setContentText("Make sure to fill in all necessary information and try again.");
            alert.showAndWait();
        } else {
            String survey;
            if (name == null) {
                survey = "DATE:[" + date + "] FLIGHT:{" + flightNumber.trim() + "}, CONTENT:{" + content.trim() + "}";
            } else {
                survey = "DATE:[" + date + "] FLIGHT:{" + flightNumber.trim() + "}, CONTENT:{" + content.trim() + "}, NAME:{" + name.trim() + "}";
            }
            client.addSurvey(survey);
            this.surveyTextArea.clear();
            this.surveyNameTextField.clear();
            this.surveyFlightNumberTextField.clear();
            //gutschein
        }
    }


    @FXML
    public void switchPane() {
        if (this.surveyPane.isVisible()) {
            this.surveyPane.setVisible(false);
            this.drinkPane.setVisible(true);
            this.switchButton.setText("Survey");
            File file = new File("src/main/resources/Images/Service/drinksBackground.jpg");
            Image image = new Image(file.toURI().toString());
            this.background.setImage(image);
        } else {
            this.surveyPane.setVisible(true);
            this.drinkPane.setVisible(false);
            this.switchButton.setText("Drinks");
            File file = new File("src/main/resources/Images/Service/surveyBackground.png");
            Image image = new Image(file.toURI().toString());
            this.background.setImage(image);
        }
    }

    public void requestDrink1() {
        System.out.println("requested drink = " + drinkNames[0]);
        client.requestDrink(drinkNames[0]);
    }

    public void requestDrink2() {
        System.out.println("requested drink = " + drinkNames[1]);
        client.requestDrink(drinkNames[1]);
    }

    public void requestDrink3() {
        System.out.println("requested drink = " + drinkNames[2]);
        client.requestDrink(drinkNames[2]);
    }

    public void requestDrink4() {
        System.out.println("requested drink = " + drinkNames[3]);
        client.requestDrink(drinkNames[3]);
    }

    public void requestDrink5() {
        System.out.println("requested drink = " + drinkNames[4]);
        client.requestDrink(drinkNames[4]);
    }

    public void requestDrink6() {
        System.out.println("requested drink = " + drinkNames[5]);
        client.requestDrink(drinkNames[5]);
    }

    public void requestDrink7() {
        System.out.println("requested drink = " + drinkNames[6]);
        client.requestDrink(drinkNames[6]);
    }

    public void requestDrink8() {
        System.out.println("requested drink = " + drinkNames[7]);
        client.requestDrink(drinkNames[7]);
    }

    public void requestDrink9() {
        System.out.println("requested drink = " + drinkNames[8]);
        client.requestDrink(drinkNames[8]);
    }

    @FXML
    private ImageView d1;
    @FXML
    private ImageView d2;
    @FXML
    private ImageView d3;
    @FXML
    private ImageView d4;
    @FXML
    private ImageView d5;
    @FXML
    private ImageView d6;
    @FXML
    private ImageView d7;
    @FXML
    private ImageView d8;
    @FXML
    private ImageView d9;
}
