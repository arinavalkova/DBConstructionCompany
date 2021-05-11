package presentation.data.brigades;

import data.tables.brigades.BrigadeAndEmployeesTableImpl;
import data.tables.brigades.BrigadeAndForemanTableImpl;
import data.tables.people.PeopleAndProfessionsTableImpl;
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

public class BrigadesViewModel {

    private final BrigadeAndEmployeesTableImpl brigadeAndEmployeesTable = new BrigadeAndEmployeesTableImpl();
    private final BrigadeAndForemanTableImpl brigadeAndForemanTable = new BrigadeAndForemanTableImpl();
    private final PeopleAndProfessionsTableImpl peopleAndProfessionsTable = new PeopleAndProfessionsTableImpl();

    public void goBack() {
        try {
            SceneController.load("manager.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEmployeesPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(peopleAndProfessionsTable, 200),
                "tableShower.fxml",
                pane
        );
    }

    public void loadBrigadesPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(brigadeAndEmployeesTable, brigadeAndForemanTable),
                "customTableEditor.fxml",
                pane
        );
    }
}
