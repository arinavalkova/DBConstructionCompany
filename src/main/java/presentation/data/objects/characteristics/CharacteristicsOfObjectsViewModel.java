package presentation.data.objects.characteristics;

import presentation.SceneController;

import java.io.IOException;

public class CharacteristicsOfObjectsViewModel {

    public void loadDataMenuWindow() {
        try {
            SceneController.load("dataEditing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadObjectCategoriesWindow() {
        try {
            SceneController.load("objectsCategories.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTestData() {

        updateTable();
    }

    public void insertCharacteristic(String name) {

        updateTable();
    }

    public void deleteCharacteristic(int id) {

        updateTable();
    }

    public void updateCharacteristic(int id, String name) {

        updateTable();
    }

    private void updateTable() {

    }
}
