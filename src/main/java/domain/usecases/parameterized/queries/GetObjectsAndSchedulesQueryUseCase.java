package domain.usecases.parameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.NameAndProfessionRow;
import domain.rows.queries.ObjectAndScheduleRow;
import domain.usecases.parameterized.ParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetObjectsAndSchedulesQueryUseCase implements ParamUseCase {

    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetObjectsAndSchedulesQueryUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke(Object... object) {
        String sql = "SELECT O.NAME as OBJECT_NAME, TOJ.NAME AS TOJ_NAME, S.START_DATE AS START_DATE, S.END_DATE AS END_DATE\n" +
                "FROM \"18206_VALKOVA\".MANAGEMENTS\n" +
                "         inner join \"18206_VALKOVA\".MANAG_AND_SECT on \"18206_VALKOVA\".MANAGEMENTS.ID " +
                "= \"18206_VALKOVA\".MANAG_AND_SECT.MANAGEMENT_ID\n" +
                "         inner join \"18206_VALKOVA\".OBJECTS O on \"18206_VALKOVA\".MANAG_AND_SECT.SECTOR_ID = O.SECTOR_ID\n" +
                "         inner join \"18206_VALKOVA\".SCHEDULES S on O.ID = S.OBJECT_ID\n" +
                "         inner join \"18206_VALKOVA\".TYPES_OF_JOBS TOJ on S.TYPE_OF_WORK_ID = TOJ.ID\n" +
                "WHERE MANAGEMENT_ID=" + object[0].toString();
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<ObjectAndScheduleRow> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new ObjectAndScheduleRow(
                        resultSet.getString("object_name"),
                        resultSet.getString("toj_name"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date")
                ));
            } catch (SQLException throwables) {
                dataReceiver.onDataError(Coder.encodingRUS("Нет результата"), property);
                return null;
            }
        }
        dataReceiver.onDataSuccess(rowArrayList, property);
        return null;
    }
}
