package next.dao;
import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
    public List query(String sql) throws SQLException {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            rs = con.prepareStatement(sql).executeQuery();
            List list = new ArrayList();
            while(rs.next()) {
                list.add(mapRow(rs));
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

    public Object queryForObject(String sql, String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);
            rs = pstmt.executeQuery();

            Object value = null;
            if (rs.next()) {
                value = mapRow(rs);
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

    public abstract PreparedStatement setValues( PreparedStatement pstmt) throws SQLException;

    public abstract Object mapRow(ResultSet rs) throws SQLException;

}
