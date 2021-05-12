package presentation.admin.all;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import presentation.View;

public class AllAdminView implements View {

    private final AllAdminViewModel allAdminViewModel = new AllAdminViewModel();

    @FXML
    private Button loadTestDataButton;

    @FXML
    private Label answerLabel;

    @FXML
    private FlowPane rolesPane;

    @FXML
    private FlowPane usersAndRolesPane;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField roleIdField;

    @FXML
    private Button addButton;

    @FXML
    private Label label;

    @FXML
    private Button adminPeopleEditorButton;

    @FXML
    private Button adminWorkEditorButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        bind();
        initPanes();
        initButtons();
    }

    private void bind() {
        answerLabel.textProperty().bind(allAdminViewModel.getAnswerProperty());
    }

    private void initButtons() {
        addButton.setOnAction(event -> {
            allAdminViewModel.addNewUser(
                    userNameField.getText(),
                    Integer.parseInt(roleIdField.getText())
            );
            userNameField.setText("");
        });

        backButton.setOnAction(event -> allAdminViewModel.goBack());

        loadTestDataButton.setOnAction(event -> allAdminViewModel.loadTestData());
        adminPeopleEditorButton.setOnAction(event -> allAdminViewModel.loadAdminPeopleEditorButton());
        adminWorkEditorButton.setOnAction(event -> allAdminViewModel.loadAdminWorkEditorButton());
    }

    private void initPanes() {
        allAdminViewModel.loadRolesPane(rolesPane);
        allAdminViewModel.loadUsersAndRolesPane(usersAndRolesPane);
    }
}
