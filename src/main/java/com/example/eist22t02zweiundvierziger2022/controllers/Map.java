package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;

public class Map extends VBox {

    public Map() {

        //create an embeded web browser
        WebView browser = new WebView();

        browser.getEngine().getLoadWorker()
                .stateProperty()
                .addListener((obs, old, neww) ->
                {
                    if (neww == Worker.State.SUCCEEDED) {
                        // Let JavaScript make calls to adder object,
                        //so we need to inject an [Adder] object into the JS code
                        JSObject bridge = (JSObject) browser.getEngine()
                                .executeScript("window");
                        bridge.setMember("adder", new Adder());
                    }
                });
        //load the html page

        File file = new File("src/main/resources/com/example/eist22t02zweiundvierziger2022/index.html");
        browser.getEngine().load(file.toURI().toString());

        new VBox(browser);

//        Scene scene = new Scene(box);
//        stage.setScene(scene);
//        stage.show();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}

