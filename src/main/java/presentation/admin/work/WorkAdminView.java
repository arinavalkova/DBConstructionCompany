package presentation.admin.work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WorkAdminView {

    private final WorkAdminViewModel workAdminViewModel = new WorkAdminViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane materialsPane;

    @FXML
    private AnchorPane typesOfJobsPane;

    @FXML
    private AnchorPane techiquesPane;

    @FXML
    private AnchorPane queryMaterialsPane;

    @FXML
    private AnchorPane queryTechnicsPane;

    @FXML
    void initialize() {
        initButtons();
        loadTables();
    }

    private void loadTables() {
        workAdminViewModel.loadTechniquesPane(techiquesPane);
        workAdminViewModel.loadTypesOfJobsPane(typesOfJobsPane);
        workAdminViewModel.loadMaterialsPane(materialsPane);
        workAdminViewModel.loadQueryMaterialsPane(queryMaterialsPane);
        workAdminViewModel.loadQueryTechnicsPane(queryTechnicsPane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> workAdminViewModel.goBack());
    }

}

