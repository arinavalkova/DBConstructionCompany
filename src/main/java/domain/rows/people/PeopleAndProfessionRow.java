package domain.rows.people;

import domain.rows.Row;

import java.util.ArrayList;
import java.util.Arrays;

public class PeopleAndProfessionRow implements Row {

    private final int id;
    private final String name;
    private final int professionId;

    public PeopleAndProfessionRow(int id, String name, int professionId) {
        this.id = id;
        this.name = name;
        this.professionId = professionId;
    }

    public PeopleAndProfessionRow(ArrayList<String> rowLines) {
        this.id = Integer.parseInt(rowLines.get(0));
        this.name =  rowLines.get(1);
        this.professionId = Integer.parseInt(rowLines.get(2));
    }

    public String getName() {
        return name;
    }

    public int getProfessionId() {
        return professionId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name='" + name + "'and profession_id=" + professionId;
    }

    @Override
    public String getTableName() {
        return "\"18206_VALKOVA\".people_and_professions";
    }
}
