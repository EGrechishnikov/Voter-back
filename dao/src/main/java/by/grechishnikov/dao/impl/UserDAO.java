package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IUserDAO;
import by.grechishnikov.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User> implements IUserDAO {
    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(String login) {
        return (User) super.getSession().createQuery("FROM User WHERE login = :login").
                setParameter("login", login).uniqueResult();
    }

    @Override
    public boolean isLoginFree(String login) {
        String sql = "SELECT id FROM User WHERE login = :login";
        return super.getSession().createQuery(sql).
                setParameter("login", login).uniqueResult() == null;
    }

    @Override
    public void detachUserFromSession(User user) {
        getSession().detach(user);
    }
}
