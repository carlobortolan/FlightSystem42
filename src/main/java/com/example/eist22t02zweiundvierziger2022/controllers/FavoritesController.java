package com.example.eist22t02zweiundvierziger2022.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.POI;

import java.util.List;

public class FavoritesController {
    @FXML
    private ScrollPane favoritesPane;

    public void initialize(List<POI> favorites) {
        GridPane pane = new GridPane();



        for (int i = 0; i < favorites.size(); i++) {
            pane.add(new TextField(favorites.get(i).getName() + favorites.get(i).getLink()), 1, i);
        }

        this.favoritesPane.setContent(pane);
    }
}
