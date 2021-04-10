package presentation.table.edit.custom;

import domain.DataBaseRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CustomTableView {

    @FXML
    private HBox insertFirstTableHBox;

    @FXML
    private HBox updateFirstTableField;

    @FXML
    private Button insertFirstTableButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateFirstTableButton;

    @FXML
    private Label answerLabel;

    @FXML
    private AnchorPane firstShowerPane;

    @FXML
    private AnchorPane secondShowPane;

    @FXML
    private HBox insertSecondTableHBox;

    @FXML
    private Button insertSecondTableButton;

    @FXML
    private TextField idForDelete;

    @FXML
    private HBox updateSecondTableField;

    @FXML
    private Button updateSecondTableButton;

    public CustomTableView(DataBaseRepository firstRepository,
                           DataBaseRepository secondRepository,
                           ArrayList<String> firstClassFieldNames,
                           ArrayList<String> secondClassFieldNames,
                           ArrayList<String> firstColumnNames,
                           ArrayList<String> secondColumnNames,
                           String firstTableName,
                           String secondTableName) {


    }

    @FXML
    void initialize() {
        initShowerPanes();
    }

    private void initShowerPanes() {

    }

}
