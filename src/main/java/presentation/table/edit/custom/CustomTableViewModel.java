package presentation.table.edit.custom;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class CustomTableViewModel implements AnswerReceiver {

    private final static String INSERTING = "Inserting...";
    private final static String DELETING = "Deleting...";
    private final static String UPDATING = "Updating...";

    private final DataBaseRepository firstRepository;
    private final DataBaseRepository secondRepository;

    private final InsertRowUseCase insertFirstTableUseCase;
    private final InsertRowUseCase insertSecondTableUseCase;
    private final DeleteRowUseCase deleteRowUseCase;
    private final UpdateRowUseCase updateFirstTableUseCase;
    private final UpdateRowUseCase updateSecondTableUseCase;

    private final StringProperty answerProperty = new SimpleStringProperty();

    public CustomTableViewModel(DataBaseRepository firstRepository, DataBaseRepository secondRepository) {
        this.firstRepository = firstRepository;
        this.secondRepository = secondRepository;

        this.insertFirstTableUseCase = new InsertRowUseCase(firstRepository, this);
        this.insertSecondTableUseCase = new InsertRowUseCase(secondRepository, this);
        this.deleteRowUseCase = new DeleteRowUseCase(secondRepository, this);
        this.updateFirstTableUseCase = new UpdateRowUseCase(firstRepository, this);
        this.updateSecondTableUseCase = new UpdateRowUseCase(secondRepository, this);
    }

    public ObservableValue<String> getAnswerProperty() {
       return answerProperty;
    }

    @Override
    public void onAnswerSuccess(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }

    @Override
    public void onAnswerError(String answer) {
        Platform.runLater(() -> answerProperty.setValue(answer));
    }
}
