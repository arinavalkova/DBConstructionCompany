package domain.usecases.nonParameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.ManagementAndBossRow;
import domain.rows.queries.NameAndProfessionRow;
import domain.usecases.nonParameterized.NonParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetManagementBossQueryUseCase implements NonParamUseCase {

    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetManagementBossQueryUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke() {
        String sql = "select M.NAME as MANAGEMENT_NAME, PAP.NAME AS BOSS_NAME FROM \"18206_VALKOVA\".MANAG_AND_SECT " +
                "inner join \"18206_VALKOVA\".SECTOR_AND_BOSS SAB on \"18206_VALKOVA\".MANAG_AND_SECT.SECTOR_ID " +
                "= SAB.SECTOR_ID\n" +
                "    inner join \"18206_VALKOVA\".MANAGEMENTS M on M.ID = \"18206_VALKOVA\".MANAG_AND_SECT.MANAGEMENT_ID\n" +
                "inner join \"18206_VALKOVA\".PEOPLE_AND_PROF PAP on PAP.ID = SAB.BOSS_ID";
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<ManagementAndBossRow> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new ManagementAndBossRow(
                        resultSet.getString("management_name"),
                        resultSet.getString("boss_name"))
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
