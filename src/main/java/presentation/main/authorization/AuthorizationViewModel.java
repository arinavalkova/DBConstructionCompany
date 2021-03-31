package presentation.main.authorization;

import data.JDBCConnection;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import presentation.SceneController;

import java.io.IOException;

public class AuthorizationViewModel {

    private final StringProperty answerProperty = new SimpleStringProperty();

    public void authorize(String userName, String password) {
        answerProperty.setValue("Connecting...");
        Thread thread = new Thread(() -> {
            if (JDBCConnection.establishJDBCConnection(userName, password)) {
                Platform.runLater(() -> answerProperty.setValue("Connected..."));
                try {
                    SceneController.load("start.fxml");
                } catch (IOException e) {
                    Platform.runLater(() -> answerProperty.setValue("Error..."));
                }
            } else {
                Platform.runLater(() -> answerProperty.setValue("Problems with connection..."));
            }
        });
        thread.start();
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }
}
