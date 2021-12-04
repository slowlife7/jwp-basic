package next.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.User;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    /*@Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);
        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);

        expected.update(new User("userId", "password2", "name2", "sanjigi@email.com"));
        userDao.update(expected);
        actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }*/

    @Test
    @Transactional
    public void findAll() throws Exception {
        UserDao userDao = new UserDao();
        User expected = new User("test1", "12341", "haha1", "javajigi@email.com");
        userDao.insert(expected);

        User expected1 = new User("test2", "12341", "haha1", "javajigi@email.com");
        userDao.insert(expected1);

        List<User> users = userDao.findAll();

        Optional<User> find = users.stream().filter(user -> user.getUserId().equals("test1")).findAny();
        assertTrue(find.isPresent());

        assertEquals(3, users.size());
    }
}