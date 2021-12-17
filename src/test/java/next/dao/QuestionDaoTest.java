package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void findByUserId() {
        long questionId = 1L;
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(questionId);

        Assert.assertEquals(question.getQuestionId(), questionId);
        Assert.assertEquals(question.getWriter(), "자바지기");
    }

    @Test
    public void findAll() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();

        Assert.assertEquals(8, questions.size());
    }

    @Test
    @Transactional
    public void insert() {
        Question question = new Question(9L, "Test", "haha",
                "zzzz", new Date(), 0);

        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);

        Question expected = questionDao.findByQuestionId(9L);
        Assert.assertEquals(question.getQuestionId(), expected.getQuestionId());
    }
}
