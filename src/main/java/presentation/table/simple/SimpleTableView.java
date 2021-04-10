package presentation.table.simple;

import domain.DataBaseRepository;
import domain.rows.Row;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SimpleTableView {

    private SimpleTableViewModel simpleTableViewModel;

    @FXML
    private TableView<Row> table;

    @FXML
    private Label tableNameLabel;

    @FXML
    private HBox insertHBox;

    @FXML
    private TextField idForDelete;

    @FXML
    private HBox updateHBox;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Label answerLabel;

    @FXML
    private Button loadDataButton;

    private ArrayList<TableColumn<Row, Type>> columns = new ArrayList<>();
    private ArrayList<TextField> insertFields = new ArrayList<>();
    private ArrayList<TextField> updateFields = new ArrayList<>();

    private ArrayList<String> classFieldNames;
    private ArrayList<String> columnNames;
    private String tableName;

    public SimpleTableView(
            DataBaseRepository repository,
            ArrayList<String> classFieldNames,
            ArrayList<String> columnNames,
            String tableName) {
        this.classFieldNames = classFieldNames;
        this.columnNames = columnNames;
        this.tableName = tableName;

        this.simpleTableViewModel = new SimpleTableViewModel(repository);
    }

    @FXML
    void initialize() {
        answerLabel.textProperty().bind(simpleTableViewModel.getAnswerProperty());
        tableNameLabel.setText(tableName);
        table.itemsProperty().bind(simpleTableViewModel.getRowProperty());

        int i = 0;
        for (String fieldName : classFieldNames) {
                TableColumn<Row, Type> tableColumn = new TableColumn<>();
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                columns.add(tableColumn);
                tableColumn.setText(columnNames.get(i));
                table.getColumns().add(tableColumn);
                if (!fieldName.equals("id")) {
                    TextField insert = new TextField();
                    insert.setPromptText(columnNames.get(i));
                    insertFields.add(insert);
                    insertHBox.getChildren().add(insert);
                }
                TextField updateField = new TextField();
                updateField.setPromptText(columnNames.get(i));
                updateFields.add(updateField);
                updateHBox.getChildren().add(updateField);
                i++;
        }

        initButtons();
    }

    private void initButtons() {
        insertButton.setOnAction(event -> {
            ArrayList<String> rowLines = new ArrayList<>();
            rowLines.add("0");
            for (TextField textField : insertFields) {
                rowLines.add(textField.getText());
                textField.setText("");
            }
            simpleTableViewModel.insert(rowLines);
        });

        deleteButton.setOnAction(event -> {
            simpleTableViewModel.delete(Integer.parseInt(idForDelete.getText()));
            idForDelete.setText("");
        });

        updateButton.setOnAction(event -> {
            ArrayList<String> rowLines = new ArrayList<>();
            for (TextField textField : updateFields) {
                rowLines.add(textField.getText());
                textField.setText("");
            }
            simpleTableViewModel.update(rowLines);
        });

        loadDataButton.setOnAction(event -> simpleTableViewModel.updateTable());
    }
}
