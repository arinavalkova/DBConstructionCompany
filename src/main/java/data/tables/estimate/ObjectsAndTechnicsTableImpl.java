package data.tables.estimate;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.estimate.ObjectsAndTechnicsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectsAndTechnicsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".estimate";

    @Override
    public boolean insertRow(Row row) {
        ObjectsAndTechnicsRow objectsAndTechnicsRow = (ObjectsAndTechnicsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + objectsAndTechnicsRow.getId()
                + ", " + objectsAndTechnicsRow.getObjectId() + ", "
                + objectsAndTechnicsRow.getTechnicsId() + ", "
                + objectsAndTechnicsRow.getCount() + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() +
                " ( id int primary key, object_id int, technics_id int, count int, " +
                "foreign key (object_id)" +
                " references objects (id) on delete cascade, " +
                "foreign key (technics_id)" +
                " references techniques (id) on delete cascade )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        ObjectsAndTechnicsRow objectsAndTechnicsRow = (ObjectsAndTechnicsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET object_id = " + objectsAndTechnicsRow.getObjectId()
                + " , technics_id = " + objectsAndTechnicsRow.getTechnicsId()
                + " , count = " + objectsAndTechnicsRow.getCount()
                + " WHERE id = " + objectsAndTechnicsRow.getId();
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
        if (!insertRow(new ObjectsAndTechnicsRow(0,0, 0, 1))) {
            return false;
        }
        if (!insertRow(new ObjectsAndTechnicsRow(0,0, 1, 3))) {
            return false;
        }
        if (!insertRow(new ObjectsAndTechnicsRow(0,1, 0, 5))) {
            return false;
        }
        if (!insertRow(new ObjectsAndTechnicsRow(0,1, 1, 5))) {
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
        return Coder.encodingRUS("Смета по объектам");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new ObjectsAndTechnicsRow(rowLines);
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
                        Coder.encodingRUS("Id объекта"),
                        Coder.encodingRUS("Id техники"),
                        Coder.encodingRUS("Количество")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "objectId", "technicsId", "count")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "object_id", "technics_id", "count")
        );
    }
}
