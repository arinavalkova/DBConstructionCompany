package presentation.queries.query2;

import domain.rows.Row;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.SceneController;
import presentation.View;

import java.io.IOException;

public class GetManagementBossView implements View {

    private final GetManagementBossViewModel getManagementBossViewModel = new GetManagementBossViewModel();

    @FXML
    private TableView<Object> resultTable;

    @FXML
    private TableColumn<Object, String> nameOfManagementColumn;

    @FXML
    private TableColumn<Object, String> nameOfBossColumn;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        bind();
        loadData();
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
        getManagementBossViewModel.loadData();
    }

    private void bind() {
        resultTable.itemsProperty().bind(getManagementBossViewModel.getResultRowProperty());

        nameOfManagementColumn.setCellValueFactory(new PropertyValueFactory<>("managementName"));
        nameOfBossColumn.setCellValueFactory(new PropertyValueFactory<>("bossName"));
    }
}
