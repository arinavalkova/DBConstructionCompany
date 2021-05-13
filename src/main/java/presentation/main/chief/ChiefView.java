package presentation.main.chief;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChiefView {

    private final ChiefViewModel chiefViewModel = new ChiefViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button estimateEditingButton;

    @FXML
    private Button schedulesEditorButton;

    @FXML
    private Button reportsEditorButton;

    @FXML
    void initialize() {
        initButtons();
    }

    private void initButtons() {
        backButton.setOnAction(event -> chiefViewModel.loadStartWindow());
        estimateEditingButton.setOnAction(event -> chiefViewModel.loadEstimateEditingWindow());
        schedulesEditorButton.setOnAction(event -> chiefViewModel.loadSchedulesEditingWindow());
        reportsEditorButton.setOnAction(event -> chiefViewModel.loadReportsEditingWindow());
    }
}
