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
    public void invoke(Object object) {
        return dataBaseRepository.deleteRow((Integer) object);
    }
}
