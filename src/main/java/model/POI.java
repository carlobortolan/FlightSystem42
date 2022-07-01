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

package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class POI {

    private String name;
    private String address;
    private String photo_reference;
    private String link;


    public POI(String link) {

        link.replaceAll("Ä", "Ae").replaceAll("Ã¤", "ae").replaceAll("Ö", "Oe").replaceAll("Ã¶", "oe").replaceAll("ÃŸ", "ss").replaceAll("Ã¼", "ue");

        this.link = link;
        try {
            parsePOIbyLink(link);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public POI(City city){

        String cityName = city.getCityName().trim().replaceAll(" ", "%20");
        try{
            parserPOI(cityName);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void parsePOIbyLink(String link) throws IOException {
        String[] detailsLink = link.split("\\/");
        String placeName = detailsLink[5].trim();
        parserPOI(placeName);
    }


    public void parserPOI(String placeName) throws IOException, ArrayIndexOutOfBoundsException {
        try {
            URL urlID = new URL("https://maps.googleapis.com/maps/api/place/findplacefromtext/" +
                    "json?input=/" + placeName + "&inputtype=textquery&fields=place_id&key=AIzaSyCFHuvSLicFOEbrNAMgRkOL0HPbVKNLqhU");
            URLConnection connID = urlID.openConnection();
            BufferedReader rdID = new BufferedReader(new InputStreamReader(connID.getInputStream()));
            String lineID;
            StringBuilder resultID = new StringBuilder();
            while ((lineID = rdID.readLine()) != null) {
                resultID.append(lineID);
            }
            String ausgabeplaceID = resultID.toString();
            String placeIDshorted = ausgabeplaceID.
                    replaceAll("\"", "").
                    replaceAll(":", "").
                    replaceAll("\\}", "").
                    replaceAll("\\]", "").
                    replaceAll("\\[", "");


            String[] splittedID = placeIDshorted.split("\\{");
            String[] statusPlaceID = splittedID[2].split(",");
            String status = statusPlaceID[1].replaceAll("status", "").trim();
            String placeID = statusPlaceID[0].replaceAll("place_id", "").trim();
            rdID.close();


            URL url = new URL("https://maps.googleapis.com/maps/api/place/details/json?" +
                    "fields=name%2Cformatted_address%2Cphoto" +
                    "&place_id=" + placeID +
                    "&key=AIzaSyCFHuvSLicFOEbrNAMgRkOL0HPbVKNLqhU");
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            String responsePOI = result.toString();
            System.out.println("RESPONSE = " + responsePOI);
            String[] detailsPOI = responsePOI.split("\\{");

            String addressName = detailsPOI[2]

                    .replaceAll("Ä", "Ae").replaceAll("Ã¤", "ae").replaceAll("Ö", "Oe").replaceAll("Ã¶", "oe").replaceAll("ÃŸ", "ss").replaceAll("Ã¼", "ue");
            String address = addressName.substring(addressName.indexOf(":") + 1, addressName.indexOf("name") - 2).replaceAll("\"", "").trim();
            if(address.endsWith(",")) {
                address = address.substring(0, address.length()-1);
            }

            String name = addressName.substring(addressName.indexOf("\"name\" : \"") + 10, addressName.substring(addressName.indexOf("\"name\" : \"") + 10).indexOf("\"") + addressName.substring(0, addressName.indexOf("\"name\" : \"") + 10).length())
                    .replaceAll("\"", "")
                    .replaceAll(":", "")
                    .replace("name :", "").trim()
                    .replaceAll("Ä", "Ae").replaceAll("Ã¤", "ae").replaceAll("Ö", "Oe").replaceAll("Ã¶", "oe").replaceAll("ÃŸ", "ss").replaceAll("Ã¼", "ue");

            if(detailsPOI.length >= 4) {
            String detailsphotoID = detailsPOI[3];
            String photo_reference = detailsphotoID.substring(detailsphotoID.
                            indexOf("photo_reference"), detailsphotoID.indexOf("width")).
                    replaceAll("\"", "").
                    replaceAll("photo_reference :", "").
                    replaceAll(",", "").trim();
                this.photo_reference = photo_reference;
            }

            this.name = name;
            this.address = address;

            System.out.println("name = " + name);
            System.out.println("address = " + address);
            System.out.println("photo_reference = " + photo_reference);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

}
