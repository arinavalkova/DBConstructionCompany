package domain.rows.organizations;

import domain.rows.Row;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    public String toString() {
        return "name='" + name + "'";
    }

    @Override
    public String getTableName() {
        return "managements";
    }
}
