package presentation.main.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManagerView {

    private final ManagerViewModel managerViewModel = new ManagerViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button peopleEditingButton;

    @FXML
    private Button organizationsEditingButton;

    @FXML
    private Button brigadesEditingButton;

    @FXML
    void initialize() {
        initButtons();
    }

    private void initButtons() {
        backButton.setOnAction(event -> managerViewModel.loadStartWindow());
        peopleEditingButton.setOnAction(event -> managerViewModel.loadPeopleEditingWindow());
        organizationsEditingButton.setOnAction(event -> managerViewModel.loadOrganizationsEditingWindow());
        brigadesEditingButton.setOnAction(event -> managerViewModel.loadBrigadesEditingWindow());
    }

}
