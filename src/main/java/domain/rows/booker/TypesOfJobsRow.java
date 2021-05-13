package domain.rows.booker;

import domain.rows.Row;

import java.util.ArrayList;

public class TypesOfJobsRow implements Row {

    private final int id;
    private final String name;

    public TypesOfJobsRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypesOfJobsRow(ArrayList<String> list) {
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
        return "id=" + id + "and name=" + name;
    }

    @Override
    public String getTableName() {
        return "\"18206_VALKOVA\".types_of_jobs";
    }
}
