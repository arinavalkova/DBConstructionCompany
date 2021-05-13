package presentation.queries.query1;

import data.tables.organizations.ManagementsTableImpl;
import data.tables.people.SectorsTableImpl;
import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.queries.GetBossOfManagementQueryUseCase;
import domain.usecases.parameterized.queries.GetBossOfSectorQueryUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetBossOfAreaViewModel implements DataReceiver {

    private final Property<ObservableList<Object>> managementsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Object>> sectorsRowProperty = new SimpleObjectProperty<>();

    private final GetRowsUseCase getManagementRowsUseCase = new GetRowsUseCase(
            new ManagementsTableImpl(),
            this,
            managementsRowProperty
    );

    private final GetRowsUseCase getSectorsRowUseCase = new GetRowsUseCase(
            new SectorsTableImpl(),
            this,
            sectorsRowProperty
    );

    private final Property<ObservableList<Object>> resultRowProperty = new SimpleObjectProperty<>();

    private final GetBossOfSectorQueryUseCase getBossOfSectorQuery = new GetBossOfSectorQueryUseCase(
            this,
            resultRowProperty
    );

    private final GetBossOfManagementQueryUseCase getBossOfManagementQueryUseCase = new GetBossOfManagementQueryUseCase(
            this,
            resultRowProperty
    );

    public Property<ObservableList<Object>> getManagementsRowProperty() {
        return managementsRowProperty;
    }

    public Property<ObservableList<Object>> getSectorsRowProperty() {
        return sectorsRowProperty;
    }

    public Property<ObservableList<Object>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadSectors() {
        getSectorsRowUseCase.invoke();
    }

    public void loadManagements() {
        getManagementRowsUseCase.invoke();
    }

    public void loadResultForManagementQuery(int id) {
        getBossOfManagementQueryUseCase.invoke(id);
    }

    public void loadResultForSectorQuery(int id) {
        getBossOfSectorQuery.invoke(id);
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
