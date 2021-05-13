package data.tables.authorize;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.admin.UsersAndRolesRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class UsersAndRolesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".us_and_rol";

    @Override
    public boolean insertRow(Row row) {
        UsersAndRolesRow usersAndRolesRow = (UsersAndRolesRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + usersAndRolesRow.getId()
                + ", '" + usersAndRolesRow.getUserName() + "', '" + usersAndRolesRow.getPassword() + "', " +
                usersAndRolesRow.getRoleId() + " )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, " +
                "user_name varchar(40), password varchar(40), role_id int, " +
                "foreign key (role_id)" +
                " references \"18206_VALKOVA\".roles (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        UsersAndRolesRow usersAndRolesRow = (UsersAndRolesRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET user_name = '" + usersAndRolesRow.getUserName()
                + "' , role_id = " + usersAndRolesRow.getRoleId()
                + " WHERE id = " + usersAndRolesRow.getId();
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
        if (!insertRow(new UsersAndRolesRow(0, Coder.encodingRUS("18206_VALKOVA"), "nar&Alex", 0))) {
            return false;
        }
        if (!insertRow((new UsersAndRolesRow(0, Coder.encodingRUS("18206_VALKOVA_MANAGER"), "manager", 1)))) {
            return false;
        }
        if (!insertRow(new UsersAndRolesRow(0, Coder.encodingRUS("18206_VALKOVA_CHIEF"), "chief", 2))) {
            return false;
        }
        if (!insertRow(new UsersAndRolesRow(0, Coder.encodingRUS("18206_VALKOVA_DIRECTOR"), "director", 3))) {
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
        return Coder.encodingRUS("Пользователь и роль");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new UsersAndRolesRow(rowLines);
    }

    @Override
    public ArrayList getCheckName() {
        return null;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList(
                        "Id",
                        Coder.encodingRUS("ФИО"),
                        Coder.encodingRUS("Пароль"),
                        Coder.encodingRUS("id роли")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "userName", "password", "roleId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "user_name", "password", "role_id")
        );
    }
}
