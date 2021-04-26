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
    private AnchorPane technicsPane;

    @FXML
    private AnchorPane areasPane;

    @FXML
    private AnchorPane estimatePane;

    @FXML
    private AnchorPane queryTechnicsPane;

    @FXML
    void initialize() {
        initButtons();
        initPanes();
    }

    private void initPanes() {
        estimateViewModel.loadAreasPane(areasPane);
        estimateViewModel.loadTechnicsPane(technicsPane);
        estimateViewModel.loadEstimatePane(estimatePane);
        estimateViewModel.loadQueryTechnicsPane(queryTechnicsPane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> estimateViewModel.goBack());
    }
}
