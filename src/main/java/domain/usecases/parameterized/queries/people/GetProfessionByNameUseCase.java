package domain.usecases.parameterized.queries.people;

import data.JDBCConnection;
import data.tables.people.PeopleAndProfessionsTableImpl;
import domain.usecases.parameterized.ParamUseCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProfessionByNameUseCase implements ParamUseCase {

    public GetProfessionByNameUseCase() {
    }

    @Override
    public Object invoke(Object object) {
        int id = (int) object;
        ResultSet resultSet;
        String sql = "SELECT profession_id FROM " +
                "people_and_professions WHERE people_and_professions.id = " + id;
        System.out.println(sql);
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            } else {
                return resultSet.getInt("profession_id");
            }
        } catch (SQLException throwables) {
            return null;
        }
    }
}
