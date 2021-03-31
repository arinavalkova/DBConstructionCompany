package domain.usecases.nonParameterized;

import data.JDBCConnection;
import domain.DataBaseRepository;

import java.sql.SQLException;

public class LoadTestDataUseCase implements NonParamUseCase {

    private final DataBaseRepository customersRepository;
    private final DataBaseRepository categoriesRepository;
    private final DataBaseRepository objectsRepository;

    public LoadTestDataUseCase(DataBaseRepository objectsRepository,
                               DataBaseRepository categoriesRepository,
                               DataBaseRepository customersRepository) {
        this.objectsRepository = objectsRepository;
        this.categoriesRepository = categoriesRepository;
        this.customersRepository = customersRepository;
    }

    @Override
    public Boolean invoke() {
        try {
            JDBCConnection.getConnection().setAutoCommit(false);

            if (!objectsRepository.deleteTable()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!customersRepository.deleteTable()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!categoriesRepository.deleteTable()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!categoriesRepository.createTable()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!customersRepository.createTable()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!objectsRepository.createTable()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!categoriesRepository.createIdAutoIncrementTrigger()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!customersRepository.createIdAutoIncrementTrigger()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }
            if (!objectsRepository.createIdAutoIncrementTrigger()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }

            if (!categoriesRepository.loadTestData()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }

            if (!customersRepository.loadTestData()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }

            if (!objectsRepository.loadTestData()) {
                JDBCConnection.getConnection().rollback();
                return false;
            }

            JDBCConnection.getConnection().commit();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
}
