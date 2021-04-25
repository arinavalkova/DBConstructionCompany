package presentation.main.data.editing;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.View;

public class DataEditingView implements View {

    private DataEditingViewModel dataEditingViewModel = new DataEditingViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button peopleEditingButton;

    @FXML
    private Button organizationsEditingButton;

    @FXML
    private Button brigadesEditingButton;

    @FXML
    private Button estimateEditingButton;

    @FXML
    private Button adminPeopleEditorButton;

    @FXML
    private Button adminWorkEditorButton;

    @FXML
    private Button schedulesEditorButton;

    @FXML
    private Button reportsEditorButton;

    @FXML
    void initialize() {
        initButtons();
    }

    private void initButtons() {
        backButton.setOnAction(event -> dataEditingViewModel.loadStartWindow());
        peopleEditingButton.setOnAction(event -> dataEditingViewModel.loadPeopleEditingWindow());
        organizationsEditingButton.setOnAction(event -> dataEditingViewModel.loadOrganizationsEditingWindow());
        brigadesEditingButton.setOnAction(event -> dataEditingViewModel.loadBrigadesEditingWindow());
        adminPeopleEditorButton.setOnAction(event -> dataEditingViewModel.loadAdminPeopleEditorButton());
        adminWorkEditorButton.setOnAction(event -> dataEditingViewModel.loadAdminWorkEditorButton());
        estimateEditingButton.setOnAction(event -> dataEditingViewModel.loadEstimateEditingWindow());
        schedulesEditorButton.setOnAction(event -> dataEditingViewModel.loadSchedulesEditingWindow());
        reportsEditorButton.setOnAction(event -> dataEditingViewModel.loadReportsEditingWindow());
    }
}
