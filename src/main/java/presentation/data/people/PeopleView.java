package presentation.data.people;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import presentation.View;

public class PeopleView implements View {

    private final PeopleViewModel peopleViewModel = new PeopleViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane bossAndEmployeesPane;

    @FXML
    private AnchorPane professionsPane;

    @FXML
    private AnchorPane peopleAndProfessionsPane;

    @FXML
    private AnchorPane sectorsAndBossPane;

    @FXML
    private AnchorPane sectorsPane;

    @FXML
    private Button goToAdminButton;

    @FXML
    void initialize() {
        initButtons();
        loadTables();
    }

    private void loadTables() {
        peopleViewModel.loadProfessionsTable(professionsPane);
        peopleViewModel.loadPeopleAndProfessionsTable(peopleAndProfessionsPane);
        peopleViewModel.loadBossAndEmployeeTable(bossAndEmployeesPane);
        peopleViewModel.loadSectorsAndBossTable(sectorsAndBossPane);
        peopleViewModel.loadSectorsTable(sectorsPane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> peopleViewModel.goBack());
        goToAdminButton.setOnAction(event -> peopleViewModel.goToAdmin());
    }
}
