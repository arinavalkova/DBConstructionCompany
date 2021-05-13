package domain.usecases.parameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.BrigadeAndEmployeeRow;
import domain.rows.queries.ManagementsAndTechnicsRow;
import domain.usecases.parameterized.ParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetManagementTechnicsQueryUseCase implements ParamUseCase {


    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetManagementTechnicsQueryUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke(Object... object) {
        String sql = "SELECT T.NAME as NAME, SUM(E.COUNT) as COUNT\n" +
                "FROM MANAGEMENTS\n" +
                "         inner join MANAG_AND_SECT MAS on MANAGEMENTS.ID = MAS.MANAGEMENT_ID\n" +
                "         inner join OBJECTS O on MAS.SECTOR_ID = O.SECTOR_ID\n" +
                "         inner join ESTIMATE E on O.ID = E.OBJECT_ID\n" +
                "         inner join TECHNIQUES T on T.ID = E.TECHNICS_ID\n" +
                "WHERE MANAGEMENT_ID=\n" + object[0].toString() +
                " GROUP BY T.NAME";
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
