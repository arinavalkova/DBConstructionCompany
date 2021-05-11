package presentation.main.start;

import domain.AnswerReceiver;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import presentation.SceneController;

import java.io.IOException;

public class StartViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

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

    public void loadAdminWindow() {
        try {
            SceneController.load("admin/allAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startManagerWindow() {
        try {
            SceneController.load("manager.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startChiefWindow() {
        try {
            SceneController.load("chief.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
