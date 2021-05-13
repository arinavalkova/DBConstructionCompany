package presentation.main.authorization.roles;

import domain.rows.Row;
import domain.rows.queries.UserAndRoleRow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import presentation.View;

public class SqlAuthorizationView implements View {

    private final SqlAuthorizationViewModel sqlAuthorizationViewModel = new SqlAuthorizationViewModel();

    @FXML
    private ChoiceBox<Object> userChoiceBox;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    void initialize() {
        bind();
        initButton();
        loadData();
    }

    private void loadData() {
        sqlAuthorizationViewModel.loadListOfUsers();
    }

    private void initButton() {
        button.setOnAction(event -> sqlAuthorizationViewModel.authorize(userChoiceBox.getValue()));
    }

    void bind() {
        label.textProperty().bind(sqlAuthorizationViewModel.getAnswerProperty());
        userChoiceBox.itemsProperty().bind(sqlAuthorizationViewModel.getListProperty());
    }
}
