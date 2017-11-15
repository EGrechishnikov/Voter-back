package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IVotingDAO;
import by.grechishnikov.entity.Voting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class VotingDAO extends BaseDAO<Voting> implements IVotingDAO {
    @Autowired
    public VotingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Voting> getAll(int start, int count) {
        String sql = "FROM Voting ORDER BY closingDate";
        return getSession().createQuery(sql).setFirstResult(start).setMaxResults(count).list();
    }
}
