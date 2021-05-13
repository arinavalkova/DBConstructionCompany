package domain.usecases.parameterized;

import data.Coder;
import data.JDBCConnection;
import domain.AnswerReceiver;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateNewMangerUserUseCase implements ParamUseCase {

    private final AnswerReceiver answerReceiver;

    public CreateNewMangerUserUseCase(AnswerReceiver answerReceiver) {
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke(Object... object) {
        String userName = (String) object[0];
        String password = (String) object[1];
        try {
            JDBCConnection.getConnection().setAutoCommit(false);

            String sql1 = "create user \"" + userName + "\" identified by \"" + password + "\";";
            try {
                PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql1);
                preStatement.executeQuery();
            } catch (SQLException throwables) {
                JDBCConnection.getConnection().rollback();
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать пользователя "));
                JDBCConnection.getConnection().setAutoCommit(true);
                return null;
            }

            String sql2 = "grant create session to \"" + userName + "\";";
            try {
                PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql2);
                preStatement.executeQuery();
            } catch (SQLException throwables) {
                JDBCConnection.getConnection().rollback();
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать пользователя "));
                JDBCConnection.getConnection().setAutoCommit(true);
                return null;
            }

            String sql3 = "GRANT \"18206_VALKOVA_MANAGER_ROLE\" TO \"" + userName + "\";";
            try {
                PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql3);
                preStatement.executeQuery();
            } catch (SQLException throwables) {
                JDBCConnection.getConnection().rollback();
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать пользователя "));
                JDBCConnection.getConnection().setAutoCommit(true);
                return null;
            }

            JDBCConnection.getConnection().commit();
            JDBCConnection.getConnection().setAutoCommit(true);
            answerReceiver.onAnswerSuccess(Coder.encodingRUS("Пользователь создан"));
        } catch (SQLException throwables) {
            answerReceiver.onAnswerError(Coder.encodingRUS("Ошибка"));
        }
        return null;
    }
}
