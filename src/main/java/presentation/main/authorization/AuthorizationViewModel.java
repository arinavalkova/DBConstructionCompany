package presentation.main.authorization;

import domain.AnswerReceiver;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.UserAndRoleRow;
import domain.usecases.nonParameterized.queries.GetUsersAndRolesUseCase;
import domain.usecases.parameterized.AuthorizeRoleUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.SceneController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthorizationViewModel implements DataReceiver, AnswerReceiver  {

    private final StringProperty answerProperty = new SimpleStringProperty();
    private final Property<ObservableList<Object>> listProperty =
            new SimpleListProperty<>(FXCollections.observableArrayList());
    private final GetUsersAndRolesUseCase getUsersAndRolesUseCase =
            new GetUsersAndRolesUseCase(this, listProperty);
    private final AuthorizeRoleUseCase authorizeRoleUseCase = new AuthorizeRoleUseCase(this);

    public Property<ObservableList<Object>> getListProperty() {
        return listProperty;
    }

    public void authorize(String userName, String password) {
        Integer roleId = (Integer) authorizeRoleUseCase.invoke(userName, password);
       if (roleId != null) {
           loadRoleWindow(roleId);
       }
    }

    Map<Integer, String> rolesWindowMap = Stream.of(new Object[][] {
            { 0, "admin/allAdmin.fxml" },
            { 1, "manager.fxml" },
            { 2, "chief.fxml" },
            { 3, "queries.fxml" }
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    private void loadRoleWindow(int id) {
        try {
            SceneController.load(rolesWindowMap.get(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public void loadData() {
        getUsersAndRolesUseCase.invoke();
    }

    @Override
    public void onDataSuccess(Object object, Property<ObservableList<Object>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList((List<UserAndRoleRow>) object)));
    }

    @Override
    public void onDataError(String answer, Property<ObservableList<Object>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList()));
    }

    @Override
    public void onAnswerSuccess(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    @Override
    public void onAnswerError(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }
}
