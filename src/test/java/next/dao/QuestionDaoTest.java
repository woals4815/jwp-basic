package next.dao;


import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import static org.junit.Assert.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class QuestionDaoTest {

    @Before
    public void setUp() throws Exception {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }


    @Test
    public void testInsert() {
        QuestionDao questionDao = new QuestionDao();
        Question question = new Question(
                "jaemin jeong",
                "hihihi",
                "content",
                new Timestamp(new Date().getTime()),
                0
        );
        questionDao.insert(question);
    }

    @Test
    public void testFindAll() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        assertEquals(8, questions.size());
    }

    @Test
    public void testFindById() {
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findById(1);
        assertEquals("자바지기", question.getWriter());
    }
}