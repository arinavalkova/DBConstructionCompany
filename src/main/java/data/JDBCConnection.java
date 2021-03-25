package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class JDBCConnection {
    private final Connection connection;

    public JDBCConnection(String userName, String password) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:oracle:thin:@84.237.50.81:1521:";
        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", password);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        connection = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return connection;
    }
}
