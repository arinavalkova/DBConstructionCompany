package presentation.data.booker;

import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TechniquesTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import data.tables.people.ProfessionsTableImpl;
import domain.AnswerReceiver;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;

import java.io.IOException;

public class BookerViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final LoadTestDataUseCase loadTestDataUseCase;

    private final ProfessionsTableImpl professionsTable = new ProfessionsTableImpl();
    private final MaterialsTableImpl materialsTable = new MaterialsTableImpl();
    private final TechniquesTableImpl techniquesTable = new TechniquesTableImpl();
    private final TypesOfJobsTableImpl typesOfJobsTable = new TypesOfJobsTableImpl();

    public BookerViewModel() {
        this.loadTestDataUseCase = new LoadTestDataUseCase(this);
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

    @Override
    public void onAnswerSuccess(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    @Override
    public void onAnswerError(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public void loadTestData() {
        loadTestDataUseCase.invoke();
    }

    public void loadProfessionsPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(professionsTable),
                "simpleTableEditor.fxml",
                pane
        );
    }

    public void loadMaterialsPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(materialsTable),
                "simpleTableEditor.fxml",
                pane
        );
    }


    public void loadTechniquesPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(techniquesTable),
                "simpleTableEditor.fxml",
                pane
        );
    }

    public void loadTypesOfJobsPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(typesOfJobsTable),
                "simpleTableEditor.fxml",
                pane
        );
    }
}
