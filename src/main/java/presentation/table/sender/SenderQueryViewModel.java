package presentation.table.sender;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.InsertRowUseCase;

public class SenderQueryViewModel implements AnswerReceiver {

    private final InsertRowUseCase insertRowUseCase;

    public SenderQueryViewModel(DataBaseRepository dataBaseRepository) {
        this.insertRowUseCase = new InsertRowUseCase(dataBaseRepository, this);
    }

    public void sendInsertQuery(String text) {
        insertRowUseCase.invoke(text);
    }

    @Override
    public void onAnswerSuccess(String answer) { }

    @Override
    public void onAnswerError(String answer) { }
}
