package domain.rows.shedule;

import domain.rows.Row;

import java.util.ArrayList;

public class TheoreticMaterialsRow implements Row {

    private final int id;
    private final int workId;
    private final int materialId;
    private final int count;

    public TheoreticMaterialsRow(int id, int workId, int materialId, int count) {
        this.id = id;
        this.workId = workId;
        this.materialId = materialId;
        this.count = count;
    }

    public TheoreticMaterialsRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.workId = Integer.parseInt(list.get(1));
        this.materialId = Integer.parseInt(list.get(2));
        this.count = Integer.parseInt(list.get(3));
    }

    public int getId() {
        return id;
    }

    public int getWorkId() {
        return workId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getTableName() {
        return "\"18206_VALKOVA\".theor_mater";
    }

    @Override
    public String toString() {
        return "work_id=" + workId +
                "and material_id" + materialId +
                "and count=" + count;
    }
}
