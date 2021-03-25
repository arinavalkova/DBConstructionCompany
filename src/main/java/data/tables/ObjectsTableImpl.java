package data.tables;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.CustomersRow;
import domain.rows.ObjectsRow;
import domain.rows.Row;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ObjectsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "objects";

    public ObjectsTableImpl(JDBCConnection jdbcConnection) {
        super(jdbcConnection);
    }

    @Override
    public boolean insertRow(Row row) {
        ObjectsRow objectsRow = (ObjectsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + objectsRow.getId()
                + ", '" + objectsRow.getName() + ", '" + objectsRow.getCategoryId() + ", '"
                + objectsRow.getCustomerId() + "')";
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
        return false;
    }

    @Override
    public boolean deleteRow(int id) {
        return false;
    }

    @Override
    public List<Row> getRows() {
        return null;
    }
}
