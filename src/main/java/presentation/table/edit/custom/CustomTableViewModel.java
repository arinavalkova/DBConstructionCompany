package presentation.table.edit.custom;

import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.usecases.parameterized.CustomInsertRowUseCase;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import domain.usecases.parameterized.queries.people.GetProfessionByNameUseCase;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import presentation.table.show.ShowTableView;

import java.util.ArrayList;

public class CustomTableViewModel implements AnswerReceiver {

    private final static String INSERTING = "Inserting...";
    private final static String DELETING = "Deleting...";
    private final static String UPDATING = "Updating...";

    private final DataBaseRepository firstRepository;
    private final DataBaseRepository secondRepository;

    private final InsertRowUseCase insertFirstTableUseCase;
    private final CustomInsertRowUseCase insertSecondTableUseCase;
    private final DeleteRowUseCase deleteRowUseCase;
    private final UpdateRowUseCase updateFirstTableUseCase;
    private final UpdateRowUseCase updateSecondTableUseCase;

    private final GetProfessionByNameUseCase getProfessionByNameUseCase;

    private final ShowTableView firstShowTable;
    private final ShowTableView secondShowTable;

    private final StringProperty answerProperty = new SimpleStringProperty();

    public CustomTableViewModel(
            DataBaseRepository firstRepository,
            DataBaseRepository secondRepository,
            ShowTableView firstShowTable,
            ShowTableView secondShowTable) {
        this.firstRepository = firstRepository;
        this.secondRepository = secondRepository;

        this.firstShowTable = firstShowTable;
        this.secondShowTable = secondShowTable;

        this.insertFirstTableUseCase = new InsertRowUseCase(firstRepository, this);
        this.insertSecondTableUseCase = new CustomInsertRowUseCase(
                firstRepository,
                secondRepository,
                this
        );
        this.deleteRowUseCase = new DeleteRowUseCase(secondRepository, this);
        this.updateFirstTableUseCase = new UpdateRowUseCase(firstRepository, this);
        this.updateSecondTableUseCase = new UpdateRowUseCase(secondRepository, this);
        this.getProfessionByNameUseCase = new GetProfessionByNameUseCase();
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

    public void deleteFromSecondTable(int id) {
        answerProperty.setValue(DELETING);
        deleteRowUseCase.invoke(id);
        secondShowTable.getModel().updateTable();
    }

    public void updateSecondTable(ArrayList<String> rowLines) {
        answerProperty.setValue(UPDATING);
        updateSecondTableUseCase.invoke(secondRepository.createRow(rowLines));
        secondShowTable.getModel().updateTable();
    }

    public void updateFirstTable(ArrayList<String> rowLines) {
        answerProperty.setValue(UPDATING);
        updateFirstTableUseCase.invoke(firstRepository.createRow(rowLines));
        firstShowTable.getModel().updateTable();
    }

    public void insertFirstTable(ArrayList<String> rowLines) {
        answerProperty.setValue(INSERTING);
        if (!startCheck(firstRepository, rowLines)) {
            return;
        }
        insertFirstTableUseCase.invoke(firstRepository.createRow(rowLines));
        firstShowTable.getModel().updateTable();
    }

    public void insertSecondTable(ArrayList<String> rowLines) {
        if (!startCheck(secondRepository, rowLines)) {
            return;
        }
        insertSecondTableUseCase.invoke(rowLines);
        secondShowTable.getModel().updateTable();
    }

    public boolean startCheck(DataBaseRepository dataBaseRepository, ArrayList<String> rowList) {
        ArrayList<String> list = dataBaseRepository.getCheckName();
        for (String check : list) {
            System.out.println(check);
            if (check.equals("ENGINEER")) {
                if (!engineerCheck(Integer.parseInt(String.valueOf(rowList.get(rowList.size() - 1))))) {
                    answerProperty.setValue("Error: boss should be with engineer professions");
                    return false;
                }
            } else if (check.equals("NOT_ENGINEER")) {
                if (engineerCheck(Integer.parseInt(String.valueOf(rowList.get(1))))) {
                    answerProperty.setValue("Error: foreman shouldn't be with engineer profession");
                    return false;
                }
            } else if (check.equals("BRIGADE_PROF")) {
                if (!brigadeProfessionCheck
                        (
                        Integer.parseInt(String.valueOf(rowList.get(rowList.size() - 2))),
                        Integer.parseInt(String.valueOf(rowList.get(rowList.size() - 1))))) {
                    answerProperty.setValue("Error: employee and foreman should have the same profession");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean engineerCheck(int bossId) {
        int professionName = (int) getProfessionByNameUseCase.invoke(bossId);
        System.out.println(professionName);
        return professionName == 0 ||
                professionName == 1 ||
                professionName == 2;
    }
    
    private boolean brigadeProfessionCheck(int foremanId, int employeeId) {
        int foremanProfessionId = (int) getProfessionByNameUseCase.invoke(foremanId);
        int employeeProfessionId = (int) getProfessionByNameUseCase.invoke(employeeId);

        return foremanProfessionId == employeeProfessionId;
    }
}
