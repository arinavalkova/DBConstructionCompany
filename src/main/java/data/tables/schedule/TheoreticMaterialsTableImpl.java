package data.tables.schedule;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.report.FactMaterialsRow;
import domain.rows.shedule.TheoreticMaterialsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TheoreticMaterialsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".theor_mater";

    @Override
    public boolean insertRow(Row row) {
        TheoreticMaterialsRow theoreticMaterialsRow = (TheoreticMaterialsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + theoreticMaterialsRow.getId()
                + ", " + theoreticMaterialsRow.getWorkId()
                + ", " + theoreticMaterialsRow.getMaterialId()
                + ", " + theoreticMaterialsRow.getCount() + ")";
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
        if (!insertRow(new TheoreticMaterialsRow(0, 0, 0, 10))) {
            return false;
        }
        if (!insertRow(new TheoreticMaterialsRow(0, 0, 1, 10))) {
            return false;
        }
        if (!insertRow(new TheoreticMaterialsRow(0, 0, 2, 5))) {
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
        return Coder.encodingRUS("Теоретические материалы");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new TheoreticMaterialsRow(rowLines);
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
                        Coder.encodingRUS("id работы"),
                        Coder.encodingRUS("id материалов"),
                        Coder.encodingRUS("Кол-во")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "workId", "materialId", "count")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "work_id", "material_id", "count")
        );
    }
}
