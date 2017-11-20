package by.grechishnikov;

import by.grechishnikov.dao.IUserDAO;
import by.grechishnikov.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@ContextConfiguration("/test-dao-config.xml")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class DAOTest {
    @Autowired
    private IUserDAO userDAO;

    @Test
    public void test() {
        int size = userDAO.getAll().size();
        User user = new User("user3", "password", "3");
        userDAO.saveOrUpdate(user);
        int id = user.getId();
        assertEquals(user, userDAO.get("user3"));
        assertEquals(++size, userDAO.getAll().size());
        user.setPassword("pass");
        userDAO.saveOrUpdate(user);
        assertEquals(id, user.getId());
        userDAO.delete(user);
        assertEquals(--size, userDAO.getAll().size());
        assertNull(userDAO.get(user.getLogin()));
    }
}
