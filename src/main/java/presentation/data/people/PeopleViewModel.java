package presentation.data.people;

import data.tables.BossAndEmployeesTableImpl;
import data.tables.PeopleAndProfessionsTableImpl;
import data.tables.ProfessionsTableImpl;
import data.tables.SectorAndBossTableImpl;
import domain.AnswerReceiver;
import domain.rows.BossAndEmployeesRow;
import domain.rows.PeopleAndProfessionRow;
import domain.rows.ProfessionsRow;
import domain.rows.SectorAndBossRow;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import presentation.SceneController;

import java.io.IOException;

public class PeopleViewModel {

    private final static String INSERTING = "Inserting...";
    private final static String DELETING = "Deleting...";
    private final static String UPDATING = "Updating...";

    private final StringProperty professionAnswerProperty = new SimpleStringProperty();
    private final StringProperty peopleAndProfessionsAnswerProperty = new SimpleStringProperty();
    private final StringProperty bossAndEmployeesAnswerProperty = new SimpleStringProperty();
    private final StringProperty sectorAndBossAnswerProperty = new SimpleStringProperty();

    private final InsertRowUseCase professionsInsertUseCase;
    private final InsertRowUseCase peopleAndProfessionsInsertUseCase;
    private final InsertRowUseCase bossAndEmployeesInsertUseCase;
    private final InsertRowUseCase sectorAndBossInsertUseCase;

    private final DeleteRowUseCase professionsDeleteUseCase;
    private final DeleteRowUseCase peopleAndProfessionsDeleteUseCase;
    private final DeleteRowUseCase bossAndEmployeesDeleteUseCase;
    private final DeleteRowUseCase sectorAndBossDeleteUseCase;

    private final UpdateRowUseCase professionsUpdateUseCase;
    private final UpdateRowUseCase peopleAndProfessionsUpdateUseCase;
    private final UpdateRowUseCase bossAndEmployeesUpdateUseCase;
    private final UpdateRowUseCase sectorAndBossUpdateUseCase;

