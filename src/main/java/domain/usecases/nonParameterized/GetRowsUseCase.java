package domain.usecases.nonParameterized;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.rows.Row;

import java.util.ArrayList;

public class GetRowsUseCase implements NonParamUseCase {

    private final DataBaseRepository dataBaseRepository;
    private final AnswerReceiver answerReceiver;

    public GetRowsUseCase(DataBaseRepository dataBaseRepository, AnswerReceiver answerReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke() {
        Thread thread = new Thread(() -> {
            ArrayList<Row> rowArrayList = dataBaseRepository.getRows();
            if (rowArrayList== null) {
                answerReceiver.onError("Error with getting rows");
            } else {
                answerReceiver.onSuccess(rowArrayList);
            }
        });
        thread.start();
        return null;
    }
}
