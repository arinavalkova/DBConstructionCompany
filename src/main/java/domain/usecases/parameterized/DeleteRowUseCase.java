package domain.usecases.parameterized;

import data.Coder;
import domain.AnswerReceiver;
import domain.DataBaseRepository;

public class DeleteRowUseCase implements ParamUseCase {

    private final DataBaseRepository dataBaseRepository;
    private final AnswerReceiver answerReceiver;

    public DeleteRowUseCase(DataBaseRepository dataBaseRepository, AnswerReceiver answerReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke(Object... object) {
        Thread thread = new Thread(() -> {
            if (!dataBaseRepository.deleteRow((Integer) object[0])) {
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось удалить"));
            } else {
                answerReceiver.onAnswerSuccess(Coder.encodingRUS("Успешно удалено"));
            }
        });
        thread.start();
        return null;
    }
}
