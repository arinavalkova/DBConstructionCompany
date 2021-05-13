package presentation.main.authorization;

import domain.rows.Row;
import domain.rows.queries.UserAndRoleRow;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentation.View;

public class AuthorizationView implements View {

    private AuthorizationViewModel authorizationViewModel = new AuthorizationViewModel();

    @FXML
    private ChoiceBox<Object> userChoiceBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authorizeButton;

    @FXML
    private Label answerLabel;

    @FXML
    void initialize() {
        bind();
        authorizeButton.setOnAction(event -> authorizationViewModel.authorize(
                userChoiceBox.getValue().toString(),
                passwordField.getText()
        ));

        ChangeListener<? super Object> choiceBoxSelectionChangeListener = (
                ChangeListener<Object>) (observable, oldValue, newValue) -> {
            UserAndRoleRow userAndRoleRow = (UserAndRoleRow) newValue;
            passwordField.setText(userAndRoleRow.getPassword());
        };
        userChoiceBox.getSelectionModel().selectedItemProperty().addListener(choiceBoxSelectionChangeListener);
        authorizationViewModel.loadData();
    }

    void bind() {
        answerLabel.textProperty().bind(authorizationViewModel.getAnswerProperty());
        userChoiceBox.itemsProperty().bind(authorizationViewModel.getListProperty());
    }
}