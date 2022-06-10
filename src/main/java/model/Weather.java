package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class Weather {

    private String weatherDescirption;
    private String currentTemp;
    private String tempfeelslike;
    private String temp_min;
    private String temp_max;
    private String windSpeed;
    private String windDirection;
    private Coordinates coordinates;


    public void getWeather(String city) throws IOException {
        String respond = requestWeather(city);
        parser(respond);
    }

    private String requestWeather(String city) throws IOException {
        URL url = new URL("http://pro.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=ee9232b8316d7023c32d7911701417ca&" +
                "units=metric");
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        System.out.println(result);
        return result.toString();
    }

    private void parser(String respond) {

        String colon ="";
        String colat = "";
        String name = "";

        respond = respond.
                replaceAll("\\{", "").
                replaceAll("\\}", "").
                replaceAll("\"", "").
                replaceAll(":", " ");

        String[] details = respond.split(",");

        for (int i = 0; i < details.length; i++) {
            if (details[i].contains("lon")) {
                colon = details[i].replace("lon", "").replace("coord","").trim();
            }
            if (details[i].contains("lat")) {
                colat = details[i].replace("lat", "").trim();
            }
            if (details[i].contains("description")) {
                this.weatherDescirption = details[i].replace("description", "").trim();
            }
            if (details[i].contains("main temp")) {
                this.currentTemp = details[i].replace("main temp", "").trim() + " °C";
            }
            if (details[i].contains("feels_like")) {
                this.tempfeelslike = details[i].replace("feels_like", "").trim() + " °C";
            }
            if (details[i].contains("temp_min")) {
                this.temp_min = details[i].replace("temp_min", "").trim() + " °C";
            }
            if (details[i].contains("temp_max")) {
                this.temp_max = details[i].replace("temp_max", "").trim() + " °C";
            }
            if (details[i].contains("speed")) {
                this.windSpeed = details[i].replace("speed", "").replace("wind","").trim() + " km/h";
            }
            if (details[i].contains("deg")) {
                this.windDirection = details[i].replace("deg", "").trim()+"°";
            }
            if (details[i].contains("name")) {
                name = details[i].replaceAll("name", "").trim();

            }
        }
        this.coordinates = new Coordinates(colon,colat);


        System.out.println("ende");


    }


    public static void main(String[] args) throws IOException {

        System.out.println("Ende");

    }


}

