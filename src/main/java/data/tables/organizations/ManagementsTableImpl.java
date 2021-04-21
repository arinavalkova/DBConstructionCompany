package data.tables.organizations;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.organizations.ManagementsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagementsTableImpl extends BaseTable implements DataBaseRepository {

    public ManagementsTableImpl() {
        super("managements");
    }

    @Override
    public boolean insertRow(Row row) {
        ManagementsRow managementsRow = (ManagementsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + managementsRow.getId()
                + ", '" + managementsRow.getName() + "')";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + getSQLTableName() + " ( id int primary key, name varchar(20))";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        ManagementsRow managementsRow = (ManagementsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + managementsRow.getName()
                + "' WHERE id = " + managementsRow.getId();
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
        if (!insertRow(new ManagementsRow(0, Coder.encodingRUS("Управление")))) {
            return false;
        }
        return true;
    }

    @Override
    public String getUITableName() {
        return Coder.encodingRUS("Управления");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new ManagementsRow(rowLines);
    }

    @Override
    public ArrayList<String> getCheckName() {
        return null;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(
                Arrays.asList("Id", Coder.encodingRUS("Название")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name")
        );
    }
}
