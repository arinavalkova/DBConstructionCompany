package presentation.table.sender;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.InsertRowUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class SenderQueryViewModel implements AnswerReceiver {

    private final InsertRowUseCase insertRowUseCase;
    private final DataBaseRepository dataBaseRepository;

    public SenderQueryViewModel(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
        this.insertRowUseCase = new InsertRowUseCase(dataBaseRepository, this);
    }

    public void sendInsertQuery(String text) {
        insertRowUseCase.invoke(dataBaseRepository.createRow(
                new ArrayList<>(Arrays.asList("0", text)))
        );
    }

    @Override
    public void onAnswerSuccess(String answer) { }

    @Override
    public void onAnswerError(String answer) { }
}
