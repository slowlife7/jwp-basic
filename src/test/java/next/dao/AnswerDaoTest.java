package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Answer;
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

public class AnswerDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void findByQuestionId() {
        long questionId = 7L;
        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.findByQuestionId(questionId);

        Assert.assertEquals(answers.size(), 2);
    }

    @Test
    @Transactional
    public void updateAnswerByQuestionId() {
        long questionId = 7L;
        AnswerDao answerDao = new AnswerDao();
        long count = answerDao.countByQuestionId(questionId);
        Assert.assertEquals(count, 2);
    }
}
