package presentation.admin.people;

import data.tables.people.ProfessionsTableImpl;
import data.tables.people.SectorsTableImpl;
import data.tables.query.QueryProfessionsTableImpl;
import data.tables.query.QuerySectorsTableImpl;
import domain.AnswerReceiver;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.simple.SimpleTableView;
import presentation.table.receiver.insert.ReceiverInsertView;

import java.io.IOException;

public class PeopleAdminViewModel {

    private final ProfessionsTableImpl professionsTable = new ProfessionsTableImpl();
    private final SectorsTableImpl sectorsTable = new SectorsTableImpl();

    public void loadProfessionsTable(Pane professionsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(professionsTable),
                "simpleTableEditor.fxml",
                professionsPane
        );
    }

    public void loadSectorsTable(Pane sectorsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SimpleTableView(sectorsTable),
                "simpleTableEditor.fxml",
                sectorsPane
        );
    }

    public void goBack() {
        try {
            SceneController.load("admin/allAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProfQueryPane(Pane profQueryPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ReceiverInsertView(new QueryProfessionsTableImpl(), professionsTable),
                "queryInsertReceiver.fxml",
                profQueryPane
        );
    }

    public void loadSectorsQueryPane(Pane sectorsQueryPane) {
        SceneController.loadControllerToFXMLAndPane(
                new ReceiverInsertView(new QuerySectorsTableImpl(), sectorsTable),
                "queryInsertReceiver.fxml",
                sectorsQueryPane
        );
    }
}
