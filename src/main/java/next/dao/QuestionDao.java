package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Question;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao implements Dao<Question> {
    @Override
    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)", question.getWriter(), question.getTitle(), question.getContents(), question.getCreatedDate(), 0);
    }

    @Override
    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.query("SELECT * FROM QUESTIONS", (ResultSet rs) -> {
            int id = rs.getInt("questionId");
            String writer = rs.getString("writer");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            Timestamp createdDate = rs.getTimestamp("createdDate");
            int countOfAnswer = rs.getInt("countOfAnswer");
            return new Question(
                    id,
                    writer,
                    title,
                    contents,
                    createdDate,
                    countOfAnswer
            );
        });
    }

    @Override
    public Question findById(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.queryForObject("SELECT * FROM QUESTIONS WHERE questionId=?", (ResultSet rs) -> {
            String writer = rs.getString("writer");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            Timestamp createdDate = rs.getTimestamp("createdDate");
            int countOfAnswer = rs.getInt("countOfAnswer");
            return new Question(
                    writer,
                    title,
                    contents,
                    createdDate,
                    countOfAnswer
            );
        }, id);
    }
}
