package presentation.data.brigades;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import presentation.View;

public class BrigadesView implements View {

    private final BrigadesViewModel brigadesViewModel = new BrigadesViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane brigadesPane;

    @FXML
    private FlowPane employeesPane;

    @FXML
    void initialize() {
        initButtons();
        initPanes();
    }

    private void initPanes() {
        brigadesViewModel.loadEmployeesPane(employeesPane);
        brigadesViewModel.loadBrigadesPane(brigadesPane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> brigadesViewModel.goBack());
    }
}
