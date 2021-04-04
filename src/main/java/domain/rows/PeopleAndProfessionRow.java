package domain.rows;

public class PeopleAndProfessionRow implements Row {

    private final int id;
    private final String name;
    private final int professionId;

    public PeopleAndProfessionRow(int id, String name, int professionId) {
        this.id = id;
        this.name = name;
        this.professionId = professionId;
    }

    public String getName() {
        return name;
    }

    public int getProfessionId() {
        return professionId;
    }

    public int getId() {
        return id;
    }
}
