package data.tables.report;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.report.FactMaterialsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class FactMaterialsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".fact_mater";

    @Override
    public boolean insertRow(Row row) {
        FactMaterialsRow factMaterialsRow = (FactMaterialsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + factMaterialsRow.getId()
                + ", " + factMaterialsRow.getWorkId()
                + ", " + factMaterialsRow.getMaterialsId()
                + ", " + factMaterialsRow.getCount() + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() +
                " ( id int primary key, work_id int, material_id int, count int," +
                "foreign key (work_id)" +
                " references \"18206_VALKOVA\".schedules (id) on delete cascade, " +
                "foreign key (material_id)" +
                " references \"18206_VALKOVA\".materials (id) on delete cascade" +
                ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        FactMaterialsRow factMaterialsRow = (FactMaterialsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET work_id = " + factMaterialsRow.getWorkId()
                + ", material_id = " + factMaterialsRow.getMaterialsId()
                + ", count = " + factMaterialsRow.getCount()
                + " WHERE id = " + factMaterialsRow.getId();
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
        if (!insertRow(new FactMaterialsRow(0, 0, 0, 20))) {
            return false;
        }
        if (!insertRow(new FactMaterialsRow(0, 0, 1, 10))) {
            return false;
        }
        if (!insertRow(new FactMaterialsRow(0, 0, 2, 5))) {
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
        return Coder.encodingRUS("?????????????????????? ??????????????????");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new FactMaterialsRow(rowLines);
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
                        Coder.encodingRUS("id ????????????"),
                        Coder.encodingRUS("id ????????????????????"),
                        Coder.encodingRUS("??????-????")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "workId", "materialsId", "count")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "work_id", "material_id", "count")
        );
    }
}
