package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IVoteDAO;
import by.grechishnikov.dto.MyVote;
import by.grechishnikov.entity.Vote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class VoteDAO extends BaseDAO<Vote> implements IVoteDAO {
    @Autowired
    public VoteDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<MyVote> getAllVotesForUser(int userId) {
        String sql = "SELECT variant.voting.id, variant.id FROM Vote WHERE voter.id = :id";
        List<Object[]> answer =
                super.getSession().createQuery(sql).setParameter("id", userId).list();
        List<MyVote> result = new ArrayList<>();
        for(Object[] arr : answer) {
            result.add(new MyVote((int) arr[0], (int) arr[1]));
        }
        return result;
    }
}
