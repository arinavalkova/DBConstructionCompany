package domain;

public interface AnswerReceiver {
    void onAnswerSuccess(String answer);
    void onAnswerError(String answer);
}
