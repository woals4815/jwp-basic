package next.dao;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;
import next.model.User;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
    public <T> List<T> query(String sql, RowMapper<T> mapper, PreParedStatementSetter pstmtSetter) throws SQLException {
        Connection con = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            pstmtSetter.setValues(ps);

            rs = ps.executeQuery();
            List<T> list = new ArrayList();
            while(rs.next()) {
                list.add(mapper.mapRow(rs));
            }
            return list;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> mappper, PreParedStatementSetter pstmtSetter) throws DataAccessException {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql))  {
            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();

            T value = null;

            if (rs.next()) {
                value = mappper.mapRow(rs);
            }

            return value;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            if (rs != null) {
                try {rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException(e);
                }

            }
        }

    }

    public void update(String sql, PreParedStatementSetter pstmtSetter) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(String sql, Object... values) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
