package presentation.queries.query5;

import domain.rows.Row;
import domain.rows.organizations.ManagementsRow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.SceneController;
import presentation.View;

import java.io.IOException;

public class GetManagementTechnicsView implements View {

    private final GetManagementTechnicsViewModel getManagementTechnicsViewModel = new GetManagementTechnicsViewModel();

    @FXML
    private TableView<Object> managementTable;

    @FXML
    private TableColumn<Object, Integer> managementIdColumn;

    @FXML
    private TableColumn<Object, String> managementNameColumn;

    @FXML
    private TableView<Object> resultTable;

    @FXML
    private TableColumn<Object, String> nameOfTechnicsColumn;

    @FXML
    private TableColumn<Object, Integer> countOfTechnicsColumn;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        bind();
        loadData();
        initActions();
        initButtons();
    }

    private void initButtons() {
        backButton.setOnAction(event -> {
            try {
                SceneController.load("queries.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadData() {
        getManagementTechnicsViewModel.loadManagements();
    }

    private void initActions() {
        managementTable.setRowFactory( tv -> {
            TableRow<Object> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                ManagementsRow managementsRow = (ManagementsRow) row.getItem();
                if (managementsRow != null) {
                    getManagementTechnicsViewModel.loadResultQuery(managementsRow.getId());
                }
            });
            return row ;
        });
    }

    private void bind() {
        managementTable.itemsProperty().bind(getManagementTechnicsViewModel.getManagementRowProperty());
        resultTable.itemsProperty().bind(getManagementTechnicsViewModel.getResultRowProperty());

        managementIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        managementNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameOfTechnicsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countOfTechnicsColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
}
