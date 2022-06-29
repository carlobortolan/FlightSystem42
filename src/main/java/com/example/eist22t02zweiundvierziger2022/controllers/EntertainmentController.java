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

package com.example.eist22t02zweiundvierziger2022.controllers;

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class EntertainmentController {

    @FXML
    private ToggleButton instructionsButton;
    @FXML
    private ToggleButton moviesButton;
    @FXML
    private ToggleButton musicButton;
    @FXML
    private ScrollPane instructionsScrollPane= new ScrollPane();
    @FXML
    private ScrollPane movieScrollPane = new ScrollPane();
    @FXML
    private ScrollPane musicScrollPane = new ScrollPane();

    public void initialize() {
        this.instructionsButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(true);
            this.movieScrollPane.setVisible(false);
            this.musicScrollPane.setVisible(false);
            this.instructionsButton.setSelected(true);
            this.moviesButton.setSelected(false);
            this.musicButton.setSelected(false);
        });
        this.moviesButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(false);
            this.movieScrollPane.setVisible(true);
            this.musicScrollPane.setVisible(false);
            this.instructionsButton.setSelected(false);
            this.moviesButton.setSelected(true);
            this.musicButton.setSelected(false);
        });
        this.musicButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(false);
            this.movieScrollPane.setVisible(false);
            this.musicScrollPane.setVisible(true);
            this.instructionsButton.setSelected(false);
            this.moviesButton.setSelected(false);
            this.musicButton.setSelected(true);
        });

        initializeInstructions();
        initializeMovies();
        initializeMusic();

        this.instructionsScrollPane.setVisible(false);
        this.movieScrollPane.setVisible(false);
        this.musicScrollPane.setVisible(false);
    }

    private void initializeInstructions() {
        GridPane instructionPane = new GridPane();
        File file = new File("src/main/resources/Images/Instructions/instructionPoster.png");
        ImageView instructionView = new ImageView(new Image(file.toURI().toString()));
        instructionView.setFitHeight(260);
        instructionView.setFitWidth(437);
        instructionPane.add(instructionView, 0, 0);
        instructionView.setOnMouseEntered(e -> {
            instructionView.setCursor(Cursor.HAND);
        });
        instructionView.setOnMouseClicked(e -> {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("video-view.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("MOVIE");
                stage.setScene(new Scene(root));
                stage.show();
                VideoController videoController = fxmlLoader.getController();
                videoController.initialize("src/main/resources/Videos/safety_video.mp4");


                stage.setOnCloseRequest(exit -> videoController.stop());

//                    ((Node) (e.getSource())).getScene().getWindow().hide();
//                    stage.setOnCloseRequest(ev -> ((Window) (ev.getSource())).getScene().getWindow());
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        });
        this.instructionsScrollPane.setContent(instructionPane);
    }

    private void initializeMovies() {
        GridPane moviesPane = new GridPane();
        for (int i = 1; i <= 11; i++) {
            File file = new File("src/main/resources/Images/Movies/m" + i + ".png");
            ImageView movieView = new ImageView(new Image(file.toURI().toString()));
            movieView.setFitHeight(240);
            movieView.setFitWidth(160);
            moviesPane.add(movieView, i, 0);
            movieView.setOnMouseEntered(e -> {
                movieView.setCursor(Cursor.HAND);
            });
            int finalI = i;
            movieView.setOnMouseClicked(e -> {
                Parent root;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("video-view.fxml"));
                    root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("MOVIE");
                    stage.setScene(new Scene(root));
                    stage.show();
                    VideoController videoController = fxmlLoader.getController();
                    videoController.initialize("src/main/resources/Images/Movies/T" + finalI + ".mp4");


                    stage.setOnCloseRequest(exit -> videoController.stop());

//                    ((Node) (e.getSource())).getScene().getWindow().hide();
//                    stage.setOnCloseRequest(ev -> ((Window) (ev.getSource())).getScene().getWindow());
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            });
        }
        this.movieScrollPane.setContent(moviesPane);
    }

    private void initializeMusic() {
        GridPane musicPane = new GridPane();
        for (int i = 1; i <= 4; i++) {
            File file = new File("src/main/resources/Images/Movies/c" + i + ".png");
            ImageView musicView = new ImageView(new Image(file.toURI().toString()));
            musicView.setFitHeight(240);
            musicView.setFitWidth(240);
            musicPane.add(musicView, i, 0);
            musicView.setOnMouseEntered(e -> {
                musicView.setCursor(Cursor.HAND);
            });
            int finalI = i;
            musicView.setOnMouseClicked(e -> {
                Parent root;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("video-view.fxml"));
                    root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("MUSIC");
                    stage.setScene(new Scene(root));
                    stage.show();
                    VideoController videoController = fxmlLoader.getController();
                    videoController.initialize("src/main/resources/Images/Movies/S" + finalI + ".mp4");


                    stage.setOnCloseRequest(exit -> videoController.stop());

//                    ((Node) (e.getSource())).getScene().getWindow().hide();
//                    stage.setOnCloseRequest(ev -> ((Window) (ev.getSource())).getScene().getWindow());
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            });
        }
        this.musicScrollPane.setContent(musicPane);
    }
}
