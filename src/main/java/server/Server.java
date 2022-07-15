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

package server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class Server {
    private final List<String> surveys = new ArrayList<>();
    private final List<String> drinks = new ArrayList<>();
    private final List<String> requests = new ArrayList<>();
    private int[] counts = new int[9];
    private final String[] drinkNames = new String[]{"Water", "Apple juice", "Coke", "Hot chocolate", "Coffee", "Tee", "Beer", "Martini", "Champagne"};

    @GetMapping("Surveys")
    public String showSurveys() {
        System.out.println("surveys size = " + surveys.size());
        String s = "<html><head><style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>\n" +
                "</head><body>\n" +
                "<h2>Surveylogs</h2>\n" +
                "\n" +
                "<p>(1) How was your experience before departure?</p>\n" +
                "<p>(2) How would you rate the flight itself?</p>\n" +
                "<p>(3) What was your experience with our stewardess?</p>\n" +
                "<p>(4) Did your meals and drinks taste fine?</p>\n" +
                "<p>(5) How do you feel about our entertaiment system?</p>\n" +
                "\n" +
                "    \n" +
                "<table style=\"width:100%\">\n" +
                "  <tbody><tr>\n" +
                "    <th>Date</th>\n" +
                "    <th>Flightnumber</th>\n" +
                "    <th>1</th>\n" +
                "    <th>2</th>\n" +
                "    <th>3</th>\n" +
                "    <th>4</th>\n" +
                "  <th>5</th>\n" +
                "      <th>Name</th>\n" +
                "      <th>Additional text</th>\n" +
                "  </tr>";
        for (String string : surveys) {
            s += string;
        }
        s += "</tbody></table></body></html>";
        return s;
    }

    @PostMapping("Surveys")
    public ResponseEntity<String> addSurvey(@RequestBody String survey) {
        System.out.println("survey received = " + survey);
        surveys.add(survey);
        return ResponseEntity.ok().build();
    }

    @GetMapping("Drinks")
    public String showDrinks() {
        System.out.println("drinks size = " + drinks.size());
        String s = "";
        counts = new int[9];
        for (String string : drinks) {
            if (string.equals(drinkNames[0])) counts[0]++;
            if (string.equals(drinkNames[1])) counts[1]++;
            if (string.equals(drinkNames[2])) counts[2]++;
            if (string.equals(drinkNames[3])) counts[3]++;
            if (string.equals(drinkNames[4])) counts[4]++;
            if (string.equals(drinkNames[5])) counts[5]++;
            if (string.equals(drinkNames[6])) counts[6]++;
            if (string.equals(drinkNames[7])) counts[7]++;
            if (string.equals(drinkNames[8])) counts[8]++;
        }

        for (int i = 0; i < 9; i++) {
            s += drinkNames[i] + " - " + counts[i] + "<br /><br />";
        }
        return s;
    }

    @PostMapping("Drinks")
    public ResponseEntity<String> addDrinks(@RequestBody String drink) {
        System.out.println("survey received = " + drink);
        if (drink == null) {
            return ResponseEntity.badRequest().build();
        }
        drinks.add(drink);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("Requests")
    public String showRequests() {
        System.out.println("requests size = " + drinks.size());
        String s = "";
        counts = new int[9];

        for (String r : this.requests) {
            s += r + "<br /><br />";
        }
        return s;
    }

    @PostMapping("Requests")
    public ResponseEntity<String> addRequest(@RequestBody String request) {
        System.out.println("request received = " + request);
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        drinks.add(request);
        return ResponseEntity.ok("OK");
    }
}
