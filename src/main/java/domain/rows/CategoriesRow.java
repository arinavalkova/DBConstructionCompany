package domain.rows;

public class CategoriesRow implements Row {
    private final int id;
    private final String name;

    public CategoriesRow(int id, String name) {
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
