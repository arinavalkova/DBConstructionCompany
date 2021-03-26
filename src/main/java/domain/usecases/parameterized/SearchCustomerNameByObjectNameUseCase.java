package domain.usecases.parameterized;

import data.JDBCConnection;
import data.tables.CustomersTableImpl;
import data.tables.ObjectsTableImpl;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;

public class SearchCustomerNameByObjectNameUseCase implements ParamUseCase {

    private final JDBCConnection jdbcConnection;

    public SearchCustomerNameByObjectNameUseCase(JDBCConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    @Override
    public Object invoke(Object object) {
        String sql =
                "SELECT customer.name FROM customer, objects WHERE customer.id = objects.id and objects.name = '" +
                        object.toString() + "'";
        ResultSet resultSet;
        String answer = null;
        PreparedStatement preStatement = null;
        try {
            preStatement = jdbcConnection.getConnection().prepareStatement(sql);
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
