package domain.usecases.nonParameterized;

import data.Coder;
import data.JDBCConnection;
import data.tables.authorize.RolesTableImpl;
import data.tables.authorize.UsersAndRolesTableImpl;
import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TechniquesTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import data.tables.brigades.BrigadeAndEmployeesTableImpl;
import data.tables.brigades.BrigadeAndForemanTableImpl;
import data.tables.estimate.ObjectsAndTechnicsTableImpl;
import data.tables.estimate.ObjectsTableImpl;
import data.tables.organizations.ManagementsAndSectorsTableImpl;
import data.tables.organizations.ManagementsTableImpl;
import data.tables.organizations.OrganizationsAndManagementTableImpl;
import data.tables.organizations.OrganizationsTableImpl;
import data.tables.people.*;
import data.tables.query.*;
import data.tables.report.FactMaterialsTableImpl;
import data.tables.report.FactTechnicsTableImpl;
import data.tables.report.FactWorksTableImpl;
import data.tables.schedule.SchedulesTableImpl;
import data.tables.schedule.TheoreticMaterialsTableImpl;
import domain.AnswerReceiver;
import domain.DataBaseRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LoadTestDataUseCase implements NonParamUseCase {

    private final ArrayList<DataBaseRepository> repositoryArrayList;
    private final AnswerReceiver answerReceiver;

    private final ArrayList<String> managerGrants = new ArrayList<>(
            Arrays.asList(
                    "GRANT SELECT ON \"US_AND_ROL\" TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT ON \"ROLES\" TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON BOSS_AND_EMPL TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON SECTOR_AND_BOSS TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON PEOPLE_AND_PROF TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON MANAGEMENTS TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON MANAG_AND_SECT TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON ORGANIZATIONS TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON ORGAN_AND_MANAG TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON BRIGADE_AND_EMPL TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON BRIGADE_AND_MAN TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT ON SECTORS TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT SELECT ON PROFESSIONS TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT INSERT ON Q_PROF TO \"18206_VALKOVA_MANAGER_ROLE\"",
                    "GRANT INSERT ON Q_SECTORS TO \"18206_VALKOVA_MANAGER_ROLE\""

            )
    );

    private final ArrayList<String> chiefGrants = new ArrayList<>(
            Arrays.asList(
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON FACT_MATER TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON FACT_TECHN TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON FACT_WORK TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON SCHEDULES TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON THEOR_MATER TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON OBJECTS TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT, INSERT, UPDATE, DELETE ON ESTIMATE TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON MATERIALS TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON TECHNIQUES TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON TYPES_OF_JOBS TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON BRIGADE_AND_MAN TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON SECTORS TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON US_AND_ROL TO \"18206_VALKOVA_CHIEF_ROLE\"",
                    "GRANT SELECT ON ROLES TO \"18206_VALKOVA_CHIEF_ROLE\""
            )
    );

    private final ArrayList<String> directorGrants = new ArrayList<>(
            Arrays.asList(
                    "GRANT SELECT ON SECTORS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON PEOPLE_AND_PROF TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON MANAG_AND_SECT TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON SECTOR_AND_BOSS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON PROFESSIONS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON MANAGEMENTS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON OBJECTS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON ESTIMATE TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON TECHNIQUES TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON SCHEDULES TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON BRIGADE_AND_EMPL TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON BRIGADE_AND_MAN TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON TYPES_OF_JOBS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON FACT_MATER TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON FACT_TECHN TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON FACT_WORK TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON ORGANIZATIONS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON ORGAN_AND_MANAG TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON THEOR_MATER TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON MATERIALS TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON \"US_AND_ROL\" TO \"18206_VALKOVA_DIRECTOR_ROLE\"",
                    "GRANT SELECT ON \"ROLES\" TO \"18206_VALKOVA_DIRECTOR_ROLE\""
            )
    );


    public LoadTestDataUseCase(ArrayList<DataBaseRepository> repositoryArrayList, AnswerReceiver answerReceiver) {
        this.repositoryArrayList = repositoryArrayList;
        this.answerReceiver = answerReceiver;
    }

    public LoadTestDataUseCase(AnswerReceiver answerReceiver) {
        this.repositoryArrayList = new ArrayList<>();
        this.repositoryArrayList.add(new ProfessionsTableImpl());
        this.repositoryArrayList.add(new PeopleAndProfessionsTableImpl());
        this.repositoryArrayList.add(new BossAndEmployeesTableImpl());
        this.repositoryArrayList.add(new SectorsTableImpl());
        this.repositoryArrayList.add(new SectorAndBossTableImpl());

        this.repositoryArrayList.add(new ManagementsTableImpl());
        this.repositoryArrayList.add(new OrganizationsTableImpl());
        this.repositoryArrayList.add(new ManagementsAndSectorsTableImpl());
        this.repositoryArrayList.add(new OrganizationsAndManagementTableImpl());

        this.repositoryArrayList.add(new BrigadeAndForemanTableImpl());
        this.repositoryArrayList.add(new BrigadeAndEmployeesTableImpl());

        this.repositoryArrayList.add(new MaterialsTableImpl());
        this.repositoryArrayList.add(new TechniquesTableImpl());
        this.repositoryArrayList.add(new TypesOfJobsTableImpl());

        this.repositoryArrayList.add(new ObjectsTableImpl());
        this.repositoryArrayList.add(new ObjectsAndTechnicsTableImpl());

        this.repositoryArrayList.add(new SchedulesTableImpl());
        this.repositoryArrayList.add(new TheoreticMaterialsTableImpl());

        this.repositoryArrayList.add(new FactWorksTableImpl());
        this.repositoryArrayList.add(new FactMaterialsTableImpl());
        this.repositoryArrayList.add(new FactTechnicsTableImpl());

        this.repositoryArrayList.add(new QueryMaterialsTableImpl());
        this.repositoryArrayList.add(new QueryProfessionsTableImpl());
        this.repositoryArrayList.add(new QuerySectorsTableImpl());
        this.repositoryArrayList.add(new QueryTechnicsTableImpl());
        this.repositoryArrayList.add(new QueryTypesOfWorkTableImpl());

        this.repositoryArrayList.add(new RolesTableImpl());
        this.repositoryArrayList.add(new UsersAndRolesTableImpl());

        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke() {
        Thread thread = new Thread(() -> {
            try {
                JDBCConnection.getConnection().setAutoCommit(false);
                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.deleteTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось удалить старую таблицу ") +
                                        currentRepository.getUITableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Удалена старая таблица ") +
                                        currentRepository.getUITableName()
                        );
                    }
                }

                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось создать новую таблицу ")
                                        + currentRepository.getUITableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Создана новая таблица ")
                                        + currentRepository.getUITableName()
                        );
                    }
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createIdAutoIncrementTrigger()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось создать триггер автоинкрементации id для ")
                                        + currentRepository.getUITableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Создан триггер автоинкрементации id для ")
                                        + currentRepository.getUITableName()
                        );
                    }
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.loadTestData()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось загрузить тестовые данные для ") +
                                        currentRepository.getUITableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Загружены тестовые данные для ") +
                                        currentRepository.getUITableName()
                        );
                    }
                }

                for (String grant : managerGrants) {
                    try {
                        PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(grant);
                        preStatement.execute();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать роль "));
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                for (String grant : chiefGrants) {
                    try {
                        PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(grant);
                        preStatement.execute();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать роль "));
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                for (String grant : directorGrants) {
                    try {
                        PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(grant);
                        preStatement.execute();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(Coder.encodingRUS("Не удалось создать роль "));
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                }

                JDBCConnection.getConnection().commit();
                JDBCConnection.getConnection().setAutoCommit(true);
                answerReceiver.onAnswerSuccess(Coder.encodingRUS("Все данные успешно загружены"));
            } catch (SQLException throwables) {
                answerReceiver.onAnswerError(Coder.encodingRUS("Ошибка"));
            }
        });
        thread.start();
        return null;
    }
}
