package data.tables.booker;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.booker.MaterialsRow;
import domain.rows.booker.TechniquesRow;
import domain.rows.booker.TypesOfJobsRow;
import domain.rows.people.SectorsRow;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class TypesOfJobsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "types_of_jobs";

    @Override
    public boolean insertRow(Row row) {
        TypesOfJobsRow typesOfJobsRow = (TypesOfJobsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + typesOfJobsRow.getId()
                + ", '" + typesOfJobsRow.getName() + "')";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20))";
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
        TypesOfJobsRow typesOfJobsRow = (TypesOfJobsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + typesOfJobsRow.getName()
                + "' WHERE id = " + typesOfJobsRow.getId();
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
                rowArrayList.add(new SectorsRow(resultSet.getInt("id"), resultSet.getString("name")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE types_of_jobs_seq";
        String createSeq = "CREATE SEQUENCE types_of_jobs_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER types_of_jobs_autoincrement\n" +
                "BEFORE INSERT ON types_of_jobs\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT types_of_jobs_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new TypesOfJobsRow(0, Coder.encodingRUS("Кирпич")))) {
            return false;
        }
        if (!insertRow(new TypesOfJobsRow(0, Coder.encodingRUS("Краска")))) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return Coder.encodingRUS("Тип работы");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new TypesOfJobsRow(rowLines);
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
}
