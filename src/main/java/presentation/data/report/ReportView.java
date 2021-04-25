package presentation.data.report;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ReportView {

    private final ReportViewModel reportViewModel = new ReportViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane factTechnicsPane;

    @FXML
    private AnchorPane factWorksPane;

    @FXML
    private AnchorPane materialsPane;

    @FXML
    private AnchorPane technicsPane;

    @FXML
    private AnchorPane workPane;

    @FXML
    private AnchorPane factMaterialsPane;

    @FXML
    void initialize() {
        initButtons();
        loadTables();
    }

    private void loadTables() {
        reportViewModel.loadFactTechnicsPane(factTechnicsPane);
        reportViewModel.loadFactWorkPane(factWorksPane);
        reportViewModel.loadFactMaterialsPane(factMaterialsPane);
        reportViewModel.loadMaterialsShowerPane(materialsPane);
        reportViewModel.loadTechnicsPane(technicsPane);
        reportViewModel.loadWorkPane(workPane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> reportViewModel.goBack());
    }
}
