package presentation.main.data.editing;

import presentation.SceneController;

import java.io.IOException;

public class DataEditingViewModel {

    public void loadStartWindow() {
        try {
            SceneController.load("start.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCharacteristicsOfObjectsWindow() {
        try {
            SceneController.load("characteristicsOfObjects.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadObjectsCategoriesWindow() {
        try {
            SceneController.load("objectsCategories.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomersWindow() {
        try {
            SceneController.load("customers.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