    public PeopleViewModel() {
        ProfessionsTableImpl professionsTable = new ProfessionsTableImpl();
        professionsInsertUseCase = new InsertRowUseCase(professionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                professionAnswerProperty.setValue((String) answer);
                updateProfessionsTable();
            }

            @Override
            public void onError(Object answer) {
                professionAnswerProperty.setValue((String) answer);
            }
        });
        professionsDeleteUseCase = new DeleteRowUseCase(professionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                professionAnswerProperty.setValue((String) answer);
                updateProfessionsTable();
            }

            @Override
            public void onError(Object answer) {
                professionAnswerProperty.setValue((String) answer);
            }
        });
        professionsUpdateUseCase = new UpdateRowUseCase(professionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                professionAnswerProperty.setValue((String) answer);
                updateProfessionsTable();
            }

            @Override
            public void onError(Object answer) {
                professionAnswerProperty.setValue((String) answer);
            }
        });

        PeopleAndProfessionsTableImpl peopleAndProfessionsTable = new PeopleAndProfessionsTableImpl();
        peopleAndProfessionsInsertUseCase = new InsertRowUseCase(peopleAndProfessionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
                updatePeopleAndProfessionsTable();
            }

            @Override
            public void onError(Object answer) {
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
            }
        });
        peopleAndProfessionsDeleteUseCase = new DeleteRowUseCase(peopleAndProfessionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
                updatePeopleAndProfessionsTable();
            }

            @Override
            public void onError(Object answer) {
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
            }
        });
        peopleAndProfessionsUpdateUseCase = new UpdateRowUseCase(peopleAndProfessionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
                updatePeopleAndProfessionsTable();
            }

            @Override
            public void onError(Object answer) {
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
            }
        });

        BossAndEmployeesTableImpl bossAndEmployeesTable = new BossAndEmployeesTableImpl();
        bossAndEmployeesInsertUseCase = new InsertRowUseCase(bossAndEmployeesTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                bossAndEmployeesAnswerProperty.setValue((String) answer);
                updateBossAndEmployeesTable();
            }

            @Override
            public void onError(Object answer) {
                bossAndEmployeesAnswerProperty.setValue((String) answer);
            }
        });
        bossAndEmployeesDeleteUseCase = new DeleteRowUseCase(bossAndEmployeesTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                bossAndEmployeesAnswerProperty.setValue((String) answer);
                updateBossAndEmployeesTable();
            }

            @Override
            public void onError(Object answer) {
                bossAndEmployeesAnswerProperty.setValue((String) answer);
            }
        });
        bossAndEmployeesUpdateUseCase = new UpdateRowUseCase(bossAndEmployeesTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                bossAndEmployeesAnswerProperty.setValue((String) answer);
                updateBossAndEmployeesTable();
            }

            @Override
            public void onError(Object answer) {
                bossAndEmployeesAnswerProperty.setValue((String) answer);
            }
        });

        SectorAndBossTableImpl sectorAndBossTable = new SectorAndBossTableImpl();
        sectorAndBossInsertUseCase = new InsertRowUseCase(sectorAndBossTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                sectorAndBossAnswerProperty.setValue((String) answer);
                updateSectorAndBossTable();
            }

            @Override
            public void onError(Object answer) {
                sectorAndBossAnswerProperty.setValue((String) answer);
            }
        });
        sectorAndBossDeleteUseCase = new DeleteRowUseCase(sectorAndBossTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                sectorAndBossAnswerProperty.setValue((String) answer);
                updateSectorAndBossTable();
            }

            @Override
            public void onError(Object answer) {
                sectorAndBossAnswerProperty.setValue((String) answer);
            }
        });
        sectorAndBossUpdateUseCase = new UpdateRowUseCase(sectorAndBossTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                sectorAndBossAnswerProperty.setValue((String) answer);
                updateSectorAndBossTable();
            }

            @Override
            public void onError(Object answer) {
                sectorAndBossAnswerProperty.setValue((String) answer);
                updateSectorAndBossTable();
            }
        });
    }

    public void backToEditingMenu() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAreasEditingWindow() {

    }

    public void insertProfession(String professionName) {
        professionAnswerProperty.setValue(INSERTING);
        professionsInsertUseCase.invoke(new ProfessionsRow(0, professionName));
    }

    public void deleteProfession(int id) {
        professionAnswerProperty.setValue(DELETING);
        professionsDeleteUseCase.invoke(id);
    }

    public void updateProfession(int id, String professionName) {
        professionAnswerProperty.setValue(UPDATING);
        professionsUpdateUseCase.invoke(new ProfessionsRow(id, professionName));
    }

    public void insertPeopleAndProfession(String name, int professionId) {
        peopleAndProfessionsAnswerProperty.setValue(INSERTING);
        peopleAndProfessionsInsertUseCase.invoke(new PeopleAndProfessionRow(0, name, professionId));
    }

    public void deletePeopleAndProfession(int id) {
        peopleAndProfessionsAnswerProperty.setValue(DELETING);
        peopleAndProfessionsDeleteUseCase.invoke(id);
    }

    public void updatePeopleAndProfession(int id, String personName, int professionId) {
        peopleAndProfessionsAnswerProperty.setValue(UPDATING);
        peopleAndProfessionsUpdateUseCase.invoke(new PeopleAndProfessionRow(id, personName, professionId));
    }

    public void insertBossAndEmployees(int bossId, int employeeId) {
        bossAndEmployeesAnswerProperty.setValue(INSERTING);
        bossAndEmployeesInsertUseCase.invoke(new BossAndEmployeesRow(0, bossId, employeeId));
    }

    public void deleteBossAndEmployees(int id) {
        bossAndEmployeesAnswerProperty.setValue(DELETING);
        bossAndEmployeesDeleteUseCase.invoke(id);
    }

    public void updateBossAndEmployees(int id, int bossId, int employeesId) {
        bossAndEmployeesAnswerProperty.setValue(UPDATING);
        bossAndEmployeesUpdateUseCase.invoke(new BossAndEmployeesRow(id, bossId, employeesId));
    }

    public void insertSectorAndBoss(String sectorName, int bossId) {
        sectorAndBossAnswerProperty.setValue(INSERTING);
        sectorAndBossInsertUseCase.invoke(new SectorAndBossRow(0, sectorName, bossId));
    }

    public void deleteSectorAndBoss(int id) {
        sectorAndBossAnswerProperty.setValue(DELETING);
        sectorAndBossDeleteUseCase.invoke(id);
    }

    public void updateSectorAndBoss(int id, String sectorName, int bossId) {
        sectorAndBossUpdateUseCase.invoke(UPDATING);
        sectorAndBossUpdateUseCase.invoke(new SectorAndBossRow(id, sectorName, bossId));
    }

    public void loadTestData() {

    }

    public void updateProfessionsTable() {

    }

    public void updatePeopleAndProfessionsTable() {

    }

    public void updateBossAndEmployeesTable() {

    }

    public void updateSectorAndBossTable() {

    }

    public ObservableValue<String> getProfessionsAnswerProperty() {
        return professionAnswerProperty;
    }

    public ObservableValue<String> getPeopleAndProfessionAnswerProperty() {
        return peopleAndProfessionsAnswerProperty;
    }

    public ObservableValue<String> getBossAndEmployeesAnswerProperty() {
        return bossAndEmployeesAnswerProperty;
    }

    public ObservableValue<String> getSectorAndBossAnswerProperty() {
        return sectorAndBossAnswerProperty;
    }
}
