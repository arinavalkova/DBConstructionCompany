package data.tables.brigades;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.brigades.BrigadeAndEmployeesRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class BrigadeAndEmployeesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".brigade_and_empl";

    @Override
    public boolean insertRow(Row row) {
        BrigadeAndEmployeesRow brigadeAndEmployeesRow = (BrigadeAndEmployeesRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + brigadeAndEmployeesRow.getId()
                + ", " + brigadeAndEmployeesRow.getBrigadeId() + ", " + brigadeAndEmployeesRow.getEmployeeId() + " )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, brigade_id int, employee_id int, " +
                "foreign key (brigade_id)" +
                " references \"18206_VALKOVA\".brigade_and_man (id) on delete cascade, " +
                "foreign key (employee_id)" +
                " references \"18206_VALKOVA\".people_and_prof (id) on delete cascade )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        BrigadeAndEmployeesRow brigadeAndEmployeesRow = (BrigadeAndEmployeesRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET brigade_id = " + brigadeAndEmployeesRow.getBrigadeId()
                + " , employee_id = " + brigadeAndEmployeesRow.getEmployeeId()
                + " WHERE id = " + brigadeAndEmployeesRow.getId();
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
        if (!insertRow(new BrigadeAndEmployeesRow(0, 0, 1))) {
            return false;
        }
        if (!insertRow(new BrigadeAndEmployeesRow(0, 0, 3))) {
            return false;
        }
        if (!insertRow(new BrigadeAndEmployeesRow(0, 0, 5))) {
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
        return Coder.encodingRUS("Бригада и работники");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new BrigadeAndEmployeesRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("BRIGADE_PROF");
        return list;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList(
                        "Id",
                        Coder.encodingRUS("Id бригады"),
                        Coder.encodingRUS("Id работника")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "brigadeId", "employeeId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "brigade_id", "employee_id")
        );
    }
}
