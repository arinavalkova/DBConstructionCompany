package domain.usecases.parameterized;

import domain.DataBaseRepository;

public class DeleteRowUseCase implements ParamUseCase {

    private final DataBaseRepository dataBaseRepository;

    public DeleteRowUseCase(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @Override
    public Boolean invoke(Object object) {
        return dataBaseRepository.deleteRow((Integer) object);
    }
}
