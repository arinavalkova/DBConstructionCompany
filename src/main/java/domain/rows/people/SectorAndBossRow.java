package domain.rows.people;

import domain.rows.Row;

import java.util.ArrayList;
import java.util.Arrays;

public class SectorAndBossRow implements Row {
    private final int id;
    private final int sectorId;
    private final int bossId;

    public SectorAndBossRow(int id, int sectorId, int bossId) {
        this.id = id;
        this.sectorId = sectorId;
        this.bossId = bossId;
    }

    public SectorAndBossRow(ArrayList<String> rowLines) {
        this.id = Integer.parseInt(rowLines.get(0));
        this.sectorId = Integer.parseInt(rowLines.get(1));
        this.bossId = Integer.parseInt(rowLines.get(2));
    }

    public int getId() {
        return id;
    }

    public int getSectorId() {
        return sectorId;
    }

    public int getBossId() {
        return bossId;
    }

    @Override
    public String toString() {
        return "sector_id=" + sectorId + "and boss_id=" + bossId;
    }

    @Override
    public String getTableName() {
        return "sector_and_boss";
    }
}
