package presentation.data.people;

import data.tables.people.BossAndEmployeesTableImpl;
import data.tables.people.PeopleAndProfessionsTableImpl;
import data.tables.people.ProfessionsTableImpl;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;

import java.io.IOException;
import java.util.ArrayList;

public class PeopleViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final LoadTestDataUseCase loadTestDataUseCase;

    private final ProfessionsTableImpl professionsTable = new ProfessionsTableImpl();
    private final PeopleAndProfessionsTableImpl peopleAndProfessionsTable = new PeopleAndProfessionsTableImpl();
    private final BossAndEmployeesTableImpl bossAndEmployeesTable = new BossAndEmployeesTableImpl();

    public PeopleViewModel() {
        ArrayList<DataBaseRepository> dataBaseRepositoryArrayList = new ArrayList<>();
        dataBaseRepositoryArrayList.add(professionsTable);
        dataBaseRepositoryArrayList.add(peopleAndProfessionsTable);
        dataBaseRepositoryArrayList.add(bossAndEmployeesTable);
        this.loadTestDataUseCase = new LoadTestDataUseCase(dataBaseRepositoryArrayList, this);
    }

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        FXMLLoader fxmlLoader = SceneController.getLoader("simpleTableEditor.fxml");
        fxmlLoader.setController(simpleTableController);

        AnchorPane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bossAndEmployeesPane.getChildren().add(pane);
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

        FXMLLoader fxmlLoader = SceneController.getLoader("simpleTableEditor.fxml");
        fxmlLoader.setController(simpleTableController);

        AnchorPane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        peopleAndProfessionsPane.getChildren().add(pane);
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

        FXMLLoader fxmlLoader = SceneController.getLoader("simpleTableEditor.fxml");
        fxmlLoader.setController(simpleTableController);

        AnchorPane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        professionsPane.getChildren().add(pane);
    }
}
