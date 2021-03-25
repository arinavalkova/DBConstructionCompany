package domain.usecases.simple;

import java.sql.SQLException;

public interface UseCase {
    Object invoke() throws SQLException;
}
