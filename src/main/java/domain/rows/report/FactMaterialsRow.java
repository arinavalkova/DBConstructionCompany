package domain.rows.report;

import domain.rows.Row;

import java.util.ArrayList;

public class FactMaterialsRow implements Row {

    private final int id;
    private final int workId;
    private final int materialsId;
    private final int count;

    public FactMaterialsRow(int id, int workId, int materialsId, int count) {
        this.id = id;
        this.workId = workId;
        this.materialsId = materialsId;
        this.count = count;
    }

    public FactMaterialsRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.workId = Integer.parseInt(list.get(1));
        this.materialsId = Integer.parseInt(list.get(2));
        this.count = Integer.parseInt(list.get(3));
    }

    public int getId() {
        return id;
    }

    public int getWorkId() {
        return workId;
    }

    public int getMaterialsId() {
        return materialsId;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getTableName() {
        return "fact_mater";
    }

    @Override
    public String toString() {
        return "work_id=" + workId +
                "and materials_id=" + materialsId +
                "and count=" + count;
    }
}
