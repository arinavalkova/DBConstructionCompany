package data.tables.report;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.report.FactWorkRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class FactWorksTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".fact_work";

    @Override
    public boolean insertRow(Row row) {
        FactWorkRow factWorkRow = (FactWorkRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + factWorkRow.getId()
                + ", " + factWorkRow.getWorkId()
                + ", TO_DATE ('" + factWorkRow.getStartDate() + "','YYYY-MM-DD')"
                + ", TO_DATE ('" + factWorkRow.getEndDate() + "','YYYY-MM-DD')"
                + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() +
                " ( id int primary key, work_id int, start_date date," +
                "end_date date," +
                "foreign key (work_id)" +
                " references \"18206_VALKOVA\".schedules (id) on delete cascade" +
                ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        FactWorkRow factWorkRow = (FactWorkRow) row;
        String sql = "UPDATE " + getSQLTableName() +
                " work_id = " + factWorkRow.getWorkId()
                + ", start_date = '" + factWorkRow.getStartDate()
                + "', end_date = '" + factWorkRow.getEndDate()
                + "' WHERE id = " + factWorkRow.getId();
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
        if (!insertRow(new FactWorkRow(
                0, 0, "2020-01-11", "2020-04-16"))) {
            return false;
        }
        if (!insertRow(new FactWorkRow(
                0, 1, "2020-03-15", "2020-11-18"))) {
            return false;
        }
        if (!insertRow(new FactWorkRow(
                0, 2, "2021-09-27", "2021-12-06"))) {
            return false;
        }
        if (!insertRow(new FactWorkRow(
                0, 3, "2021-12-20", "2022-02-06"))) {
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
        return Coder.encodingRUS("?????????????????????? ?????????????? ??????????");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new FactWorkRow(rowLines);
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
                        Coder.encodingRUS("???????? ???????????? ??????????"),
                        Coder.encodingRUS("???????? ?????????? ??????????")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "workId", "startDate", "endDate")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "work_id", "start_date", "end_date")
        );
    }
}
