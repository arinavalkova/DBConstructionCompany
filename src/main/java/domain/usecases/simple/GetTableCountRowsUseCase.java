package domain.usecases.simple;

import data.BaseTable;
import data.JDBCConnection;

import java.sql.SQLException;

public class GetTableCountRowsUseCase implements UseCase {
    private final BaseTable baseTable;

    public GetTableCountRowsUseCase(String tableName, JDBCConnection jdbcConnection) throws SQLException {
        this.baseTable = new BaseTable(tableName, jdbcConnection);
    }

    @Override
    public Object invoke() throws SQLException {
        return baseTable.getCountRows();
    }
}
