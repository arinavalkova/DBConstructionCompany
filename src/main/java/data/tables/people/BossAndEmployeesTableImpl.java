package data.tables.people;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.people.BossAndEmployeesRow;
import domain.rows.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BossAndEmployeesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "boss_and_employees";

    @Override
    public boolean insertRow(Row row) {
        BossAndEmployeesRow bossAndEmployeesRow = (BossAndEmployeesRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + bossAndEmployeesRow.getId()
                + ", " + bossAndEmployeesRow.getBossId() + ", " + bossAndEmployeesRow.getEmployeeId() + ")";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, boss_id int, employee_id int, "
                + " foreign key (boss_id)" + " references people_and_professions (id) on delete cascade, "
                + " foreign key (employee_id)" + " references people_and_professions (id) on delete cascade)";
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
        BossAndEmployeesRow bossAndEmployeesRow = (BossAndEmployeesRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET boss_id = " + bossAndEmployeesRow.getBossId()
                + " , employee_id = " + bossAndEmployeesRow.getEmployeeId()
                + " WHERE id = " + bossAndEmployeesRow.getId();
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
                rowArrayList.add(new BossAndEmployeesRow(
                        resultSet.getInt("id"),
                        resultSet.getInt("boss_id"),
                        resultSet.getInt("employee_id")
                ));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE boss_and_employees_seq";
        String createSeq = "CREATE SEQUENCE boss_and_employees_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER boss_and_employees_auto\n" +
                "BEFORE INSERT ON boss_and_employees\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT boss_and_employees_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new BossAndEmployeesRow(0, 0, 0))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 0, 2))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 0, 4))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 1, 3))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 1, 5))) {
            return false;
        }
        if (!insertRow(new BossAndEmployeesRow(0, 1, 1))) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new BossAndEmployeesRow(rowLines);
    }
}
