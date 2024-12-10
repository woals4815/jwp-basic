package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public void insert(User user) throws SQLException {
        JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {
            @Override
            public PreparedStatement setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
                return pstmt;
            }
        };
        insertJdbcTemplate.update("INSERT INTO USERS VALUES (?,?,?,?)");
    }

    public void update(User user) throws SQLException {
        JdbcTemplate updateJdbcTemplate = new JdbcTemplate() {
            @Override
            public PreparedStatement setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
                return pstmt;
            }
        };
        updateJdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=? WHERE userId=?");
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {
            @Override
            public PreparedStatement setValues(PreparedStatement pstmt) throws SQLException {
                return null;
            }
        };

        RowMapper mapper = new RowMapper() {
            @Override
            public List<User> mapRow(ResultSet rs) throws SQLException {
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User newUser = new User(
                            rs.getString("userId"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                    users.add(newUser);
                }
                return users;
            }
        };

        List<User> users = selectJdbcTemplate.query("SELECT * FROM USERS", mapper);
        return users;
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {
            @Override
            public PreparedStatement setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
                return pstmt;
            }
        };
        RowMapper mapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            }
        };
        Object result = selectJdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?", mapper);
        return (User) result;
    }
}
