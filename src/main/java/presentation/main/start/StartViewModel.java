package presentation.main.start;

import presentation.SceneController;

import java.io.IOException;

public class StartViewModel {

    public void startDataEditingWindow() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startQueriesWindow() {
        try {
            SceneController.load("queries.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
