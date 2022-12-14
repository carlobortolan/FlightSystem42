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

import com.example.eist22t02zweiundvierziger2022.components.POIPane;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
            pane.add(new POIPane(favorites.get(i), favorites, vBox, browser, this), 0, j++);

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
