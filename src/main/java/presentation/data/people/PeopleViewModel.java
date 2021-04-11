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
        ArrayList<DataBaseRepository> dataBaseRepositoryArrayList = new ArrayList<>();
        dataBaseRepositoryArrayList.add(professionsTable);
        dataBaseRepositoryArrayList.add(peopleAndProfessionsTable);
        dataBaseRepositoryArrayList.add(bossAndEmployeesTable);
        dataBaseRepositoryArrayList.add(sectorAndBossTable);
        dataBaseRepositoryArrayList.add(sectorsTable);

        this.loadTestDataUseCase = new LoadTestDataUseCase(dataBaseRepositoryArrayList, this);
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
        ArrayList<String> classFieldsNames = new ArrayList<>();
        classFieldsNames.add("id");
        classFieldsNames.add("bossId");
        classFieldsNames.add("employeeId");

        ArrayList<String> columnsNames = new ArrayList<>();
        columnsNames.add("Id");
        columnsNames.add("Boss id");
        columnsNames.add("Employee id");

        SimpleTableView simpleTableController = new SimpleTableView(
                bossAndEmployeesTable,
                classFieldsNames,
                columnsNames,
                "Boss and employees"
        );

        SceneController.loadControllerToFXMLAndPane(
                simpleTableController,
                "simpleTableEditor.fxml",
                bossAndEmployeesPane
        );
    }

    public void loadPeopleAndProfessionsTable(AnchorPane peopleAndProfessionsPane) {
        ArrayList<String> classFieldsNames = new ArrayList<>();
        classFieldsNames.add("id");
        classFieldsNames.add("name");
        classFieldsNames.add("professionId");

        ArrayList<String> columnsNames = new ArrayList<>();
        columnsNames.add("Id");
        columnsNames.add("Name");
        columnsNames.add("Profession id");

        SimpleTableView simpleTableController = new SimpleTableView(
                peopleAndProfessionsTable,
                classFieldsNames,
                columnsNames,
                "People and professions"
        );

        SceneController.loadControllerToFXMLAndPane(
                simpleTableController,
                "simpleTableEditor.fxml",
                peopleAndProfessionsPane
        );
    }

    public void loadProfessionsTable(AnchorPane professionsPane) {
        ArrayList<String> classFieldsNames = new ArrayList<>();
        classFieldsNames.add("id");
        classFieldsNames.add("name");

        ArrayList<String> columnsNames = new ArrayList<>();
        columnsNames.add("Id");
        columnsNames.add("Name");

        SimpleTableView simpleTableController = new SimpleTableView(
                professionsTable,
                classFieldsNames,
                columnsNames,
                "Professions"
        );

        SceneController.loadControllerToFXMLAndPane(
               simpleTableController,
                "simpleTableEditor.fxml",
                professionsPane
        );
    }

    public void loadSectorsTable(AnchorPane sectorsPane) {
        ArrayList<String> firstFieldsNames = new ArrayList<>();
        firstFieldsNames.add("id");
        firstFieldsNames.add("sectorId");
        firstFieldsNames.add("bossId");

        ArrayList<String> secondFieldsNames = new ArrayList<>();
        secondFieldsNames.add("id");
        secondFieldsNames.add("name");

        ArrayList<String> firstColumnNames = new ArrayList<>();
        firstColumnNames.add("Id");
        firstColumnNames.add("Sector id");
        firstColumnNames.add("Boss id");

        ArrayList<String> secondColumnNames = new ArrayList<>();
        secondColumnNames.add("Id");
        secondColumnNames.add("Name");

        CustomTableView customTableView = new CustomTableView(
                sectorAndBossTable,
                sectorsTable,
                firstFieldsNames,
                secondFieldsNames,
                firstColumnNames,
                secondColumnNames,
                "Sectors and boss",
                "Sectors"
        );

        SceneController.loadControllerToFXMLAndPane(customTableView, "customTableEditor.fxml", sectorsPane);
    }
}
