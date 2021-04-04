package domain.usecases.nonParameterized;

import domain.DataBaseRepository;

public class GetRowsUseCase implements NonParamUseCase {

    private final DataBaseRepository dataBaseRepository;
    private final DataReceiver dataReceiver;

    public GetRowsUseCase(DataBaseRepository dataBaseRepository, DataReceiver dataReceiver) {
        this.dataBaseRepository = dataBaseRepository;
        this.dataReceiver = dataReceiver;
    }

    @Override
    public void invoke() {
        return dataBaseRepository.getRows();
    }
}
