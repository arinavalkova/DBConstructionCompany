package domain.rows.queries;

public class NameAndProfessionRow {

    private final String name;
    private final String profession;

    public NameAndProfessionRow(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
