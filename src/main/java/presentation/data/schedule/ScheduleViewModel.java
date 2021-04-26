package presentation.data.schedule;

import data.Coder;
import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import data.tables.brigades.BrigadeAndForemanTableImpl;
import data.tables.estimate.ObjectsTableImpl;
import data.tables.query.QueryMaterialsTableImpl;
import data.tables.query.QueryTypesOfWorkTableImpl;
import data.tables.schedule.SchedulesTableImpl;
import data.tables.schedule.TheoreticMaterialsTableImpl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;
import presentation.table.sender.SenderQueryView;
import presentation.table.show.ShowTableView;

import java.io.IOException;

public class ScheduleViewModel {

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSchedulePane(Pane schedulePane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new SchedulesTableImpl()),
                "simpleTableEditor.fxml",
                schedulePane
        );
    }

    public void loadTheoreticMaterialsPane(Pane theoreticMaterialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new TheoreticMaterialsTableImpl()),
                "simpleTableEditor.fxml",
                theoreticMaterialsPane
        );
    }

    public void loadMaterialsShowerPane(Pane materialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(
                        new MaterialsTableImpl(),
                        200
                ),
                "tableShower.fxml",
                materialsPane
        );
    }

    public void loadTypesOfWorkShowerPane(Pane typesOfWorkPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(
                        new TypesOfJobsTableImpl(),
                        200
                ),
                "tableShower.fxml",
                typesOfWorkPane
        );
    }

    public void loadObjectsShowerPane(Pane objectsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(
                        new ObjectsTableImpl(),
                        200
                ),
                "tableShower.fxml",
                objectsPane
        );
    }

    public void loadBrigadesShowerPane(Pane brigadesPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(
                        new BrigadeAndForemanTableImpl(),
                        200
                ),
                "tableShower.fxml",
                brigadesPane
        );
    }

    public void loadQueryMaterialsPane(Pane queryMaterialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SenderQueryView(
                        new QueryMaterialsTableImpl(),
                        "Запрос на материалы"
                ),
                "querySender.fxml",
                queryMaterialsPane
        );
    }

    public void loadQueryTypesOfWorkPane(Pane queryTypesOfWorkPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SenderQueryView(
                        new QueryTypesOfWorkTableImpl(),
                        "Запрос на типы работ"
                ),
                "querySender.fxml",
                queryTypesOfWorkPane
        );
    }
}
