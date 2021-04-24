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
    private final Date startDate;
    private final Date endDate;
    private final int brigadeId;

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    public SchedulesRow(int id, int typesOfWorkId, int objectId, String startDate, String endDate, int brigadeId) throws ParseException {
        this.id = id;
        this.typesOfWorkId = typesOfWorkId;
        this.objectId = objectId;

        this.startDate = format.parse(startDate);
        this.endDate = format.parse(endDate);

        this.brigadeId = brigadeId;
    }

    public SchedulesRow(ArrayList<String> list) throws ParseException {
        this.id = Integer.parseInt(list.get(0));
        this.typesOfWorkId = Integer.parseInt(list.get(1));
        this.objectId = Integer.parseInt(list.get(2));
        this.startDate = format.parse(list.get(3));
        this.endDate = format.parse(list.get(4));
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getBrigadeId() {
        return brigadeId;
    }

    @Override
    public String getTableName() {
        return "schedules";
    }

    @Override
    public String toString() {
        return "types_of_work_id=" + typesOfWorkId +
                "and object_id=" + objectId +
                "and start_date=" + startDate +
                "and end_date=" + endDate +
                "and brigade_id=" + brigadeId;
    }
}
