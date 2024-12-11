package next.dao;

import core.jdbc.ConnectionManager;
import junit.framework.TestCase;
import next.model.Answer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.Timestamp;
import java.util.List;

public class AnswerDaoTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void testInsert() {
        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(new Answer(
                "writer",
                "contents",
                new Timestamp(System.currentTimeMillis()),
                1
        ));
    }

    @Test
    public void testFindAll() {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.findAll();
        assertEquals(5, answers.size());
    }

    public void testFindById() {
        AnswerDao answerDao = new AnswerDao();
        Answer answer = answerDao.findById(1);
        assertEquals("eungju", answer.getWriter());
    }
}