package data.tables.booker;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.booker.MaterialsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MaterialsTableImpl extends BaseTable implements DataBaseRepository {

    public MaterialsTableImpl() {
        super("materials");
    }

    @Override
    public boolean insertRow(Row row) {
        MaterialsRow materialsRow = (MaterialsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + materialsRow.getId()
                + ", '" + materialsRow.getName() + "')";
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
        MaterialsRow materialsRow = (MaterialsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + materialsRow.getName()
                + "' WHERE id = " + materialsRow.getId();
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
        if (!insertRow(new MaterialsRow(0, Coder.encodingRUS("Кирпич")))) {
            return false;
        }
        if (!insertRow(new MaterialsRow(0, Coder.encodingRUS("Окна")))) {
            return false;
        }
        if (!insertRow(new MaterialsRow(0, Coder.encodingRUS("Краска")))) {
            return false;
        }
        return true;
    }

    @Override
    public String getUITableName() {
        return Coder.encodingRUS("Метериалы");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new MaterialsRow(rowLines);
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
                        Coder.encodingRUS("Название")
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