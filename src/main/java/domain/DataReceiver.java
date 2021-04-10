package domain;

public interface DataReceiver {
    void onDataSuccess(Object object);
    void onDataError(String answer);
}
