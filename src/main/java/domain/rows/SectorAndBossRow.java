package domain.rows;

public class SectorAndBossRow implements Row {
    private final int id;
    private final String name;
    private final int bossId;

    public SectorAndBossRow(int id, String name, int bossId) {
        this.id = id;
        this.name = name;
        this.bossId = bossId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBossId() {
        return bossId;
    }
}
