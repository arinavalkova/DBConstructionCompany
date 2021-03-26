package domain.usecases.nonParameterized;

import domain.DataBaseRepository;

public class GetRowsUseCase implements NonParamUseCase {

    private final DataBaseRepository dataBaseRepository;

    public GetRowsUseCase(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @Override
    public Object invoke() {
        return dataBaseRepository.getRows();
    }
}
