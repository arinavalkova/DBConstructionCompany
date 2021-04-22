package presentation.data.organizations;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import presentation.View;

public class OrganizationsView implements View {

    private final OrganizationsViewModel organizationsViewModel = new OrganizationsViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane organizationsPane;

    @FXML
    private AnchorPane managementsPane;

    @FXML
    private FlowPane sectorsShowerPane;

    @FXML
    private Button peopleAdminButton;

    @FXML
    private Button peopleEditorButton;

    @FXML
    void initialize() {
        initButtons();
        initPanes();
    }

    private void initButtons() {
        backButton.setOnAction(event -> organizationsViewModel.goBack());
        peopleAdminButton.setOnAction(event -> organizationsViewModel.goToPeopleAdminWindow());
        peopleEditorButton.setOnAction(event -> organizationsViewModel.goToPeopleEditorWindow());
    }

    private void initPanes() {
        organizationsViewModel.loadSectorsTable(sectorsShowerPane);
        organizationsViewModel.loadOrganizationsTable(organizationsPane);
        organizationsViewModel.loadManagementsTable(managementsPane);
    }
}
