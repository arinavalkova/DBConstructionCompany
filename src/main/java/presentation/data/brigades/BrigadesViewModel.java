package presentation.data.brigades;

import data.tables.brigades.BrigadeAndEmployeesTableImpl;
import data.tables.brigades.BrigadeAndForemanTableImpl;
import data.tables.people.PeopleAndProfessionsTableImpl;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
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
import java.util.ArrayList;

public class BrigadesViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final LoadTestDataUseCase loadTestDataUseCase;

    private final BrigadeAndEmployeesTableImpl brigadeAndEmployeesTable = new BrigadeAndEmployeesTableImpl();
    private final BrigadeAndForemanTableImpl brigadeAndForemanTable = new BrigadeAndForemanTableImpl();
    private final PeopleAndProfessionsTableImpl peopleAndProfessionsTable = new PeopleAndProfessionsTableImpl();

    public BrigadesViewModel() {
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

    public void loadEmployeesPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(peopleAndProfessionsTable, 200),
                "tableShower.fxml",
                pane
        );
    }

    public void loadBrigadesPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(brigadeAndEmployeesTable, brigadeAndForemanTable),
                "customTableEditor.fxml",
                pane
        );
    }
}
