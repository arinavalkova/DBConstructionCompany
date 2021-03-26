package data.tables;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.CategoriesRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "categories";

    public CategoriesTableImpl(JDBCConnection jdbcConnection) {
        super(jdbcConnection);
    }

    @Override
    public boolean insertRow(Row row) {
        CategoriesRow categoriesRow = (CategoriesRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + categoriesRow.getId()
                + ", '" + categoriesRow.getName() + "')";
        System.out.println(sql);
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
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
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
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
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRow(Row row) {
        CategoriesRow categoriesRow = (CategoriesRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + categoriesRow.getName() + "' WHERE id = " + categoriesRow.getId();
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
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
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            preStatement.executeQuery();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public List<Row> getRows() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<Row> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new CategoriesRow(resultSet.getInt("id"), resultSet.getString("name")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE categories_seq";
        String createSeq = "CREATE SEQUENCE categories_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER categories_autoincrement\n" +
                "BEFORE INSERT ON categories\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT categories_seq.NextVal INTO :new.ID FROM dual;\n" +
                "END;";

        try {
            Statement statement = getConnection().createStatement();
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
        if (!insertRow(new CategoriesRow(0, "House"))) {
            return false;
        }
        if (!insertRow(new CategoriesRow(0, "Library"))) {
            return false;
        }
        return true;
    }
}
