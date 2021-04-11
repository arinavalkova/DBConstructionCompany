package data.tables.organizations;

import data.BaseTable;
import domain.DataBaseRepository;
import domain.rows.Row;

import java.util.ArrayList;

public class ManagementsTableImpl extends BaseTable implements DataBaseRepository {

    @Override
    public boolean insertRow(Row row) {
        return false;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    public boolean updateRow(Row row) {
        return false;
    }

    @Override
    public boolean deleteRow(int id) {
        return false;
    }

    @Override
    public ArrayList<Row> getRows() {
        return null;
    }

    @Override
    public boolean createIdAutoIncrementTrigger() {
        return false;
    }

    @Override
    public boolean loadTestData() {
        return false;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Row createRow(ArrayList<String> rowLines) {
        return null;
    }
}
