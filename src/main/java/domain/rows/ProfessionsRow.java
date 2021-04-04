package domain.rows;

public class ProfessionsRow implements Row {
    private final int id;
    private final String name;

    public ProfessionsRow(int id, String name) {
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
