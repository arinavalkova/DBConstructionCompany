package presentation.data.objects.characteristics;

import domain.rows.CharacteristicsOfObjectsRow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CharacteristicsOfObjectsView {

    private CharacteristicsOfObjectsViewModel characteristicsOfObjectsViewModel =
            new CharacteristicsOfObjectsViewModel();

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private TableView<CharacteristicsOfObjectsRow> table;

    @FXML
    private TableColumn<CharacteristicsOfObjectsRow, Integer> idColumn;

    @FXML
    private TableColumn<CharacteristicsOfObjectsRow, String> nameColumn;

    @FXML
    private TextField nameForInsertField;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idForDeleteField;

    @FXML
    private Button updateButton;

    @FXML
    private TextField idForUpdateField;

    @FXML
    private TextField nameForUpdateField;

    @FXML
    private Button testDataButton;


    @FXML
    void initialize() {
        initButtons();
        bind();
    }

    private void bind() {

    }

    private void initButtons() {
        backButton.setOnMouseClicked(event -> characteristicsOfObjectsViewModel.loadDataMenuWindow());
        nextButton.setOnMouseClicked(event -> characteristicsOfObjectsViewModel.loadObjectCategoriesWindow());
        testDataButton.setOnMouseClicked(event -> characteristicsOfObjectsViewModel.loadTestData());
    }
}
