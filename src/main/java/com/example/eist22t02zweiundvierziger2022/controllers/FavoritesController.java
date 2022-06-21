package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.components.POIPane;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import model.POI;
import netscape.javascript.JSObject;

import java.util.List;

public class FavoritesController {
    @FXML
    private ScrollPane favoritesPane = new ScrollPane();
    @FXML
    private VBox vBox;

    public void initialize(List<POI> favorites) {

        WebView browser = new WebView();
        browser.getEngine().getLoadWorker()
                .stateProperty()
                .addListener((obs, old, neww) -> {
                    if (neww == Worker.State.SUCCEEDED) {
                        JSObject bridge = (JSObject) browser.getEngine().executeScript("window");
                        bridge.setMember("adder", new Adder());
                    }
                });

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        System.out.println("SIZE = " + favorites.size());
        int j = 0;
        for (int i = 0; i < favorites.size(); i++) {
            pane.add(new POIPane(favorites.get(i), favorites, vBox, browser), 0, j++);

            Separator separator = new Separator();
            separator.setOpacity(0);
            pane.add(separator, 0, j++);

            separator = new Separator();
            separator.setOpacity(0);
            pane.add(separator, 0, j++);
        }

        this.favoritesPane.setContent(pane);
    }
}
