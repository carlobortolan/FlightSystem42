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

import Music.Music;
import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EntertainmentController {

    @FXML
    private ToggleButton instructionsButton;
    @FXML
    private ToggleButton moviesButton;
    @FXML
    private ToggleButton musicButton;
    @FXML
    private ScrollPane instructionsScrollPane = new ScrollPane();
    @FXML
    private ScrollPane movieScrollPane = new ScrollPane();
    @FXML
    private ScrollPane musicScrollPane = new ScrollPane();

    @FXML
    private ImageView musicIcon;

    @FXML
    private Button musicplay;

    @FXML
    private Button musicstop;

    @FXML
    private TextArea musicTitle;


    private MediaPlayer musicplayer;

    private Boolean isplayingMusic;

    private List<Music> library;


    public void initialize() {
        supportMusic();
        this.instructionsButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(true);
            this.movieScrollPane.setVisible(false);
            this.musicScrollPane.setVisible(false);
            this.instructionsButton.setSelected(true);
            this.moviesButton.setSelected(false);
            this.musicButton.setSelected(false);

            if (!isplayingMusic) {
                this.musicIcon.setVisible(false);
                this.musicplay.setVisible(false);
                this.musicstop.setVisible(false);
                this.musicTitle.setVisible(false);

            } else {
                this.musicIcon.setVisible(true);
                this.musicplay.setVisible(true);
                this.musicstop.setVisible(true);
                this.musicTitle.setVisible(true);

            }


        });
        this.moviesButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(false);
            this.movieScrollPane.setVisible(true);
            this.musicScrollPane.setVisible(false);
            this.instructionsButton.setSelected(false);
            this.moviesButton.setSelected(true);
            this.musicButton.setSelected(false);

            if (!isplayingMusic) {
                this.musicIcon.setVisible(false);
                this.musicplay.setVisible(false);
                this.musicstop.setVisible(false);
                this.musicTitle.setVisible(false);

            } else {
                this.musicIcon.setVisible(true);
                this.musicplay.setVisible(true);
                this.musicstop.setVisible(true);
                this.musicTitle.setVisible(true);
            }


        });
        this.musicButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(false);
            this.movieScrollPane.setVisible(false);
            this.musicScrollPane.setVisible(true);
            this.instructionsButton.setSelected(false);
            this.moviesButton.setSelected(false);
            this.musicButton.setSelected(true);


            this.musicIcon.setVisible(true);
            this.musicplay.setVisible(true);
            this.musicstop.setVisible(true);
            this.musicTitle.setVisible(true);


        });

        initializeInstructions();
        initializeMovies();
        initializeMusic();

        /*
        this.instructionsScrollPane.setVisible(false);
        this.movieScrollPane.setVisible(false);
        this.musicScrollPane.setVisible(false);

         */
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
                stopMusic();
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
                    stopMusic();
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

    private void supportMusic(){
        Music music = new Music();
        try {
            music.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        library = music.getMusicLibrary();
        isplayingMusic = false;
        this.musicTitle.setBackground(Background.EMPTY);
        this.musicTitle.setEditable(false);

    }

    private void initializeMusic() {
        GridPane musicPane = new GridPane();

        for (int i = 0; i < library.size(); i++) {
            File file = new File(library.get(i).getIcon());
            Image cover = new Image(file.toURI().toString());
            ImageView musicView = new ImageView(cover);
            musicView.setFitHeight(240);
            musicView.setFitWidth(240);
            musicPane.add(musicView, i+1, 0);
            musicView.setOnMouseEntered(e -> {
                musicView.setCursor(Cursor.HAND);
            });
            Text artist = new Text();
            artist.setText(library.get(i).getArtist() + "\n");
            artist.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
            Text title = new Text();
            title.setText(library.get(i).getTitle());
            title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));

            int finalI = i;
            musicView.setOnMouseClicked(e -> {
                File songSource = new File(library.get(finalI).getSource());
                Media song = new Media(songSource.toURI().toString());
                this.isplayingMusic = true;
                if(this.musicplayer != null) {
                    this.musicplayer.stop();
                    this.musicplayer = new MediaPlayer(song);
                    this.musicplayer.play();
                }
                else{
                    this.musicplayer = new MediaPlayer(song);
                    this.musicplayer.play();
                }
                this.musicIcon.setImage(cover);
                this.musicTitle.setText(artist.getText() + title.getText());
            });
        }
        this.musicScrollPane.setContent(musicPane);
    }
    @FXML
    private void stopMusic(){
       this.musicplayer.pause();
    }
    @FXML
    private void playMusic(){
      this.musicplayer.play();
    }

    @FXML
    private void restartMusic(){
        this.musicplayer.stop();
        this.musicplayer.play();
    }



}
