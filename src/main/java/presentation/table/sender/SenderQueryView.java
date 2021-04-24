package presentation.table.sender;

import domain.DataBaseRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.View;

public class SenderQueryView implements View {

    private final SenderQueryViewModel senderQueryViewModel;

    @FXML
    private Label label;

    @FXML
    private TextField textFiled;

    @FXML
    private Button button;

    private final String descriptionName;

    public SenderQueryView(
            DataBaseRepository dataBaseRepository,
            String descriptionName) {
        this.descriptionName = descriptionName;
        this.senderQueryViewModel = new SenderQueryViewModel(dataBaseRepository);
    }

    @FXML
    void initialize() {
        initLabel();
        initButton();
    }

    private void initLabel() {
        label.setText(descriptionName);
    }

    private void initButton() {
        button.setOnAction(event -> {
            senderQueryViewModel.sendInsertQuery(textFiled.getText());
            textFiled.setText("");
        });
    }
}
