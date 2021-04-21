package data.tables.people;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.people.ProfessionsRow;
import domain.rows.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfessionsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "professions";

    public ProfessionsTableImpl(String TABLE_NAME) {
        super(TABLE_NAME);
    }

    @Override
    public boolean insertRow(Row row) {
        ProfessionsRow professionsRow = (ProfessionsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + professionsRow.getId()
                + ", '" + professionsRow.getName() + "')";
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
        ProfessionsRow professionsRow = (ProfessionsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + professionsRow.getName()
                + "' WHERE id = " + professionsRow.getId();
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
                rowArrayList.add(new ProfessionsRow(resultSet.getInt("id"), resultSet.getString("name")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE professions_seq";
        String createSeq = "CREATE SEQUENCE professions_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER professions_autoincrement\n" +
                "BEFORE INSERT ON professions\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT professions_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new ProfessionsRow(0, Coder.encodingRUS("Инженер")))) {
            return false;
        }
        if (!insertRow(new ProfessionsRow(0, Coder.encodingRUS("Технолог")))) {
            return false;
        }
        if (!insertRow(new ProfessionsRow(0, Coder.encodingRUS("Техник")))) {
            return false;
        }
        if (!insertRow(new ProfessionsRow(0, Coder.encodingRUS("Каменщик")))) {
            return false;
        }
        if (!insertRow(new ProfessionsRow(0, Coder.encodingRUS("Бетонщик")))) {
            return false;
        }
        return true;
    }

    @Override
    public String getUITableName() {
        return Coder.encodingRUS("Профессии");
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new ProfessionsRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        return null;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList("Id", Coder.encodingRUS("Название"))
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name")
        );
    }
}
