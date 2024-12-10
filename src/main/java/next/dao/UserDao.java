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
        // TODO 구현 필요함.
        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate();
        List<User> users =  selectJdbcTemplate.query();
        return users;
    }

    public User findByUserId(String userId) throws SQLException {
       SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate();
        User user = selectJdbcTemplate.queryForObject(userId);
        return user;
    }
}
