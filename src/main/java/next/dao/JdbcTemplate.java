package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
    public List query(String sql, RowMapper mapper, PreParedStatementSetter pstmtSetter) throws SQLException {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            pstmtSetter.setValues(ps);

            rs = ps.executeQuery();
            List list = new ArrayList();
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

    public Object queryForObject(String sql, RowMapper mappper, PreParedStatementSetter pstmtSetter) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();

            Object value = null;
            if (rs.next()) {
                value = mappper.mapRow(rs);
            }

            return value;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void update(String sql, PreParedStatementSetter pstmtSetter) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
    public abstract PreparedStatement setValues(PreparedStatement pstmt) throws SQLException;
}
