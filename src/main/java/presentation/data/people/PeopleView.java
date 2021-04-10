package presentation.data.people;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PeopleView {

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
    Label answerLabel;

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
    }

    private void bind() {
        answerLabel.textProperty().bind(peopleViewModel.getAnswerProperty());
    }

    private void initButtons() {
        backButton.setOnAction(event -> peopleViewModel.goBack());
        nextButton.setOnAction(event -> {
            //TODO
        });

        loadTestDataButton.setOnAction(event -> peopleViewModel.loadTestData());
    }
}
