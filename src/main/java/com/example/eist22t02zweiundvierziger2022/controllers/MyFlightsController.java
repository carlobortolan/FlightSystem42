package com.example.eist22t02zweiundvierziger2022.controllers;

import model.FlightCollection;

public class MyFlightsController {
    private static FlightCollection myFlights = new FlightCollection();
    public static FlightCollection getMyFlights() {
        return myFlights;
    }

    public static void setMyFlights(FlightCollection myFlights) {
        MyFlightsController.myFlights = myFlights;
    }
}
