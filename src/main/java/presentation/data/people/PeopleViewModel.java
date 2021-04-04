package presentation.data.people;

import data.tables.BossAndEmployeesTableImpl;
import data.tables.PeopleAndProfessionsTableImpl;
import data.tables.ProfessionsTableImpl;
import data.tables.SectorAndBossTableImpl;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.rows.BossAndEmployeesRow;
import domain.rows.PeopleAndProfessionRow;
import domain.rows.ProfessionsRow;
import domain.rows.SectorAndBossRow;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.SceneController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PeopleViewModel {

    private final static String INSERTING = "Inserting...";
    private final static String DELETING = "Deleting...";
    private final static String UPDATING = "Updating...";
    private final static String LOADING = "Loading...";

    private final StringProperty professionAnswerProperty = new SimpleStringProperty();
    private final StringProperty peopleAndProfessionsAnswerProperty = new SimpleStringProperty();
    private final StringProperty bossAndEmployeesAnswerProperty = new SimpleStringProperty();
    private final StringProperty sectorAndBossAnswerProperty = new SimpleStringProperty();
    private final StringProperty loadingTestDataAnswerProperty = new SimpleStringProperty();

    private final Property<ObservableList<ProfessionsRow>> professionsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<PeopleAndProfessionRow>> peopleAndProfessionsRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<BossAndEmployeesRow>> bossAndEmployeesRowProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<SectorAndBossRow>> sectorAndBossRowProperty = new SimpleObjectProperty<>();

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

    private final GetRowsUseCase professionsGetRowsUseCase;
    private final GetRowsUseCase peopleAndProfessionsGetRowsUseCase;
    private final GetRowsUseCase bossAndEmployeesGetRowsUseCase;
    private final GetRowsUseCase sectorAndBossGetRowsUseCase;

    private final LoadTestDataUseCase loadTestDataUseCase;

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
            }
        });

        professionsGetRowsUseCase = new GetRowsUseCase(professionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                professionsRowProperty.setValue(FXCollections.observableArrayList((List<ProfessionsRow>) answer));
            }

            @Override
            public void onError(Object answer) {
                professionsRowProperty.setValue(FXCollections.observableArrayList());
                professionAnswerProperty.setValue((String) answer);
            }
        });
        peopleAndProfessionsGetRowsUseCase = new GetRowsUseCase(peopleAndProfessionsTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                peopleAndProfessionsRowProperty.setValue(FXCollections.observableArrayList((List<PeopleAndProfessionRow>) answer));
            }

            @Override
            public void onError(Object answer) {
                peopleAndProfessionsRowProperty.setValue(FXCollections.observableArrayList());
                peopleAndProfessionsAnswerProperty.setValue((String) answer);
            }
        });
        bossAndEmployeesGetRowsUseCase = new GetRowsUseCase(bossAndEmployeesTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                bossAndEmployeesRowProperty.setValue(FXCollections.observableArrayList((List<BossAndEmployeesRow>) answer));
            }

            @Override
            public void onError(Object answer) {
                bossAndEmployeesRowProperty.setValue(FXCollections.observableArrayList());
                bossAndEmployeesAnswerProperty.setValue((String) answer);
            }
        });
        sectorAndBossGetRowsUseCase = new GetRowsUseCase(sectorAndBossTable, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                sectorAndBossRowProperty.setValue(FXCollections.observableArrayList((List<SectorAndBossRow>) answer));
            }

            @Override
            public void onError(Object answer) {
                sectorAndBossRowProperty.setValue(FXCollections.observableArrayList());
                sectorAndBossAnswerProperty.setValue((String) answer);
            }
        });

        ArrayList<DataBaseRepository> dataBaseRepositoryArrayList = new ArrayList<>();
        dataBaseRepositoryArrayList.add(professionsTable);
        dataBaseRepositoryArrayList.add(peopleAndProfessionsTable);
        dataBaseRepositoryArrayList.add(bossAndEmployeesTable);
        dataBaseRepositoryArrayList.add(sectorAndBossTable);

        loadTestDataUseCase = new LoadTestDataUseCase(dataBaseRepositoryArrayList, new AnswerReceiver() {
            @Override
            public void onSuccess(Object answer) {
                loadingTestDataAnswerProperty.setValue((String) answer);
                updateProfessionsTable();
                updatePeopleAndProfessionsTable();
                updateBossAndEmployeesTable();
                updateSectorAndBossTable();
            }

            @Override
            public void onError(Object answer) {
                loadingTestDataAnswerProperty.setValue((String) answer);
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
        //TODO
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
        loadTestDataUseCase.invoke();
    }

    public void updateProfessionsTable() {
        professionAnswerProperty.setValue(LOADING);
        professionsGetRowsUseCase.invoke();
    }

    public void updatePeopleAndProfessionsTable() {
        peopleAndProfessionsAnswerProperty.setValue(LOADING);
        peopleAndProfessionsGetRowsUseCase.invoke();
    }

    public void updateBossAndEmployeesTable() {
        bossAndEmployeesAnswerProperty.setValue(LOADING);
        bossAndEmployeesGetRowsUseCase.invoke();
    }

    public void updateSectorAndBossTable() {
        sectorAndBossAnswerProperty.setValue(LOADING);
        sectorAndBossGetRowsUseCase.invoke();
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

    public Property<ObservableList<ProfessionsRow>> getProfessionsRowProperty() {
        return professionsRowProperty;
    }

    public ObservableValue<String> getLoadingTestDataAnswerProperty() {
        return loadingTestDataAnswerProperty;
    }

    public Property<ObservableList<PeopleAndProfessionRow>> getPeopleAndProfessionsRowProperty() {
        return peopleAndProfessionsRowProperty;
    }

    public Property<ObservableList<BossAndEmployeesRow>> getBossAndEmployeesRowProperty() {
        return bossAndEmployeesRowProperty;
    }

    public Property<ObservableList<SectorAndBossRow>> getSectorAndBossRowProperty() {
        return sectorAndBossRowProperty;
    }
}
