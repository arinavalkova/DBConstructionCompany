package presentation.main.start;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.View;

public class StartView implements View {

    private StartViewModel startViewModel = new StartViewModel();

    @FXML
    private Button dataEditingButton;

    @FXML
    private Button queriesButton;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private Label answerLabel;

    @FXML
    void initialize() {
        bind();
        initButtons();
    }

    private void bind() {
        answerLabel.textProperty().bind(startViewModel.getAnswerProperty());
    }

    private void initButtons() {
        dataEditingButton.setOnAction(event -> startViewModel.startDataEditingWindow());
        queriesButton.setOnAction(event -> startViewModel.startQueriesWindow());
        loadTestDataButton.setOnAction(event -> startViewModel.loadTestData());
    }
}
