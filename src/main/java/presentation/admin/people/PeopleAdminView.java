package presentation.admin.people;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PeopleAdminView {

    private final PeopleAdminViewModel peopleAdminViewModel = new PeopleAdminViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane sectorsPane;

    @FXML
    private AnchorPane professionsPane;

    @FXML
    void initialize() {
        initButtons();
        loadTables();
    }

    private void loadTables() {
        peopleAdminViewModel.loadProfessionsTable(professionsPane);
        peopleAdminViewModel.loadSectorsTable(sectorsPane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> peopleAdminViewModel.goBack());
    }
}
