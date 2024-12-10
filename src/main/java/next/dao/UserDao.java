package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    private final JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {
        @Override
        public PreparedStatement setValues(User user, PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            return pstmt;
        }

        @Override
        public String createQuery() {
            return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        }
    };

    private final JdbcTemplate updateJdbcTemplate = new JdbcTemplate() {
        @Override
        public PreparedStatement setValues(User user, PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
            return pstmt;
        }

        @Override
        public String createQuery() {
            String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
            return sql;
        }
    };


    public void insert(User user) throws SQLException {
        insertJdbcTemplate.update(user);
    }

    public void update(User user) throws SQLException {
        updateJdbcTemplate.update(user);
    }

    public List<User> findAll() throws SQLException {
        // TODO 구현 필요함.
        Connection con = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM USERS";
            rs = con.prepareStatement(sql).executeQuery();
            List<User> users = new ArrayList<>();
            while(rs.next()) {
                User newUser = new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                users.add(newUser);
            }
            return users;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
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
}
