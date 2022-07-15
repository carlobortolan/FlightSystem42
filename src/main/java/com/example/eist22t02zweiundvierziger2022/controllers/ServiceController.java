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
import javafx.scene.control.*;
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
import java.util.Optional;

public class ServiceController {
    private final Client client = new Client();


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
    private AnchorPane thirdPageSurvey;

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
    private Text dearText;

    @FXML
    private TextArea passengerRespond;


    private int rate1;

    private int rate2;

    private int rate3;

    private int rate4;

    private int rate5;


    private final String[] drinkNames = new String[]{"Water", "Apple juice", "Coke", "Hot chocolate", "Coffee", "Tee", "Beer", "Martini", "Champagne"};

    private FlightController flightController;

    public void initialize(FlightController flightController) {
        this.firstPageSurvey.setVisible(true);
        this.secondPageSurvey.setVisible(false);
        this.thirdPageSurvey.setVisible(false);
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
    public void requestStewardess() {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("STEWARDESS REQUEST");
        dialog.setHeaderText("Do you want to request a stewardess?");
        dialog.setContentText("Please enter your request:");

        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()){
//            System.out.println("Your name: " + result.get());
//        }
        result.ifPresent(request -> client.requestStewardess(request));
    }

    @FXML
    public void sendSurvey() {
        String date = LocalDateTime.now().toString();
        String additionalRespond = "-";
        if (!passengerRespond.getText().isBlank()) {
            additionalRespond = passengerRespond.getText();
        }
        if (enterFlightNr.getText() == null) {
            System.out.println("SURVEY WAS NULL");
            System.out.println("NOT SENT!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("SURVEY NOT READY");
            alert.setHeaderText("Information missing");
            alert.setContentText("Make sure to fill in all necessary information and try again.");
            alert.showAndWait();
        } else {
            String survey;
            survey = "<tr>\n" +
                    "    <td>" + date + "</td>" +
                    "    <td>" + enterFlightNr.getText().trim() + "</td>" +
                    "    <td>" + rate1 + "</td>" +
                    "    <td>" + rate2 + "</td>" +
                    "    <td>" + rate3 + "</td>" +
                    "    <td>" + rate4 + "</td>" +
                    "    <td>" + rate5 + "</td>" +
                    "    <td>" + enterName.getText().trim() + "</td>" +
                    "    <td>" + additionalRespond + "</td>" +
                    "  </tr>";
            client.addSurvey(survey);
            loadgiftCard();
            enterName.clear();
            enterFlightNr.clear();
        }
    }


    @FXML
    public void switchPane() {
        if (!this.drinkPane.isVisible()) {
            this.firstPageSurvey.setVisible(false);
            this.secondPageSurvey.setVisible(false);
            this.thirdPageSurvey.setVisible(false);
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
        thirdPageSurvey.setVisible(false);
    }

    public void loadgiftCard() {
        nameGiftCard.setText(enterName.getText());
        this.firstPageSurvey.setVisible(false);
        this.secondPageSurvey.setVisible(false);
        this.thirdPageSurvey.setVisible(true);
    }

    public void setNextPage1() throws IOException, InterruptedException {
        FlightCollection collection = flightController.getMyFlights();
        String flightNr = enterFlightNr.getText();
        List<Flight> flights = collection.getFlights();
        FlightObject selectedFlight = new FlightObject(null, null, null, null);
        Boolean isInList = false;
        for (int i = 0; i < flights.size(); i++) {
            Flight current = flights.get(i);
            List<FlightObject> flightObjects = current.getFlight();
            for (int j = 0; j < flightObjects.size(); j++) {
                FlightObject currentObject = flightObjects.get(j);
                if (currentObject.getTrackingNumber().trim().equalsIgnoreCase(flightNr.trim())) {
                    isInList = true;
                    selectedFlight = currentObject;
                    break;
                }
            }
        }
        if (isInList) {
            firstPageSurvey.setVisible(false);
            secondPageSurvey.setVisible(true);

            if (enterName.getText() == null || enterName.getText().isBlank()) {
                setName.setText("passenger");
            } else {
                setName.setText(enterName.getText());
            }

            startingAirport.setText(selectedFlight.getFrom().getCityName());
            ArrivalAirport.setText(selectedFlight.getTo().getCityName());
            initializeStars();
        } else {
            System.out.println("Flight not in your List");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Flight not in favorites List!");
            alert.setHeaderText("Flight you want to rate is not in your favorites list.");
            alert.setContentText("FlightNr: " + flightNr + " is not found in your favorite List. Therefore we assume, that you didn't take this flight!");
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


        star21.setOnMouseEntered(e -> {
            star21.setCursor(Cursor.HAND);
        });
        star21.setOnMouseClicked(e -> {
            setStar1(2);
        });
        star22.setOnMouseEntered(e -> {
            star22.setCursor(Cursor.HAND);
        });
        star22.setOnMouseClicked(e -> {
            setStar2(2);
        });

        star23.setOnMouseEntered(e -> {
            star23.setCursor(Cursor.HAND);
        });
        star23.setOnMouseClicked(e -> {
            setStar3(2);
        });
        star24.setOnMouseEntered(e -> {
            star24.setCursor(Cursor.HAND);
        });
        star24.setOnMouseClicked(e -> {
            setStar4(2);
        });
        star25.setOnMouseEntered(e -> {
            star25.setCursor(Cursor.HAND);
        });
        star25.setOnMouseClicked(e -> {
            setStar5(2);
        });


        star31.setOnMouseEntered(e -> {
            star31.setCursor(Cursor.HAND);
        });
        star31.setOnMouseClicked(e -> {
            setStar1(3);
        });
        star32.setOnMouseEntered(e -> {
            star32.setCursor(Cursor.HAND);
        });
        star32.setOnMouseClicked(e -> {
            setStar2(3);
        });

        star33.setOnMouseEntered(e -> {
            star33.setCursor(Cursor.HAND);
        });
        star33.setOnMouseClicked(e -> {
            setStar3(3);
        });
        star34.setOnMouseEntered(e -> {
            star34.setCursor(Cursor.HAND);
        });
        star34.setOnMouseClicked(e -> {
            setStar4(3);
        });
        star35.setOnMouseEntered(e -> {
            star35.setCursor(Cursor.HAND);
        });
        star35.setOnMouseClicked(e -> {
            setStar5(3);
        });

        star41.setOnMouseEntered(e -> {
            star41.setCursor(Cursor.HAND);
        });
        star41.setOnMouseClicked(e -> {
            setStar1(4);
        });
        star42.setOnMouseEntered(e -> {
            star42.setCursor(Cursor.HAND);
        });
        star42.setOnMouseClicked(e -> {
            setStar2(4);
        });

        star43.setOnMouseEntered(e -> {
            star43.setCursor(Cursor.HAND);
        });
        star43.setOnMouseClicked(e -> {
            setStar3(4);
        });
        star44.setOnMouseEntered(e -> {
            star44.setCursor(Cursor.HAND);
        });
        star44.setOnMouseClicked(e -> {
            setStar4(4);
        });
        star45.setOnMouseEntered(e -> {
            star45.setCursor(Cursor.HAND);
        });
        star45.setOnMouseClicked(e -> {
            setStar5(4);
        });

        star51.setOnMouseEntered(e -> {
            star51.setCursor(Cursor.HAND);
        });
        star51.setOnMouseClicked(e -> {
            setStar1(5);
        });
        star52.setOnMouseEntered(e -> {
            star52.setCursor(Cursor.HAND);
        });
        star52.setOnMouseClicked(e -> {
            setStar2(5);
        });

        star53.setOnMouseEntered(e -> {
            star53.setCursor(Cursor.HAND);
        });
        star53.setOnMouseClicked(e -> {
            setStar3(5);
        });
        star54.setOnMouseEntered(e -> {
            star54.setCursor(Cursor.HAND);
        });
        star54.setOnMouseClicked(e -> {
            setStar4(5);
        });
        star55.setOnMouseEntered(e -> {
            star55.setCursor(Cursor.HAND);
        });
        star55.setOnMouseClicked(e -> {
            setStar5(5);
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
            case 2 -> {
                this.star21.setImage(goldenStar);
                this.star22.setImage(grayStar);
                this.star23.setImage(grayStar);
                this.star24.setImage(grayStar);
                this.star25.setImage(grayStar);
                this.rate2 = 1;
            }
            case 3 -> {
                this.star31.setImage(goldenStar);
                this.star32.setImage(grayStar);
                this.star33.setImage(grayStar);
                this.star34.setImage(grayStar);
                this.star35.setImage(grayStar);
                this.rate3 = 1;
            }

            case 4 -> {
                this.star41.setImage(goldenStar);
                this.star42.setImage(grayStar);
                this.star43.setImage(grayStar);
                this.star44.setImage(grayStar);
                this.star45.setImage(grayStar);
                this.rate4 = 1;
            }

            case 5 -> {
                this.star51.setImage(goldenStar);
                this.star52.setImage(grayStar);
                this.star53.setImage(grayStar);
                this.star54.setImage(grayStar);
                this.star55.setImage(grayStar);
                this.rate5 = 3;
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
            case 2 -> {
                this.star21.setImage(goldenStar);
                this.star22.setImage(goldenStar);
                this.star23.setImage(grayStar);
                this.star24.setImage(grayStar);
                this.star25.setImage(grayStar);
                this.rate2 = 2;
            }
            case 3 -> {
                this.star31.setImage(goldenStar);
                this.star32.setImage(goldenStar);
                this.star33.setImage(grayStar);
                this.star34.setImage(grayStar);
                this.star35.setImage(grayStar);
                this.rate3 = 2;
            }

            case 4 -> {
                this.star41.setImage(goldenStar);
                this.star42.setImage(goldenStar);
                this.star43.setImage(grayStar);
                this.star44.setImage(grayStar);
                this.star45.setImage(grayStar);
                this.rate4 = 2;
            }

            case 5 -> {
                this.star51.setImage(goldenStar);
                this.star52.setImage(goldenStar);
                this.star53.setImage(grayStar);
                this.star54.setImage(grayStar);
                this.star55.setImage(grayStar);
                this.rate5 = 2;
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
            case 2 -> {
                this.star21.setImage(goldenStar);
                this.star22.setImage(goldenStar);
                this.star23.setImage(goldenStar);
                this.star24.setImage(grayStar);
                this.star25.setImage(grayStar);
                this.rate2 = 3;
            }
            case 3 -> {
                this.star31.setImage(goldenStar);
                this.star32.setImage(goldenStar);
                this.star33.setImage(goldenStar);
                this.star34.setImage(grayStar);
                this.star35.setImage(grayStar);
                this.rate3 = 3;
            }

            case 4 -> {
                this.star41.setImage(goldenStar);
                this.star42.setImage(goldenStar);
                this.star43.setImage(goldenStar);
                this.star44.setImage(grayStar);
                this.star45.setImage(grayStar);
                this.rate4 = 3;
            }

            case 5 -> {
                this.star51.setImage(goldenStar);
                this.star52.setImage(goldenStar);
                this.star53.setImage(goldenStar);
                this.star54.setImage(grayStar);
                this.star55.setImage(grayStar);
                this.rate5 = 3;
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
            case 2 -> {
                this.star21.setImage(goldenStar);
                this.star22.setImage(goldenStar);
                this.star23.setImage(goldenStar);
                this.star24.setImage(goldenStar);
                this.star25.setImage(grayStar);
                this.rate2 = 4;
            }
            case 3 -> {
                this.star31.setImage(goldenStar);
                this.star32.setImage(goldenStar);
                this.star33.setImage(goldenStar);
                this.star34.setImage(goldenStar);
                this.star35.setImage(grayStar);
                this.rate3 = 4;
            }

            case 4 -> {
                this.star41.setImage(goldenStar);
                this.star42.setImage(goldenStar);
                this.star43.setImage(goldenStar);
                this.star44.setImage(goldenStar);
                this.star45.setImage(grayStar);
                this.rate4 = 4;
            }

            case 5 -> {
                this.star51.setImage(goldenStar);
                this.star52.setImage(goldenStar);
                this.star53.setImage(goldenStar);
                this.star54.setImage(goldenStar);
                this.star55.setImage(grayStar);
                this.rate5 = 4;
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
            case 2 -> {
                this.star21.setImage(goldenStar);
                this.star22.setImage(goldenStar);
                this.star23.setImage(goldenStar);
                this.star24.setImage(goldenStar);
                this.star25.setImage(goldenStar);
                this.rate2 = 5;
            }
            case 3 -> {
                this.star31.setImage(goldenStar);
                this.star32.setImage(goldenStar);
                this.star33.setImage(goldenStar);
                this.star34.setImage(goldenStar);
                this.star35.setImage(goldenStar);
                this.rate3 = 5;
            }

            case 4 -> {
                this.star41.setImage(goldenStar);
                this.star42.setImage(goldenStar);
                this.star43.setImage(goldenStar);
                this.star44.setImage(goldenStar);
                this.star45.setImage(goldenStar);
                this.rate4 = 5;
            }

            case 5 -> {
                this.star51.setImage(goldenStar);
                this.star52.setImage(goldenStar);
                this.star53.setImage(goldenStar);
                this.star54.setImage(goldenStar);
                this.star55.setImage(goldenStar);
                this.rate5 = 5;
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
    private Text nameGiftCard;

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


    @FXML
    private ImageView star21;

    @FXML
    private ImageView star22;

    @FXML
    private ImageView star23;

    @FXML
    private ImageView star24;

    @FXML
    private ImageView star25;


    @FXML
    private ImageView star31;

    @FXML
    private ImageView star32;

    @FXML
    private ImageView star33;

    @FXML
    private ImageView star34;

    @FXML
    private ImageView star35;


    @FXML
    private ImageView star41;

    @FXML
    private ImageView star42;

    @FXML
    private ImageView star43;

    @FXML
    private ImageView star44;

    @FXML
    private ImageView star45;


    @FXML
    private ImageView star51;

    @FXML
    private ImageView star52;

    @FXML
    private ImageView star53;

    @FXML
    private ImageView star54;

    @FXML
    private ImageView star55;


}


