package domain.rows.people;

import domain.rows.Row;

import java.util.ArrayList;

public class SectorsRow implements Row {
    private final int id;
    private final String name;

    public SectorsRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SectorsRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.name = list.get(1);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name='" + name + "'";
    }

    @Override
    public String getTableName() {
        return "sectors";
    }
}
