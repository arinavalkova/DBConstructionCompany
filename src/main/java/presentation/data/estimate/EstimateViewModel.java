package presentation.data.estimate;

import data.tables.booker.TechniquesTableImpl;
import data.tables.estimate.ObjectsAndTechnicsTableImpl;
import data.tables.estimate.ObjectsTableImpl;
import data.tables.people.SectorsTableImpl;
import domain.AnswerReceiver;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.custom.CustomTableView;
import presentation.table.show.ShowTableView;

import java.io.IOException;

public class EstimateViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final LoadTestDataUseCase loadTestDataUseCase;

    public EstimateViewModel() {
        this.loadTestDataUseCase = new LoadTestDataUseCase(this);
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goNext() {
        //TODO
    }

    public void loadTestData() {
        loadTestDataUseCase.invoke();
    }

    public void loadAreasPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new SectorsTableImpl(), 200),
                "tableShower.fxml",
                pane
        );
    }

    public void loadTechnicsPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new TechniquesTableImpl(), 200),
                "tableShower.fxml",
                pane
        );
    }

    public void loadEstimatePane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(new ObjectsAndTechnicsTableImpl(), new ObjectsTableImpl()),
                "customTableEditor.fxml",
                pane
        );
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
