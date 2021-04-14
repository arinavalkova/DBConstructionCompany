package domain.usecases.nonParameterized;

import data.JDBCConnection;
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

        this.answerReceiver = answerReceiver;
    }

    @Override
    public Object invoke() {
        Thread thread = new Thread(() -> {
            try {
                JDBCConnection.getConnection().setAutoCommit(false);
                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    System.out.println("***" + currentRepository.getTableName() + "***");
                    if (!currentRepository.deleteTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError("Error with deleting old table " + currentRepository.getTableName());
                        System.out.println("Error with deleting old table " + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        Collections.reverse(repositoryArrayList);
                        //return;
                    } else
                    System.out.println("Deleted old table " + currentRepository.getTableName());
                }

                Collections.reverse(repositoryArrayList);

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createTable()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError("Error with creating table " + currentRepository.getTableName());
                        System.out.println("Error with creating table " + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    } else
                    System.out.println("Created old table " + currentRepository.getTableName());
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.createIdAutoIncrementTrigger()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError("Error with creating auto increment id trigger for "
                                + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                    System.out.println("Created trigger old table " + currentRepository.getTableName());
                }

                for (DataBaseRepository currentRepository : repositoryArrayList) {
                    if (!currentRepository.loadTestData()) {
                        JDBCConnection.getConnection().rollback();
                        answerReceiver.onAnswerError("Error with loading test data to " + currentRepository.getTableName());
                        JDBCConnection.getConnection().setAutoCommit(true);
                        return;
                    }
                    System.out.println("Loaded data of " + currentRepository.getTableName());
                }

                JDBCConnection.getConnection().commit();
                JDBCConnection.getConnection().setAutoCommit(true);
                answerReceiver.onAnswerSuccess("Successfully loaded test data");
            } catch (SQLException throwables) {
                answerReceiver.onAnswerError("Error");
            }
        });
        thread.start();
        return null;
    }
}
