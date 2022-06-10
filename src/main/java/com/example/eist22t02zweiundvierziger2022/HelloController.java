package com.example.eist22t02zweiundvierziger2022;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void doSomeThing() {
        System.out.println("actionEvent = ");
    }
}