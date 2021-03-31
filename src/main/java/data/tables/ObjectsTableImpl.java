package data.tables;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.ObjectsRow;
import domain.rows.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "objects";

    @Override
    public boolean insertRow(Row row) {
        ObjectsRow objectsRow = (ObjectsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + objectsRow.getId()
                + ", '" + objectsRow.getName() + "', " + objectsRow.getCategoryId() + ", "
                + objectsRow.getCustomerId() + ")";
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
        String sql =
                "CREATE TABLE " +
                        TABLE_NAME +
                        " ( id int primary key, name varchar(20), categories_id int," +
                        " customer_id int," +
                        " foreign key (categories_id)" +
                        " references categories (id)" +
                        " on delete cascade," +
                        " foreign key (customer_id)" +
                        " references customer (id)" +
                        " on delete cascade)";
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
        ObjectsRow objectsRow = (ObjectsRow) row;
        String sql = "UPDATE " + TABLE_NAME
                + " SET name = '" + objectsRow.getName()
                + "', categories_id = " + objectsRow.getCategoryId()
                + ", customer_id = " + objectsRow.getCustomerId()
                + " WHERE id = " + objectsRow.getId();
        System.out.println(sql);
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
    public List<Row> getRows() {
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
                rowArrayList.add(new ObjectsRow(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("categories_id"),
                        resultSet.getInt("customer_id")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }


    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE objects_seq";
        String createSeq = "CREATE SEQUENCE objects_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER objects_autoincrement\n" +
                "BEFORE INSERT ON objects\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT objects_seq.NextVal INTO :new.ID FROM dual;" +
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
        if (!insertRow(new ObjectsRow(0, "Esenina 37/1", 0, 0))) {
            return false;
        }
        if (!insertRow(new ObjectsRow(0, "Sverdlovskaia 45", 1, 1))) {
            return false;
        }
        return true;
    }
}
