package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IVoteDAO;
import by.grechishnikov.entity.Vote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDAO extends BaseDAO<Vote> implements IVoteDAO {
    @Autowired
    public VoteDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
