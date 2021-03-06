package presentation.table.show;

import domain.DataBaseRepository;
import domain.rows.Row;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.View;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowTableView implements View {

    private final ShowTableViewModel showTableViewModel;

    private final ArrayList<String> classFieldsNames;
    private final ArrayList<String> columnNames;
    private final String tableName;
    private final Integer tableHeight;
    private final Integer tableWidth;

    private ArrayList<TableColumn<Object, Type>> columns = new ArrayList<>();

    @FXML
    private TableView<Object> table;

    @FXML
    private Label tableNameLabel;

    @FXML
    private Button loadDataButton;

    public ShowTableView(
            DataBaseRepository repository,
            int tableHeight, int tableWidth) {
        this.classFieldsNames = repository.getFieldNames();
        this.columnNames = repository.getColumnNames();
        this.tableName = repository.getUITableName();
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;

        this.showTableViewModel = new ShowTableViewModel(repository);
    }

    public ShowTableView(
            DataBaseRepository repository,
            int tableHeight) {
        this.classFieldsNames = repository.getFieldNames();
        this.columnNames = repository.getColumnNames();
        this.tableName = repository.getUITableName();
        this.tableHeight = tableHeight;
        this.tableWidth = null;

        this.showTableViewModel = new ShowTableViewModel(repository);
    }

    @FXML
    void initialize() {
        showTableViewModel.updateTable();
        tableNameLabel.setText(tableName);
        table.itemsProperty().bind(showTableViewModel.getRowProperty());
        table.setMaxHeight(tableHeight);
        if (tableWidth != null) {
            table.setMinWidth(tableWidth);
        }

        int i = 0;
        for (String fieldName : classFieldsNames) {
            TableColumn<Object, Type> tableColumn = new TableColumn<>();
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));
            columns.add(tableColumn);
            tableColumn.setText(columnNames.get(i));
            table.getColumns().add(tableColumn);
            i++;
        }

        loadDataButton.setOnAction(event -> showTableViewModel.updateTable());
    }

    public ShowTableViewModel getModel() {
        return showTableViewModel;
    }
}
