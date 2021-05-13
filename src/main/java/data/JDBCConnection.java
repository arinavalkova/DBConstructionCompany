package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class JDBCConnection {
    private static Connection connection;

    private JDBCConnection() {}

    public static boolean establishJDBCConnection(String userName, String password) {
        String url = "jdbc:oracle:thin:@84.237.50.81:1521:";
        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", password);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static Connection getConnection() {
        if (connection == null) {
            establishJDBCConnection("18206_VALKOVA", "nar&Alex");
        }
        return connection;
    }
}
