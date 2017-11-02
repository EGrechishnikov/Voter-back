package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IVariantDAO;
import by.grechishnikov.entity.Variant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VariantDAO extends BaseDAO<Variant> implements IVariantDAO {
    @Autowired
    public VariantDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
