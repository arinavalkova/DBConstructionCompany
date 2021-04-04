package data.tables.people;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.PeopleAndProfessionRow;
import domain.rows.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PeopleAndProfessionsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "people_and_profession";

    @Override
    public boolean insertRow(Row row) {
        PeopleAndProfessionRow peopleAndProfessionRow = (PeopleAndProfessionRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + peopleAndProfessionRow.getId()
                + ", '" + peopleAndProfessionRow.getName() + "', " + peopleAndProfessionRow.getProfessionId() + " )";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20), profession_id int, " +
                "foreign key (profession_id)" +
                " references professions (id) on delete cascade)";
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
        PeopleAndProfessionRow peopleAndProfessionRow = (PeopleAndProfessionRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + peopleAndProfessionRow.getName()
                + "' , profession_id = " + peopleAndProfessionRow.getProfessionId()
                + " WHERE id = " + peopleAndProfessionRow.getId();
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
                rowArrayList.add(new PeopleAndProfessionRow(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("profession_id")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE people_and_professions_seq";
        String createSeq = "CREATE SEQUENCE people_and_professions_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER people_and_professions_autoincrement\n" +
                "BEFORE INSERT ON people_and_professions\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT people_and_professions_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new PeopleAndProfessionRow(0, "Vasilii", 0))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, "Gleb", 3))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, "Vlad", 0))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, "Igor", 3))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, "Sergei", 0))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, "Nikita", 3))) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
