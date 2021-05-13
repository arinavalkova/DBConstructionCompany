package domain.usecases.nonParameterized.queries;

import data.Coder;
import data.JDBCConnection;
import domain.DataReceiver;
import domain.rows.Row;
import domain.rows.queries.ManagementAndBossRow;
import domain.rows.queries.UserAndRoleRow;
import domain.usecases.nonParameterized.NonParamUseCase;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetUsersAndRolesUseCase implements NonParamUseCase {

    private final DataReceiver dataReceiver;
    private final Property<ObservableList<Object>> property;

    public GetUsersAndRolesUseCase(DataReceiver dataReceiver, Property<ObservableList<Object>> property) {
        this.dataReceiver = dataReceiver;
        this.property = property;
    }

    @Override
    public Object invoke() {
        String sql = "SELECT USER_NAME,PASSWORD, ROLE_ID, NAME AS ROLE_NAME\n" +
                "FROM US_AND_ROL\n" +
                "         inner join ROLES R on US_AND_ROL.ROLE_ID = R.ID";
        ResultSet resultSet;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
        } catch (SQLException throwables) {
            return null;
        }
        ArrayList<UserAndRoleRow> rowArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                rowArrayList.add(new UserAndRoleRow(
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name"))
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
