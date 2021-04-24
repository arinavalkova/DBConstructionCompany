package presentation.data.schedule;

import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import data.tables.brigades.BrigadeAndForemanTableImpl;
import data.tables.estimate.ObjectsTableImpl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.SceneController;
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

    }

    public void loadTeopeticMaterialsPane(Pane teoreticMaterialsPane) {

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
}
