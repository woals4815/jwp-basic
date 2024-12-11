package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Answer;

import java.sql.ResultSet;
import java.util.List;

public class AnswerDao implements Dao<Answer> {
    @Override
    public void insert(Answer object) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES(?, ?, ?, ?)", object.getWriter(), object.getContents(), object.getCreatedDate(), object.getQuestionId());
    }

    @Override
    public List<Answer> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List<Answer> answers= jdbcTemplate.query("SELECT * FROM ANSWERS", (ResultSet rs) -> new Answer(
                rs.getInt("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("questionId")
        ));
        return answers;
    }

    @Override
    public Answer findById(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.queryForObject("SELECT * FROM ANSWERS where answerId=?", (ResultSet rs) -> new Answer(
                rs.getInt("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("questionId")
        ), id);
    }
}
