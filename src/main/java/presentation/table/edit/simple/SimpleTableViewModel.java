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
import presentation.table.show.ShowTableView;
import presentation.table.show.ShowTableViewModel;

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
    private final ShowTableView showTableView;

    public SimpleTableViewModel(DataBaseRepository repository, ShowTableView showTableView) {
        this.dataBaseRepository = repository;
        this.showTableView = showTableView;
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
        showTableView.getModel().updateTable();
    }

    public void insert(ArrayList<String> rowLines) {
        answerProperty.setValue(INSERTING);
        insertRowUseCase.invoke(dataBaseRepository.createRow(rowLines));
        showTableView.getModel().updateTable();
    }

    public void update(ArrayList<String> rowLines) {
        answerProperty.setValue(UPDATING);
        updateRowUseCase.invoke(dataBaseRepository.createRow(rowLines));
        showTableView.getModel().updateTable();
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
