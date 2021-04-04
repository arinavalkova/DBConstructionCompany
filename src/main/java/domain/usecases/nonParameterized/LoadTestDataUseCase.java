package domain.usecases.nonParameterized;

import domain.AnswerReceiver;
import domain.DataBaseRepository;

public class LoadTestDataUseCase implements NonParamUseCase {

    private DataBaseRepository dataBaseRepository;
    private AnswerReceiver answerReceiver;

    public LoadTestDataUseCase(DataBaseRepository dataBaseRepository, AnswerReceiver answerReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public void invoke() {
        return dataBaseRepository.loadTestData();
    }
}
