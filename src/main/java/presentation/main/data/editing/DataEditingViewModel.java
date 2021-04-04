package presentation.main.data.editing;

import presentation.SceneController;

import java.io.IOException;

public class DataEditingViewModel {

    public void loadStartWindow() {
        try {
            SceneController.load("start.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPeopleEditingWindow() {
        try {
            SceneController.load("people.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
