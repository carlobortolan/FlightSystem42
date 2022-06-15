package com.example.eist22t02zweiundvierziger2022.controllers;

import model.City;
import model.Coordinates;
import model.Flight;

public class MapController {
    //TODO: CONNECT CONTROLLER TO API
    public void initialize(Flight flight) {
        City from = flight.getFrom();
        City to = flight.getTo();

        if(from.getWeather() != null) {
            Coordinates fromCoordinate = from.getWeather().getCoordinates();
        }
        if(to.getWeather() != null) {
            Coordinates toCoordinate = to.getWeather().getCoordinates();
        }

        System.out.println(from.getCityName() + " - " + to.getCityName());
    }
}
