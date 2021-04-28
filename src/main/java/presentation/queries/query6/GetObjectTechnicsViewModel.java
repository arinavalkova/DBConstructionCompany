package presentation.queries.query6;

import data.tables.estimate.ObjectsTableImpl;
import data.tables.organizations.ManagementsTableImpl;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.queries.GetManagementTechnicsQueryUseCase;
import domain.usecases.parameterized.queries.GetObjectTechnicsQueryUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetObjectTechnicsViewModel implements DataReceiver {

    private final Property<ObservableList<Row>> objectRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Row>> resultRowProperty = new SimpleObjectProperty<>();

    private final GetRowsUseCase getObjectRowUseCase = new GetRowsUseCase(
            new ObjectsTableImpl(),
            this,
            objectRowProperty
    );


    private final GetObjectTechnicsQueryUseCase getObjectTechnicsQueryUseCase =
            new GetObjectTechnicsQueryUseCase(
                    this,
                    resultRowProperty
            );

    public Property<ObservableList<Row>> getObjectRowProperty() {
        return objectRowProperty;
    }

    public Property<ObservableList<Row>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadObjects() {
        getObjectRowUseCase.invoke();
    }

    public void executeQuery(Row row, String firstDate, String secondDate) {
        getObjectTechnicsQueryUseCase.invoke(row, firstDate, secondDate);
    }

    @Override
    public void onDataSuccess(Object object, Property<ObservableList<Row>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList((List<Row>) object)));
    }

    @Override
    public void onDataError(String answer, Property<ObservableList<Row>> property) {
        Platform.runLater(() -> property.setValue(FXCollections.observableArrayList()));
    }
}
