package data;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseTable implements Closeable {

    private final Connection connection;

    public BaseTable(JDBCConnection jdbcConnection) {
        this.connection = jdbcConnection.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Error: closing sql!");
        }
    }
}
