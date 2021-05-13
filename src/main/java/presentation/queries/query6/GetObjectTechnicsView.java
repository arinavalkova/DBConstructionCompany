package presentation.queries.query6;

import domain.rows.Row;
import domain.rows.estimate.ObjectsRow;
import domain.rows.organizations.ManagementsRow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.SceneController;
import presentation.View;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GetObjectTechnicsView implements View {

    private final GetObjectTechnicsViewModel getObjectTechnicsViewModel = new GetObjectTechnicsViewModel();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    private TableView<Object> objectsTable;

    @FXML
    private TableColumn<Object, Integer> objectIdColumn;

    @FXML
    private TableColumn<Object, String> nameOfObjectColumn;

    @FXML
    private TableView<Object> resultTable;

    @FXML
    private TableColumn<Object, String> nameOfTechnicsColumn;

    @FXML
    private TableColumn<Object, Integer> countOfTechnicsColumn;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker firstDatePicker;

    @FXML
    private DatePicker secondDatePicker;

    @FXML
    private Button queryButton;

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

        queryButton.setOnAction(event -> {
            Object row = objectsTable.getSelectionModel().selectedItemProperty().get();
            String firstDate = formatter.format(firstDatePicker.getValue());
            String secondDate = formatter.format(secondDatePicker.getValue());
            getObjectTechnicsViewModel.executeQuery(row, firstDate, secondDate);
            firstDatePicker.setValue(null);
            secondDatePicker.setValue(null);
        });
    }

    private void loadData() {
        getObjectTechnicsViewModel.loadObjects();
    }

    private void bind() {
        objectsTable.itemsProperty().bind(getObjectTechnicsViewModel.getObjectRowProperty());
        resultTable.itemsProperty().bind(getObjectTechnicsViewModel.getResultRowProperty());

        objectIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfObjectColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameOfTechnicsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countOfTechnicsColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
}
