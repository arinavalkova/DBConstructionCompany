package presentation.table.edit.custom;

import domain.DataBaseRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import presentation.SceneController;
import presentation.View;
import presentation.table.show.ShowTableView;

import java.util.ArrayList;

public class CustomTableView implements View {

    private final CustomTableViewModel customTableViewModel;

    @FXML
    private HBox insertFirstTableHBox;

    @FXML
    private HBox updateFirstTableHBox;

    @FXML
    private Button insertFirstTableButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateFirstTableButton;

    @FXML
    private Label answerLabel;

    @FXML
    private FlowPane firstShowerPane;

    @FXML
    private FlowPane secondShowPane;

    @FXML
    private HBox insertSecondTableHBox;

    @FXML
    private Button insertSecondTableButton;

    @FXML
    private TextField idForDelete;

    @FXML
    private HBox updateSecondTableHBox;

    @FXML
    private Button updateSecondTableButton;

    private final DataBaseRepository firstRepository;
    private final DataBaseRepository secondRepository;
    private final ArrayList<String> firstClassFieldNames;
    private final ArrayList<String> secondClassFieldNames;
    private final ArrayList<String> firstColumnNames;
    private final ArrayList<String> secondColumnNames;

    private final ArrayList<TextField> insertFirstTextFields = new ArrayList<>();
    private final ArrayList<TextField> insertSecondTextFields = new ArrayList<>();
    private final ArrayList<TextField> updateFirstTextFields = new ArrayList<>();
    private final ArrayList<TextField> updateSecondTextFields = new ArrayList<>();

    public CustomTableView(DataBaseRepository firstRepository,
                           DataBaseRepository secondRepository) {
        this.firstRepository = firstRepository;
        this.secondRepository = secondRepository;
        this.firstClassFieldNames = firstRepository.getFieldNames();
        this.secondClassFieldNames = secondRepository.getFieldNames();
        this.firstColumnNames = firstRepository.getColumnNames();
        this.secondColumnNames = secondRepository.getColumnNames();

        this.customTableViewModel = new CustomTableViewModel(firstRepository, secondRepository);
    }

    @FXML
    void initialize() {
        initShowerPanes();
        initFields();
        bind();
        initButtons();
    }

    private void bind() {
        answerLabel.textProperty().bind(customTableViewModel.getAnswerProperty());
    }

    private void initButtons() {
        insertFirstTableButton.setOnAction(event -> {
            ArrayList<String> rowLines = new ArrayList<>();
            rowLines.add("0");
            for (TextField textField : insertFirstTextFields) {
                rowLines.add(textField.getText());
                textField.setText("");
            }
            customTableViewModel.insertFirstTable(rowLines);
        });
        insertSecondTableButton.setOnAction(event -> {
            ArrayList<String> rowLines = new ArrayList<>();
            for (TextField textField : insertSecondTextFields) {
                rowLines.add(textField.getText());
                textField.setText("");
            }
            customTableViewModel.insertSecondTable(rowLines);
        });
        updateFirstTableButton.setOnAction(event -> {
            ArrayList<String> rowLines = new ArrayList<>();
            for (TextField textField : updateFirstTextFields) {
                rowLines.add(textField.getText());
                textField.setText("");
            }
            customTableViewModel.updateFirstTable(rowLines);
        });
        updateSecondTableButton.setOnAction(event -> {
            ArrayList<String> rowLines = new ArrayList<>();
            for (TextField textField : updateSecondTextFields) {
                rowLines.add(textField.getText());
                textField.setText("");
            }
            customTableViewModel.updateSecondTable(rowLines);
        });
        deleteButton.setOnAction(event -> {
            customTableViewModel.deleteFromSecondTable(Integer.parseInt(idForDelete.getText()));
            idForDelete.setText("");
        });
    }

    private void initFields() {
        int i = 0;
        for (String fieldName : secondClassFieldNames) {
            if (!fieldName.equals("id")) {
                TextField insert = new TextField();
                insert.setPromptText(secondColumnNames.get(i));
                insertSecondTextFields.add(insert);
                insertSecondTableHBox.getChildren().add(insert);
            }
            TextField updateField = new TextField();
            updateField.setPromptText(secondColumnNames.get(i));
            updateSecondTextFields.add(updateField);
            updateSecondTableHBox.getChildren().add(updateField);
            i++;
        }

        i = 0;
        for (String fieldName : firstClassFieldNames) {
            if (!fieldName.equals("id")) {
                if (i != 1) {
                    TextField insert = new TextField();
                    insert.setPromptText(firstColumnNames.get(i));
                    insertSecondTextFields.add(insert);
                    insertSecondTableHBox.getChildren().add(insert);
                }
                TextField insert = new TextField();
                insert.setPromptText(firstColumnNames.get(i));
                insertFirstTextFields.add(insert);
                insertFirstTableHBox.getChildren().add(insert);
            }
            TextField updateField = new TextField();
            updateField.setPromptText(firstColumnNames.get(i));
            updateFirstTextFields.add(updateField);
            updateFirstTableHBox.getChildren().add(updateField);
            i++;
        }
    }

    private void initShowerPanes() {
        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(firstRepository, 100),
                "tableShower.fxml",
                firstShowerPane
        );

        SceneController.loadControllerToFXMLAndPane(
                new ShowTableView(secondRepository, 100),
                "tableShower.fxml",
                secondShowPane
        );
    }
}
