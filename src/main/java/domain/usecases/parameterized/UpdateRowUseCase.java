package domain.usecases.parameterized;

import data.Coder;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.rows.Row;

public class UpdateRowUseCase implements ParamUseCase{

    private final DataBaseRepository dataBaseRepository;
    private final AnswerReceiver answerReceiver;

    public UpdateRowUseCase(DataBaseRepository dataBaseRepository, AnswerReceiver answerReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke(Object... object) {
        Thread thread = new Thread(() -> {
            if (!dataBaseRepository.updateRow((Row) object[0])) {
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось обновить"));
            } else {
                answerReceiver.onAnswerSuccess(Coder.encodingRUS("Успешно обновлено"));
            }
        });
        thread.start();
        return null;
    }
}
