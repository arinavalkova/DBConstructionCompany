package data.tables.people;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.people.SectorAndBossRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SectorAndBossTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "sector_and_boss";

    @Override
    public boolean insertRow(Row row) {
        SectorAndBossRow sectorAndBossRow = (SectorAndBossRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + sectorAndBossRow.getId()
                + ", " + sectorAndBossRow.getSectorId() + ", " + sectorAndBossRow.getBossId() + " )";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, sector_id int, boss_id int, " +
                "foreign key (boss_id)" +
                " references people_and_professions (id) on delete cascade, " +
                "foreign key (sector_id)" +
                " references sectors (id) on delete cascade )";
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
        SectorAndBossRow sectorAndBossRow = (SectorAndBossRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET sector_id = " + sectorAndBossRow.getSectorId()
                + " , boss_id = " + sectorAndBossRow.getBossId()
                + " WHERE id = " + sectorAndBossRow.getId();
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
    public ArrayList<Row> getRows() {
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
                rowArrayList.add(new SectorAndBossRow(
                        resultSet.getInt("id"),
                        resultSet.getInt("sector_id"),
                        resultSet.getInt("boss_id")));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE sector_and_boss_seq";
        String createSeq = "CREATE SEQUENCE sector_and_boss_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER sector_and_boss_autoincrement\n" +
                "BEFORE INSERT ON sector_and_boss\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT sector_and_boss_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new SectorAndBossRow(0, 0, 0))) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
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
}
