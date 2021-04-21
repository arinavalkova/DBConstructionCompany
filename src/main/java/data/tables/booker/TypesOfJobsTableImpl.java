package data.tables.booker;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.booker.TypesOfJobsRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TypesOfJobsTableImpl extends BaseTable implements DataBaseRepository {

    public TypesOfJobsTableImpl() {
        super("types_of_jobs");
    }

    @Override
    public boolean insertRow(Row row) {
        TypesOfJobsRow typesOfJobsRow = (TypesOfJobsRow) row;
        String sql = "insert into " + getSQLTableName() + " values(" + typesOfJobsRow.getId()
                + ", '" + typesOfJobsRow.getName() + "')";
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
        TypesOfJobsRow typesOfJobsRow = (TypesOfJobsRow) row;
        String sql = "UPDATE " + getSQLTableName() + " SET name = '" + typesOfJobsRow.getName()
                + "' WHERE id = " + typesOfJobsRow.getId();
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
        if (!insertRow(new TypesOfJobsRow(0, Coder.encodingRUS("Кирпич")))) {
            return false;
        }
        if (!insertRow(new TypesOfJobsRow(0, Coder.encodingRUS("Краска")))) {
            return false;
        }
        return true;
    }

    @Override
    public String getUITableName() {
        return Coder.encodingRUS("Тип работы");
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new TypesOfJobsRow(rowLines);
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
