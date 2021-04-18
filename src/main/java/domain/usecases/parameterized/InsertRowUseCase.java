package domain.usecases.parameterized;

import data.Coder;
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
    public Object invoke(Object object) {
        Thread thread = new Thread(() -> {
            if (!dataBaseRepository.insertRow((Row) object)) {
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось добавить строку"));
            } else {
                answerReceiver.onAnswerSuccess(Coder.encodingRUS("Успешно добавлена строка"));
            }
        });
        thread.start();
        return null;
    }
}
