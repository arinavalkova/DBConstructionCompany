package presentation.queries.query4;

import domain.rows.Row;
import domain.rows.estimate.ObjectsRow;
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

public class GetBrigadeAndEmployeeView implements View {

    private final GetBrigadeAndEmployeeViewModel getBrigadeAndEmployeeViewModel = new GetBrigadeAndEmployeeViewModel();

    @FXML
    private TableView<Row> objectTable;

    @FXML
    private TableColumn<Row, Integer> objectIdColumn;

    @FXML
    private TableColumn<Row, String> nameOfObjectColumn;

    @FXML
    private TableView<Row> resultTable;

    @FXML
    private TableColumn<Row, String> nameOfBrigadeColumn;

    @FXML
    private TableColumn<Row, String> nameOfEmployeeColumn;

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
        getBrigadeAndEmployeeViewModel.loadObjects();
    }

    private void initActions() {
        objectTable.setRowFactory( tv -> {
            TableRow<Row> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                ObjectsRow objectsRow = (ObjectsRow) row.getItem();
                if (objectsRow != null) {
                    getBrigadeAndEmployeeViewModel.loadResultQuery(objectsRow.getId());
                }
            });
            return row;
        });
    }

    private void bind() {
        objectTable.itemsProperty().bind(getBrigadeAndEmployeeViewModel.getObjectsRowProperty());
        resultTable.itemsProperty().bind(getBrigadeAndEmployeeViewModel.getResultRowProperty());

        objectIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfObjectColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameOfBrigadeColumn.setCellValueFactory(new PropertyValueFactory<>("brigadeName"));
        nameOfEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    }
}
