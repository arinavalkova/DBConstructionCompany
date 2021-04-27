package presentation.main.queries;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.SceneController;
import presentation.View;

import java.io.BufferedReader;
import java.io.IOException;

public class QueriesView implements View {

    @FXML
    private Button backButton;

    @FXML
    private Button getBossForSectorWindowButton;

    @FXML
    private Button getManagementBossQueryButton;

    @FXML
    private Button getObjectsAndSchedulesButton;

    @FXML
    private Button getBrigadeAndEmployeeButton;

    @FXML
    void initialize()  {
        backButton.setOnAction(event -> {
            try {
                SceneController.load("start.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getBossForSectorWindowButton.setOnAction(event -> {
            try {
                SceneController.load("queries/getBossOfArea.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getManagementBossQueryButton.setOnAction(event -> {
            try {
                SceneController.load("queries/getManagementBoss.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getObjectsAndSchedulesButton.setOnAction(event -> {
            try {
                SceneController.load("queries/getObjectsAndSchedules.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getBrigadeAndEmployeeButton.setOnAction(event -> {
            try {
                SceneController.load("queries/getBrigadeAndEmployee.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
