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

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import lufthansa.FlightStatus;
import model.City;
import model.Flight;
import model.FlightCollection;
import model.FlightObject;
import server.Client;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

    @FXML
    private AnchorPane firstPageSurvey;

    @FXML
    private AnchorPane secondPageSurvey;

    @FXML
    private Text setName;

    @FXML
    private Text startingAirport;

    @FXML
    private Text ArrivalAirport;

    @FXML
    private TextField enterName;

    @FXML
    private TextField enterFlightNr;


    @FXML
    private ImageView nextPage1;
    @FXML
    private ImageView nextPage2;

    private int rate1;


    private final String[] drinkNames = new String[]{"Water", "Apple juice", "Coke", "Hot chocolate", "Coffee", "Tee", "Beer", "Martini", "Champagne"};

    private FlightController flightController;

    public void initialize(FlightController flightController) {
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

        this.flightController = flightController;
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
                survey = "DATE:[" + date + "] FLIGHT:{" + flightNumber.trim() + "}, CONTENT:{ " +
                        "Questions: \n" + " How was your experience before departure? Rated with: " + rate1 + "\n" +
                        "How would you rate our entertaiment sytem?" +
                        "}";
            } else {
                survey = "DATE:[" + date + "] FLIGHT:{" + flightNumber.trim() + "}, CONTENT:{ " +
                        "Questions: \n" + " How was your experience before departure? Rated with: " + rate1 + "\n" +
                        "How would you rate our entertaiment sytem?" +
                        "}" +
                        "NAME:{" + name.trim() + "}";
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
        if (!this.drinkPane.isVisible()) {
            this.firstPageSurvey.setVisible(false);
            this.secondPageSurvey.setVisible(false);
            this.drinkPane.setVisible(true);
            this.switchButton.setText("Survey");
            File file = new File("src/main/resources/Images/Service/drinksBackground.jpg");
            Image image = new Image(file.toURI().toString());
            this.background.setImage(image);
        } else {
            this.drinkPane.setVisible(false);
            loadSurvey();
            this.switchButton.setText("Drinks");
            File file = new File("src/main/resources/Images/Service/surveyBackground.png");
            Image image = new Image(file.toURI().toString());
            this.background.setImage(image);
        }
    }

    public void loadSurvey() {
        firstPageSurvey.setVisible(true);
        secondPageSurvey.setVisible(false);
    }

    public void setNextPage1() throws IOException, InterruptedException {
        FlightCollection collection = flightController.getMyFlights();
        String flightNr = enterFlightNr.getText();
        List<Flight> flights = collection.getFlights();
        FlightObject selectedFlight = new FlightObject(null, null, null, null);
        Boolean isinList = false;
        for (int i = 0; i < flights.size(); i++) {
            Flight current = flights.get(i);
            List<FlightObject> flightObjects = current.getFlight();
            for (int j = 0; j < flightObjects.size(); j++) {
                FlightObject currentObject = flightObjects.get(j);
                if (currentObject.getTrackingNumber().equals(flightNr)) {
                    isinList = true;
                    selectedFlight = currentObject;
                    break;
                }
            }
        }
        if (isinList) {
            firstPageSurvey.setVisible(false);
            secondPageSurvey.setVisible(true);
            setName.setText(enterName.getText());
            startingAirport.setText(selectedFlight.getFrom().getCityName());
            ArrivalAirport.setText(selectedFlight.getTo().getCityName());
            initializeStars();
        } else {
            System.out.println("Flight not in your List");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Flight not in favorit List!");
            alert.setHeaderText("Flight you want to rate is not in your favorites list.");
            alert.setContentText("FlightNr: " + flightNr + "is not found in your favorite List. Therefore we assume, that you didn't take this flight!");
            alert.showAndWait();
            firstPageSurvey.setVisible(true);
            secondPageSurvey.setVisible(false);

        }
    }


    public void initializeStars() {
        star11.setOnMouseEntered(e -> {
            star11.setCursor(Cursor.HAND);
        });
        star11.setOnMouseClicked(e -> {
            setStar1(1);
        });
        star12.setOnMouseEntered(e -> {
            star12.setCursor(Cursor.HAND);
        });
        star12.setOnMouseClicked(e -> {
            setStar2(1);
        });

        star13.setOnMouseEntered(e -> {
            star13.setCursor(Cursor.HAND);
        });
        star13.setOnMouseClicked(e -> {
            setStar3(1);
        });
        star14.setOnMouseEntered(e -> {
            star14.setCursor(Cursor.HAND);
        });
        star14.setOnMouseClicked(e -> {
            setStar4(1);
        });
        star15.setOnMouseEntered(e -> {
            star15.setCursor(Cursor.HAND);
        });
        star15.setOnMouseClicked(e -> {
            setStar5(1);
        });
    }



    @FXML
    public void setStar1(int gruppe) {
        File file = new File("src/main/resources/Images/Survey/goldenStar.png");
        Image goldenStar = new Image(file.toURI().toString());
        File file2 = new File("src/main/resources/Images/Survey/greyStar.png");
        Image grayStar = new Image(file2.toURI().toString());
        switch (gruppe) {
            case 1 -> {
                this.star11.setImage(goldenStar);
                this.star12.setImage(grayStar);
                this.star13.setImage(grayStar);
                this.star14.setImage(grayStar);
                this.star15.setImage(grayStar);
                this.rate1 = 1;
            }
        }
    }

    @FXML
    public void setStar2(int gruppe) {
        File file = new File("src/main/resources/Images/Survey/goldenStar.png");
        Image goldenStar = new Image(file.toURI().toString());
        File file2 = new File("src/main/resources/Images/Survey/greyStar.png");
        Image grayStar = new Image(file2.toURI().toString());
        switch (gruppe) {
            case 1 -> {
                this.star11.setImage(goldenStar);
                this.star12.setImage(goldenStar);
                this.star13.setImage(grayStar);
                this.star14.setImage(grayStar);
                this.star15.setImage(grayStar);
                this.rate1 = 2;
            }
        }

    }

    @FXML
    public void setStar3(int gruppe) {
        File file = new File("src/main/resources/Images/Survey/goldenStar.png");
        Image goldenStar = new Image(file.toURI().toString());
        File file2 = new File("src/main/resources/Images/Survey/greyStar.png");
        Image grayStar = new Image(file2.toURI().toString());
        switch (gruppe) {
            case 1 -> {
                this.star11.setImage(goldenStar);
                this.star12.setImage(goldenStar);
                this.star13.setImage(goldenStar);
                this.star14.setImage(grayStar);
                this.star15.setImage(grayStar);
                this.rate1 = 3;
            }
        }
    }

    @FXML
    public void setStar4(int gruppe) {
        File file = new File("src/main/resources/Images/Survey/goldenStar.png");
        Image goldenStar = new Image(file.toURI().toString());
        File file2 = new File("src/main/resources/Images/Survey/greyStar.png");
        Image grayStar = new Image(file2.toURI().toString());
        switch (gruppe) {
            case 1 -> {
                this.star11.setImage(goldenStar);
                this.star12.setImage(goldenStar);
                this.star13.setImage(goldenStar);
                this.star14.setImage(goldenStar);
                this.star15.setImage(grayStar);
                this.rate1 = 4;
            }
        }

    }

    @FXML
    public void setStar5(int gruppe) {
        File file = new File("src/main/resources/Images/Survey/goldenStar.png");
        Image goldenStar = new Image(file.toURI().toString());
        File file2 = new File("src/main/resources/Images/Survey/greyStar.png");
        Image grayStar = new Image(file2.toURI().toString());
        switch (gruppe) {
            case 1 -> {
                this.star11.setImage(goldenStar);
                this.star12.setImage(goldenStar);
                this.star13.setImage(goldenStar);
                this.star14.setImage(goldenStar);
                this.star15.setImage(goldenStar);
                this.rate1 = 5;
            }
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


    //Images Stars:

    @FXML
    private ImageView star11;

    @FXML
    private ImageView star12;

    @FXML
    private ImageView star13;

    @FXML
    private ImageView star14;

    @FXML
    private ImageView star15;

}


