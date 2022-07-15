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
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button replayMusic;


    @FXML
    private ScrollPane lyrik;


    @FXML
    private Button closeLyrik;
    @FXML
    private Text title;

    @FXML
    private Text artist;

    private MediaPlayer musicplayer;

    private Boolean isplayingMusic;

    private List<Music> library;

    private Text lyrikText;


    public void initialize() {
        supportMusic();
        this.instructionsButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(true);
            this.movieScrollPane.setVisible(false);
            this.musicScrollPane.setVisible(false);
            this.instructionsButton.setSelected(true);
            this.moviesButton.setSelected(false);
            this.musicButton.setSelected(false);
            this.lyrik.setVisible(false);
            showingMusic();
        });
        this.moviesButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(false);
            this.movieScrollPane.setVisible(true);
            this.musicScrollPane.setVisible(false);
            this.instructionsButton.setSelected(false);
            this.moviesButton.setSelected(true);
            this.musicButton.setSelected(false);
            this.lyrik.setVisible(false);
            showingMusic();

        });
        this.musicButton.setOnAction(e -> {
            this.instructionsScrollPane.setVisible(false);
            this.movieScrollPane.setVisible(false);
            this.musicScrollPane.setVisible(true);
            this.instructionsButton.setSelected(false);
            this.moviesButton.setSelected(false);
            this.musicButton.setSelected(true);
            showingMusic();


        });
        initializeMusic();
        initializeInstructions();
        initializeMovies();

        /*
        this.instructionsScrollPane.setVisible(false);
        this.movieScrollPane.setVisible(false);
        this.musicScrollPane.setVisible(false);
         */
    }

    private void initializeInstructions() {
        GridPane instructionPane = new GridPane();
        File file = new File("src/main/resources/images/instructions/instructionPoster.png");
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
                videoController.initialize("src/main/resources/videos/safety_video.mp4");


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
        for (int i = 1; i < 18; i++) {
            File file = new File("src/main/resources/images/movies/p" + i + ".png");
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
                    videoController.initialize("src/main/resources/images/movies/TR" + finalI + ".mp4");


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

    private void supportMusic() {
        Music music = new Music();
        try {
            music.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        library = music.getMusicLibrary();
        isplayingMusic = false;
        showingMusic();
        this.lyrik.setVisible(false);
        this.closeLyrik.setVisible(false);
        this.lyrikText = new Text("");
        lyrik.setFitToHeight(true);
        lyrik.setFitToWidth(true);
        this.lyrik.setContent(lyrikText);
    }

    private void showingMusic(){
        if(isplayingMusic){
            this.musicIcon.setVisible(true);
            this.musicplay.setVisible(true);
            this.musicstop.setVisible(true);
            this.artist.setVisible(true);
            this.title.setVisible(true);
            this.replayMusic.setVisible(true);
        }else{
            this.musicIcon.setVisible(false);
            this.musicplay.setVisible(false);
            this.musicstop.setVisible(false);
            this.title.setVisible(false);
            this.artist.setVisible(false);
            this.replayMusic.setVisible(false);
        }
    }

    private void initializeMusic() {
        GridPane musicPane = new GridPane();

        for (int i = 0; i < library.size(); i++) {
            File file = new File(library.get(i).getIcon());
            Image cover = new Image(file.toURI().toString());
            ImageView musicView = new ImageView(cover);
            musicView.setFitHeight(240);
            musicView.setFitWidth(240);
            musicPane.add(musicView, i + 1, 0);
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

                if (this.musicplayer != null) {
                    musicplayer.stop();
                    this.musicplayer = new MediaPlayer(song);
                    this.musicplayer.stop();
                    this.musicplayer.play();
                } else {
                    this.musicplayer = new MediaPlayer(song);
                    this.musicplayer.play();
                }
                this.musicIcon.setImage(cover);
                this.isplayingMusic = true;
                this.title.setText(library.get(finalI).getTitle());
                this.artist.setText(library.get(finalI).getArtist());
                this.lyrikText.setText(library.get(finalI).getLyrik());
                showingMusic();
            });
            musicIcon.setOnMouseEntered(e -> {
                musicIcon.setCursor(Cursor.HAND);
            });
            musicIcon.setOnMouseClicked(e-> {
                if(this.artist.getText().trim().equals("Rick Astley")) {
                    lyrikText.setTextAlignment(TextAlignment.CENTER);
                } else {
                    lyrikText.setTextAlignment(TextAlignment.LEFT);
                }
                lyrikText.setWrappingWidth(800);
                this.lyrik.setVisible(true);
                this.closeLyrik.setVisible(true);
            });
        }
        this.musicScrollPane.setContent(musicPane);
    }

    @FXML
    private void stopMusic() {
        if (this.musicplayer != null) {
            this.musicplayer.pause();
            isplayingMusic = false;
        }
    }

    @FXML
    private void playMusic() {
        if (this.musicplayer != null) {
            this.musicplayer.play();
            this.isplayingMusic = true;
        }
    }

    @FXML
    private void restartMusic() {
        if (this.musicplayer != null) {
            this.musicplayer.seek(Duration.ZERO);
            this.musicplayer.play();
            this.isplayingMusic = true;
        }
    }

    @FXML
    private void closeLyrik(){
        this.lyrik.setVisible(false);
        this.closeLyrik.setVisible(false);
    }

}
