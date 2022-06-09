package client.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import model.FlightCollection;

import java.net.URL;
import java.util.HashMap;


public class FlightView extends Canvas {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 700;
    private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    private HashMap<String, Image> imageCache;

    public static Dimension2D getPreferredSize() {
        return DEFAULT_SIZE;
    }

    private FlightCollection flightCollection;

    public FlightView(FlightCollection flightCollection) {
        this.flightCollection = flightCollection;
        setupImageCache();
        setupFlightView();
        setupImage();
    }

    private void setupFlightView() {
        Dimension2D size = getPreferredSize();
        widthProperty().set(size.getWidth());
        heightProperty().set(size.getHeight());
    }

    private void setupImage() {
//        getGraphicsContext2D().drawImage(getImage("background.png"), 0, 0, getWidth(), getHeight());
        getGraphicsContext2D().drawImage(getImage("background.png"), 0, 0, getWidth(), getHeight());
    }

    private void setupImageCache() {
        this.imageCache = new HashMap<>();
    }

    private Image getImage(String carImageFilePath) {
        return this.imageCache.computeIfAbsent(carImageFilePath, this::createImage);
    }
    private Image createImage(String carImageFilePath) {
//		System.out.println("carImageFilePath = " + carImageFilePath);

        URL carImageUrl = getClass().getClassLoader().getResource(carImageFilePath);
        if (carImageUrl == null) {
            throw new IllegalArgumentException(
                    "Please ensure that your resources folder contains the appropriate files for this exercise.");
        }
        return new Image(carImageUrl.toExternalForm());
    }
}
