package data.tables.organizations;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.OrganizationsRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class OrganizationsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "organizations";

    @Override
    public boolean insertRow(Row row) {
        OrganizationsRow organizationsRow = (OrganizationsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + organizationsRow.getId()
                + ", '" + organizationsRow.getName() + "')";
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
        OrganizationsRow organizationsRow = (OrganizationsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + organizationsRow.getName()
                + "' WHERE id = " + organizationsRow.getId();
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
        if (!insertRow(new OrganizationsRow(0, Coder.encodingRUS("Вектор")))) {
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
        return Coder.encodingRUS("Организации");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new OrganizationsRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        return null;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList("Id", Coder.encodingRUS("Название")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name")
        );
    }
}
