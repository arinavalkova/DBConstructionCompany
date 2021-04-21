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

    public ManagementsAndSectorsTableImpl() {
        super("manag_and_sectors");
    }

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
                + " foreign key (management_id)" + " references managements (id) on delete cascade, "
                + " foreign key (sector_id)" + " references sectors (id) on delete cascade)";
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
        return true;
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
}
