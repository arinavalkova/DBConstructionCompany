package domain;

import domain.rows.Row;

import java.util.ArrayList;
import java.util.List;

public interface DataBaseRepository {

    boolean insertRow(Row row);

    boolean createTable();

    boolean updateRow(Row row);

    ArrayList<Row> getRows();

    boolean loadTestData();

    String getUITableName();

    Row createRow(ArrayList<String> rowLines);

    ArrayList<String> getCheckName();

    ArrayList<String> getColumnNames();

    ArrayList<String> getFieldNames();

    String getSQLTableName();

    boolean deleteTable();

    boolean deleteRow(int id);

    boolean createIdAutoIncrementTrigger();

    ArrayList<String> getSQLFieldNames();
}
