package presentation.table.simple;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.Receiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SimpleTableViewModel implements Receiver {

    private final static String INSERTING = "Inserting...";
    private final static String DELETING = "Deleting...";
    private final static String UPDATING = "Updating...";

    private final InsertRowUseCase insertRowUseCase;
    private final DeleteRowUseCase deleteRowUseCase;
    private final UpdateRowUseCase updateRowUseCase;
    private final GetRowsUseCase getRowsUseCase;

    private final StringProperty answerProperty = new SimpleStringProperty();
    private final Property<ObservableList<Row>> rowProperty = new SimpleObjectProperty<>();

    private final DataBaseRepository dataBaseRepository;

    public SimpleTableViewModel(DataBaseRepository repository) {
        this.dataBaseRepository = repository;

        this.insertRowUseCase = new InsertRowUseCase(dataBaseRepository, this);
        this.deleteRowUseCase = new DeleteRowUseCase(dataBaseRepository, this);
        this.updateRowUseCase = new UpdateRowUseCase(dataBaseRepository, this);
        this.getRowsUseCase = new GetRowsUseCase(dataBaseRepository, this);
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public Property<ObservableList<Row>> getRowProperty() {
        return rowProperty;
    }

    public void updateTable() {
        getRowsUseCase.invoke();
    }

    public void delete(int id) {
        answerProperty.setValue(DELETING);
        deleteRowUseCase.invoke(id);
    }

    public void insert(ArrayList<String> rowLines) {
        answerProperty.setValue(INSERTING);
        insertRowUseCase.invoke(dataBaseRepository.createRow(rowLines));
    }

    public void update(ArrayList<String> rowLines) {
        answerProperty.setValue(UPDATING);
        updateRowUseCase.invoke(dataBaseRepository.createRow(rowLines));
    }

    @Override
    public void onDataSuccess(Object object) {
        rowProperty.setValue(FXCollections.observableArrayList((List<Row>) object));
    }

    @Override
    public void onDataError(String answer) {
        rowProperty.setValue(FXCollections.observableArrayList());
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    @Override
    public void onAnswerSuccess(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
        //updateTable();
    }

    @Override
    public void onAnswerError(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }
}
