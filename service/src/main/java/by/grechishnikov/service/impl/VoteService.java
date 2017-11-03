package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IVoteDAO;
import by.grechishnikov.entity.Vote;
import by.grechishnikov.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoteService implements IVoteService {
    private IVoteDAO voteDAO;

    @Autowired
    public VoteService(IVoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    @Override
    public void saveOrUpdate(Vote vote) {
        voteDAO.saveOrUpdate(vote);
    }

    @Override
    public Vote get(int id) {
        return voteDAO.get(id);
    }

    @Override
    public void delete(Vote vote) {
        voteDAO.delete(vote);
    }

    @Override
    public List<Vote> getAll() {
        return voteDAO.getAll();
    }
}
