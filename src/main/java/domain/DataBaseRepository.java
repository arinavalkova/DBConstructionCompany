package domain;

import domain.rows.Row;

import java.util.ArrayList;
import java.util.List;

public interface DataBaseRepository {
    boolean insertRow(Row row);

    boolean createTable();

    boolean deleteTable();

    boolean updateRow(Row row);

    boolean deleteRow(int id);

    ArrayList<Row> getRows();

    boolean createIdAutoIncrementTrigger();

    boolean loadTestData();

    String getTableName();

    Row createRow(ArrayList<String> rowLines);

    ArrayList<String> getCheckName();

    ArrayList<String> getColumnNames();

    ArrayList<String> getFieldNames();
}
