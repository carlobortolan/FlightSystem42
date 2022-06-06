package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class H05E01ClientApplication extends Application {
    private final NoteController noteController = new NoteController();
    private final PersonController personController = new PersonController();
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        primaryStage.setScene(new HomeScene(this));
        primaryStage.show();
    }

    public void showHomeScene() {
        stage.setScene(new HomeScene(this));
    }

    public void showNoteScene() {
        stage.setScene(new NoteScene(noteController, this));
    }

    public void showPersonScene() {
        stage.setScene(new PersonScene(personController, this));
    }

    public Stage getStage() {
        return stage;
    }
}
