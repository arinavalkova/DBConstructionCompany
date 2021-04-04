package domain.rows;

public class BossAndEmployeesRow implements Row {
    private final int id;
    private final int bossId;
    private final int employeeId;

    public BossAndEmployeesRow(int id, int bossId, int employeeId) {
        this.id = id;
        this.bossId = bossId;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public int getBossId() {
        return bossId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
