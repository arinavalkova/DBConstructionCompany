package presentation.main;

import data.JDBCConnection;
import data.tables.CategoriesTableImpl;
import data.tables.CustomersTableImpl;
import data.tables.ObjectsTableImpl;
import domain.rows.CategoriesRow;
import domain.rows.CustomersRow;
import domain.rows.ObjectsRow;
import domain.usecases.nonParameterized.GetRowsUseCase;
import domain.usecases.nonParameterized.LoadTestDataUseCase;
import domain.usecases.parameterized.DeleteRowUseCase;
import domain.usecases.parameterized.InsertRowUseCase;
import domain.usecases.parameterized.SearchCustomerNameByObjectNameUseCase;
import domain.usecases.parameterized.UpdateRowUseCase;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MainViewModel {

    private final StringProperty answerProperty = new SimpleStringProperty();
    private final StringProperty customerProperty = new SimpleStringProperty();

    private final Property<ObservableList<CategoriesRow>> categoriesRowsProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<CustomersRow>> customersRowsProperty = new SimpleObjectProperty<>();
    private final Property<ObservableList<ObjectsRow>> objectsRowsProperty = new SimpleObjectProperty<>();

    public ObservableValue<String> getAnswerProperty() {
        return answerProperty;
    }

    public ObservableValue<String> getCustomerProperty() {
        return customerProperty;
    }

    public Property<ObservableList<CategoriesRow>> getCategoryTableProperty() {
        return categoriesRowsProperty;
    }

    public Property<ObservableList<CustomersRow>> getCustomersTableProperty() {
        return customersRowsProperty;
    }

    public Property<ObservableList<ObjectsRow>> getObjectsTableProperty() {
        return objectsRowsProperty;
    }

    private final InsertRowUseCase insertToCategoriesTableUseCase;
    private final InsertRowUseCase insertToCustomersUseCase;
    private final InsertRowUseCase insertToObjectsUseCase;

    private final DeleteRowUseCase deleteFromCategoriesUseCase;
    private final DeleteRowUseCase deleteFromCustomersUseCase;
    private final DeleteRowUseCase deleteFromObjectsUseCase;

    private final UpdateRowUseCase updateCategoriesUseCase;
    private final UpdateRowUseCase updateCustomersUsecase;
    private final UpdateRowUseCase updateObjectsUsecase;

    private final GetRowsUseCase getCategoriesRowsUseCase;
    private final GetRowsUseCase getCustomersRowsUseCase;
    private final GetRowsUseCase getObjectsRowsUseCase;

    private final LoadTestDataUseCase loadTestDataUseCase;
    private final SearchCustomerNameByObjectNameUseCase searchCustomerNameByObjectNameUseCase;

    public MainViewModel(String userName, String password) throws SQLException {
        JDBCConnection jdbcConnection = new JDBCConnection(userName, password);

        CategoriesTableImpl categoriesTable = new CategoriesTableImpl(jdbcConnection);
        CustomersTableImpl customersTable = new CustomersTableImpl(jdbcConnection);
        ObjectsTableImpl objectsTable = new ObjectsTableImpl(jdbcConnection);

        insertToObjectsUseCase = new InsertRowUseCase(objectsTable);
        updateObjectsUsecase = new UpdateRowUseCase(objectsTable);
        deleteFromObjectsUseCase = new DeleteRowUseCase(objectsTable);

        insertToCategoriesTableUseCase = new InsertRowUseCase(categoriesTable);
        deleteFromCategoriesUseCase = new DeleteRowUseCase(categoriesTable);
        updateCategoriesUseCase = new UpdateRowUseCase(categoriesTable);

        insertToCustomersUseCase = new InsertRowUseCase(customersTable);
        updateCustomersUsecase = new UpdateRowUseCase(customersTable);
        deleteFromCustomersUseCase = new DeleteRowUseCase(customersTable);

        loadTestDataUseCase = new LoadTestDataUseCase(objectsTable, categoriesTable, customersTable, jdbcConnection);

        getCategoriesRowsUseCase = new GetRowsUseCase(categoriesTable);
        getCustomersRowsUseCase = new GetRowsUseCase(customersTable);
        getObjectsRowsUseCase = new GetRowsUseCase(objectsTable);

        searchCustomerNameByObjectNameUseCase = new SearchCustomerNameByObjectNameUseCase(jdbcConnection);
    }

    public void getCategories() {
        List<CategoriesRow> categoriesRowList = (List<CategoriesRow>) getCategoriesRowsUseCase.invoke();
        if (categoriesRowList == null) {
            categoriesRowsProperty.setValue(FXCollections.observableArrayList());
            answerProperty.setValue("Error with loading categories");
        } else {
            categoriesRowsProperty.setValue(FXCollections.observableArrayList(categoriesRowList));
            answerProperty.setValue("Successfully loaded categories");
        }
    }

    public void loadTestData() {
        if (!loadTestDataUseCase.invoke()) {
            answerProperty.setValue("Error with loading test data");
        } else {
            answerProperty.setValue("Successfully loaded test data");
        }
    }

    public void getCustomers() {
        List<CustomersRow> customersRowList = (List<CustomersRow>) getCustomersRowsUseCase.invoke();
        if (customersRowList == null) {
            customersRowsProperty.setValue(FXCollections.observableArrayList());
            answerProperty.setValue("Error with loading customers");
        } else {
            customersRowsProperty.setValue(FXCollections.observableArrayList(customersRowList));
            answerProperty.setValue("Successfully loaded customers");
        }
    }

    public void getObjects() {
        List<ObjectsRow> objectsRowList = (List<ObjectsRow>) getObjectsRowsUseCase.invoke();
        if (objectsRowList == null) {
            objectsRowsProperty.setValue(FXCollections.observableArrayList());
            answerProperty.setValue("Error with loading objects");
        } else {
            objectsRowsProperty.setValue(FXCollections.observableArrayList(objectsRowList));
            answerProperty.setValue("Successfully loaded objects");
        }
    }

    public void insertCategory(String categoryName) {
        if (!insertToCategoriesTableUseCase.invoke(new CategoriesRow(0, categoryName))) {
            answerProperty.setValue("Error with inserting category");
        } else {
            answerProperty.setValue("Successfully inserted category");
        }
    }

    public void insertCustomer(String customerName) {
        if (!insertToCustomersUseCase.invoke(new CustomersRow(0, customerName))) {
            answerProperty.setValue("Error with inserting customer");
        } else {
            answerProperty.setValue("Successfully inserted customer");
        }
    }

    public void insertObject(String name, int categoryId, int customerId) {
        if (!insertToObjectsUseCase.invoke(new ObjectsRow(0, name, categoryId, customerId))) {
            answerProperty.setValue("Error with inserting object");
        } else {
            answerProperty.setValue("Successfully inserted object");
        }
    }

    public void deleteCategory(int id) {
        if (!deleteFromCategoriesUseCase.invoke(id)) {
            answerProperty.setValue("Error with deleting category");
        } else {
            answerProperty.setValue("Successfully deleted category");
        }
    }

    public void deleteCustomer(int id) {
        if (!deleteFromCustomersUseCase.invoke(id)) {
            answerProperty.setValue("Error with deleting customer");
        } else {
            answerProperty.setValue("Successfully deleted customer");
        }
    }

    public void deleteObject(int id) {
        if (!deleteFromObjectsUseCase.invoke(id)) {
            answerProperty.setValue("Error with deleting object");
        } else {
            answerProperty.setValue("Successfully deleted object");
        }
    }

    public void updateCategory(int id, String categoryName) {
        if (!updateCategoriesUseCase.invoke(new CategoriesRow(id, categoryName))) {
            answerProperty.setValue("Error with updating category");
        } else {
            answerProperty.setValue("Successfully updated category");
        }
    }

    public void updateCustomers(int id, String customerName) {
        if (!updateCustomersUsecase.invoke(new CustomersRow(id, customerName))) {
            answerProperty.setValue("Error with updating customer");
        } else {
            answerProperty.setValue("Successfully updated customer");
        }
    }

    public void updateObject(int id, String objectName, int categoriesId, int customerId) {
        if (!updateObjectsUsecase.invoke(new ObjectsRow(id, objectName, categoriesId, customerId))) {
            answerProperty.setValue("Error with updating object");
        } else {
            answerProperty.setValue("Successfully updated object");
        }
    }

    public void searchCustomerByObjectName(String objectName) {
        String answer = (String) searchCustomerNameByObjectNameUseCase.invoke(objectName);
        if (answer == null) {
            answerProperty.setValue("Error with searching customer for object");
        } else {
            customerProperty.setValue(answer);
            answerProperty.setValue("Successfully find customer for object");
        }
    }
}
