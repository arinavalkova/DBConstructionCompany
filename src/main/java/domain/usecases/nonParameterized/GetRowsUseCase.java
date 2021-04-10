package domain.usecases.nonParameterized;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.DataReceiver;
import domain.rows.Row;

import java.util.ArrayList;

public class GetRowsUseCase implements NonParamUseCase {

    private final DataBaseRepository dataBaseRepository;
    private final DataReceiver dataReceiver;

    public GetRowsUseCase(DataBaseRepository dataBaseRepository, DataReceiver dataReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.dataReceiver = dataReceiver;
    }

    @Override
    public Object invoke() {
        Thread thread = new Thread(() -> {
            ArrayList<Row> rowArrayList = dataBaseRepository.getRows();
            if (rowArrayList== null) {
                dataReceiver.onDataError("Error with getting rows");
            } else {
                dataReceiver.onDataSuccess(rowArrayList);
            }
        });
        thread.start();
        return null;
    }
}
