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
        return dataBaseRepository.insertRow((Row) object);
    }
}
