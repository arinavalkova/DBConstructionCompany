package domain.usecases.parameterized;

import data.JDBCConnection;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.queries.GetIdByFiledUseCase;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomInsertRowUseCase implements ParamUseCase {

    private final DataBaseRepository firstRepository;
    private final DataBaseRepository secondRepository;
    private final AnswerReceiver answerReceiver;

    private final InsertRowUseCase insertFirstTableUseCase;
    private final InsertRowUseCase insertSecondTableUseCase;
    private final GetIdByFiledUseCase getIdByFiledUseCase;

    public CustomInsertRowUseCase(
            DataBaseRepository firstRepository,
            DataBaseRepository secondRepository,
            AnswerReceiver answerReceiver) {
        this.firstRepository = firstRepository;
        this.secondRepository = secondRepository;
        this.answerReceiver = answerReceiver;

        this.insertFirstTableUseCase = new InsertRowUseCase(firstRepository, answerReceiver);
        this.insertSecondTableUseCase = new InsertRowUseCase(secondRepository, answerReceiver);
        this.getIdByFiledUseCase = new GetIdByFiledUseCase(secondRepository);
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

                insertSecondTableUseCase.invoke(secondRepository.createRow(firstList));
                Integer id = (Integer) getIdByFiledUseCase.invoke(secondRepository.createRow(firstList));

                if (id == null) {
                    JDBCConnection.getConnection().rollback();
                    answerReceiver.onAnswerError("Error with inserting to " + secondRepository.getTableName());
                    System.out.println("Error with inserting to " + secondRepository.getTableName());
                    JDBCConnection.getConnection().setAutoCommit(true);
                    return;
                } else {
                    System.out.println("Inserted to " + secondRepository.getTableName() + "with id=" + id);;
                }

                ArrayList<String> secondList = new ArrayList<>();
                secondList.add("0");
                secondList.add(String.valueOf(id));
                secondList.add(rowLines.get(rowLines.size() - 1));

                //check

                insertFirstTableUseCase.invoke(firstRepository.createRow(secondList));
                id = (Integer) getIdByFiledUseCase.invoke(firstRepository.createRow(secondList));

                if (id == null) {
                    JDBCConnection.getConnection().rollback();
                    answerReceiver.onAnswerError("Error with inserting to " + firstRepository.getTableName());
                    System.out.println("Error with inserting to " + firstRepository.getTableName());
                    JDBCConnection.getConnection().setAutoCommit(true);
                    return;
                } else {
                    System.out.println("Inserted to " + firstRepository.getTableName() + "with id=" + id);
                    JDBCConnection.getConnection().commit();
                    JDBCConnection.getConnection().setAutoCommit(true);
                    answerReceiver.onAnswerSuccess("Successfully inserted");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        thread.start();
        return null;
    }

    //checkers here
}
