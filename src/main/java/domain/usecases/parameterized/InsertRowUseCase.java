package domain.usecases.parameterized;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.rows.Row;

public class InsertRowUseCase implements ParamUseCase{

    private final DataBaseRepository dataBaseRepository;
    private final AnswerReceiver answerReceiver;

    public InsertRowUseCase(DataBaseRepository dataBaseRepository, AnswerReceiver answerReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public void invoke(Object object) {
        Thread thread = new Thread(() -> {
            if (!dataBaseRepository.insertRow((Row) object)) {
                answerReceiver.onError("Error with inserting");
            } else {
                answerReceiver.onSuccess("Successfully inserted");
            }
        });
        thread.start();
    }
}
