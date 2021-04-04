package domain.usecases.nonParameterized;

import data.JDBCConnection;
import domain.AnswerReceiver;
import domain.DataBaseRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class LoadTestDataUseCase implements NonParamUseCase {

    private final ArrayList<DataBaseRepository> repositoryArrayList;
    private final AnswerReceiver answerReceiver;

    public LoadTestDataUseCase(ArrayList<DataBaseRepository> repositoryArrayList, AnswerReceiver answerReceiver) {
        this.repositoryArrayList = repositoryArrayList;
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke() {
        Thread thread = new Thread(() -> {
            try {
                JDBCConnection.getConnection().setAutoCommit(false);
                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.deleteTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onError("Error with deleting old table " + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onError("Error with creating table " + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createIdAutoIncrementTrigger()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onError("Error with creating auto increment id trigger for "
                                + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.loadTestData()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onError("Error with loading test data to " + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                JDBCConnection.getConnection().commit();
                JDBCConnection.getConnection().setAutoCommit(true);
                answerReceiver.onSuccess("Successfully loaded test data");
            } catch (SQLException throwables) {
                answerReceiver.onError("Error");
            }
        });
        thread.start();
        return null;
    }
}
