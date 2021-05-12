package presentation.main.authorization.roles;

import domain.AnswerReceiver;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.queries.GetUsersAndRolesUseCase;
import domain.usecases.parameterized.AuthorizeRoleUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.SceneController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlAuthorizationViewModel implements DataReceiver, AnswerReceiver {

    private final Property<ObservableList<Row>> listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final GetUsersAndRolesUseCase getUsersAndRolesUseCase = new GetUsersAndRolesUseCase(this, listProperty);
    private final AuthorizeRoleUseCase authorizeRoleUseCase = new AuthorizeRoleUseCase(this);

    private final StringProperty answerProperty = new SimpleStringProperty();

    public StringProperty getAnswerProperty() {
        return answerProperty;
    }

    public Property<ObservableList<Row>> getListProperty() {
        return listProperty;
    }

    public void loadListOfUsers() {
        getUsersAndRolesUseCase.invoke();
    }

    @Override
    public void onDataSuccess(Object object, Property<ObservableList<Row>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList((List<Row>) object)));
    }

    @Override
    public void onDataError(String answer, Property<ObservableList<Row>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList()));
    }

    public void authorize(Row value) {
        Thread thread = new Thread(() -> {
            authorizeRoleUseCase.invoke(value);
            loadRoleWindow(value);
        });
        thread.start();
    }

    private void loadRoleWindow(Row value) {
        try {
            SceneController.load(rolesWindowMap.get(value.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onAnswerSuccess(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    @Override
    public void onAnswerError(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    Map<Integer, String> rolesWindowMap = Stream.of(new Object[][] {
            { 0, "admin/allAdmin.fxml" },
            { 1, "manager.fxml" },
            { 2, "chief.fxml" }
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
}
