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

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final WebClient webClient;
    private final List<String> surveys;
    private final List<String> drinks;
    private final List<String> requests;


    public Client() {
        this.webClient = WebClient.builder()
//                TODO: Make sure to change to an unused port if the default port is occupied.
                .baseUrl("http://localhost:8081/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.surveys = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public void addSurvey(String string) {
        System.out.println("ADDING SURVEY = " + string);
        surveys.add(string);
        webClient.post()
                .uri("Surveys")
                .bodyValue(string)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorStop()
                .subscribe(newSurvey -> {
                    surveys.add(newSurvey);
                });
    }

    public void requestDrink(String string) {
        System.out.println("REQUESTING DRINK = " + string);
        drinks.add(string);
        webClient.post()
                .uri("Drinks")
                .bodyValue(string)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorStop()
                .subscribe(newSurvey -> {
                    drinks.add(newSurvey);
                });
    }

    public void requestStewardess(String string) {
        System.out.println("REQUESTING STEWARDESS = " + string);
        requests.add(string);
        webClient.post()
                .uri("Requests")
                .bodyValue(string)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorStop()
                .subscribe(newRequest -> {
                    requests.add(newRequest);
                });
    }

}
