package data.tables;

import domain.DataBaseRepository;
import domain.rows.Row;

import java.util.List;

public class SectorAndBossTableImpl implements DataBaseRepository {
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
    public List<Row> getRows() {
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
}
