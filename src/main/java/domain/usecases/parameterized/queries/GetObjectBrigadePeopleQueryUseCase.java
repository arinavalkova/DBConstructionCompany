package domain.usecases.parameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.BrigadeAndEmployeeRow;
import domain.rows.queries.ObjectAndScheduleRow;
import domain.usecases.parameterized.ParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetObjectBrigadePeopleQueryUseCase implements ParamUseCase {

    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetObjectBrigadePeopleQueryUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke(Object... object) {
        String sql = "SELECT distinct BAM.NAME as BRIGADE_NAME, PAP.NAME AS EMPLOYEE_NAME\n" +
                "FROM \"18206_VALKOVA\".OBJECTS\n" +
                "         inner join \"18206_VALKOVA\".SCHEDULES S on \"18206_VALKOVA\".OBJECTS.ID = S.OBJECT_ID\n" +
                "         inner join \"18206_VALKOVA\".BRIGADE_AND_EMPL BAE on S.BRIGADE_ID = BAE.BRIGADE_ID\n" +
                "         inner join \"18206_VALKOVA\".BRIGADE_AND_MAN BAM on S.BRIGADE_ID = BAM.ID\n" +
                "         inner join \"18206_VALKOVA\".PEOPLE_AND_PROF PAP on PAP.ID = BAE.EMPLOYEE_ID\n" +
                "WHERE OBJECT_ID=" + object[0].toString();
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<BrigadeAndEmployeeRow> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new BrigadeAndEmployeeRow(
                        resultSet.getString("brigade_name"),
                        resultSet.getString("employee_name")
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
