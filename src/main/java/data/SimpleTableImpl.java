package data;

import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.CategoriesRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "persons";

    public SimpleTableImpl(JDBCConnection jdbcConnection) throws SQLException {
        super(jdbcConnection);
    }

    @Override
    public void insert(Row row) throws SQLException {
        CategoriesRow categoriesRow = (CategoriesRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + categoriesRow.getId()
                + ", '" + categoriesRow.getName() + "')";
        System.out.println(sql);
        PreparedStatement preStatement = getConnection().prepareStatement(sql);
        preStatement.executeQuery();
    }

    @Override
    public Integer getCountRows() throws SQLException {
        String sql = "select count(*) from " + TABLE_NAME;
        PreparedStatement preStatement = getConnection().prepareStatement(sql);
        ResultSet result = preStatement.executeQuery();
        while (result.next()) {
            return result.getInt(1);
        }
        return null;
    }

    @Override
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int NOT NULL, name char(50) NOT NULL)";
        PreparedStatement preStatement = getConnection().prepareStatement(sql);
        preStatement.executeQuery();
    }

    @Override
    public void deleteTable() throws SQLException {
        String sql = "drop table " + TABLE_NAME;
        PreparedStatement preStatement = getConnection().prepareStatement(sql);
        preStatement.executeQuery();
    }
}
