package by.grechishnikov;

import by.grechishnikov.entity.User;
import by.grechishnikov.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration("/service-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
public class ServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void test() {
        int size = userService.getAll().size();
        User user = new User("newTestUser", "password");
        userService.saveOrUpdate(user);
        int id = user.getId();
        assertEquals(user, userService.get("newTestUser"));
        assertEquals(++size, userService.getAll().size());
        user.setPassword("pass");
        userService.saveOrUpdate(user);
        assertEquals(id, user.getId());
        userService.delete(user);
        assertEquals(--size, userService.getAll().size());
        assertNull(userService.get(user.getLogin()));
    }
}
