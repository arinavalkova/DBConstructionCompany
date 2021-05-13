package domain.usecases.parameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.NameAndProfessionRow;
import domain.usecases.parameterized.ParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetBossOfManagementQueryUseCase implements ParamUseCase {

    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetBossOfManagementQueryUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke(Object... object) {
        String sql = "select PAP.NAME as NAME, P.NAME as PROFESSION FROM\n" +
                "\"18206_VALKOVA\".MANAG_AND_SECT inner join \"18206_VALKOVA\".SECTOR_AND_BOSS SAB " +
                "on \"18206_VALKOVA\".MANAG_AND_SECT.SECTOR_ID = SAB.SECTOR_ID\n" +
                "inner join \"18206_VALKOVA\".PEOPLE_AND_PROF PAP on PAP.ID = " +
                "SAB.BOSS_ID inner join \"18206_VALKOVA\".PROFESSIONS P on P.ID = PAP.PROFESSION_ID\n" +
                "WHERE MANAGEMENT_ID=" + object[0].toString();
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<NameAndProfessionRow> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new NameAndProfessionRow(
                        resultSet.getString("name"),
                        resultSet.getString("profession"))
                );
            } catch (SQLException throwables) {
                dataReceiver.onDataError(Coder.encodingRUS("Нет результата"), property);
                return null;
            }
        }
        dataReceiver.onDataSuccess(rowArrayList, property);
        return null;
    }
}
