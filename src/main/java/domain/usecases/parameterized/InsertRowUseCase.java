package domain.usecases.parameterized;

import domain.DataBaseRepository;
import domain.rows.Row;

public class InsertRowUseCase implements ParamUseCase{

    private final DataBaseRepository dataBaseRepository;

    public InsertRowUseCase(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @Override
    public Boolean invoke(Object object) {
        return dataBaseRepository.insertRow((Row) object);
    }
}
