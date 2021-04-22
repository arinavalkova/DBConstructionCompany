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

    private final LoadTestDataUseCase loadTestDataUseCase = new LoadTestDataUseCase(this);

    public void startDataEditingWindow() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startQueriesWindow() {
        try {
            SceneController.load("queries.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public void loadTestData() {
        loadTestDataUseCase.invoke();
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
