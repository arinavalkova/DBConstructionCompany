package presentation.queries.query3;

import domain.rows.Row;
import domain.rows.organizations.ManagementsRow;
import domain.rows.people.SectorsRow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.SceneController;
import presentation.View;

import java.io.IOException;

public class GetObjectsAndSchedulesView implements View {

    private final GetObjectsAndSchedulesViewModel getObjectsAndSchedulesViewModel = new GetObjectsAndSchedulesViewModel();

    @FXML
    private TableView<Row> managementTable;

    @FXML
    private TableColumn<Row, Integer> managementsIdColumn;

    @FXML
    private TableColumn<Row, String> nameOfManagementColumn;

    @FXML
    private TableView<Row> resultTable;

    @FXML
    private TableColumn<Row, String> nameOfObjectColumn;

    @FXML
    private TableColumn<Row, String> nameOfTypeOfWorkColumn;

    @FXML
    private TableColumn<Row, String> startDateColumn;

    @FXML
    private TableColumn<Row, String> endDateColumn;

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
        getObjectsAndSchedulesViewModel.loadManagements();
    }

    private void initActions() {
        managementTable.setRowFactory( tv -> {
            TableRow<Row> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                ManagementsRow managementsRow = (ManagementsRow) row.getItem();
                if (managementsRow != null) {
                    getObjectsAndSchedulesViewModel.loadResultForManagementQuery(managementsRow.getId());
                }
            });
            return row ;
        });
    }

    private void bind() {
        managementTable.itemsProperty().bind(getObjectsAndSchedulesViewModel.getManagementsRowProperty());
        resultTable.itemsProperty().bind(getObjectsAndSchedulesViewModel.getResultRowProperty());

        managementsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfManagementColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameOfObjectColumn.setCellValueFactory(new PropertyValueFactory<>("objectName"));
        nameOfTypeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfWorkName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    }
}
