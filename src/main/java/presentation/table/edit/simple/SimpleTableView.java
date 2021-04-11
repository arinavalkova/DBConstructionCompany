package presentation.table.edit.simple;

import domain.DataBaseRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import presentation.SceneController;
import presentation.View;
import presentation.table.show.ShowTableView;

import java.util.ArrayList;

public class SimpleTableView implements View {

    private SimpleTableViewModel simpleTableViewModel;

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
    private FlowPane showerPane;

    private ArrayList<TextField> insertFields = new ArrayList<>();
    private ArrayList<TextField> updateFields = new ArrayList<>();

    private final ArrayList<String> classFieldNames;
    private final ArrayList<String> columnNames;
    private final String tableName;
    private final DataBaseRepository repository;

    public SimpleTableView(
            DataBaseRepository repository,
            ArrayList<String> classFieldNames,
            ArrayList<String> columnNames,
            String tableName) {
        this.classFieldNames = classFieldNames;
        this.columnNames = columnNames;
        this.tableName = tableName;
        this.repository = repository;

        this.simpleTableViewModel = new SimpleTableViewModel(repository);
    }

    @FXML
    void initialize() {
        initShowerPane();
        initFields();
        initButtons();
    }

    private void initFields() {
        answerLabel.textProperty().bind(simpleTableViewModel.getAnswerProperty());

        int i = 0;
        for (String fieldName : classFieldNames) {
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
    }

    private void initShowerPane() {
        ShowTableView showTableView = new ShowTableView(
                repository,
                classFieldNames,
                columnNames,
                tableName,
                200
        );

        SceneController.loadControllerToFXMLAndPane(showTableView, "showTable.fxml", showerPane);
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
    }
}
