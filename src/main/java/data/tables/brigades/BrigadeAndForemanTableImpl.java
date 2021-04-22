package data.tables.brigades;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.brigades.BrigadeAndForemanRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class BrigadeAndForemanTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "brigade_and_man";

    @Override
    public boolean insertRow(Row row) {
        BrigadeAndForemanRow brigadeAndForemanRow = (BrigadeAndForemanRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + brigadeAndForemanRow.getId()
                + ", '" + brigadeAndForemanRow.getName() + "', " + brigadeAndForemanRow.getForemanId() + " )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, name varchar(20), foreman_id int, " +
                "foreign key (foreman_id)" +
                " references people_and_prof (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        BrigadeAndForemanRow brigadeAndForemanRow = (BrigadeAndForemanRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + brigadeAndForemanRow.getName()
                + "' , foreman_id = " + brigadeAndForemanRow.getForemanId()
                + " WHERE id = " + brigadeAndForemanRow.getId();
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
        if (!insertRow(new BrigadeAndForemanRow(0, Coder.encodingRUS("Быстрые"), 1))) {
            return false;
        }
        return true;
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
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
        return Coder.encodingRUS("Бригада и бригадир");
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

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList(
                        "Id",
                        Coder.encodingRUS("Название"),
                        Coder.encodingRUS("id бригадира")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name", "foremanId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name", "foreman_id")
        );
    }
}
