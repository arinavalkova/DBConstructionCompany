package domain.rows.organizations;

import domain.rows.Row;

import java.util.ArrayList;

public class ManagementsRow implements Row {
    private final int id;
    private final String name;

    public ManagementsRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ManagementsRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.name = list.get(1);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
