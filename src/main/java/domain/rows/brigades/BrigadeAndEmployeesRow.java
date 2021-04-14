package domain.rows.brigades;

import domain.rows.Row;

import java.util.ArrayList;

public class BrigadeAndEmployeesRow implements Row {

    private final int id;
    private final int brigadeId;
    private final int employeeId;

    public BrigadeAndEmployeesRow(int id, int brigadeId, int employeeId) {
        this.id = id;
        this.brigadeId = brigadeId;
        this.employeeId = employeeId;
    }

    public BrigadeAndEmployeesRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.brigadeId = Integer.parseInt(list.get(1));
        this.employeeId = Integer.parseInt(list.get(2));
    }

    public int getId() {
        return id;
    }

    public int getBrigadeId() {
        return brigadeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "brigade_id=" + brigadeId + "and employee_id=" + employeeId;
    }

    @Override
    public String getTableName() {
        return "brigade_and_empl";
    }
}
