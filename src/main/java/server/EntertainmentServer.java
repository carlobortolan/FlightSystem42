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

import com.example.eist22t02zweiundvierziger2022.FlightSystemApplication;
import com.example.eist22t02zweiundvierziger2022.controllers.EntertainmentController;
import com.example.eist22t02zweiundvierziger2022.controllers.VideoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EntertainmentServer {

    private EntertainmentController requester;
    private VideoController videoController;
    private FXMLLoader fxmlLoader;
    private Stage stage;

    public EntertainmentServer(EntertainmentController requester) {
        this.requester = requester;
    }

    public void stageAndShowInstructions() throws IOException {
        Parent root;
        this.fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("video-view.fxml"));
        root = fxmlLoader.load();
        this.stage = new Stage();
        stage.setTitle("MOVIE");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void stageAndShowMovies() throws IOException {
        Parent root;
        this.fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("video-view.fxml"));
        root = fxmlLoader.load();
        this.stage = new Stage();
        stage.setTitle("MOVIE");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void stageAndShowMusic() throws IOException {
        Parent root;
        this.fxmlLoader = new FXMLLoader(FlightSystemApplication.class.getResource("video-view.fxml"));
        root = fxmlLoader.load();
        this.stage = new Stage();
        stage.setTitle("MUSIC");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public void closeOnRequest() {
        stage.setOnCloseRequest(exit -> this.videoController.stop());
    }

    public void setVideoController(VideoController controller) {
        this.videoController = controller;
    }

    public EntertainmentController getRequester() {
        return requester;
    }

    public VideoController getVideoController() {
        return videoController;
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public Stage getStage() {
        return stage;
    }
}
