package presentation.table.show;

import domain.DataBaseRepository;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ShowTableViewModel implements DataReceiver {

    private final Property<ObservableList<Object>> rowProperty = new SimpleObjectProperty<>();

    private final GetRowsUseCase getRowsUseCase;

    public ShowTableViewModel(DataBaseRepository dataBaseRepository) {
        this.getRowsUseCase = new GetRowsUseCase(dataBaseRepository, this, rowProperty);
    }

    public Property<ObservableList<Object>> getRowProperty() {
        return rowProperty;
    }

    public void updateTable() {
        getRowsUseCase.invoke();
    }

    @Override
    public void onDataSuccess(Object object, Property<ObservableList<Object>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList((List<Row>) object)));
    }

    @Override
    public void onDataError(String answer, Property<ObservableList<Object>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList()));
    }
}
