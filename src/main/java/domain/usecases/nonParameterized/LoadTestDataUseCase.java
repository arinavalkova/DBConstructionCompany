package domain.usecases.nonParameterized;

import domain.DataBaseRepository;

public class LoadTestDataUseCase implements NonParamUseCase {

    private final DataBaseRepository customersRepository;
    private final DataBaseRepository categoriesRepository;
    private final DataBaseRepository objectsRepository;

    public LoadTestDataUseCase(DataBaseRepository objectsRepository,
                               DataBaseRepository categoriesRepository,
                               DataBaseRepository customersRepository) {
        this.objectsRepository = objectsRepository;
        this.categoriesRepository = categoriesRepository;
        this.customersRepository = customersRepository;
    }

    @Override
    public Boolean invoke() {
        //move to transaction

        objectsRepository.deleteTable();
        customersRepository.deleteTable();
        categoriesRepository.deleteTable();

        categoriesRepository.createTable();
        customersRepository.createTable();
        objectsRepository.createTable();

        if (!categoriesRepository.createIdAutoIncrementTrigger()) {
            return false;
        }
        if (!customersRepository.createIdAutoIncrementTrigger()) {
            return false;
        }
        return objectsRepository.createIdAutoIncrementTrigger();//bad method
    }
}
