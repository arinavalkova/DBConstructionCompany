package presentation.data.report;

import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TechniquesTableImpl;
import data.tables.report.FactMaterialsTableImpl;
import data.tables.report.FactTechnicsTableImpl;
import data.tables.report.FactWorksTableImpl;
import data.tables.schedule.SchedulesTableImpl;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;
import presentation.table.show.ShowTableView;

import java.io.IOException;

public class ReportViewModel {

    public void loadFactTechnicsPane(Pane factTechnicsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new FactTechnicsTableImpl()),
                "simpleTableEditor.fxml",
                factTechnicsPane
        );
    }

    public void loadFactWorkPane(Pane factWorksPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new FactWorksTableImpl()),
                "simpleTableEditor.fxml",
                factWorksPane
        );
    }

    public void loadFactMaterialsPane(Pane factMaterialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new FactMaterialsTableImpl()),
                "simpleTableEditor.fxml",
                factMaterialsPane
        );
    }

    public void loadMaterialsShowerPane(Pane materialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new MaterialsTableImpl(), 200),
                "tableShower.fxml",
                materialsPane
        );
    }

    public void loadTechnicsPane(Pane technicsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new TechniquesTableImpl(), 200),
                "tableShower.fxml",
                technicsPane
        );
    }

    public void loadWorkPane(Pane workPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new SchedulesTableImpl(), 200),
                "tableShower.fxml",
                workPane
        );
    }

    public void goBack() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
