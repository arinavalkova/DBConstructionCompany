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
            SceneController.load("peopleEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadOrganizationsEditingWindow() {
        try {
            SceneController.load("organizationsEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
