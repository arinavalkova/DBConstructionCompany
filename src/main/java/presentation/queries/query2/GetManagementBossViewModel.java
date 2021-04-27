package presentation.queries.query2;

import domain.DataReceiver;
import domain.rows.Row;
import domain.usecases.nonParameterized.queries.GetManagementBossQueryUseCase;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetManagementBossViewModel implements DataReceiver {

    private final Property<ObservableList<Row>> resultRowProperty = new SimpleObjectProperty<>();

    private final GetManagementBossQueryUseCase getManagementAndBossQueryUseCase = new GetManagementBossQueryUseCase(
            this,
            resultRowProperty
    );

    public Property<ObservableList<Row>> getResultRowProperty() {
        return resultRowProperty;
    }

    public void loadData() {
        getManagementAndBossQueryUseCase.invoke();
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
