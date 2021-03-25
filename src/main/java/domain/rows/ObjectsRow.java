package domain.rows;

public class ObjectsRow implements Row {
    private final int id;
    private final String name;
    private final int categoryId;
    private final int customerId;

    public ObjectsRow(int id, String name, int categoryId, int customerId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
