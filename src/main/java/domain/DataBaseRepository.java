package domain;

import domain.rows.Row;

import java.sql.SQLException;

public interface DataBaseRepository {
    void insert(Row row) throws SQLException;

    Integer getCountRows() throws SQLException;

    void createTable() throws SQLException;

    void deleteTable() throws SQLException;
}
