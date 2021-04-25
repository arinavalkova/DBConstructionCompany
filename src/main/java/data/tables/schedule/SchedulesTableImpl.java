package data.tables.schedule;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.shedule.SchedulesRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SchedulesTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "schedules";

    @Override
    public boolean insertRow(Row row) {
        SchedulesRow schedulesRow = (SchedulesRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + schedulesRow.getId()
                + ", " + schedulesRow.getTypesOfWorkId()
                + ", " + schedulesRow.getObjectId()
                + ", '" + schedulesRow.getStartDate()
                + "', '" + schedulesRow.getEndDate()
                + "', " + schedulesRow.getBrigadeId() + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() +
                " ( id int primary key, type_of_work_id int, object_id int, start_date varchar(20)," +
                "end_date varchar(20), brigade_id int," +
                "foreign key (type_of_work_id)" +
                " references types_of_jobs (id) on delete cascade, " +
                "foreign key (object_id)" +
                " references objects (id) on delete cascade, " +
                "foreign key (brigade_id)" +
                " references brigade_and_man (id) on delete cascade " +
                ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        SchedulesRow schedulesRow = (SchedulesRow) row;
        String sql = "UPDATE " + getSQLTableName() +
                " type_of_work_id = " + schedulesRow.getTypesOfWorkId()
                + ", object_id = " + schedulesRow.getObjectId()
                + ", start_date = '" + schedulesRow.getStartDate()
                + "', end_date = '" + schedulesRow.getEndDate()
                + "', brigade_id = " + schedulesRow.getBrigadeId()
                + " WHERE id = " + schedulesRow.getId();
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
        if (!insertRow(new SchedulesRow(
                0, 0, 0, "10.01.2020", "15.04.2020", 0))) {
            return false;
        }
        if (!insertRow(new SchedulesRow(
                0, 1, 0, "15.02.2020", "18.09.2020", 0))) {
            return false;
        }
        if (!insertRow(new SchedulesRow(
                0, 0, 1, "27.09.2021", "06.12.2021", 0))) {
            return false;
        }
        if (!insertRow(new SchedulesRow(
                0, 1, 1, "11.01.2021", "06.02.2022", 0))) {
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
        return Coder.encodingRUS("Графики работ");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new SchedulesRow(rowLines);
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
                        Coder.encodingRUS("id типа работ"),
                        Coder.encodingRUS("id объекта"),
                        Coder.encodingRUS("Дата начала работ"),
                        Coder.encodingRUS("Дата конца работ"),
                        Coder.encodingRUS("id бригады")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "typesOfWorkId", "objectId", "startDate", "endDate", "brigadeId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "type_of_work_id", "object_id", "start_date", "end_date", "brigade_id")
        );
    }
}
