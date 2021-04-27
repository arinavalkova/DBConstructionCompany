package domain.rows.queries;

public class ManagementAndBossRow {

    private final String managementName;
    private final String bossName;

    public ManagementAndBossRow(String managementName, String bossName) {
        this.managementName = managementName;
        this.bossName = bossName;
    }

    public String getManagementName() {
        return managementName;
    }

    public String getBossName() {
        return bossName;
    }
}
