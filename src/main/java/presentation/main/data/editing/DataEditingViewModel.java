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

    public void loadBookerEditingWindow() {
        try {
            SceneController.load("editors/bookerEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEstimateEditingWindow() {
        try {
            SceneController.load("editors/estimateEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAdminPeopleEditorButton() {
        try {
            SceneController.load("admin/peopleAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAdminWorkEditorButton() {
        try {
            SceneController.load("admin/workAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadReportsEditingWindow() {
        try {
            SceneController.load("editors/reportEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSchedulesEditingWindow() {
        try {
            SceneController.load("editors/scheduleEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
