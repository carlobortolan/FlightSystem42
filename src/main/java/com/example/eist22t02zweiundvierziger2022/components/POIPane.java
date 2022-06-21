package com.example.eist22t02zweiundvierziger2022.components;

import com.almasb.fxgl.entity.component.Component;
import com.example.eist22t02zweiundvierziger2022.controllers.Adder;
import javafx.concurrent.Worker;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import model.Flight;
import model.POI;
import netscape.javascript.JSObject;

import java.util.List;

public class POIPane extends GridPane {

    public POIPane(POI poi, List<POI> favorites, VBox vBox, WebView browser) {
//        this.setPrefSize(200, 50);
        this.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        TextField p = new TextField();
        p.setEditable(false);
        p.setText(poi.getName() + ", \r\n" + poi.getAddress());
        p.setOnMouseEntered(e ->  {
            p.setCursor(Cursor.HAND);
        });
        p.setOnMouseClicked(e -> {
            //TODO:NEW LINK
            browser.getEngine().load("https://www.google.com/maps/search/" + poi.getName() + poi.getAddress());
            vBox.getChildren().addAll(browser);
            //TODO:NEW LINK
        });

        this.add(p, 0, 0);


        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            removeButton.setDisable(true);
            favorites.remove(poi);
        });
        this.add(removeButton, 0, 1);
    }
}
