package presentation.queries.query4;

import data.tables.estimate.ObjectsTableImpl;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.queries.GetObjectBrigadePeopleQueryUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetBrigadeAndEmployeeViewModel implements DataReceiver {

    private final Property<ObservableList<Object>> objectsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Object>> resultRowProperty = new SimpleObjectProperty<>();

    private final GetRowsUseCase getObjectsRowUseCase = new GetRowsUseCase(
            new ObjectsTableImpl(),
            this,
            objectsRowProperty
    );


    private final GetObjectBrigadePeopleQueryUseCase getObjectsAndSchedulesQueryUseCase =
            new GetObjectBrigadePeopleQueryUseCase(
            this,
            resultRowProperty
    );

    public Property<ObservableList<Object>> getObjectsRowProperty() {
        return objectsRowProperty;
    }

    public Property<ObservableList<Object>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadObjects() {
        getObjectsRowUseCase.invoke();
    }

    public void loadResultQuery(int id) {
        getObjectsAndSchedulesQueryUseCase.invoke(id);
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
