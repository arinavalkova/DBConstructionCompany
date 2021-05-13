package data.tables.organizations;

import data.BaseTable;
import data.Coder;
import data.JDBCConnection;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.OrganizationsAndManagementRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class OrganizationsAndManagementTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".organ_and_manag";

    @Override
    public boolean insertRow(Row row) {
        OrganizationsAndManagementRow organizationsAndManagementRow = (OrganizationsAndManagementRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + organizationsAndManagementRow.getId()
                + ", " + organizationsAndManagementRow.getOrganizationId() +
                ", " + organizationsAndManagementRow.getManagementId() + ")";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, organization_id int, management_id int, "
                + " foreign key (organization_id)" + " references organizations (id) on delete cascade, "
                + " foreign key (management_id)" + " references managements (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        OrganizationsAndManagementRow organizationsAndManagementRow = (OrganizationsAndManagementRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET organization_id = " + organizationsAndManagementRow.getOrganizationId()
                + " , management_id = " + organizationsAndManagementRow.getManagementId()
                + " WHERE id = " + organizationsAndManagementRow.getId();
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
        if (!insertRow(new OrganizationsAndManagementRow(0, 0, 0))) {
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
        return Coder.encodingRUS("Организации и управления");
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new OrganizationsAndManagementRow(rowLines);
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
                        Coder.encodingRUS("Id организации"),
                        Coder.encodingRUS("Id управления")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "organizationId", "managementId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "organization_id", "management_id")
        );
    }
}
