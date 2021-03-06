import javafx.application.Application;
import javafx.stage.Stage;
import presentation.SceneController;

public class MainApplication extends Application {

    public static void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        SceneController.loadMain(classLoader);
        primaryStage.setScene(SceneController.getScene());
        primaryStage.setMinWidth(1350);
        primaryStage.setMinHeight(950);
        primaryStage.show();
    }
}
