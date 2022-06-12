package com.example.eist22t02zweiundvierziger2022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.City;

import java.io.IOException;

public class FlightSystemApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        City.finddestination();
        FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("FlightSystem42");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}