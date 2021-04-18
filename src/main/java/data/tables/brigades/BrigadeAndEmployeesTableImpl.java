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

    private final static String TABLE_NAME = "brigade_and_empl";

    @Override
    public boolean insertRow(Row row) {
        BrigadeAndEmployeesRow brigadeAndEmployeesRow = (BrigadeAndEmployeesRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + brigadeAndEmployeesRow.getId()
                + ", " + brigadeAndEmployeesRow.getBrigadeId() + ", " + brigadeAndEmployeesRow.getEmployeeId() + " )";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, brigade_id int, employee_id int, " +
                "foreign key (brigade_id)" +
                " references brigade_and_man (id) on delete cascade, " +
                "foreign key (employee_id)" +
                " references people_and_professions (id) on delete cascade )";
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
        BrigadeAndEmployeesRow brigadeAndEmployeesRow = (BrigadeAndEmployeesRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET brigade_id = " + brigadeAndEmployeesRow.getBrigadeId()
                + " , employee_id = " + brigadeAndEmployeesRow.getEmployeeId()
                + " WHERE id = " + brigadeAndEmployeesRow.getId();
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
                rowArrayList.add(new BrigadeAndEmployeesRow(
                        resultSet.getInt("id"),
                        resultSet.getInt("brigade_id"),
                        resultSet.getInt("employee_id")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE brigade_and_empl_seq";
        String createSeq = "CREATE SEQUENCE brigade_and_empl_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER brigade_and_empl_autoincrement\n" +
                "BEFORE INSERT ON brigade_and_empl\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT brigade_and_empl_seq.NextVal INTO :new.ID FROM dual;\n" +
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
    public String getTableName() {
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
}
