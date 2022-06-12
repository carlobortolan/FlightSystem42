//package com.example.eist22t02zweiundvierziger2022.controllers;
//
//import com.example.eist22t02zweiundvierziger2022.components.FlightPane;
//import javafx.fxml.FXML;
//import javafx.geometry.Pos;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.Separator;
//import javafx.scene.layout.GridPane;
//import model.Flight;
//import model.FlightCollection;
//
//public class MyFlightsController {
//    private static FlightCollection myFlights = new FlightCollection();
//    @FXML
//    private ScrollPane myFlightsPane = new ScrollPane();
//
//    public static FlightCollection getMyFlights() {
//        return myFlights;
//    }
//
//    public MyFlightsController() {
//        GridPane pane = new GridPane();
//        int i = 0;
//        if(MyFlightsController.getMyFlights() != null) {
//
//            for (Flight flight : MyFlightsController.getMyFlights().getFlights()) {
//                pane.add(new FlightPane(flight), 0, i++);
//
//                Separator separator = new Separator();
//                separator.setOpacity(0);
//                pane.add(separator, 0, i++);
//
//                separator = new Separator();
//                separator.setOpacity(0);
//                pane.add(separator, 0, i++);
//
//                separator = new Separator();
//                separator.setOpacity(0);
//                pane.add(separator, 0, i++);
//
//                separator = new Separator();
//                separator.setOpacity(0);
//                pane.add(separator, 0, i++);
//
//                separator = new Separator();
//                separator.setOpacity(0);
//                pane.add(separator, 0, i++);
//            }
//            pane.setAlignment(Pos.CENTER);
//
//            this.myFlightsPane.setContent(pane);
//        }
//    }
//    public static void addFlight(Flight flight) {
//        MyFlightsController.getMyFlights().addFlight(flight);
//        System.out.println("added flight = " + flight);
//    }
//    public static boolean removeFlight(Flight flight) {
//        System.out.println("removed flight = " + flight);
//        return MyFlightsController.getMyFlights().removeFlight(flight);
//    }
//
//    public static void setMyFlights(FlightCollection myFlights) {
//        MyFlightsController.myFlights = myFlights;
//    }
//}
