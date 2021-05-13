package domain.usecases.parameterized;

import domain.AnswerReceiver;

public class CreateNewUserByRoleUseCase implements ParamUseCase {

    private final CreateNewMangerUserUseCase createNewMangerUserUseCase;

    public CreateNewUserByRoleUseCase(AnswerReceiver answerReceiver) {
        this.createNewMangerUserUseCase = new CreateNewMangerUserUseCase(answerReceiver);
    }

    @Override
    public Object invoke(Object... object) {
        String userName = (String) object[0];
        String password = (String) object[1];
        int roleId = (int) object[3];

        if (roleId == 0) {

        } else if (roleId == 1) {
            createNewMangerUserUseCase.invoke(userName, password);
        } else if (roleId == 2) {

        }
        return null;
    }
}
