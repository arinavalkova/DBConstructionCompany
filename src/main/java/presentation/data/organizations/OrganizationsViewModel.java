package presentation.data.organizations;

import data.tables.organizations.ManagementsAndSectorsTableImpl;
import data.tables.organizations.ManagementsTableImpl;
import data.tables.organizations.OrganizationsAndManagementTableImpl;
import data.tables.organizations.OrganizationsTableImpl;
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

public class OrganizationsViewModel {

    private final ManagementsAndSectorsTableImpl managementsAndSectorsTable = new ManagementsAndSectorsTableImpl();
    private final ManagementsTableImpl managementsTable = new ManagementsTableImpl();
    private final OrganizationsAndManagementTableImpl organizationsAndManagementTable = new OrganizationsAndManagementTableImpl();
    private final OrganizationsTableImpl organizationsTable = new OrganizationsTableImpl();
    private final SectorsTableImpl sectorsTable = new SectorsTableImpl();

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadManagementsTable(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(managementsAndSectorsTable, managementsTable),
                "customTableEditor.fxml",
                pane
        );
    }

    public void loadOrganizationsTable(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(organizationsAndManagementTable, organizationsTable),
                "customTableEditor.fxml",
                pane
        );
    }

    public void loadSectorsTable(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(sectorsTable, 200),
                "tableShower.fxml",
                pane
        );
    }
}
