package data;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseTable implements Closeable {

    private final Connection connection;

    public BaseTable(JDBCConnection jdbcConnection) throws SQLException {
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


    void executeSqlStatement(String sql) throws SQLException {
//        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
//        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
//        statement.execute(sql); // Выполняем statement - sql команду
//        statement.close();      // Закрываем statement для фиксации изменений в СУБД
//        if (description != null)
//            System.out.println(description);
    }


    void reopenConnection() throws SQLException {
//        if (connection == null || connection.isClosed()) {
//            connection = StockExchangeDB.getConnection();
//        }
    }
}
