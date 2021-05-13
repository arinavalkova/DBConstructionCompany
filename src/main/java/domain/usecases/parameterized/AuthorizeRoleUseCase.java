package domain.usecases.parameterized;

import data.Coder;
import data.JDBCConnection;
import domain.AnswerReceiver;
import domain.DataReceiver;
import domain.rows.Row;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

public class AuthorizeRoleUseCase implements ParamUseCase {

    private final AnswerReceiver answerReceiver;

    private final GetRoleIdByUserNameUseCase getRoleIdByUserNameUseCase = new GetRoleIdByUserNameUseCase();

    public AuthorizeRoleUseCase(AnswerReceiver answerReceiver) {
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke(Object... object) {
        String userName = (String) object[0];
        String password = (String) object[1];
        Integer roleId = null;
        answerReceiver.onAnswerSuccess(Coder.encodingRUS("Авторизация..."));
        System.out.println(userName + " " + password);
            if (JDBCConnection.establishJDBCConnection(userName, password)) {
                roleId = (Integer) getRoleIdByUserNameUseCase.invoke(userName);
                if (roleId == null) {
                    answerReceiver.onAnswerError(Coder.encodingRUS("Ошибка. Роль для этого пользователя не назначена..."));
                    return null;
                }
                answerReceiver.onAnswerSuccess(Coder.encodingRUS("Авторизация успешна..."));
            } else {
                answerReceiver.onAnswerError(Coder.encodingRUS("Проблемы с подключением. Проверьте VPN подключение..."));
            }
        return roleId;
    }
}
