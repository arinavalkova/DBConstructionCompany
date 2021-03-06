package data.tables.people;

import data.BaseTable;
import data.Coder;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.rows.people.PeopleAndProfessionRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class PeopleAndProfessionsTableImpl extends BaseTable implements DataBaseRepository {

    private final static String TABLE_NAME = "\"18206_VALKOVA\".people_and_prof";

    @Override
    public boolean insertRow(Row row) {
        PeopleAndProfessionRow peopleAndProfessionRow = (PeopleAndProfessionRow) row;
        String sql = "insert into " + TABLE_NAME + " values(" + peopleAndProfessionRow.getId()
                + ", '" + peopleAndProfessionRow.getName() + "', " + peopleAndProfessionRow.getProfessionId() + " )";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( id int primary key, name varchar(20), profession_id int, " +
                "foreign key (profession_id)" +
                " references \"18206_VALKOVA\".professions (id) on delete cascade)";
        try {
            return executeQuery(sql) != null;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean updateRow(Row row) {
        PeopleAndProfessionRow peopleAndProfessionRow = (PeopleAndProfessionRow) row;
        String sql = "UPDATE " + TABLE_NAME + " SET name = '" + peopleAndProfessionRow.getName()
                + "' , profession_id = " + peopleAndProfessionRow.getProfessionId()
                + " WHERE id = " + peopleAndProfessionRow.getId();
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
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("??????????????"), 0))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????"), 3))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????"), 0))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("??????????"), 3))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????????"), 0))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????????"), 3))) {
            return false;
        }

        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????????"), 1))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????????"), 1))) {
            return false;
        }
        if (!insertRow(new PeopleAndProfessionRow(0, Coder.encodingRUS("????????????????"), 1))) {
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
        return Coder.encodingRUS("?????????????????? ?? ??????????????????");
    }

    @Override
    public String getSQLTableName() {
        return TABLE_NAME;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return new PeopleAndProfessionRow(rowLines);
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
                        Coder.encodingRUS("??????"),
                        Coder.encodingRUS("Id ??????????????????")
                )
        );
    }

    @Override
    public ArrayList<String> getFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name", "professionId")
        );
    }

    @Override
    public ArrayList<String> getSQLFieldNames() {
        return new ArrayList<>(
                Arrays.asList("id", "name", "profession_id")
        );
    }
}
