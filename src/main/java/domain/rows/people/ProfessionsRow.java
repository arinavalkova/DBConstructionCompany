package domain.rows.people;

import domain.rows.Row;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfessionsRow implements Row {
    private final int id;
    private final String name;

    public ProfessionsRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProfessionsRow(ArrayList<String> rowLines) {
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
    public String toString() {
        return "name='" + name + "'";
    }

    @Override
    public String getTableName() {
        return "\"18206_VALKOVA\".professions";
    }
}
