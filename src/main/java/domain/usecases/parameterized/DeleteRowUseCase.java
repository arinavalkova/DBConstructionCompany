package domain.usecases.parameterized;

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
    public Object invoke(Object object) {
        Thread thread = new Thread(() -> {
            if (!dataBaseRepository.deleteRow((Integer) object)) {
                answerReceiver.onError("Error with deleting");
            } else {
                answerReceiver.onSuccess("Successfully deleted");
            }
        });
        thread.start();
        return null;
    }
}
