package com.example.eist22t02zweiundvierziger2022.components;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.web.WebView;
import model.POI;

import java.util.List;

public class POIPane extends GridPane {

    public POIPane(POI poi, List<POI> favorites, VBox vBox, WebView browser) {
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
        aadresse.setText(poi.getAddress());
        aadresse.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        TextFlow text = new TextFlow(name, adresse, aadresse);


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

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            removeButton.setDisable(true);
            favorites.remove(poi);
        });
        this.add(removeButton, 1, 1);
        this.setMinSize(380, 75);

    }
}
