package presentation.main.chief;

import presentation.SceneController;

import java.io.IOException;

public class ChiefViewModel {

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

    public void loadEstimateEditingWindow() {
        try {
            SceneController.load("editors/estimateEditor.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStartWindow() {
        try {
            SceneController.load("authorization.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadQueriesWindow() {
        try {
            SceneController.load("queries.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
