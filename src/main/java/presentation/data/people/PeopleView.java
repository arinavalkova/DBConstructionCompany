package presentation.data.people;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import presentation.View;

public class PeopleView implements View {

    private final PeopleViewModel peopleViewModel = new PeopleViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private AnchorPane bossAndEmployeesPane;

    @FXML
    private AnchorPane professionsPane;

    @FXML
    private AnchorPane peopleAndProfessionsPane;

    @FXML
    private Label answerLabel;

    @FXML
    private AnchorPane sectorsPane;

    @FXML
    void initialize() {
        bind();
        initButtons();
        loadTables();
    }

    private void loadTables() {
        peopleViewModel.loadProfessionsTable(professionsPane);
        peopleViewModel.loadPeopleAndProfessionsTable(peopleAndProfessionsPane);
        peopleViewModel.loadBossAndEmployeeTable(bossAndEmployeesPane);
        peopleViewModel.loadSectorsTable(sectorsPane);
    }

    private void bind() {
        answerLabel.textProperty().bind(peopleViewModel.getAnswerProperty());
    }

    private void initButtons() {
        backButton.setOnAction(event -> peopleViewModel.goBack());
        nextButton.setOnAction(event -> peopleViewModel.goNext());
        loadTestDataButton.setOnAction(event -> peopleViewModel.loadTestData());
    }
}
