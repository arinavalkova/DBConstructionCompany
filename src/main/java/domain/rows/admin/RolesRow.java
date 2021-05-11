package domain.rows.admin;

import domain.rows.Row;

import java.util.ArrayList;

public class RolesRow implements Row {

    private final int id;
    private final String name;

    public RolesRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RolesRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.name = list.get(1);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getTableName() {
        return "roles";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }
}
