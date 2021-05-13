package domain.usecases.parameterized;

import data.JDBCConnection;
import domain.rows.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRoleIdByUserNameUseCase implements ParamUseCase{

    @Override
    public Object invoke(Object... object) {
        String userName = (String) object[0];

        ResultSet resultSet;
        String sql = "SELECT role_id FROM \"18206_VALKOVA\".US_AND_ROL WHERE user_name='" + userName + "'";

        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            while(true) {
                if (!resultSet.next()) {
                    return null;
                } else {
                    return resultSet.getInt("role_id");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
