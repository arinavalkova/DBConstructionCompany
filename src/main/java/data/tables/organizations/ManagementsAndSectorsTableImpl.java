package data.tables.organizations;

import data.BaseTable;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.ManagementsAndSectorsRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagementsAndSectorsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "manag_and_sectors";

    @Override
    public boolean insertRow(Row row) {
        ManagementsAndSectorsRow managementsAndSectorsRow = (ManagementsAndSectorsRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + managementsAndSectorsRow.getId()
                + ", " + managementsAndSectorsRow.getManagementId() +
                ", " + managementsAndSectorsRow.getSectorId() + ")";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, management_id int, sector_id int, "
                + " foreign key (management_id)" + " references managements (id) on delete cascade, "
                + " foreign key (sector_id)" + " references sectors (id) on delete cascade)";
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
        ManagementsAndSectorsRow managementsAndSectorsRow = (ManagementsAndSectorsRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET management_id = " + managementsAndSectorsRow.getManagementId()
                + " , sector_id = " + managementsAndSectorsRow.getSectorId()
                + " WHERE id = " + managementsAndSectorsRow.getId();
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
                rowArrayList.add(new ManagementsAndSectorsRow(
                        resultSet.getInt("id"),
                        resultSet.getInt("management_id"),
                        resultSet.getInt("sector_id")
                ));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE manag_and_sectors_seq";
        String createSeq = "CREATE SEQUENCE manag_and_sectors_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER manag_and_sectors_auto\n" +
                "BEFORE INSERT ON manag_and_sectors\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT manag_and_sectors_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new ManagementsAndSectorsRow(0, 0, 0))) {
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
        return new ManagementsAndSectorsRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        return null;
    }
}
