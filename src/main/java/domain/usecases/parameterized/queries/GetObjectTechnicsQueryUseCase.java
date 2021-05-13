package domain.usecases.parameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.ManagementsAndTechnicsRow;
import domain.usecases.parameterized.ParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetObjectTechnicsQueryUseCase implements ParamUseCase {

    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetObjectTechnicsQueryUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke(Object... object) {
        Row row = (Row) object[0];
        int objectId = row.getId();
        String firstDate = (String) object[1];
        String secondDate = (String) object[2];

        String sql = "SELECT T.NAME as NAME, SUM(FT.COUNT) as COUNT\n" +
                "FROM OBJECTS\n" +
                "         inner join SCHEDULES S on OBJECTS.ID = S.OBJECT_ID\n" +
                "         inner join FACT_TECHN FT on S.ID = FT.WORK_ID\n" +
                "         inner join TECHNIQUES T on T.ID = FT.TECHNICS_ID\n" +
                "WHERE S.START_DATE >= TO_DATE('" + firstDate + "', 'YYYY-MM-DD')\n" +
                "  and S.END_DATE <= TO_DATE('" + secondDate + "', 'YYYY-MM-DD')\n" +
                "  and OBJECT_ID =" + objectId + "\n" +
                "GROUP BY T.NAME";
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<ManagementsAndTechnicsRow> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new ManagementsAndTechnicsRow(
                        resultSet.getString("name"),
                        resultSet.getInt("count")
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
