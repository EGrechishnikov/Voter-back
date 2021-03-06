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

    @Override
    public User get(String login) {
        String sql = "FROM User WHERE login = :login";
        return (User) getSession().createQuery(sql).setParameter("login", login).uniqueResult();
    }

    @Override
    public boolean isLoginFree(String login) {
        String sql = "SELECT id FROM User WHERE login = :login";
        return getSession().createQuery(sql).setParameter("login", login).uniqueResult() == null;
    }
}
