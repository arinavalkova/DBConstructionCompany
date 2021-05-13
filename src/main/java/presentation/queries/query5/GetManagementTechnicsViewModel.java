package presentation.queries.query5;

import data.tables.estimate.ObjectsTableImpl;
import data.tables.organizations.ManagementsTableImpl;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.nonParameterized.queries.GetManagementBossQueryUseCase;
import domain.usecases.parameterized.queries.GetManagementTechnicsQueryUseCase;
import domain.usecases.parameterized.queries.GetObjectBrigadePeopleQueryUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetManagementTechnicsViewModel implements DataReceiver {

    private final Property<ObservableList<Object>> managementRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Object>> resultRowProperty = new SimpleObjectProperty<>();

    private final GetRowsUseCase getManagementsRowUseCase = new GetRowsUseCase(
            new ManagementsTableImpl(),
            this,
            managementRowProperty
    );


    private final GetManagementTechnicsQueryUseCase getManagementTechnicsQueryUseCase =
            new GetManagementTechnicsQueryUseCase(
                    this,
                    resultRowProperty
            );

    public Property<ObservableList<Object>> getManagementRowProperty() {
        return managementRowProperty;
    }

    public Property<ObservableList<Object>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadManagements() {
        getManagementsRowUseCase.invoke();
    }

    public void loadResultQuery(int id) {
        getManagementTechnicsQueryUseCase.invoke(id);
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
