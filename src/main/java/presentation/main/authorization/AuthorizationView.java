package presentation.main.authorization;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.View;

public class AuthorizationView implements View {

    private AuthorizationViewModel authorizationViewModel = new AuthorizationViewModel();

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authorizeButton;

    @FXML
    private Label answerLabel;

    @FXML
    void initialize() {
        answerLabel.textProperty().bind(authorizationViewModel.getAnswerProperty());
        authorizeButton.setOnAction(event -> authorizationViewModel.authorize(
                userNameField.getText(),
                passwordField.getText()
        ));
    }
}