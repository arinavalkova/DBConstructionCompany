package domain.usecases.nonParameterized;

import data.Coder;
import data.JDBCConnection;
import data.tables.booker.MaterialsTableImpl;
import data.tables.booker.TechniquesTableImpl;
import data.tables.booker.TypesOfJobsTableImpl;
import data.tables.brigades.BrigadeAndEmployeesTableImpl;
import data.tables.brigades.BrigadeAndForemanTableImpl;
import data.tables.organizations.ManagementsAndSectorsTableImpl;
import data.tables.organizations.ManagementsTableImpl;
import data.tables.organizations.OrganizationsAndManagementTableImpl;
import data.tables.organizations.OrganizationsTableImpl;
import data.tables.people.*;
import domain.AnswerReceiver;
import domain.DataBaseRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class LoadTestDataUseCase implements NonParamUseCase {

    private final ArrayList<DataBaseRepository> repositoryArrayList;
    private final AnswerReceiver answerReceiver;

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
                                        currentRepository.getTableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Удалена старая таблица ") +
                                        currentRepository.getTableName()
                        );
                    }
                }

                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось создать новую таблицу ")
                                + currentRepository.getTableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Создана новая таблица ")
                                        + currentRepository.getTableName()
                        );
                    }
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createIdAutoIncrementTrigger()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось создать триггер автоинкрементации id для ")
                                + currentRepository.getTableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Создан триггер автоинкрементации id для ")
                                        + currentRepository.getTableName()
                        );
                    }
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.loadTestData()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Не удалось загрузить тестовые данные для ") +
                                        currentRepository.getTableName()
                        );
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else {
                        answerReceiver.onAnswerError(
                                Coder.encodingRUS("Загружены тестовые данные для ") +
                                        currentRepository.getTableName()
                        );
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
