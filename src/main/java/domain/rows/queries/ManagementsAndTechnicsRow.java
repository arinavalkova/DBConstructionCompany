package domain.rows.queries;

public class ManagementsAndTechnicsRow {

    private final String name;
    private final int count;

    public ManagementsAndTechnicsRow(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
