package domain.rows.brigades;

import data.tables.brigades.BrigadeAndForemanTableImpl;
import domain.rows.Row;

import java.util.ArrayList;
import java.util.Arrays;

public class BrigadeAndForemanRow implements Row {

    private final int id;
    private final String name;
    private final int foremanId;

    public BrigadeAndForemanRow(int id, String name, int foremanId) {
        this.id = id;
        this.name = name;
        this.foremanId = foremanId;
    }

    public BrigadeAndForemanRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.name = list.get(1);
        this.foremanId = Integer.parseInt(list.get(2));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getForemanId() {
        return foremanId;
    }

    @Override
    public String toString() {
        return "name='" + name + "' and foreman_id=" + foremanId;
    }

    @Override
    public String getTableName() {
        return "brigade_and_man";
    }
}
