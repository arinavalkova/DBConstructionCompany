package presentation.main.queries;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.SceneController;

import java.io.IOException;

public class QueriesView {

    @FXML
    private Button backButton;

    @FXML
    void initialize()  {
        backButton.setOnMouseClicked(event -> {
            try {
                SceneController.load("start.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
