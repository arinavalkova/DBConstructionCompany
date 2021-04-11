package domain.rows.people;

import domain.rows.Row;

public class SectorsRow implements Row {
    private final int id;
    private final String name;

    public SectorsRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
