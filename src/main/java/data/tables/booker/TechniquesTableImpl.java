package data.tables.booker;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.booker.TechniquesRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TechniquesTableImpl extends BaseTable implements DataBaseRepository {

    public TechniquesTableImpl() {
        super("techniques");
    }

    @Override
    public boolean insertRow(Row row) {
        TechniquesRow techniquesRow = (TechniquesRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + techniquesRow.getId()
                + ", '" + techniquesRow.getName() + "')";
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
        TechniquesRow techniquesRow = (TechniquesRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + techniquesRow.getName()
                + "' WHERE id = " + techniquesRow.getId();
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
        if (!insertRow(new TechniquesRow(0, Coder.encodingRUS("Кран")))) {
            return false;
        }
        if (!insertRow(new TechniquesRow(0, Coder.encodingRUS("Бульдозер")))) {
            return false;
        }
        return true;
    }

    @Override
    public String getUITableName() {
        return Coder.encodingRUS("Техника");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new TechniquesRow(rowLines);
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
