package domain.rows.queries;

public class BrigadeAndEmployeeRow {

    private final String brigadeName;
    private final String employeeName;

    public BrigadeAndEmployeeRow(String brigadeName, String employeeName) {
        this.brigadeName = brigadeName;
        this.employeeName = employeeName;
    }

    public String getBrigadeName() {
        return brigadeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
