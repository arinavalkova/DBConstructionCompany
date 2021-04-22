package presentation.admin.work;

import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TechniquesTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;

import java.io.IOException;

public class WorkAdminViewModel {

    public void loadTechniquesPane(Pane techniquesPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new TechniquesTableImpl()),
                "simpleTableEditor.fxml",
                techniquesPane
        );
    }

    public void loadMaterialsPane(Pane materialsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new MaterialsTableImpl()),
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
}
