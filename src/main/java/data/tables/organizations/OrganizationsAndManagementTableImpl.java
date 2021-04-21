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

    private final static String TABLE_NAME = "organizat_and_managments";

    public OrganizationsAndManagementTableImpl(String TABLE_NAME) {
        super(TABLE_NAME);
    }

    @Override
    public boolean insertRow(Row row) {
        OrganizationsAndManagementRow organizationsAndManagementRow = (OrganizationsAndManagementRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + organizationsAndManagementRow.getId()
                + ", " + organizationsAndManagementRow.getOrganizationId() +
                ", " + organizationsAndManagementRow.getManagementId() + ")";
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
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, organization_id int, management_id int, "
                + " foreign key (organization_id)" + " references organizations (id) on delete cascade, "
                + " foreign key (management_id)" + " references managements (id) on delete cascade)";
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
        OrganizationsAndManagementRow organizationsAndManagementRow = (OrganizationsAndManagementRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET organization_id = " + organizationsAndManagementRow.getOrganizationId()
                + " , management_id = " + organizationsAndManagementRow.getManagementId()
                + " WHERE id = " + organizationsAndManagementRow.getId();
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
                rowArrayList.add(new OrganizationsAndManagementRow(
                        resultSet.getInt("id"),
                        resultSet.getInt("organization_id"),
                        resultSet.getInt("management_id")
                ));
            } catch (SQLException throwables) {
                return null;
            }
        }
        return rowArrayList;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        String dropSeq = "DROP SEQUENCE organ_and_managment_seq";
        String createSeq = "CREATE SEQUENCE organ_and_managment_seq minvalue 0";
        String trigger = "CREATE OR REPLACE TRIGGER organ_and_managment_auto\n" +
                "BEFORE INSERT ON organizat_and_managments\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "SELECT organ_and_managment_seq.NextVal INTO :new.ID FROM dual;\n" +
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
        if (!insertRow(new OrganizationsAndManagementRow(0, 0, 0))) {
            return false;
        }
        return true;
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
}
