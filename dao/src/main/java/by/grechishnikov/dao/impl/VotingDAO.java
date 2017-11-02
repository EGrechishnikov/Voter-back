package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IVotingDAO;
import by.grechishnikov.entity.Voting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VotingDAO extends BaseDAO<Voting> implements IVotingDAO {
    @Autowired
    public VotingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
