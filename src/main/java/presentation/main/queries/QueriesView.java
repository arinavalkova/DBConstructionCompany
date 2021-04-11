package presentation.main.queries;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.SceneController;
import presentation.View;

import java.io.IOException;

public class QueriesView implements View {

    @FXML
    private Button backButton;

    @FXML
    void initialize()  {
        backButton.setOnAction(event -> {
            try {
                SceneController.load("start.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
