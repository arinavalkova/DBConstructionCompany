package presentation.table.show;

import domain.DataBaseRepository;
import domain.rows.Row;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowTableView {

    private final ShowTableViewModel showTableViewModel;

    private final ArrayList<String> classFieldsNames;
    private final ArrayList<String> columnNames;
    private final String tableName;
    private final int tableHeight;

    private ArrayList<TableColumn<Row, Type>> columns = new ArrayList<>();

    @FXML
    private TableView<Row> table;

    @FXML
    private Label tableNameLabel;

    @FXML
    private Button loadDataButton;

    public ShowTableView(
            DataBaseRepository repository,
            ArrayList<String> classFieldsNames,
            ArrayList<String> columnNames,
            String tableName,
            int tableHeight) {
        this.classFieldsNames = classFieldsNames;
        this.columnNames = columnNames;
        this.tableName = tableName;
        this.tableHeight = tableHeight;

        this.showTableViewModel = new ShowTableViewModel(repository);
    }

    @FXML
    void initialize() {
        tableNameLabel.setText(tableName);
        table.itemsProperty().bind(showTableViewModel.getRowProperty());
        table.setMaxHeight(tableHeight);

        int i = 0;
        for (String fieldName : classFieldsNames) {
            TableColumn<Row, Type> tableColumn = new TableColumn<>();
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));
            columns.add(tableColumn);
            tableColumn.setText(columnNames.get(i));
            table.getColumns().add(tableColumn);
            i++;
        }

        loadDataButton.setOnAction(event -> showTableViewModel.updateTable());
    }
}
