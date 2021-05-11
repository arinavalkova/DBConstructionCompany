package presentation.main.start;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.View;

public class StartView implements View {

    private StartViewModel startViewModel = new StartViewModel();


    @FXML
    private Button managerWindowButton;

    @FXML
    private Button chiefWindowButton;

    @FXML
    private Button adminWindowButton;

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
        managerWindowButton.setOnAction(event -> startViewModel.startManagerWindow());
        chiefWindowButton.setOnAction(event -> startViewModel.startChiefWindow());
        adminWindowButton.setOnAction(event -> startViewModel.loadAdminWindow());
    }
}
