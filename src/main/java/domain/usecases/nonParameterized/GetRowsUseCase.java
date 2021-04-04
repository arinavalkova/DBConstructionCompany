package domain.usecases.nonParameterized;

import domain.AnswerReceiver;
import domain.DataBaseRepository;

public class GetRowsUseCase implements NonParamUseCase {

    private final DataBaseRepository dataBaseRepository;
    private final AnswerReceiver answerReceiver;

    public GetRowsUseCase(DataBaseRepository dataBaseRepository, AnswerReceiver answerReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public void invoke() {
        return dataBaseRepository.getRows();
    }
}
