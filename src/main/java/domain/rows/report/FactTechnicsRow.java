package domain.rows.report;

import domain.rows.Row;

import java.util.ArrayList;

public class FactTechnicsRow implements Row {

    private final int id;
    private final int workId;
    private final int technicsId;
    private final int count;

    public FactTechnicsRow(int id, int workId, int technicsId, int count) {
        this.id = id;
        this.workId = workId;
        this.technicsId = technicsId;
        this.count = count;
    }

    public FactTechnicsRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.workId = Integer.parseInt(list.get(1));
        this.technicsId = Integer.parseInt(list.get(2));
        this.count = Integer.parseInt(list.get(3));
    }

    public int getId() {
        return id;
    }

    public int getWorkId() {
        return workId;
    }

    public int getTechnicsId() {
        return technicsId;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getTableName() {
        return "fact_techn";
    }

    @Override
    public String toString() {
        return "work_id=" + workId +
                "and technics_id=" + technicsId +
                "and count=" + count;
    }
}
