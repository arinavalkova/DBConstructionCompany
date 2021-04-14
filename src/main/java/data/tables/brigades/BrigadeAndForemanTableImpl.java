package data.tables.brigades;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.brigades.BrigadeAndForemanRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BrigadeAndForemanTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "brigade_and_man";

    @Override
    public boolean insertRow(Row row) {
        BrigadeAndForemanRow brigadeAndForemanRow = (BrigadeAndForemanRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + brigadeAndForemanRow.getId()
                + ", '" + brigadeAndForemanRow.getName() + "', " + brigadeAndForemanRow.getForemanId() + " )";
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20), foreman_id int, " +
                "foreign key (foreman_id)" +
                " references people_and_professions (id) on delete cascade)";
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
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
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRow(Row row) {
        BrigadeAndForemanRow brigadeAndForemanRow = (BrigadeAndForemanRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + brigadeAndForemanRow.getName()
                + "' , foreman_id = " + brigadeAndForemanRow.getForemanId()
                + " WHERE id = " + brigadeAndForemanRow.getId();
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
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
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Row> getRows() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<Row> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new BrigadeAndForemanRow(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("foreman_id")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE brigade_and_man_seq";
        String createSeq = "CREATE SEQUENCE brigade_and_man_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER brigade_and_man_auto\n" +
                "BEFORE INSERT ON brigade_and_man\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT brigade_and_man_seq.NextVal INTO :new.ID FROM dual;\n" +
                "END;";

        try {
            Statement statement = JDBCConnection.getConnection().createStatement();
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
        if (!insertRow(new BrigadeAndForemanRow(0, "Fasters", 1))) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new BrigadeAndForemanRow(rowLines);
    }

    @Override
    public ArrayList getCheckName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("NOT_ENGINEER");
        list.add("BRIGADE_PROF");
        return list;
    }
}
