package presentation.main;

import domain.rows.CategoriesRow;
import domain.rows.CustomersRow;
import domain.rows.ObjectsRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class MainView {

    private final static String userName = "18206_VALKOVA";
    private final static String password = "nar&Alex";

    private MainViewModel mainViewModel;

    @FXML
    private TableView<CategoriesRow> categoriesTable;

    @FXML
    private TableView<CustomersRow> customersTable;

    @FXML
    private TableView<ObjectsRow> objectsTable;

    @FXML
    private TableColumn<CategoriesRow, Integer> numberOfcategoriesColumn;

    @FXML
    private TableColumn<CategoriesRow, String> namesOfCategoriesColumn;

    @FXML
    private TableColumn<CustomersRow, Integer> numberOfCustomersColumn;

    @FXML
    private TableColumn<CustomersRow, String> nameOfCustomersColumn;

    @FXML
    private TableColumn<ObjectsRow, Integer> numberOfObjectsColumn;

    @FXML
    private TableColumn<ObjectsRow, String> nameOfObjectsColumn;

    @FXML
    private TableColumn<ObjectsRow, Integer> numberOfObjectCategoriesColumn;

    @FXML
    private TableColumn<ObjectsRow, Integer> numberOfCustomersObjectsColumn;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private Button loadCategoriesData;

    @FXML
    private Button loadCustomersData;

    @FXML
    private Button loadObjectsDatat;

    @FXML
    private TextField newCategoryField;

    @FXML
    private Button insertCategoryButton;

    @FXML
    private TextField categoryNumberForDeleteField;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private TextField numberOfCategoryForUpdateField;

    @FXML
    private Button updateCategoriesButton;

    @FXML
    private TextField newCustomerField;

    @FXML
    private Button insertCustomerButton;

    @FXML
    private TextField numberOfCustumerForDeleteField;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private TextField numberOfCustomerForUpdateField;

    @FXML
    private TextField customerForUpdateFiled;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private TextField nameOfObjectForInsertField;

    @FXML
    private TextField numberOfCategoryObjectForInsert;

    @FXML
    private TextField numberOfCustomerObjectForInsertFiled;

    @FXML
    private Button insertObjectButton;

    @FXML
    private TextField numberOfObjectForDeleteFiled;

    @FXML
    private Button deleteObjectButton;

    @FXML
    private TextField numberOfObjectForUpdateField;

    @FXML
    private TextField nameOfObjectForUpdateField;

    @FXML
    private TextField numberOfCategoryObjectForUpdateField;

    @FXML
    private TextField numberOfCustomerObjectForUpdateField;

    @FXML
    private Button updateObjectButton;

    @FXML
    private Label answerLabel;

    @FXML
    private TextField categoryNameForUpdate;

    @FXML
    private TextField objectNameForSearchFiled;

    @FXML
    private Button searchButton;

    @FXML
    private Label customerNamelabel;

    @FXML
    void initialize() {
        try {
            mainViewModel = new MainViewModel(userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        initControls();
        bindControls();
    }

    private void bindControls() {
        answerLabel.textProperty().bind(mainViewModel.getAnswerProperty());
        customerNamelabel.textProperty().bind(mainViewModel.getCustomerProperty());

        numberOfObjectsColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfObjectsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberOfObjectCategoriesColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        numberOfCustomersObjectsColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        numberOfCustomersColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfCustomersColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        numberOfcategoriesColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namesOfCategoriesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        categoriesTable.itemsProperty().bind(mainViewModel.getCategoryTableProperty());
        customersTable.itemsProperty().bind(mainViewModel.getCustomersTableProperty());
        objectsTable.itemsProperty().bind(mainViewModel.getObjectsTableProperty());
    }

    private void initControls() {
        initLoadTestDataButton();
        initLoadCategoryDataButton();
        intiLoadCustomersDataButton();
        initLoadObjectDataButton();
        initInsertCategoryButton();
        initInsertCustomerButton();
        initInsertObjectButton();
        initDeleteCategoryButton();
        initDeleteCustomerButton();
        initDeleteObjectButton();
        initUpdateCategoryButton();
        initUpdateCustomerButton();
        initUpdateObjectButton();
        initSearchCustomerButton();
    }

    private void initSearchCustomerButton() {
        searchButton.setOnMouseClicked(event -> {
            mainViewModel.searchCustomerByObjectName(objectNameForSearchFiled.getText());
            objectNameForSearchFiled.setText("");
        });
    }

    private void initUpdateObjectButton() {
        updateObjectButton.setOnMouseClicked(event -> {
            mainViewModel.updateObject(
                    Integer.parseInt(numberOfObjectForUpdateField.getText()),
                    nameOfObjectForUpdateField.getText(),
                    Integer.parseInt(numberOfCategoryObjectForUpdateField.getText()),
                    Integer.parseInt(numberOfCustomerObjectForUpdateField.getText())
            );
            numberOfObjectForUpdateField.setText("");
            nameOfObjectForUpdateField.setText("");
            numberOfCategoryObjectForUpdateField.setText("");
            numberOfCustomerObjectForUpdateField.setText("");
        });
    }

    private void initUpdateCustomerButton() {
        updateCustomerButton.setOnMouseClicked(event -> {
            mainViewModel.updateCustomers(
                    Integer.parseInt(numberOfCustomerForUpdateField.getText()),
                    customerForUpdateFiled.getText()
            );
            numberOfCustomerForUpdateField.setText("");
            customerForUpdateFiled.setText("");
        });
    }

    private void initUpdateCategoryButton() {
        updateCategoriesButton.setOnMouseClicked(event -> {
            mainViewModel.updateCategory(
                    Integer.parseInt(numberOfCategoryForUpdateField.getText()),
                    categoryNameForUpdate.getText()
            );
            numberOfCategoryForUpdateField.setText("");
            categoryNameForUpdate.setText("");
        });
    }

    private void initDeleteObjectButton() {
        deleteObjectButton.setOnMouseClicked(event -> {
            mainViewModel.deleteObject(
                    Integer.parseInt(numberOfObjectForDeleteFiled.getText()));
            numberOfObjectForDeleteFiled.setText("");
        });
    }

    private void initDeleteCustomerButton() {
        deleteCustomerButton.setOnMouseClicked(event -> {
            mainViewModel.deleteCustomer(
                    Integer.parseInt(numberOfCustumerForDeleteField.getText()));
            numberOfCustumerForDeleteField.setText("");
        });
    }

    private void initDeleteCategoryButton() {
        deleteCategoryButton.setOnMouseClicked(event -> {
            mainViewModel.deleteCategory(
                    Integer.parseInt(categoryNumberForDeleteField.getText()));
            categoryNumberForDeleteField.setText("");
        });
    }

    private void initInsertObjectButton() {
        insertObjectButton.setOnMouseClicked(event -> {
            mainViewModel.insertObject(
                    nameOfObjectForInsertField.getText(),
                    Integer.parseInt(numberOfCategoryObjectForInsert.getText()),
                    Integer.parseInt(numberOfCustomerObjectForInsertFiled.getText()));
            nameOfObjectForInsertField.setText("");
            numberOfCategoryObjectForInsert.setText("");
            numberOfCustomerObjectForInsertFiled.setText("");
        });
    }

    private void initInsertCustomerButton() {
        insertCustomerButton.setOnMouseClicked(event -> {
            mainViewModel.insertCustomer(newCustomerField.getText());
            newCustomerField.setText("");
        });
    }

    private void initInsertCategoryButton() {
        insertCategoryButton.setOnMouseClicked(event -> {
            mainViewModel.insertCategory(newCategoryField.getText());
            newCategoryField.setText("");
        });
    }

    private void initLoadObjectDataButton() {
        loadObjectsDatat.setOnMouseClicked(event -> mainViewModel.getObjects());
    }

    private void intiLoadCustomersDataButton() {
        loadCustomersData.setOnMouseClicked(event -> mainViewModel.getCustomers());
    }

    private void initLoadCategoryDataButton() {
        loadCategoriesData.setOnMouseClicked(event -> mainViewModel.getCategories());
    }

    private void initLoadTestDataButton() {
        loadTestDataButton.setOnMouseClicked(event -> mainViewModel.loadTestData());
    }
}
