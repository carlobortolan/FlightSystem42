package com.example.eist22t02zweiundvierziger2022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FlightSystemApplication extends Application {

    @Override
        public void start(Stage stage) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-Test.fxml")));
            Scene scene = new Scene(root, 1100, 700);
            stage.setTitle("FlightSystem42");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }