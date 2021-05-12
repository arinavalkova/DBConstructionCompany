package domain.usecases.parameterized;

import domain.AnswerReceiver;

public class AuthorizeRoleUseCase implements ParamUseCase {

    private final AnswerReceiver answerReceiver;

    public AuthorizeRoleUseCase(AnswerReceiver answerReceiver) {
        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke(Object... object) {
        return null;
    }
}
