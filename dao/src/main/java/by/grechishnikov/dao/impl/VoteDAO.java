package by.grechishnikov.dao.impl;

import by.grechishnikov.dao.IVoteDAO;
import by.grechishnikov.dto.MyVoteDTO;
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
    public List<MyVoteDTO> getAllVotesForUser(int userId) {
        String sql = "SELECT variant.voting.id, variant.id FROM Vote WHERE voter.id = :id";
        List<Object[]> answer =
                getSession().createQuery(sql).setParameter("id", userId).list();
        List<MyVoteDTO> result = new ArrayList<>();
        for(Object[] arr : answer) {
            result.add(new MyVoteDTO((int) arr[0], (int) arr[1]));
        }
        return result;
    }
}
