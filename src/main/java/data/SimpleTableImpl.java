package data;

import domain.DataBaseRepository;

import java.sql.SQLException;

public class SimpleTableImpl extends BaseTable implements DataBaseRepository {
    //simple table row
    private final static String TABLE_NAME = "simple";

    SimpleTableImpl(JDBCConnection jdbcConnection) throws SQLException {
        super(TABLE_NAME, jdbcConnection);
    }
}
