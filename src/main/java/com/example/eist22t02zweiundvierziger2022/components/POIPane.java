package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import model.POI;

import java.util.List;

public class POIPane extends GridPane {

    public POIPane(POI poi, List<POI> favorites, VBox vBox, WebView browser) {
        super();
        GridPane details = new GridPane();

        String base = "https://maps.googleapis.com/maps/api/place/photo";
        String maxwidth = "400";
        String maxHeight = "200";
        String reference = poi.getPhoto_reference();
        String key = "AIzaSyCFHuvSLicFOEbrNAMgRkOL0HPbVKNLqhU";
        String url = base+"?maxwidth="+maxwidth+"&maxHeight="+maxHeight+"&photo_reference="+reference+"&key="+key;
        ImageView view = new ImageView(new Image(url));
        view.setOnMouseEntered(e ->  {
            view.setCursor(Cursor.HAND);
        });
        view.setOnMouseClicked(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/" + poi.getName()+ poi.getAddress() );
            vBox.getChildren().addAll(browser);
        });
        view.setFitWidth(90);
        view.setFitHeight(80);
        this.add(view, 0, 0);


        this.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        TextField p = new TextField();
        p.setEditable(false);
        p.setText(poi.getName());
        p.setOnMouseEntered(e ->  {
            p.setCursor(Cursor.HAND);
        });
        p.setOnMouseClicked(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/" + poi.getName()+ poi.getAddress() );
            vBox.getChildren().addAll(browser);
        });
        details.add(p, 1, 0);


        TextField p1 = new TextField();
        p1.setEditable(false);
        p1.setText(poi.getAddress());
        p1.setOnMouseEntered(e ->  {
            p1.setCursor(Cursor.HAND);
        });
        p1.setOnMouseClicked(e -> {
            browser.getEngine().load("https://www.google.com/maps/search/" + poi.getName() + poi.getAddress());
            vBox.getChildren().addAll(browser);
        });
        details.add(p1, 1, 1);


        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            removeButton.setDisable(true);
            favorites.remove(poi);
        });
        this.add(removeButton, 0, 1);
        this.setMinSize(200, 75);
        this.add(details,1,0);
    }
}
