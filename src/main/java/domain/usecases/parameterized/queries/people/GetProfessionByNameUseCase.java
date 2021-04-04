package domain.usecases.parameterized.queries.people;

import data.JDBCConnection;
import data.tables.people.PeopleAndProfessionsTableImpl;
import domain.usecases.parameterized.ParamUseCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProfessionByNameUseCase implements ParamUseCase {

    private final PeopleAndProfessionsTableImpl peopleAndProfessionsTable;

    public GetProfessionByNameUseCase(PeopleAndProfessionsTableImpl peopleAndProfessionsTable) {
        this.peopleAndProfessionsTable = peopleAndProfessionsTable;
    }

    @Override
    public Object invoke(Object object) {
        int id = (int) object;
        ResultSet resultSet;
        String sql = "SELECT people_and_profession.name FROM people_and_profession WHERE id = " + id;
        try {
            PreparedStatement preStatement = JDBCConnection.getConnection().prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            } else {
                return resultSet.getString("name");
            }
        } catch (SQLException throwables) {
            return null;
        }
    }
}
