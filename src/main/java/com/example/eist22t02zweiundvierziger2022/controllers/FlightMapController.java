package com.example.eist22t02zweiundvierziger2022.controllers;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class FlightMapController {

    @FXML
    private WebView webView;

    public void initialize(String code){
    this.webView.getEngine().getLoadWorker()
                .stateProperty()
                .addListener((obs, old, neww) -> {
        if (neww == Worker.State.SUCCEEDED) {
            JSObject bridge = (JSObject) this.webView.getEngine().executeScript("window");
            bridge.setMember("adder", new Adder());
        }
    });
    String search = "D"+code;
       this.webView.getEngine().load("https://de.flightaware.com/live/flight/map/" + search);

    }
}
