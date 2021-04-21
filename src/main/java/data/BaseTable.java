package data;

import domain.DataBaseRepository;
import domain.rows.Row;

import java.io.Closeable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseTable implements Closeable {

    private final String TABLE_NAME;

    public BaseTable(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public void close() {
        try {
            if (JDBCConnection.getConnection() != null && !JDBCConnection.getConnection().isClosed())
                JDBCConnection.getConnection().close();
        } catch (SQLException e) {
            System.out.println("Error: closing sql!");
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            return preStatement.executeQuery();
    }

    public int executeUpdate(String sql) throws SQLException {
        Statement statement = JDBCConnection.getConnection().createStatement();
        return statement.executeUpdate(sql);
    }

    public boolean createTrigger(String tableName) {
        String dropSeq = "DROP SEQUENCE " + tableName + "_seq";
        String createSeq = "CREATE SEQUENCE " + tableName + "_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER " + tableName + "_autoincrement\n" +
                "    BEFORE INSERT\n" +
                "    ON " + tableName + "\n" +
                "    FOR EACH ROW\n" +
                "BEGIN\n" +
                "    SELECT " + tableName + "_seq.NextVal INTO :new.ID FROM dual;\n" +
                "END;";
        try {
            executeUpdate(dropSeq);
            executeUpdate(createSeq);
            executeUpdate(trigger);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    public ArrayList<Row> getArrayOfRows(DataBaseRepository dataBaseRepository) {
        String sql = "SELECT * FROM " + dataBaseRepository.getSQLTableName();
        ResultSet resultSet;
        try {
            resultSet = executeQuery(sql);
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<Row> rowArrayList = new ArrayList<>();
        ArrayList<String> fieldsNames = dataBaseRepository.getFieldNames();
        while (true) {
            try {
                if (!resultSet.next()) break;
                ArrayList<String> resultList = new ArrayList<>();
                for (String field : fieldsNames) {
                    resultList.add(resultSet.getString(field));
                }
                rowArrayList.add(dataBaseRepository.createRow(resultList));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    public String getSQLTableName() {
        return TABLE_NAME;
    }

    public boolean deleteTable() {
        String sql = "drop table " + getSQLTableName();
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public boolean deleteRow(int id) {
        String sql = "DELETE FROM " + getSQLTableName() + " WHERE id = " + id;
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public boolean createIdAutoIncrementTrigger() {
        return createTrigger(getSQLTableName());
    }
}
