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

@ContextConfiguration("/test-dao-config.xml")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {
    @Autowired
    private IUserDAO userDAO;

    @Test
    public void test() {
        User user = new User("1", "2");
        userDAO.saveOrUpdate(user);
        System.out.println(user.getId());
    }
}
