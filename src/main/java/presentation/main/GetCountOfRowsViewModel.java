package presentation.main;

import data.JDBCConnection;
import domain.usecases.simple.GetTableCountRowsUseCase;

import java.sql.SQLException;

public class GetCountOfRowsViewModel {

    private final JDBCConnection jdbcConnection;

    public GetCountOfRowsViewModel(String userName, String password) throws SQLException {
        this.jdbcConnection = new JDBCConnection(userName, password);
    }

    public Integer loadCountOfRows(String tableName) throws SQLException {
        return (Integer) new GetTableCountRowsUseCase(tableName, jdbcConnection).invoke();
    }
}
