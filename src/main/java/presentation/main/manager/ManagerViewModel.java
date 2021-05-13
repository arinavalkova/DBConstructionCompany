package presentation.main.manager;

import presentation.SceneController;

import java.io.IOException;

public class ManagerViewModel {

    public void loadStartWindow() {
        try {
            SceneController.load("authorization.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPeopleEditingWindow() {
        try {
            SceneController.load("editors/peopleEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadOrganizationsEditingWindow() {
        try {
            SceneController.load("editors/organizationsEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBrigadesEditingWindow() {
        try {
            SceneController.load("editors/brigadesEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
