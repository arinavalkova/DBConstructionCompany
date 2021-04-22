package presentation.queries.querie1;

import data.tables.organizations.ManagementsTableImpl;
import data.tables.people.SectorsTableImpl;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.organizations.ManagementsRow;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.parameterized.queries.GetBossOfManagementQuery;
import domain.usecases.parameterized.queries.GetBossOfSectorQuery;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetBossOfAreaViewModel implements DataReceiver {

    private final Property<ObservableList<Row>> managementsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Row>> sectorsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<Row>> resultRowProperty = new SimpleObjectProperty<>();

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

    private final GetBossOfSectorQuery getBossOfSectorQuery = new GetBossOfSectorQuery(
            this,
            resultRowProperty
    );

    private final GetBossOfManagementQuery getBossOfManagementQuery = new GetBossOfManagementQuery(
            this,
            resultRowProperty
    );

    public Property<ObservableList<Row>> getManagementsRowProperty() {
        return managementsRowProperty;
    }

    public Property<ObservableList<Row>> getSectorsRowProperty() {
        return sectorsRowProperty;
    }

    public Property<ObservableList<Row>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadSectors() {
        getSectorsRowUseCase.invoke();
    }

    public void loadManagements() {
        getManagementRowsUseCase.invoke();
    }

    public void loadResultForManagementQuery(int id) {
        getBossOfManagementQuery.invoke(id);
    }

    public void loadResultForSectorQuery(int id) {
        getBossOfSectorQuery.invoke(id);
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
