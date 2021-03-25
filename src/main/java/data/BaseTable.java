package data;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseTable implements Closeable {

    private final Connection connection;
    String tableName;

    public BaseTable(String tableName, JDBCConnection jdbcConnection) throws SQLException {
        this.tableName = tableName;
        this.connection = jdbcConnection.getConnection();
    }

    public Integer getCountRows() throws SQLException {
        String sql = "select count(*) from " + tableName;
        PreparedStatement preStatement = connection.prepareStatement(sql);
        ResultSet result = preStatement.executeQuery();
        while (result.next()) {
            return result.getInt(1);
        }
        return null;
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
