package presentation.data.organizations;

import data.tables.organizations.ManagementsAndSectorsTableImpl;
import data.tables.organizations.ManagementsTableImpl;
import data.tables.organizations.OrganizationsAndManagementTableImpl;
import data.tables.organizations.OrganizationsTableImpl;
import data.tables.people.SectorsTableImpl;
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

public class OrganizationsViewModel implements AnswerReceiver {

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final LoadTestDataUseCase loadTestDataUseCase;

    private final ManagementsAndSectorsTableImpl managementsAndSectorsTable = new ManagementsAndSectorsTableImpl();
    private final ManagementsTableImpl managementsTable = new ManagementsTableImpl();
    private final OrganizationsAndManagementTableImpl organizationsAndManagementTable = new OrganizationsAndManagementTableImpl();
    private final OrganizationsTableImpl organizationsTable = new OrganizationsTableImpl();
    private final SectorsTableImpl sectorsTable = new SectorsTableImpl();

    public OrganizationsViewModel() {
        ArrayList<DataBaseRepository> dataBaseRepositoryArrayList = new ArrayList<>();
        dataBaseRepositoryArrayList.add(managementsAndSectorsTable);
        dataBaseRepositoryArrayList.add(managementsTable);
        dataBaseRepositoryArrayList.add(organizationsAndManagementTable);
        dataBaseRepositoryArrayList.add(organizationsTable);

        this.loadTestDataUseCase = new LoadTestDataUseCase(dataBaseRepositoryArrayList, this);
    }

    public void loadTestData() {
        loadTestDataUseCase.invoke();
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

    public void loadManagementsTable(Pane pane) {
        ArrayList<String> firstFieldsNames = new ArrayList<>();
        firstFieldsNames.add("id");
        firstFieldsNames.add("managementId");
        firstFieldsNames.add("sectorId");

        ArrayList<String> firstColumnNames = new ArrayList<>();
        firstColumnNames.add("Id");
        firstColumnNames.add("Management id");
        firstColumnNames.add("Sector id");

        ArrayList<String> secondFieldsNames = new ArrayList<>();
        secondFieldsNames.add("id");
        secondFieldsNames.add("name");

        ArrayList<String> secondColumnNames = new ArrayList<>();
        secondColumnNames.add("Id");
        secondColumnNames.add("Name");

        CustomTableView customTableView = new CustomTableView(
               managementsAndSectorsTable,
               managementsTable,
               firstFieldsNames,
               secondFieldsNames,
               firstColumnNames,
               secondColumnNames,
               "Managements and sectors",
               "Sectors"
        );

        SceneController.loadControllerToFXMLAndPane(customTableView, "customTableEditor.fxml", pane);
    }

    public void loadOrganizationsTable(Pane pane) {
        ArrayList<String> firstFieldsNames = new ArrayList<>();
        firstFieldsNames.add("id");
        firstFieldsNames.add("organizationId");
        firstFieldsNames.add("managementId");

        ArrayList<String> firstColumnNames = new ArrayList<>();
        firstColumnNames.add("Id");
        firstColumnNames.add("Organization id");
        firstColumnNames.add("Management id");

        ArrayList<String> secondFieldsNames = new ArrayList<>();
        secondFieldsNames.add("id");
        secondFieldsNames.add("name");

        ArrayList<String> secondColumnNames = new ArrayList<>();
        secondColumnNames.add("Id");
        secondColumnNames.add("Name");

        CustomTableView customTableView = new CustomTableView(
                organizationsAndManagementTable,
                organizationsTable,
                firstFieldsNames,
                secondFieldsNames,
                firstColumnNames,
                secondColumnNames,
                "Organizat and management",
                "Organizations"
        );

        SceneController.loadControllerToFXMLAndPane(customTableView, "customTableEditor.fxml", pane);
    }

    public void loadSectorsTable(Pane pane) {
        ArrayList<String> fieldsNames = new ArrayList<>();
        fieldsNames.add("id");
        fieldsNames.add("name");

        ArrayList<String> columnsNames = new ArrayList<>();
        columnsNames.add("Id");
        columnsNames.add("Name");

        ShowTableView showTableView = new ShowTableView(
                sectorsTable,
                fieldsNames,
                columnsNames,
                "Sectors",
                200
        );

        SceneController.loadControllerToFXMLAndPane(showTableView, "tableShower.fxml", pane);
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
}
