package data.tables.authorize;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.admin.RolesRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class RolesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "roles";

    @Override
    public boolean insertRow(Row row) {
        RolesRow rolesRow = (RolesRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + rolesRow.getId()
                + ", '" + rolesRow.getName() + "')";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, name varchar(40))";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        RolesRow rolesRow = (RolesRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + rolesRow.getName()
                + "' WHERE id = " + rolesRow.getId();
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
        if (!insertRow(new RolesRow(0, Coder.encodingRUS("Администратор")))) {
            return false;
        }
        if (!insertRow(new RolesRow(0, Coder.encodingRUS("Менеджер")))) {
            return false;
        }
        if (!insertRow(new RolesRow(0, Coder.encodingRUS("Заведующий")))) {
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
        return Coder.encodingRUS("Роли");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new RolesRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        return null;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList(
                        "Id",
                        Coder.encodingRUS("Название")
                )
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
