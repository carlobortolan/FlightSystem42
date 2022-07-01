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

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;

public class VideoController {
    @FXML
    private Button startVideo;
    @FXML
    private Button pauseVideo;
    private MediaPlayer mediaPlayer;
    private String url;
    private boolean isPlaying = true;

    @FXML
    private MediaView videoView;

    @FXML
    private Canvas canvas;
    public void initialize(String i) {
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(new Color(0, 0, 0, 1));
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        this.url = i;
        File linkToVideo = new File(url);
        Media video = new Media(linkToVideo.toURI().toString());
        mediaPlayer = new MediaPlayer(video);
        this.startVideo.setDisable(true);
        final DoubleProperty width = videoView.fitWidthProperty();
        final DoubleProperty height = videoView.fitHeightProperty();
        width.bind(Bindings.selectDouble(videoView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(videoView.sceneProperty(), "height"));
        videoView.setPreserveRatio(true);
        videoView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        videoView.setOnMouseClicked(e -> {
            if(isPlaying) {
                this.pauseVideo();
            } else {
                this.startVideo();
            }
        });
    }

    public void stop() {
        this.mediaPlayer.stop();
        this.isPlaying = false;
    }
    @FXML
    private void startVideo() {
        this.startVideo.setDisable(true);
        this.pauseVideo.setDisable(false);
        this.isPlaying = true;
        mediaPlayer.play();
    }

    @FXML
    private void pauseVideo() {
        this.pauseVideo.setDisable(true);
        this.startVideo.setDisable(false);
        this.isPlaying = false;
        mediaPlayer.pause();
    }

    @FXML
    public void restartVideo() {
        mediaPlayer.stop();
        this.isPlaying = false;
        this.pauseVideo.setDisable(true);
        this.startVideo.setDisable(false);
    }
}
