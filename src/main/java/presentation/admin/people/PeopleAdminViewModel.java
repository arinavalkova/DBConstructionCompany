package presentation.admin.people;

import data.tables.people.ProfessionsTableImpl;
import data.tables.people.SectorsTableImpl;
import domain.AnswerReceiver;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;

import java.io.IOException;

public class PeopleAdminViewModel {

    public void loadProfessionsTable(Pane professionsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new ProfessionsTableImpl()),
                "simpleTableEditor.fxml",
                professionsPane
        );
    }

    public void loadSectorsTable(Pane sectorsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(new SectorsTableImpl()),
                "simpleTableEditor.fxml",
                sectorsPane
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
