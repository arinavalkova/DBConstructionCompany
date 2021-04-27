package domain.rows.queries;

public class ObjectAndScheduleRow {

    private final String objectName;
    private final String typeOfWorkName;
    private final String startDate;
    private final String endDate;

    public ObjectAndScheduleRow(String objectName, String typeOfWorkName, String startDate, String endDate) {
        this.objectName = objectName;
        this.typeOfWorkName = typeOfWorkName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getTypeOfWorkName() {
        return typeOfWorkName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
