package domain.rows.shedule;

import domain.rows.Row;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SchedulesRow implements Row {

    private final int id;
    private final int typesOfWorkId;
    private final int objectId;
    private final String startDate;
    private final String endDate;
    private final int brigadeId;

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    public SchedulesRow(int id, int typesOfWorkId, int objectId, String startDate, String endDate, int brigadeId) {
        this.id = id;
        this.typesOfWorkId = typesOfWorkId;
        this.objectId = objectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.brigadeId = brigadeId;
    }

    public SchedulesRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.typesOfWorkId = Integer.parseInt(list.get(1));
        this.objectId = Integer.parseInt(list.get(2));
        this.startDate = list.get(3);
        this.endDate = list.get(4);
        this.brigadeId = Integer.parseInt(list.get(5));
    }

    public int getId() {
        return id;
    }

    public int getTypesOfWorkId() {
        return typesOfWorkId;
    }

    public int getObjectId() {
        return objectId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getBrigadeId() {
        return brigadeId;
    }

    @Override
    public String getTableName() {
        return "\"18206_VALKOVA\".schedules";
    }

    @Override
    public String toString() {
        return "type_of_work_id=" + typesOfWorkId +
                "and object_id=" + objectId +
                "and start_date=" + startDate +
                "and end_date=" + endDate +
                "and brigade_id=" + brigadeId;
    }
}
