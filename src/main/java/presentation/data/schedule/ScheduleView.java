package presentation.data.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import presentation.View;

public class ScheduleView implements View {

    private final ScheduleViewModel scheduleViewModel = new ScheduleViewModel();

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane schedulePane;

    @FXML
    private AnchorPane teoreticMaterialsPane;

    @FXML
    private AnchorPane materialsPane;

    @FXML
    private AnchorPane typesOfWorkPane;

    @FXML
    private AnchorPane objectsPane;

    @FXML
    private AnchorPane brigadesPane;

    @FXML
    void initialize() {
        initButtons();
        loadTables();
    }

    private void loadTables() {
        scheduleViewModel.loadBrigadesShowerPane(brigadesPane);
        scheduleViewModel.loadObjectsShowerPane(objectsPane);
        scheduleViewModel.loadTypesOfWorkShowerPane(typesOfWorkPane);
        scheduleViewModel.loadMaterialsShowerPane(materialsPane);
        scheduleViewModel.loadTheoreticMaterialsPane(teoreticMaterialsPane);
        scheduleViewModel.loadSchedulePane(schedulePane);
    }

    private void initButtons() {
        backButton.setOnAction(event -> scheduleViewModel.goBack());
    }
}
