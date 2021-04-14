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
        ArrayList<String> fieldNames = new ArrayList<>();
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("professionId");

        ArrayList<String> columnsNames = new ArrayList<>();
        columnsNames.add("Id");
        columnsNames.add("Name");
        columnsNames.add("Profession id");

        ShowTableView showTableView = new ShowTableView(
                peopleAndProfessionsTable,
                fieldNames,
                columnsNames,
                "People and professions",
                200
        );

        SceneController.loadControllerToFXMLAndPane(showTableView, "tableShower.fxml", pane);
    }

    public void loadBrigadesPane(Pane pane) {
        ArrayList<String> firstFieldsNames = new ArrayList<>();
        firstFieldsNames.add("id");
        firstFieldsNames.add("brigadeId");
        firstFieldsNames.add("employeeId");

        ArrayList<String> firstColumnNames = new ArrayList<>();
        firstColumnNames.add("Id");
        firstColumnNames.add("Brigade id");
        firstColumnNames.add("Employee id");

        ArrayList<String> secondFieldsNames = new ArrayList<>();
        secondFieldsNames.add("id");
        secondFieldsNames.add("name");
        secondFieldsNames.add("foremanId");

        ArrayList<String> secondColumnNames = new ArrayList<>();
        secondColumnNames.add("Id");
        secondColumnNames.add("Name");
        secondColumnNames.add("Foreman id");

        CustomTableView customTableView = new CustomTableView(
                brigadeAndEmployeesTable,
                brigadeAndForemanTable,
                firstFieldsNames,
                secondFieldsNames,
                firstColumnNames,
                secondColumnNames,
                "Brigades and employees",
                "Brigades and foreman",
                null
        );

        SceneController.loadControllerToFXMLAndPane(customTableView, "customTableEditor.fxml", pane);
    }
}
