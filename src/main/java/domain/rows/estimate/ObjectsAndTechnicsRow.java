package domain.rows.estimate;

import domain.rows.Row;

import java.util.ArrayList;

public class ObjectsAndTechnicsRow implements Row {

    private final int id;
    private final int objectId;
    private final int technicsId;
    private final int count;

    public ObjectsAndTechnicsRow(int id, int objectId, int technicsId, int count) {
        this.id = id;
        this.objectId = objectId;
        this.technicsId = technicsId;
        this.count = count;
    }

    public ObjectsAndTechnicsRow(ArrayList<String> rowsList) {
        this.id = Integer.parseInt(rowsList.get(0));
        this.objectId = Integer.parseInt(rowsList.get(1));
        this.technicsId = Integer.parseInt(rowsList.get(2));
        this.count = Integer.parseInt(rowsList.get(3));
    }

    public int getId() {
        return id;
    }

    public int getObjectId() {
        return objectId;
    }

    public int getTechnicsId() {
        return technicsId;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "object_id=" + objectId + "and technics_id=" + technicsId + "and count=" + count;
    }

    @Override
    public String getTableName() {
        return "objects_and_technics";
    }
}
