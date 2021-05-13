package domain.usecases.parameterized;

import data.Coder;
import data.JDBCConnection;
import domain.AnswerReceiver;
import domain.usecases.parameterized.ParamUseCase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateNewUserByRoleUseCase implements ParamUseCase {

    private final AnswerReceiver answerReceiver;

    Map<Integer, String> rolesMap = Stream.of(new Object[][] {
            { 0, "18206_VALKOVA_ADMIN_ROLE" },
            { 1, "18206_VALKOVA_MANAGER_ROLE" },
            { 2, "18206_VALKOVA_CHIEF_ROLE" },
            { 3, "18206_VALKOVA_DIRECTOR_ROLE" }
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    public CreateNewUserByRoleUseCase(AnswerReceiver answerReceiver) {
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke(Object... object) {
        String userName = (String) object[0];
        String password = (String) object[1];
        int roleId = (int) object[2];

        try {
            JDBCConnection.getConnection().setAutoCommit(false);

            String sql1 = "create user \"" + userName + "\" identified by \"" + password + "\"";

            try {
                PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql1);
                preStatement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                JDBCConnection.getConnection().rollback();
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать пользователя "));
                JDBCConnection.getConnection().setAutoCommit(true);
                return null;
            }

            String sql2 = "grant create session to \"" + userName + "\"";
            try {
                PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql2);
                preStatement.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                JDBCConnection.getConnection().rollback();
                answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать пользователя "));
                JDBCConnection.getConnection().setAutoCommit(true);
                return null;
            }

            String sql3;
            if (roleId != 3) {
                sql3 = "GRANT \"" + rolesMap.get(roleId) + "\" TO \"" + userName + "\"";
            } else {
                sql3 = "GRANT ALL PRIVILEGES TO \"" + userName + "\";";
            }

            try {
                PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql3);
                preStatement.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
