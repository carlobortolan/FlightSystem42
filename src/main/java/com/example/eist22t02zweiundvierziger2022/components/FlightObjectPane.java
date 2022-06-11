package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.FlightObject;

public class FlightObjectPane extends GridPane {
    private FlightObject flightObject;

    private final Button addButton;
    private final Button detailsButton;
    private final Button removeButton;
    public FlightObjectPane(FlightObject flightObject, int j) {
        super();
        GridPane gridPane = new GridPane();
//        gridPane.setPrefSize(600, 150);
        this.flightObject = flightObject;
        this.addButton =new Button("add to favorites");
        this.detailsButton =new Button("view details");
        this.removeButton =new Button("remove from favorites");
//        this.setHeight(150);
//        this.setWidth(600);
        this.setVisible(true);
        getChildren().addAll(new TextField("HALLO"), new Separator(), this.addButton, new Separator(), this.detailsButton,new Separator(), this.removeButton);
//        getItems().addAll(new TextField("HALLO"), new Separator(), this.addButton, new Separator(), this.detailsButton,new Separator(), this.removeButton);

        System.out.println("ADDED NEW FLIGHTOBJECT");
    }
}
