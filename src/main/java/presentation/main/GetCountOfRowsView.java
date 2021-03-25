package presentation.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class GetCountOfRowsView {

    private final String TABLE_NAME = "persons";
    private final String userName = "18206_VALKOVA";
    private final String password = "nar&Alex";
    public AnchorPane root;

    private GetCountOfRowsViewModel getCountOfRowsViewModel;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXButton getButton;

    @FXML
    void initialize() {
        try {
            getCountOfRowsViewModel = new GetCountOfRowsViewModel(userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        getButton.setOnMouseClicked(event -> {
            try {
                bind();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void bind() throws SQLException {
        textField.setText(String.valueOf(getCountOfRowsViewModel.loadCountOfRows(TABLE_NAME)));
    }
}
