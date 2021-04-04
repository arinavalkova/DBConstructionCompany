package presentation.main.data.editing;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DataEditingView {

    private DataEditingViewModel dataEditingViewModel = new DataEditingViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button peopleEditingButton;

    @FXML
    void initialize() {
        initButtons();
    }

    private void initButtons() {
        backButton.setOnAction(event -> dataEditingViewModel.loadStartWindow());
        peopleEditingButton.setOnAction(event -> dataEditingViewModel.loadPeopleEditingWindow());
    }
}
