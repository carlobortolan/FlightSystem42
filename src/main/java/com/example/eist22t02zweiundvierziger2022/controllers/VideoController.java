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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class VideoController {
    @FXML
    private Button startVideo;
    @FXML
    private Button pauseVideo;
    @FXML
    private Button restartVideo;
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView videoView;


    @FXML
    private void startVideo() {
        this.startVideo.setDisable(true);
        this.pauseVideo.setDisable(false);
        File linktoVideo = new File("src/main/resources/Videos/safety_video.mp4");
        Media video = new Media(linktoVideo.toURI().toString());
        mediaPlayer = new MediaPlayer(video);
        videoView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    private void pauseVideo() {
        this.pauseVideo.setDisable(true);
        this.startVideo.setDisable(false);
        mediaPlayer.pause();
    }

    @FXML
    private void restartVideo() {
        mediaPlayer.stop();
        this.pauseVideo.setDisable(true);
        this.startVideo.setDisable(false);
    }
}
