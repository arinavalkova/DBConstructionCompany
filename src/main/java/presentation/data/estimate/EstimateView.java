package presentation.data.estimate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import presentation.View;

public class EstimateView implements View {

    private final EstimateViewModel estimateViewModel = new EstimateViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private AnchorPane technicsPane;

    @FXML
    private Label answerLabel;

    @FXML
    private AnchorPane areasPane;

    @FXML
    private AnchorPane estimatePane;

    @FXML
    void initialize() {
        bind();
        initButtons();
        initPanes();
    }

    private void initPanes() {
        estimateViewModel.loadAreasPane(areasPane);
        estimateViewModel.loadTechnicsPane(technicsPane);
        estimateViewModel.loadEstimatePane(estimatePane);
    }

    private void bind() {
        answerLabel.textProperty().bind(estimateViewModel.getAnswerProperty());
    }

    private void initButtons() {
        backButton.setOnAction(event -> estimateViewModel.goBack());
        nextButton.setOnAction(event -> estimateViewModel.goNext());
        loadTestDataButton.setOnAction(event -> estimateViewModel.loadTestData());
    }
}
