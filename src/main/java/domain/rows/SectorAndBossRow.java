package domain.rows;

public class SectorAndBossRow implements Row {
    private final int id;
    private final int sectorId;
    private final int bossId;

    public SectorAndBossRow(int id, int sectorId, int bossId) {
        this.id = id;
        this.sectorId = sectorId;
        this.bossId = bossId;
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
}
