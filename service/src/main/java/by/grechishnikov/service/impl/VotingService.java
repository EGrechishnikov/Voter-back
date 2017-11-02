package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IVotingDAO;
import by.grechishnikov.entity.Voting;
import by.grechishnikov.service.IVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VotingService implements IVotingService {
    private IVotingDAO votingDAO;

    @Autowired
    public VotingService(IVotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }

    @Override
    public void saveOrUpdate(Voting voting) {
        votingDAO.saveOrUpdate(voting);
    }

    @Override
    public Voting get(int id) {
        return votingDAO.get(id);
    }

    @Override
    public void delete(Voting voting) {
        votingDAO.delete(voting);
    }

    @Override
    public List<Voting> getAll() {
        return votingDAO.getAll();
    }
}
