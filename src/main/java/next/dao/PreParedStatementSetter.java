package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreParedStatementSetter {
    public PreparedStatement setValues(PreparedStatement preparedStatement) throws SQLException;
}
