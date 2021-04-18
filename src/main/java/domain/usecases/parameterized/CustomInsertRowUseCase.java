package domain.usecases.parameterized;

import data.Coder;
import data.JDBCConnection;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.queries.GetIdByFiledUseCase;
import domain.usecases.parameterized.queries.people.GetProfessionByNameUseCase;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomInsertRowUseCase implements ParamUseCase {

    private final DataBaseRepository firstRepository;
    private final DataBaseRepository secondRepository;
    private final AnswerReceiver answerReceiver;

    private final GetIdByFiledUseCase getIdByFiledUseCase;

    public CustomInsertRowUseCase(
            DataBaseRepository firstRepository,
            DataBaseRepository secondRepository,
            AnswerReceiver answerReceiver) {
        this.firstRepository = firstRepository;
        this.secondRepository = secondRepository;
        this.answerReceiver = answerReceiver;
        this.getIdByFiledUseCase = new GetIdByFiledUseCase();
    }

    @Override
    public Object invoke(Object object) {
        Thread thread = new Thread(() -> {
            ArrayList<String> rowLines = (ArrayList<String>) object;

            ArrayList<String> firstList = new ArrayList<>();
            firstList.add("0");
            for (int i = 0; i < rowLines.size() - 1; i++) {
                firstList.add(rowLines.get(i));
            }

            try {
                JDBCConnection.getConnection().setAutoCommit(false);

                secondRepository.insertRow(secondRepository.createRow(firstList));

                String id = (String) getIdByFiledUseCase.invoke(secondRepository.createRow(firstList));
                if (id == null) {
                    JDBCConnection.getConnection().rollback();
                    answerReceiver.onAnswerError(
                            Coder.encodingRUS("Не удалось добавить строку в  ")
                                    + secondRepository.getTableName()
                    );
                    JDBCConnection.getConnection().setAutoCommit(true);
                    return;
                } else {
                    answerReceiver.onAnswerSuccess(
                            Coder.encodingRUS("Добавлена строка в ")
                                    + secondRepository.getTableName()
                                    + Coder.encodingRUS("с id=")
                                    + id
                    );
                }

                ArrayList<String> secondList = new ArrayList<>();
                secondList.add("0");
                secondList.add(id);
                secondList.add(rowLines.get(rowLines.size() - 1));

                firstRepository.insertRow(firstRepository.createRow(secondList));
                id = (String) getIdByFiledUseCase.invoke(firstRepository.createRow(secondList));

                if (id == null) {
                    JDBCConnection.getConnection().rollback();
                    answerReceiver.onAnswerError(
                            Coder.encodingRUS("Не удалось добавить строку в ")
                                    + firstRepository.getTableName()
                    );
                    JDBCConnection.getConnection().setAutoCommit(true);
                    return;
                } else {
                    answerReceiver.onAnswerError(
                            Coder.encodingRUS("Строка добавлена в ")
                                    + firstRepository.getTableName()
                                    + Coder.encodingRUS("с id=")
                                    + id
                    );
                    JDBCConnection.getConnection().commit();
                    JDBCConnection.getConnection().setAutoCommit(true);
                    answerReceiver.onAnswerSuccess(Coder.encodingRUS("Успешно"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        thread.start();
        return null;
    }
}
