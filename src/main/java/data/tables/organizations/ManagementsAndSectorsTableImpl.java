package data.tables.organizations;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.ManagementsAndSectorsRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagementsAndSectorsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".manag_and_sect";

    @Override
    public boolean insertRow(Row row) {
        ManagementsAndSectorsRow managementsAndSectorsRow = (ManagementsAndSectorsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + managementsAndSectorsRow.getId()
                + ", " + managementsAndSectorsRow.getManagementId() +
                ", " + managementsAndSectorsRow.getSectorId() + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, management_id int, sector_id int, "
                + " foreign key (management_id)" + " references \"18206_VALKOVA\".managements (id) on delete cascade, "
                + " foreign key (sector_id)" + " references \"18206_VALKOVA\".sectors (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        ManagementsAndSectorsRow managementsAndSectorsRow = (ManagementsAndSectorsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET management_id = " + managementsAndSectorsRow.getManagementId()
                + " , sector_id = " + managementsAndSectorsRow.getSectorId()
                + " WHERE id = " + managementsAndSectorsRow.getId();
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
        if (!insertRow(new ManagementsAndSectorsRow(0, 0, 0))) {
            return false;
        }
        if (!insertRow(new ManagementsAndSectorsRow(0, 0, 1))) {
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
        return Coder.encodingRUS("Управления и участки");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new ManagementsAndSectorsRow(rowLines);
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
                        Coder.encodingRUS("Id управления"),
                        Coder.encodingRUS("Id участка")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "managementId", "sectorId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "management_id", "sector_id")
        );
    }
}
