package presentation.queries.query3;

import data.tables.organizations.ManagementsTableImpl;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.queries.GetObjectsAndSchedulesQueryUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetObjectsAndSchedulesViewModel implements DataReceiver {

    private final Property<ObservableList<Object>> managementsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Object>> resultRowProperty = new SimpleObjectProperty<>();

    private final GetRowsUseCase getManagementRowsUseCase = new GetRowsUseCase(
            new ManagementsTableImpl(),
            this,
            managementsRowProperty
    );


    private final GetObjectsAndSchedulesQueryUseCase getObjectsAndSchedulesQueryUseCase = new GetObjectsAndSchedulesQueryUseCase(
            this,
            resultRowProperty
    );

    public Property<ObservableList<Object>> getManagementsRowProperty() {
        return managementsRowProperty;
    }

    public Property<ObservableList<Object>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadManagements() {
        getManagementRowsUseCase.invoke();
    }

    public void loadResultForManagementQuery(int id) {
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
