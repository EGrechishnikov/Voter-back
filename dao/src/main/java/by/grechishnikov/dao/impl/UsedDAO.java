package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IUserDAO;
import by.grechishnikov.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsedDAO extends BaseDAO<User> implements IUserDAO {
    @Autowired
    public UsedDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(String login) {
        return (User) super.getSession().createQuery("FROM User WHERE login = :login").
                setParameter("login", login).uniqueResult();
    }
}
