package data.tables.people;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.people.SectorAndBossRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class SectorAndBossTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".sector_and_boss";

    @Override
    public boolean insertRow(Row row) {
        SectorAndBossRow sectorAndBossRow = (SectorAndBossRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + sectorAndBossRow.getId()
                + ", " + sectorAndBossRow.getSectorId() + ", " + sectorAndBossRow.getBossId() + " )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, sector_id int, boss_id int, " +
                "foreign key (boss_id)" +
                " references \"18206_VALKOVA\".people_and_prof (id) on delete cascade, " +
                "foreign key (sector_id)" +
                " references \"18206_VALKOVA\".sectors (id) on delete cascade )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        SectorAndBossRow sectorAndBossRow = (SectorAndBossRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET sector_id = " + sectorAndBossRow.getSectorId()
                + " , boss_id = " + sectorAndBossRow.getBossId()
                + " WHERE id = " + sectorAndBossRow.getId();
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
        if (!insertRow(new SectorAndBossRow(0, 0, 0))) {
            return false;
        }
        if (!insertRow(new SectorAndBossRow(0, 1, 6))) {
            return false;
        }
        return true;
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
        return Coder.encodingRUS("Участки и начальники");
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new SectorAndBossRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("ENGINEER");
        return list;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList(
                        "Id",
                        Coder.encodingRUS("Id участка"),
                        Coder.encodingRUS("Id начальника")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "sectorId", "bossId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "sector_id", "boss_id")
        );
    }
}
