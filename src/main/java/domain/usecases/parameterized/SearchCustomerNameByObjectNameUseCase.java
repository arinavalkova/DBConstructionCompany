package domain.usecases.parameterized;

import data.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchCustomerNameByObjectNameUseCase implements ParamUseCase {

    @Override
    public Object invoke(Object object) {
        String sql =
                "SELECT customer.name FROM customer, objects WHERE customer.id = objects.id and objects.name = '" +
                        object.toString() + "'";
        ResultSet resultSet;
        String answer = null;
        PreparedStatement preStatement = null;
        try {
            preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            while (true) {
                if (!resultSet.next()) break;
                answer = resultSet.getString("name");
            }
        } catch (SQLException throwables) {
            return null;
        }
        return answer;
    }
}
