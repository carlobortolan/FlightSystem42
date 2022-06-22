/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lufthansa;

import javafx.util.converter.LongStringConverter;
import model.City;
import model.Details;
import model.Flight;
import model.FlightObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class FlightStatus {

    String deAirportCode = "-";
    String deorDateTime ="-";
    String deestDateTime = "-";
    String deActualTime = "-";

    String detimeStatus ="-";
    String deTerminalName ="-";
    String deTerminalGate = "-";

    String arAirportCode = "-";
    String arScheduledTime = "-";
    String arEstimatedTime = "-";

    String arActualTime = "-";
    String artimeStatus = "-";
    String arTerminalName ="-";
    String arTerminalGate = "-";



    private FlightObject flightObject;

    private String token;

    public  FlightStatus(FlightObject flightObject) throws IOException, InterruptedException {
        long lastAsked = new LongStringConverter().fromString(readFromFile(Path.of("time")));
        if(System.currentTimeMillis() - lastAsked > 1.72e+5){
            getToken();
        }
        else this.token = readFromFile(Path.of("key"));
        System.out.println(token);

        this.flightObject = flightObject;
    }


    public String getStatus() throws IOException {
        String flightNr = flightObject.getTrackingNumber().substring(0,flightObject.getTrackingNumber().indexOf("-"));
        String date = flightObject.getDetails().getTimeOfDeparture().toString();
        String subdate = date.substring(0,date.indexOf("T"));
        String[] commands = new String[]{"curl", "-H", "Authorization: Bearer " + token, "-H", "Accept: application/json",
                "https://api.lufthansa.com/v1/operations/flightstatus/" + flightNr + "/"
                        + subdate};

        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response = response + line;
        }
        return response;
        }

    public void parseStatus(String response) {

        JSONObject obj = new JSONObject(response);

        JSONArray array = obj.getJSONObject("FlightStatusResource").
                getJSONObject("Flights").getJSONArray("Flight");


        if (array.getJSONObject(0).getJSONObject("Departure").keySet().contains("AirportCode")) {
            deAirportCode = array.getJSONObject(0).getJSONObject("Departure").getString("AirportCode");
        }
        if (array.getJSONObject(0).getJSONObject("Departure").keySet().contains("ScheduledTimeLocal")) {
            deorDateTime = array.getJSONObject(0).getJSONObject("Departure").getJSONObject("ScheduledTimeLocal").getString("DateTime");

        }
        if (array.getJSONObject(0).getJSONObject("Departure").keySet().contains("EstimatedTimeLocal")) {
            deestDateTime = array.getJSONObject(0).getJSONObject("Departure").getJSONObject("EstimatedTimeLocal").getString("DateTime");
        }
        if (array.getJSONObject(0).getJSONObject("Departure").keySet().contains("ActualTimeLocal")) {
            deActualTime = array.getJSONObject(0).getJSONObject("Departure").getJSONObject("ActualTimeLocal").getString("DateTime");
        }
        if (array.getJSONObject(0).getJSONObject("Departure").keySet().contains("TimeStatus")) {
            detimeStatus = array.getJSONObject(0).getJSONObject("Departure").getJSONObject("TimeStatus").getString("Definition");
        }
        if (array.getJSONObject(0).getJSONObject("Departure").getJSONObject("Terminal").keySet().contains("Name")) {
            deTerminalName = array.getJSONObject(0).getJSONObject("Departure").getJSONObject("Terminal").getString("Name");
        }
        if (array.getJSONObject(0).getJSONObject("Departure").getJSONObject("Terminal").keySet().contains("Gate")) {
            deTerminalGate = array.getJSONObject(0).getJSONObject("Departure").getJSONObject("Terminal").getString("Gate");
        }


        if (array.getJSONObject(0).getJSONObject("Arrival").keySet().contains("AirportCode")) {
            arAirportCode = array.getJSONObject(0).getJSONObject("Arrival").getString("AirportCode");
        }
        if (array.getJSONObject(0).getJSONObject("Arrival").keySet().contains("ScheduledTimeLocal")) {
            arScheduledTime = array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("ScheduledTimeLocal").getString("DateTime");
        }
        if (array.getJSONObject(0).getJSONObject("Arrival").keySet().contains("EstimatedTimeLocal")) {
            arEstimatedTime = array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("EstimatedTimeLocal").getString("DateTime");
        }
        if (array.getJSONObject(0).getJSONObject("Arrival").keySet().contains("ActualTimeLocal")) {
            deActualTime = array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("ActualTimeLocal").getString("DateTime");
        }
        if (array.getJSONObject(0).getJSONObject("Arrival").keySet().contains("TimeStatus")) {
            artimeStatus = array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("TimeStatus").getString("Definition");
        }
        if (array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("Terminal").keySet().contains("Name")) {
            arTerminalName = array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("Terminal").getString("Name");
        }
        if (array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("Terminal").keySet().contains("Gate")) {
            arTerminalGate = array.getJSONObject(0).getJSONObject("Arrival").getJSONObject("Terminal").getString("Gate");
        }

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        LocalDateTime timeOfDeparture = LocalDateTime.of(2022, 06, 22, 12, 35);
        Details details = new Details(timeOfDeparture,null, 0, null, null, null, null);
        City from = new City("MUC");
        City to = new City("JFK");

        FlightObject flightObject1 = new FlightObject("LH410-346",from, to,details );

        FlightStatus status = new FlightStatus(flightObject1);
        String getStatus = status.getStatus();
        status.parseStatus(getStatus);

        System.out.println("Ende");
    }












    private String readFromFile(Path path) throws IOException {
        return Files.readString(path);
    }
    private void saveToFile(Path path, String s) throws IOException {
        Files.writeString(path, s);
    }
    public void getToken() throws IOException, InterruptedException {

        String[] commands = new String[]{"curl", "https://api.lufthansa.com/v1/oauth/token", "-X", "POST", "-d",
                "client_id=vzr85vbughdj7tbvkqhbabj7", "-d", "client_secret=aGtfzGbDhN54TQPBjFQ6", "-d", "grant_type=client_credentials"};
        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response = response + line;
        }
        String token = "";
        String restTime = "";
        token = response.substring(17, 41);
        System.out.println(token);
        restTime = response.substring(78, 84);

        this.token = token;
        saveToFile(Path.of("key"), token);
        saveToFile(Path.of("time"), new LongStringConverter().toString(System.currentTimeMillis()));

        System.out.println(token);
        System.out.println(response);
    }
}
