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

package com.example.eist22t02zweiundvierziger2022.components;

import com.example.eist22t02zweiundvierziger2022.controllers.FavoritesController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import model.POI;

import java.util.List;

public class POIPane extends GridPane {



    public POIPane(POI poi, List<POI> favorites, VBox vBox, WebView browser, FavoritesController controller) {
        super();
        String base = "https://maps.googleapis.com/maps/api/place/photo";
        String maxwidth = "400";
        String maxHeight = "200";
        String reference = poi.getPhoto_reference();
        String key = "AIzaSyCFHuvSLicFOEbrNAMgRkOL0HPbVKNLqhU";
        String url = base + "?maxwidth=" + maxwidth + "&maxHeight=" + maxHeight + "&photo_reference=" + reference + "&key=" + key;
        ImageView view = new ImageView(new Image(url));
        view.setOnMouseEntered(e -> {
            view.setCursor(Cursor.HAND);
        });
        view.setOnMouseClicked(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/" + poi.getName() + poi.getAddress());
            vBox.getChildren().addAll(browser);
        });
        view.setFitWidth(90);
        view.setFitHeight(80);
        this.add(view, 0, 0);


        this.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        GridPane details = new GridPane();
        Text name = new Text();
        name.setText(poi.getName() + "\n");
        name.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        Text adresse = new Text();
        adresse.setText("Adresse: \n");
        adresse.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Text aadresse = new Text();
        aadresse.setText(poi.getAddress() + "\n");
        aadresse.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            removeButton.setDisable(true);
            favorites.remove(poi);
            controller.initialize(favorites);
        });

        TextFlow text = new TextFlow(name, adresse, aadresse, removeButton);


        text.setMinSize(150, 80);
        details.add(text, 0, 0);
        details.setAlignment(Pos.CENTER);
        details.setOnMouseEntered(e -> {
            details.setCursor(Cursor.HAND);
        });
        details.setOnMouseClicked(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/" + poi.getName() + poi.getAddress());
            vBox.getChildren().addAll(browser);
        });

        this.add(details, 1, 0);


//        this.add(removeButton, 1, 1, 1, 2);
        this.setMinSize(380, 75);

    }
}
