package data.tables.organizations;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.ManagementsRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagementsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "managements";

    @Override
    public boolean insertRow(Row row) {
        ManagementsRow managementsRow = (ManagementsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + managementsRow.getId()
                + ", '" + managementsRow.getName() + "')";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20))";
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
        ManagementsRow managementsRow = (ManagementsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + managementsRow.getName()
                + "' WHERE id = " + managementsRow.getId();
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
                rowArrayList.add(new ManagementsRow(resultSet.getInt("id"), resultSet.getString("name")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE managements_seq";
        String createSeq = "CREATE SEQUENCE managements_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER managements_autoincrement\n" +
                "BEFORE INSERT ON managements\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT managements_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new ManagementsRow(0, "Upravlenie1"))) {
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
        return new ManagementsRow(rowLines);
    }
}
