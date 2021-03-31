package presentation;

import javafx.fxml.FXMLLoader;

public class ClientFXMLLoader {

    private final ClassLoader classLoader;

    public ClientFXMLLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public FXMLLoader getFXMLoader(String FXMLName) {
        return new FXMLLoader(classLoader.getResource(FXMLName));
    }
}