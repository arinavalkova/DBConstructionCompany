package presentation.data.people;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PeopleView {

    private final static String EMPTY_FIELD = "";

    private PeopleViewModel peopleViewModel = new PeopleViewModel();

    @FXML
    private TableView<?> professionsTable;

    @FXML
    private TableColumn<?, ?> professionsIdColumn;

    @FXML
    private TableColumn<?, ?> professionsNameColumn;

    @FXML
    private TableView<?> bossAndEmployeesTable;

    @FXML
    private TableColumn<?, ?> bossAndEmployyesIdColumn;

    @FXML
    private TableColumn<?, ?> bossAndEmployeesBossIdColumn;

    @FXML
    private TableColumn<?, ?> bossAndEmployeesEmployeeIdColumn;

    @FXML
    private TableView<?> sectorAndBossTable;

    @FXML
    private TableColumn<?, ?> sectorAndBossIdColumn;

    @FXML
    private TableColumn<?, ?> sectorAndBossSectorNameColumn;

    @FXML
    private TableColumn<?, ?> sectorAndBossBossIdColumn;

    @FXML
    private TableView<?> peopleAndProfessionsTable;

    @FXML
    private TableColumn<?, ?> peopleAndProfessionsIdColumn;

    @FXML
    private TableColumn<?, ?> peopleAndProfessionsNameColumn;

    @FXML
    private TableColumn<?, ?> peopleAndProfessionsProfessionIdColumn;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXTextField professionNameForInsertField;

    @FXML
    private JFXButton professionsInsertButton;

    @FXML
    private JFXTextField professionsIdForDeleteFileld;

    @FXML
    private JFXButton proffessionsDeleteButton;

    @FXML
    private JFXTextField proffessionsIdForUpdateField;

    @FXML
    private JFXTextField professionsProfessionNameForUpdateField;

    @FXML
    private JFXButton professionsUpdateButton;

    @FXML
    private JFXTextField peopleAndProfessionsPersonNameForInsertFiled;

    @FXML
    private JFXTextField peopleAndProfessionsProfessionIdForInsertField;

    @FXML
    private JFXButton peopleAndProfessionsInsertButton;

    @FXML
    private JFXTextField peopleAndProfessionsIdForDeleteField;

    @FXML
    private JFXButton peopleAndProfessionsForDeleteButton;

    @FXML
    private JFXTextField peopleAndProfessionsIdForUpdateField;

    @FXML
    private JFXTextField peopleAndProfessionsPersonNameForUpdateField;

    @FXML
    private JFXTextField peopleAndProfessionsProfessionIdForUpdateField;

    @FXML
    private JFXButton peopleAndProfessionForUpdateButton;

    @FXML
    private JFXTextField bossAndEmployeesBossIdForInsertField;

    @FXML
    private JFXTextField bossAndEmployeesEmployeeIdForInsertFiled;

    @FXML
    private JFXButton bossAndEmployeesInsertButton;

    @FXML
    private JFXTextField bossAndEmployeesIdForDeleteField;

    @FXML
    private JFXButton bossAndEmployeesDeleteButton;

    @FXML
    private JFXTextField bossAndEmployeesIdForUpdateField;

    @FXML
    private JFXTextField bossAndEmployeesBossIdForUpdateField;

    @FXML
    private JFXTextField bossAndEmployeesEmployeeIdForUpdateField;

    @FXML
    private JFXButton bossAndEmployeesUpdateButton;

    @FXML
    private JFXTextField sectorAndBossSectorNameForInsertField;

    @FXML
    private JFXTextField sectorAndBossBossIdForInsertField;

    @FXML
    private JFXButton sectorAndBossInsertButton;

    @FXML
    private JFXTextField sectorAndBossIfForDeleteField;

    @FXML
    private JFXButton sectorAndBossDeleteButton;

    @FXML
    private JFXTextField sectorAndBossIdForUpdateField;

    @FXML
    private JFXTextField sectorAndBossSectorNameForUpdateField;

    @FXML
    private JFXTextField sectorAndBossBossIdForUpdateField;

    @FXML
    private JFXButton sectorAndBossUpdateButton;

    @FXML
    private Button loadTestDataButton;

    @FXML
    private Label professionsAnswerLabel;

    @FXML
    private Label peopleAndProfessionAnswerLabel;

    @FXML
    private Label bossAndEmployeesAnswerLabel;

    @FXML
    private Label sectorAndBossAnswerLabel;

    @FXML
    void initialize() {
        initButtons();
        bind();
    }

    private void bind() {
        professionsAnswerLabel.textProperty().bind(peopleViewModel.getProfessionsAnswerProperty());
        peopleAndProfessionAnswerLabel.textProperty().bind(peopleViewModel.getPeopleAndProfessionAnswerProperty());
        bossAndEmployeesAnswerLabel.textProperty().bind(peopleViewModel.getBossAndEmployeesAnswerProperty());
        sectorAndBossAnswerLabel.textProperty().bind(peopleViewModel.getSectorAndBossAnswerProperty());
    }

    private void initButtons() {
        loadTestDataButton.setOnAction(event -> peopleViewModel.loadTestData());

        backButton.setOnAction(event -> peopleViewModel.backToEditingMenu());
        nextButton.setOnAction(event -> peopleViewModel.goToAreasEditingWindow());

        professionsInsertButton.setOnAction(event -> {
            peopleViewModel.insertProfession(
                    professionNameForInsertField.getText()
            );
            professionNameForInsertField.setText(EMPTY_FIELD);
        });
        proffessionsDeleteButton.setOnAction(event -> {
            peopleViewModel.deleteProfession(
                    Integer.parseInt(professionsIdForDeleteFileld.getText())
            );
            professionsIdForDeleteFileld.setText(EMPTY_FIELD);
        });
        professionsUpdateButton.setOnAction(event -> {
            peopleViewModel.updateProfession(
                    Integer.parseInt(proffessionsIdForUpdateField.getText()),
                    professionsProfessionNameForUpdateField.getText()
            );
            proffessionsIdForUpdateField.setText(EMPTY_FIELD);
            professionsProfessionNameForUpdateField.setText(EMPTY_FIELD);
        });

        peopleAndProfessionsInsertButton.setOnAction(event -> {
            peopleViewModel.insertPeopleAndProfession(
                    peopleAndProfessionsPersonNameForInsertFiled.getText(),
                    Integer.parseInt(peopleAndProfessionsProfessionIdForInsertField.getText())
            );
            peopleAndProfessionsPersonNameForInsertFiled.setText(EMPTY_FIELD);
            peopleAndProfessionsProfessionIdForInsertField.setText(EMPTY_FIELD);
        });
        peopleAndProfessionsForDeleteButton.setOnAction(event -> {
            peopleViewModel.deletePeopleAndProfession(
                    Integer.parseInt(peopleAndProfessionsIdForDeleteField.getText())
            );
            peopleAndProfessionsIdForDeleteField.setText(EMPTY_FIELD);
        });
        peopleAndProfessionForUpdateButton.setOnAction(event -> {
            peopleViewModel.updatePeopleAndProfession(
                    Integer.parseInt(peopleAndProfessionsIdForUpdateField.getText()),
                    peopleAndProfessionsPersonNameForUpdateField.getText(),
                    Integer.parseInt(peopleAndProfessionsProfessionIdForUpdateField.getText())
            );
            peopleAndProfessionsIdForUpdateField.setText(EMPTY_FIELD);
            peopleAndProfessionsPersonNameForUpdateField.setText(EMPTY_FIELD);
            peopleAndProfessionsProfessionIdForUpdateField.setText(EMPTY_FIELD);
        });

        bossAndEmployeesInsertButton.setOnAction(event -> {
            peopleViewModel.insertBossAndEmployees(
                    Integer.parseInt(bossAndEmployeesBossIdForInsertField.getText()),
                    Integer.parseInt(bossAndEmployeesEmployeeIdForInsertFiled.getText())
            );
            bossAndEmployeesBossIdForInsertField.setText(EMPTY_FIELD);
            bossAndEmployeesEmployeeIdForInsertFiled.setText(EMPTY_FIELD);
        });
        bossAndEmployeesDeleteButton.setOnAction(event -> {
            peopleViewModel.deleteBossAndEmployees(
                    Integer.parseInt(bossAndEmployeesIdForDeleteField.getText())
            );
            bossAndEmployeesIdForDeleteField.setText(EMPTY_FIELD);
        });
        bossAndEmployeesUpdateButton.setOnAction(event -> {
            peopleViewModel.updateBossAndEmployees(
                    Integer.parseInt(bossAndEmployeesIdForUpdateField.getText()),
                    Integer.parseInt(bossAndEmployeesBossIdForUpdateField.getText()),
                    Integer.parseInt(bossAndEmployeesEmployeeIdForUpdateField.getText())
            );
            bossAndEmployeesIdForUpdateField.setText(EMPTY_FIELD);
            bossAndEmployeesBossIdForUpdateField.setText(EMPTY_FIELD);
            bossAndEmployeesEmployeeIdForUpdateField.setText(EMPTY_FIELD);
        });

        sectorAndBossInsertButton.setOnAction(event -> {
            peopleViewModel.insertSectorAndBoss(
                    sectorAndBossSectorNameForInsertField.getText(),
                    Integer.parseInt(sectorAndBossBossIdForInsertField.getText())
            );
            sectorAndBossSectorNameForInsertField.setText(EMPTY_FIELD);
            sectorAndBossBossIdForInsertField.setText(EMPTY_FIELD);
        });
        sectorAndBossDeleteButton.setOnAction(event -> {
            peopleViewModel.deleteSectorAndBoss(
                    Integer.parseInt(sectorAndBossIfForDeleteField.getText())
            );
            sectorAndBossIfForDeleteField.setText(EMPTY_FIELD);
        });
        sectorAndBossUpdateButton.setOnAction(event -> {
            peopleViewModel.updateSectorAndBoss(
                    Integer.parseInt(sectorAndBossIdForUpdateField.getText()),
                    sectorAndBossSectorNameForUpdateField.getText(),
                    Integer.parseInt(sectorAndBossBossIdForUpdateField.getText())
            );
            sectorAndBossIdForUpdateField.setText(EMPTY_FIELD);
            sectorAndBossSectorNameForUpdateField.setText(EMPTY_FIELD);
            sectorAndBossBossIdForUpdateField.setText(EMPTY_FIELD);
        });
    }
}
