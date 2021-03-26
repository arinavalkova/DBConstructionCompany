package domain.usecases.nonParameterized;

import data.JDBCConnection;
import domain.DataBaseRepository;

import java.sql.SQLException;

public class LoadTestDataUseCase implements NonParamUseCase {

    private final DataBaseRepository customersRepository;
    private final DataBaseRepository categoriesRepository;
    private final DataBaseRepository objectsRepository;

    private final JDBCConnection jdbcConnection;

    public LoadTestDataUseCase(DataBaseRepository objectsRepository,
                               DataBaseRepository categoriesRepository,
                               DataBaseRepository customersRepository,
                               JDBCConnection jdbcConnection) {
        this.objectsRepository = objectsRepository;
        this.categoriesRepository = categoriesRepository;
        this.customersRepository = customersRepository;
        this.jdbcConnection = jdbcConnection;
    }

    @Override
    public Boolean invoke() {
        try {
            jdbcConnection.getConnection().setAutoCommit(false);

            if (!objectsRepository.deleteTable()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!customersRepository.deleteTable()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!categoriesRepository.deleteTable()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!categoriesRepository.createTable()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!customersRepository.createTable()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!objectsRepository.createTable()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!categoriesRepository.createIdAutoIncrementTrigger()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!customersRepository.createIdAutoIncrementTrigger()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }
            if (!objectsRepository.createIdAutoIncrementTrigger()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }

            if (!categoriesRepository.loadTestData()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }

            if (!customersRepository.loadTestData()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }

            if (!objectsRepository.loadTestData()) {
                jdbcConnection.getConnection().rollback();
                return false;
            }

            jdbcConnection.getConnection().commit();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
}
