package domain.rows.organizations;

import domain.rows.Row;

import java.util.ArrayList;

public class ManagementsAndSectorsRow implements Row {

    private final int id;
    private final int managementId;
    private final int sectorId;

    public ManagementsAndSectorsRow(int id, int managementId, int sectorId) {
        this.id = id;
        this.managementId = managementId;
        this.sectorId = sectorId;
    }

    public ManagementsAndSectorsRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.managementId = Integer.parseInt(list.get(1));
        this.sectorId = Integer.parseInt(list.get(2));
    }

    public int getId() {
        return id;
    }

    public int getManagementId() {
        return managementId;
    }

    public int getSectorId() {
        return sectorId;
    }
}
