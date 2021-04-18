package domain.rows.booker;

import domain.rows.Row;

import java.util.ArrayList;

public class MaterialsRow implements Row {

    private final int id;
    private final String name;

    public MaterialsRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MaterialsRow(ArrayList<String> rowLines) {
        this.id = Integer.parseInt(rowLines.get(0));
        this.name = rowLines.get(1);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getTableName() {
        return "materials";
    }

    @Override
    public String toString() {
        return "id=" + id + "and name=" + name;
    }
}
