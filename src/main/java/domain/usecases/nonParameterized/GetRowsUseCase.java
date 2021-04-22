package domain.usecases.nonParameterized;

import data.Coder;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.DataReceiver;
import domain.rows.Row;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GetRowsUseCase implements NonParamUseCase {

    private final DataBaseRepository dataBaseRepository;
    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Row>> property;

    public GetRowsUseCase(
            DataBaseRepository dataBaseRepository,
            DataReceiver dataReceiver,
            Property<ObservableList<Row>> property) {
        this.dataBaseRepository = dataBaseRepository;
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke() {
        Thread thread = new Thread(() -> {
            ArrayList<Row> rowArrayList = dataBaseRepository.getRows();
            if (rowArrayList== null) {
                dataReceiver.onDataError(Coder.encodingRUS("Не удалось получить строки таблицы"), property);
            } else {
                dataReceiver.onDataSuccess(rowArrayList, property);
            }
        });
        thread.start();
        return null;
    }
}
