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

package hello.world.demo;

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
    private int[] counts = new int[9];
    private final String[] drinkNames = new String[]{"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9"};

    @GetMapping("Surveys")
    public String showSurveys() {
        System.out.println("surveys size = " + surveys.size());
        String s = "";
        for (String string : surveys) {
            s += string + "<br /><br />";
        }
        return s;
    }

    @PostMapping("Surveys")
    public ResponseEntity<String> addSurvey(@RequestBody String survey) {
        System.out.println("survey received = " + survey);
        if (survey == null || !(survey.contains("CONTENT:") && survey.contains("DATE:") && survey.contains("FLIGHT:"))) {
            return ResponseEntity.badRequest().build();
        }
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
}
