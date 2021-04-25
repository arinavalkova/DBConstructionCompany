package presentation.data.people;

import data.tables.people.*;
import data.tables.query.QueryProfessionsTableImpl;
import data.tables.query.QuerySectorsTableImpl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;
import presentation.table.sender.SenderQueryView;
import presentation.table.show.ShowTableView;

import java.io.IOException;

public class PeopleViewModel {

    private final ProfessionsTableImpl professionsTable = new ProfessionsTableImpl();
    private final PeopleAndProfessionsTableImpl peopleAndProfessionsTable = new PeopleAndProfessionsTableImpl();
    private final BossAndEmployeesTableImpl bossAndEmployeesTable = new BossAndEmployeesTableImpl();
    private final SectorAndBossTableImpl sectorAndBossTable = new SectorAndBossTableImpl();
    private final SectorsTableImpl sectorsTable = new SectorsTableImpl();

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                new ShowTableView(sectorsTable, 200),
                "tableShower.fxml",
                sectorsPane
        );
    }

    public void goToAdmin() {
        try {
            SceneController.load("admin/peopleAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSectorsAndBossTable(Pane sectorsAndBossPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(sectorAndBossTable),
                "simpleTableEditor.fxml",
                sectorsAndBossPane
        );
    }

    public void loadProfQueryPane(Pane proffQueryPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SenderQueryView(new QueryProfessionsTableImpl(), "Запрос на профессию"),
                "querySender.fxml",
                proffQueryPane
        );
    }

    public void loadSectorsQueryPane(Pane sectorsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SenderQueryView(new QuerySectorsTableImpl(), "Запрос на участок"),
                "querySender.fxml",
                sectorsPane
        );
    }
}
