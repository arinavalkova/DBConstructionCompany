package presentation.main.data.editing;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DataEditingView {

    private DataEditingViewModel dataEditingViewModel = new DataEditingViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button characteristicsOfObjectsButton;

    @FXML
    private Button objectsCategoriesButton;

    @FXML
    private Button customersButton;

    @FXML
    void initialize() {
        initButtons();
    }

    private void initButtons() {
        backButton.setOnMouseClicked(event ->
                dataEditingViewModel.loadStartWindow());
        characteristicsOfObjectsButton.setOnMouseClicked((event ->
                dataEditingViewModel.loadCharacteristicsOfObjectsWindow()));
        objectsCategoriesButton.setOnMouseClicked(event ->
                dataEditingViewModel.loadObjectsCategoriesWindow());
        customersButton.setOnMouseClicked(event ->
                dataEditingViewModel.loadCustomersWindow());
    }
}
