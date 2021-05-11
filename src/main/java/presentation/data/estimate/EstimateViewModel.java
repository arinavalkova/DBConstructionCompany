package presentation.data.estimate;

import data.Coder;
import data.tables.booker.TechniquesTableImpl;
import data.tables.estimate.ObjectsAndTechnicsTableImpl;
import data.tables.estimate.ObjectsTableImpl;
import data.tables.people.SectorsTableImpl;
import data.tables.query.QueryTechnicsTableImpl;
import domain.AnswerReceiver;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.SceneController;
import presentation.table.edit.custom.CustomTableView;
import presentation.table.sender.SenderQueryView;
import presentation.table.show.ShowTableView;

import java.io.IOException;

public class EstimateViewModel {

    public void goBack() {
        try {
            SceneController.load("chief.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAreasPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new SectorsTableImpl(), 200),
                "tableShower.fxml",
                pane
        );
    }

    public void loadTechnicsPane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(new TechniquesTableImpl(), 200),
                "tableShower.fxml",
                pane
        );
    }

    public void loadEstimatePane(Pane pane) {
        SceneController.loadControllerToFXMLAndPane(
                new CustomTableView(new ObjectsAndTechnicsTableImpl(), new ObjectsTableImpl()),
                "customTableEditor.fxml",
                pane
        );
    }

    public void goToWorkAdminWindow() {
        try {
            SceneController.load("admin/workAdmin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadQueryTechnicsPane(Pane queryTechnicsPane) {
        SceneController.loadControllerToFXMLAndPane(
                new SenderQueryView(
                        new QueryTechnicsTableImpl(),
                        "Запрос на технику"
                ),
                "querySender.fxml",
                queryTechnicsPane
        );
    }
}
