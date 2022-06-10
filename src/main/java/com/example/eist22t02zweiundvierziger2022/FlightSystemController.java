package com.example.eist22t02zweiundvierziger2022;

import client.view.FlightView;
import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class FlightSystemController {

    private Canvas flightView;
    private Canvas searchView;
    private Canvas serviceView;


    public void start(Stage primaryStage) throws Exception {

    }

    public void switchScene(String s) {
        switch (s) {
            case "flightView" -> this.flightView.setVisible(true);
            case "searchView" -> this.searchView.setVisible(true);
            case "serviceView" -> this.serviceView.setVisible(true);
        }
    }

    public static void main(String[] args) {
//        FlightSystemApplication.startApp(args);
    }
}