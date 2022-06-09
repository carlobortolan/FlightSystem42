package client.view;

import common.model.FlightCollection;
//import javafx.application.Platform;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.control.Alert;
//import javafx.scene.image.Image;
//import javafx.scene.input.*;

public class FlightView {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 700;
    private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public static Dimension2D getPreferredSize() {
        return DEFAULT_SIZE;
    }

    private FlightCollection flightCollection;

    public FlightView(FlightCollection flightCollection) {
        this.flightCollection = flightCollection;
        setupFlightView();
        setupImage();
    }

    private void setupFlightView() {

    }

    private void setupImage() {
//        getGraphicsContext2D().drawImage(getImage("background.png"), 0, 0, getWidth(), getHeight());
    }
}
