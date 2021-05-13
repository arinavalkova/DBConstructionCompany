package data.tables.people;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.people.SectorsRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class SectorsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".sectors";

    @Override
    public boolean insertRow(Row row) {
        SectorsRow sectorsRow = (SectorsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + sectorsRow.getId()
                + ", '" + sectorsRow.getName() + "')";
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
        SectorsRow sectorsRow = (SectorsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + sectorsRow.getName()
                + "' WHERE id = " + sectorsRow.getId();
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
        if (!insertRow(new SectorsRow(0, "123HV"))) {
            return false;
        }
        if (!insertRow(new SectorsRow(0, "124HV"))) {
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
        return Coder.encodingRUS("Участки");
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new SectorsRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("ENGINEER");
        return list;
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
