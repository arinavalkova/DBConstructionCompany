package domain;

public interface AnswerReceiver {
    void onSuccess(Object answer);
    void onError(Object answer);
}
