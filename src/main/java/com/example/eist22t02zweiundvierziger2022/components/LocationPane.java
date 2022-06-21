package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.image.Image;
import model.POI;

public class LocationPane {


    private Image image;
    private POI poi;

    public LocationPane(POI poi) {
        this.poi = poi;
        loadImage();
    }



    // Source: https://developers.google.com/maps/documentation/places/web-service/photos
    public void loadImage(){
        String base = "https://maps.googleapis.com/maps/api/place/photo";
        String maxwidth = "400";
        String maxHeight = "200";
        String reference = poi.getPhoto_reference();
        String key = "AIzaSyCFHuvSLicFOEbrNAMgRkOL0HPbVKNLqhU";
        String url = base+"?maxwidth="+maxwidth+"&maxHeight="+maxHeight+"&photo_reference="+reference+"&key="+key;
        this.image = new Image(url);
    }
}
