package domain.usecases.parameterized.queries;

import data.JDBCConnection;
import domain.AnswerReceiver;
import domain.DataBaseRepository;
import domain.rows.Row;
import domain.usecases.parameterized.ParamUseCase;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetIdByFiledUseCase implements ParamUseCase {

    public GetIdByFiledUseCase() {
    }

    @Override
    public Object invoke(Object... object) {

        Row row = (Row) object[0];

        ResultSet resultSet;
        String sql = "SELECT id FROM " + row.getTableName() + " WHERE " + row;

        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            while(true) {
                if (!resultSet.next()) {
                    return null;
                } else {
                    return resultSet.getString("id");
                }
            }
        } catch (SQLException throwables) {
            return null;
        }
    }
}
