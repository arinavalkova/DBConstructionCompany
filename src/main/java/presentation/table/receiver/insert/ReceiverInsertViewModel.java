package presentation.table.receiver.insert;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ReceiverInsertViewModel implements DataReceiver, AnswerReceiver {

    private final DataBaseRepository sourceRepository;
    private final DataBaseRepository destinationRepository;
    private final GetRowsUseCase getRowsUseCase;
    private final InsertRowUseCase insertRowUseCase;
    private final DeleteRowUseCase deleteRowUseCase;
    private final Property<ObservableList<Row>> rowProperty = new SimpleObjectProperty<>();

    public ReceiverInsertViewModel(DataBaseRepository sourceRepository, DataBaseRepository destinationRepository) {
        this.sourceRepository = sourceRepository;
        this.destinationRepository = destinationRepository;
        this.insertRowUseCase = new InsertRowUseCase(destinationRepository, this);
        this.deleteRowUseCase = new DeleteRowUseCase(sourceRepository, this);
        this.getRowsUseCase = new GetRowsUseCase(sourceRepository, this, rowProperty);
    }

    public Property<ObservableList<Row>> getRowProperty() {
        return rowProperty;
    }

    @Override
    public void onDataSuccess(Object object, Property<ObservableList<Row>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList((List<Row>) object)));
    }

    @Override
    public void onDataError(String answer, Property<ObservableList<Row>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList()));
    }

    public void loadData() {
        getRowsUseCase.invoke();
    }

    public void acceptAction(Row row) {
        insertRowUseCase.invoke(row);
        deleteRowUseCase.invoke(row.getId());
        getRowsUseCase.invoke();
    }

    public void rejectAction(Row row) {
        deleteRowUseCase.invoke(row.getId());
        getRowsUseCase.invoke();
    }

    @Override
    public void onAnswerSuccess(String answer) {

    }

    @Override
    public void onAnswerError(String answer) {

    }
}
