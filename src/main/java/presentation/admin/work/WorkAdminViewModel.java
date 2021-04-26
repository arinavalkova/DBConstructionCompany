package presentation.admin.work;

import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TechniquesTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import data.tables.query.QueryMaterialsTableImpl;
import data.tables.query.QueryTechnicsTableImpl;
import domain.DataBaseRepository;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;
import presentation.table.receiver.insert.ReceiverInsertView;

import java.io.IOException;

public class WorkAdminViewModel {

    private final DataBaseRepository technicsRepository = new TechniquesTableImpl();
    private final DataBaseRepository materialsRepository = new MaterialsTableImpl();

    public void loadTechniquesPane(Pane techniquesPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(technicsRepository),
                "simpleTableEditor.fxml",
                techniquesPane
        );
    }

    public void loadMaterialsPane(Pane materialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(materialsRepository),
                "simpleTableEditor.fxml",
                materialsPane
        );
    }

    public void loadTypesOfJobsPane(Pane typesOfJobsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new TypesOfJobsTableImpl()),
                "simpleTableEditor.fxml",
                typesOfJobsPane
        );
    }

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadQueryMaterialsPane(Pane queryMaterialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ReceiverInsertView(new QueryMaterialsTableImpl(), materialsRepository),
                "queryInsertReceiver.fxml",
                queryMaterialsPane
        );
    }

    public void loadQueryTechnicsPane(Pane queryTechnicsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ReceiverInsertView(new QueryTechnicsTableImpl(), technicsRepository),
                "queryInsertReceiver.fxml",
                queryTechnicsPane
        );
    }
}
