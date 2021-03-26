package domain.usecases.parameterized;

import domain.DataBaseRepository;
import domain.rows.Row;

public class UpdateRowUseCase implements ParamUseCase{

    private final DataBaseRepository dataBaseRepository;

    public UpdateRowUseCase(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @Override
    public Boolean invoke(Object object) {
        return dataBaseRepository.updateRow((Row) object);
    }
}
