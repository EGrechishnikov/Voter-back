package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IBaseDAO;
import by.grechishnikov.entity.Bean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class BaseDAO<T extends Bean> implements IBaseDAO<T> {
    private SessionFactory sessionFactory;

    BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
        t.setId((Integer) getSession().getIdentifier(t));
    }

    public T get(int id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public List<T> getAll() {
        return getSession().createQuery("FROM " + getPersistentClass().getSimpleName()).list();
    }

    private Class getPersistentClass() {
        return (Class<Bean>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
