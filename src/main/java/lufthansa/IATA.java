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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class IATA {
    private String iataCode;
    private String airportName;
    private String cityName;
    private String country;
    private String region;
    public static List<IATA> destinations = new LinkedList<>();


    public IATA(String iataCode, String airportName, String cityName, String country, String region) {
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.cityName = cityName;
        this.country = country;
        this.region = region;
    }

    public IATA() {
    }

    public void finddestinations() throws IOException {
        Path iataList = Path.of("src/main/java/lufthansa/IATA LIST");
        String content = Files.readString(iataList);
        String[] cities = content.split("\n");

        for (int i = 0; i < cities.length; i++) {
            String[] details = cities[i].split("\t");
            if (details.length < 2) {
                continue;
            }
            String iata = details[0].trim().substring(0, 3);
            String airportName = details[2].trim();
            String cityName;
            String regionName = "-";
            String countryName = "-";
            String[] location = details[3].split(",");
            cityName = location[0].trim();

            if (location.length > 2) {
                regionName = location[1].trim();
                countryName = location[2].trim();
            }
            if (location.length == 2) {
                countryName = location[1].trim();
            }

            IATA city = new IATA(iata, airportName, cityName, countryName, regionName);
            destinations.add(city);

        }
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public static List<IATA> getDestinations() {
        return destinations;
    }

    public static void main(String[] args) throws IOException {
        IATA iata = new IATA();
        iata.finddestinations();
        System.out.println("Ende");
    }

}
