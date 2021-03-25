package domain.rows;

public class CustomersRow implements Row {
    private final int id;
    private final String name;

    public CustomersRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
