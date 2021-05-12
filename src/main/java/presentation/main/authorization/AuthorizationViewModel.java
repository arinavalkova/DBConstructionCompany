package presentation.main.authorization;

import data.Coder;
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
        answerProperty.setValue(Coder.encodingRUS("Авторизация..."));
        Thread thread = new Thread(() -> {
            if (JDBCConnection.establishJDBCConnection(userName, password)) {
                Platform.runLater(() -> answerProperty.setValue(Coder.encodingRUS("Авторизация успешна...")));
                try {
                    SceneController.load("sqlAuthorization.fxml");
                } catch (IOException e) {
                    Platform.runLater(() -> answerProperty.setValue(
                            Coder.encodingRUS("Непредвиденная ошибка. Попробуйте еще раз в другое время...")));
                }
            } else {
                Platform.runLater(() -> answerProperty.setValue(
                        Coder.encodingRUS("Проблемы с подключением. Проверьте VPN подключение...")));
            }
        });
        thread.start();
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }
}
