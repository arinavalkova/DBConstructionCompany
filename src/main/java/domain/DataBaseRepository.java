package domain;

import domain.rows.Row;

import java.util.List;

public interface DataBaseRepository {
    boolean insertRow(Row row);

    boolean createTable();

    boolean deleteTable();

    boolean updateRow(Row row);

    boolean deleteRow(int id);

    List<Row> getRows();

    boolean createIdAutoIncrementTrigger();

    boolean loadTestData();
}
