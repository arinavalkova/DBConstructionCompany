package data;

import java.io.Closeable;
import java.sql.SQLException;

public class BaseTable implements Closeable {

    public void close() {
        try {
            if (JDBCConnection.getConnection() != null && !JDBCConnection.getConnection().isClosed())
                JDBCConnection.getConnection().close();
        } catch (SQLException e) {
            System.out.println("Error: closing sql!");
        }
    }
}
