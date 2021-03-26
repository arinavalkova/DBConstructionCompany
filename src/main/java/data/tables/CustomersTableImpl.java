package data.tables;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.CustomersRow;
import domain.rows.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "customer";

    public CustomersTableImpl(JDBCConnection jdbcConnection) {
        super(jdbcConnection);
    }

    @Override
    public boolean insertRow(Row row) {
        CustomersRow customersRow = (CustomersRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + customersRow.getId()
                + ", '" + customersRow.getName() + "')";
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20))";
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTable() {
        String sql = "drop table " + TABLE_NAME;
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRow(Row row) {
        CustomersRow customersRow = (CustomersRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + customersRow.getName() + "' WHERE id = " + customersRow.getId();
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRow(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id;
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public List<Row> getRows() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<Row> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new CustomersRow(resultSet.getInt("id"), resultSet.getString("name")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE customer_seq";
        String createSeq = "CREATE SEQUENCE customer_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER customer_autoincrement\n" +
                "BEFORE INSERT ON customer\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT customer_seq.NextVal INTO :new.ID FROM dual;\n" +
                "END;";

        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(dropSeq);
            statement.executeUpdate(createSeq);
            statement.executeUpdate(trigger);
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean loadTestData() {
        if (!insertRow(new CustomersRow(0, "Vasilii"))) {
            return false;
        }
        if (!insertRow(new CustomersRow(0, "Gleb"))) {
            return false;
        }
        return true;
    }
}
