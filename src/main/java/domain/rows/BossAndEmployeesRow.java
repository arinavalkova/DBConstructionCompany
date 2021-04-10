package domain.rows;

import java.util.ArrayList;

public class BossAndEmployeesRow implements Row {
    private final int id;
    private final int bossId;
    private final int employeeId;

    public BossAndEmployeesRow(int id, int bossId, int employeeId) {
        this.id = id;
        this.bossId = bossId;
        this.employeeId = employeeId;
    }

    public BossAndEmployeesRow(ArrayList<String> rowLines) {
        this.id = Integer.parseInt(rowLines.get(0));
        this.bossId = Integer.parseInt(rowLines.get(1));
        this.employeeId = Integer.parseInt(rowLines.get(2));
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
