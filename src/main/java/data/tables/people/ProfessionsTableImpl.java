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

    private final static String TABLE_NAME = "\"18206_VALKOVA\".professions";

    @Override
    public boolean insertRow(Row row) {
        ProfessionsRow professionsRow = (ProfessionsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + professionsRow.getId()
                + ", '" + professionsRow.getName() + "')";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20))";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        ProfessionsRow professionsRow = (ProfessionsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + professionsRow.getName()
                + "' WHERE id = " + professionsRow.getId();
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public ArrayList<Row> getRows() {
        return getArrayOfRows(this);
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
    public boolean deleteTable() {
        String sql = "drop table " + getSQLTableName();
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean deleteRow(int id) {
        String sql = "DELETE FROM " + getSQLTableName() + " WHERE id = " + id;
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        return createTrigger(getSQLTableName());
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

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name")
        );
    }
}
