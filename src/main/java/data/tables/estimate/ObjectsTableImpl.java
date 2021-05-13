package data.tables.estimate;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.estimate.ObjectsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".objects";

    @Override
    public boolean insertRow(Row row) {
        ObjectsRow objectsRow = (ObjectsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + objectsRow.getId()
                + ", '" + objectsRow.getName() + "', " + objectsRow.getSectorId() + " )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, name varchar(20), sector_id int, " +
                "foreign key (sector_id)" +
                " references \"18206_VALKOVA\".sectors (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        ObjectsRow objectsRow = (ObjectsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + objectsRow.getName()
                + "' , sector_id = " + objectsRow.getSectorId()
                + " WHERE id = " + objectsRow.getId();
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
        if (!insertRow(new ObjectsRow(0, Coder.encodingRUS("Дом"), 0))) {
            return false;
        }
        if (!insertRow(new ObjectsRow(0, Coder.encodingRUS("Больница"), 1))) {
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
        return Coder.encodingRUS("Объекты");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new ObjectsRow(rowLines);
    }

    @Override
    public ArrayList getCheckName() {
        return null;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList(
                        "Id",
                        Coder.encodingRUS("Название"),
                        Coder.encodingRUS("id участка")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name", "sectorId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name", "sector_id")
        );
    }
}
