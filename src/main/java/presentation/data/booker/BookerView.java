package presentation.data.booker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import presentation.View;

public class BookerView implements View {

    private final BookerViewModel bookerViewModel = new BookerViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private AnchorPane materialsPane;

    @FXML
    private Label answerLabel;

    @FXML
    private AnchorPane professionsPane;

    @FXML
    private AnchorPane typesOfJobsPane;

    @FXML
    private AnchorPane techiquesPane;

    @FXML
    void initialize() {
        bind();
        initButtons();
        initPanes();
    }

    private void initPanes() {
        bookerViewModel.loadProfessionsPane(professionsPane);
        bookerViewModel.loadMaterialsPane(materialsPane);
        bookerViewModel.loadTechniquesPane(techiquesPane);
        bookerViewModel.loadTypesOfJobsPane(typesOfJobsPane);
    }

    private void bind() {
        answerLabel.textProperty().bind(bookerViewModel.getAnswerProperty());
    }

    private void initButtons() {
        backButton.setOnAction(event -> bookerViewModel.goBack());
        nextButton.setOnAction(event -> bookerViewModel.goNext());
        loadTestDataButton.setOnAction(event -> bookerViewModel.loadTestData());
    }
}
