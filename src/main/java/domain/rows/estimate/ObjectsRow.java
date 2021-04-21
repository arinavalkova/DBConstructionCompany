package domain.rows.estimate;

import domain.rows.Row;

import java.util.ArrayList;

public class ObjectsRow implements Row {

    private final int id;
    private final String name;
    private final int sectorId;

    public ObjectsRow(int id, String name, int sectorId) {
        this.id = id;
        this.name = name;
        this.sectorId = sectorId;
    }

    public ObjectsRow(ArrayList<String> rowsList) {
        this.id = Integer.parseInt(rowsList.get(0));
        this.name = rowsList.get(1);
        this.sectorId = Integer.parseInt(rowsList.get(2));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSectorId() {
        return sectorId;
    }

    @Override
    public String toString() {
        return "name=" + name + "and sectorId=" + sectorId;
    }

    @Override
    public String getTableName() {
        return "objects";
    }
}
