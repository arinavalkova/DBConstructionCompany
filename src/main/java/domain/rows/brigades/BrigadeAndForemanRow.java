package domain.rows.brigades;

import domain.rows.Row;

import java.util.ArrayList;

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
}
