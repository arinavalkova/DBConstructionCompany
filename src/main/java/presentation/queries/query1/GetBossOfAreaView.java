package presentation.queries.query1;

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

public class GetBossOfAreaView implements View {

    private final GetBossOfAreaViewModel getBossOfAreaViewModel = new GetBossOfAreaViewModel();

    @FXML
    private TableView<Row> managementTable;

    @FXML
    private TableColumn<Row, Integer> managementsIdColumn;

    @FXML
    private TableColumn<Row, String> nameOfManagementColumn;

    @FXML
    private TableView<Row> sectorsTable;

    @FXML
    private TableColumn<Row, Integer> sectorsIdColumn;

    @FXML
    private TableColumn<Row, String> sectorsNameColumn;

    @FXML
    private TableView<Row> resultTable;

    @FXML
    private TableColumn<Row, String> nameOfResultColumn;

    @FXML
    private TableColumn<Row, String> professionOfResultColumn;

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
        getBossOfAreaViewModel.loadSectors();
        getBossOfAreaViewModel.loadManagements();
    }

    private void initActions() {
        managementTable.setRowFactory( tv -> {
            TableRow<Row> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                ManagementsRow managementsRow = (ManagementsRow) row.getItem();
                if (managementsRow != null) {
                    getBossOfAreaViewModel.loadResultForManagementQuery(managementsRow.getId());
                }
            });
            return row ;
        });

        sectorsTable.setRowFactory( tv -> {
            TableRow<Row> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                SectorsRow sectorsRow = (SectorsRow) row.getItem();
                if (sectorsRow != null) {
                    getBossOfAreaViewModel.loadResultForSectorQuery(sectorsRow.getId());
                }
            });
            return row ;
        });
    }

    private void bind() {
        managementTable.itemsProperty().bind(getBossOfAreaViewModel.getManagementsRowProperty());
        sectorsTable.itemsProperty().bind(getBossOfAreaViewModel.getSectorsRowProperty());
        resultTable.itemsProperty().bind(getBossOfAreaViewModel.getResultRowProperty());

        managementsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfManagementColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        sectorsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        sectorsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameOfResultColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        professionOfResultColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
    }
}
