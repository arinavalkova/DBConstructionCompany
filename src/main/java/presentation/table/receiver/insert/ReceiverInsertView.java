package presentation.table.receiver.insert;

import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.ManagementsRow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.View;

import java.util.ArrayList;

public class ReceiverInsertView implements View {

    @FXML
    private Label label;

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, Integer> firstColumn;

    @FXML
    private TableColumn<Object, String> secondColumn;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button acceptButton;

    @FXML
    private Button rejectButton;

    private final DataBaseRepository sourceDataBaseRepository;
    private final ReceiverInsertViewModel receiverInsertViewModel;

    public ReceiverInsertView(DataBaseRepository sourceRepository, DataBaseRepository destinationRepository) {
        this.sourceDataBaseRepository = sourceRepository;
        this.receiverInsertViewModel = new ReceiverInsertViewModel(sourceRepository, destinationRepository);
    }

    @FXML
    void initialize() {
        initTableInfo();
        initButtons();
    }

    private void initTableInfo() {
        label.setText(sourceDataBaseRepository.getUITableName());
        table.itemsProperty().bind(receiverInsertViewModel.getRowProperty());
        ArrayList<String> listOfFieldsNames = sourceDataBaseRepository.getFieldNames();
        ArrayList<String> listOfColumnNames = sourceDataBaseRepository.getColumnNames();
        firstColumn.setCellValueFactory(new PropertyValueFactory<>(listOfFieldsNames.get(0)));
        firstColumn.setText(listOfColumnNames.get(0));
        secondColumn.setCellValueFactory(new PropertyValueFactory<>(listOfFieldsNames.get(1)));
        secondColumn.setText(listOfColumnNames.get(1));
    }

    private void initButtons() {
        loadDataButton.setOnAction(event -> receiverInsertViewModel.loadData());
        acceptButton.setOnAction(event -> {
            Object row = table.getSelectionModel().selectedItemProperty().get();
            receiverInsertViewModel.acceptAction(row);
        });
        rejectButton.setOnAction(event -> {
            Object row = table.getSelectionModel().selectedItemProperty().get();
            receiverInsertViewModel.rejectAction(row);
        });
        receiverInsertViewModel.loadData();
    }
}
