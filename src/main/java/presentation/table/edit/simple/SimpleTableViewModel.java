package presentation.table.edit.simple;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

public class SimpleTableViewModel implements AnswerReceiver {

    private final static String INSERTING = "Inserting...";
    private final static String DELETING = "Deleting...";
    private final static String UPDATING = "Updating...";

    private final InsertRowUseCase insertRowUseCase;
    private final DeleteRowUseCase deleteRowUseCase;
    private final UpdateRowUseCase updateRowUseCase;

    private final StringProperty answerProperty = new SimpleStringProperty();

    private final DataBaseRepository dataBaseRepository;

    public SimpleTableViewModel(DataBaseRepository repository) {
        this.dataBaseRepository = repository;

        this.insertRowUseCase = new InsertRowUseCase(dataBaseRepository, this);
        this.deleteRowUseCase = new DeleteRowUseCase(dataBaseRepository, this);
        this.updateRowUseCase = new UpdateRowUseCase(dataBaseRepository, this);
    }

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public void delete(int id) {
        answerProperty.setValue(DELETING);
        deleteRowUseCase.invoke(id);
    }

    public void insert(ArrayList<String> rowLines) {
        answerProperty.setValue(INSERTING);
        insertRowUseCase.invoke(dataBaseRepository.createRow(rowLines));
    }

    public void update(ArrayList<String> rowLines) {
        answerProperty.setValue(UPDATING);
        updateRowUseCase.invoke(dataBaseRepository.createRow(rowLines));
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
