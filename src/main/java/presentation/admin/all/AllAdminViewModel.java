package presentation.admin.all;

import data.tables.authorize.RolesTableImpl;
import data.tables.authorize.UsersAndRolesTableImpl;
import domain.AnswerReceiver;
import domain.rows.admin.UsersAndRolesRow;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import domain.usecases.parameterized.CreateNewUserByRoleUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.show.ShowTableView;

import java.io.IOException;
import java.util.ArrayList;

public class AllAdminViewModel implements AnswerReceiver{
    
    private final InsertRowUseCase insertRowUseCase = new InsertRowUseCase(new UsersAndRolesTableImpl(), this);
    private final StringProperty answerProperty = new SimpleStringProperty();

    private final CreateNewUserByRoleUseCase createNewUserByRoleUseCase = new CreateNewUserByRoleUseCase(this);

    private final LoadTestDataUseCase loadTestDataUseCase = new LoadTestDataUseCase(this);

    public void addNewUser(String userName, String password, int roleId) {
        Thread thread = new Thread(() -> {
            createNewUserByRoleUseCase.invoke(userName, password, roleId);
            ArrayList<String> list = new ArrayList<>();
            list.add("0");
            list.add(userName);
            list.add(String.valueOf(roleId));
            insertRowUseCase.invoke(new UsersAndRolesRow(list));
        });
        thread.start();
    }

    public void loadRolesPane(Pane rolePane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new RolesTableImpl(), 200),
                "tableShower.fxml",
                rolePane
        );
    }

    public void loadUsersAndRolesPane(FlowPane usersAndRolesPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new UsersAndRolesTableImpl(), 200),
                "tableShower.fxml",
                usersAndRolesPane
        );
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    @Override
    public void onAnswerSuccess(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    @Override
    public void onAnswerError(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    public void loadTestData() {
        loadTestDataUseCase.invoke();
    }

    public void loadAdminPeopleEditorButton() {
        try {
            SceneController.load("admin/peopleAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAdminWorkEditorButton() {
        try {
            SceneController.load("admin/workAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBack() {
        try {
            SceneController.load("authorization.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
