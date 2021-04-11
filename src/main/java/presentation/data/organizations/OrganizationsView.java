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
    private Button nextButton;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private AnchorPane organizationsPane;

    @FXML
    private AnchorPane managementsPane;

    @FXML
    private Label answerLabel;

    @FXML
    private FlowPane sectorsShowerPane;

    @FXML
    void initialize() {
        bind();
        initButtons();
        initPanes();
    }

    private void bind() {
        answerLabel.textProperty().bind(organizationsViewModel.getAnswerProperty());
    }

    private void initButtons() {
        backButton.setOnAction(event -> organizationsViewModel.goBack());
        nextButton.setOnAction(event -> organizationsViewModel.goNext());
        loadTestDataButton.setOnAction(event -> organizationsViewModel.loadTestData());
    }

    private void initPanes() {
        organizationsViewModel.loadSectorsTable(sectorsShowerPane);
        organizationsViewModel.loadOrganizationsTable(organizationsPane);
        organizationsViewModel.loadManagementsTable(managementsPane);
    }
}
