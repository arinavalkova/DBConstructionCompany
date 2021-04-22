package data.tables.people;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.people.BossAndEmployeesRow;
import domain.rows.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class BossAndEmployeesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "boss_and_empl";

    @Override
    public boolean insertRow(Row row) {
        BossAndEmployeesRow bossAndEmployeesRow = (BossAndEmployeesRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + bossAndEmployeesRow.getId()
                + ", " + bossAndEmployeesRow.getBossId() + ", " + bossAndEmployeesRow.getEmployeeId() + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, boss_id int, employee_id int, "
                + " foreign key (boss_id)" + " references people_and_prof (id) on delete cascade, "
                + " foreign key (employee_id)" + " references people_and_prof (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        BossAndEmployeesRow bossAndEmployeesRow = (BossAndEmployeesRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET boss_id = " + bossAndEmployeesRow.getBossId()
                + " , employee_id = " + bossAndEmployeesRow.getEmployeeId()
                + " WHERE id = " + bossAndEmployeesRow.getId();
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
        if (!insertRow(new BossAndEmployeesRow(0, 0, 0))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 0, 2))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 0, 4))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 6, 6))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 6, 7))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 6, 8))) {
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
        return Coder.encodingRUS("Начальники и работники");
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new BossAndEmployeesRow(rowLines);
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
                        Coder.encodingRUS("Id начальника"),
                        Coder.encodingRUS("Id работника")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "bossId", "employeeId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "boss_id", "employee_id")
        );
    }
}
