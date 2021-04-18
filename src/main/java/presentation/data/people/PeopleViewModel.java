package presentation.data.people;

import data.tables.people.*;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import presentation.SceneController;
import presentation.table.edit.custom.CustomTableView;
import presentation.table.edit.simple.SimpleTableView;
import presentation.table.show.ShowTableView;

import java.io.IOException;
import java.util.ArrayList;

public class PeopleViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final LoadTestDataUseCase loadTestDataUseCase;

    private final ProfessionsTableImpl professionsTable = new ProfessionsTableImpl();
    private final PeopleAndProfessionsTableImpl peopleAndProfessionsTable = new PeopleAndProfessionsTableImpl();
    private final BossAndEmployeesTableImpl bossAndEmployeesTable = new BossAndEmployeesTableImpl();
    private final SectorAndBossTableImpl sectorAndBossTable = new SectorAndBossTableImpl();
    private final SectorsTableImpl sectorsTable = new SectorsTableImpl();

    public PeopleViewModel() {
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

    public void loadTestData() {
        loadTestDataUseCase.invoke();
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

    public void loadBossAndEmployeeTable(AnchorPane bossAndEmployeesPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(bossAndEmployeesTable),
                "simpleTableEditor.fxml",
                bossAndEmployeesPane
        );
    }

    public void loadPeopleAndProfessionsTable(AnchorPane peopleAndProfessionsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(peopleAndProfessionsTable),
                "simpleTableEditor.fxml",
                peopleAndProfessionsPane
        );
    }

    public void loadProfessionsTable(AnchorPane professionsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(professionsTable, 200),
                "tableShower.fxml",
                professionsPane
        );
    }

    public void loadSectorsTable(AnchorPane sectorsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(sectorAndBossTable, sectorsTable),
                "customTableEditor.fxml",
                sectorsPane
        );
    }
}
