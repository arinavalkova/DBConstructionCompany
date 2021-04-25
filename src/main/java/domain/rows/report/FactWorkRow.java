package domain.rows.report;

import domain.rows.Row;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FactWorkRow implements Row {

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    private final int id;
    private final int workId;
    private final String startDate;
    private final String endDate;

    public FactWorkRow(int id, int workId, String startDate, String endDate) {
        this.id = id;
        this.workId = workId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FactWorkRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.workId = Integer.parseInt(list.get(1));
        this.startDate = list.get(2);
        this.endDate = list.get(3);
    }

    public DateFormat getFormat() {
        return format;
    }

    public int getId() {
        return id;
    }

    public int getWorkId() {
        return workId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getTableName() {
        return "fact_work";
    }

    @Override
    public String toString() {
        return "work_id=" + workId +
                "and start_date=" + startDate +
                "and end_date=" + endDate;
    }
}
