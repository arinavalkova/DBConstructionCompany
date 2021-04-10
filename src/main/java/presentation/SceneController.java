package presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SceneController {
    private static Scene scene;
    private static ClientFXMLLoader gameFxmlLoader;

    public static void loadMain(ClassLoader classLoader) throws IOException {
        gameFxmlLoader = new ClientFXMLLoader(classLoader);
        FXMLLoader loader = gameFxmlLoader.getFXMLoader("authorization.fxml");
        Parent root = loader.load();
        SceneController.scene = new Scene(root);
    }

    public static FXMLLoader load(String FXMLName) throws IOException {
        FXMLLoader loader = gameFxmlLoader.getFXMLoader(FXMLName);
        Parent root = loader.load();
        scene.setRoot(root);
        return loader;
    }

    public static FXMLLoader getLoader(String fxmlName) {
        return gameFxmlLoader.getFXMLoader(fxmlName);
    }

    public static Scene getScene() {
        return scene;
    }
}