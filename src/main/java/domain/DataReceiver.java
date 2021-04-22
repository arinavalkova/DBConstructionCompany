package domain;

import domain.rows.Row;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

public interface DataReceiver {
    void onDataSuccess(Object object, Property<ObservableList<Row>> property);
    void onDataError(String answer, Property<ObservableList<Row>> property);
}
