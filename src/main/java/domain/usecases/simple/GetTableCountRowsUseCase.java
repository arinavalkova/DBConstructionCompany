package domain.usecases.simple;

import domain.DataBaseRepository;

import java.sql.SQLException;

public class GetTableCountRowsUseCase {
    private final DataBaseRepository dataBaseRepository;

    public GetTableCountRowsUseCase(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    public Object invoke() throws SQLException {
        return dataBaseRepository.getCountRows();
    }
}
