package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IVotingDAO;
import by.grechishnikov.entity.Variant;
import by.grechishnikov.entity.Voting;
import by.grechishnikov.service.IVotingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ResourceBundle;

@Service
@Transactional
public class VotingService implements IVotingService {
    private Logger logger = Logger.getLogger(VotingService.class);
    private static int defaultCountPerPage =
            Integer.parseInt(ResourceBundle.getBundle("config").getString("default.count.per.page"));

    private IVotingDAO votingDAO;

    @Autowired
    public VotingService(IVotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }

    @Override
    public void saveOrUpdate(Voting voting) {
        voting.setClosingDate();
        for(Variant variant : voting.getVariants()) {
            variant.setVoting(voting);
        }
        logger.warn("VOTING: " + voting);
        logger.warn("VARS: " + voting.getVariants());
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

    @Override
    public List<Voting> getAll(int page) {
        int start = (page - 1) * defaultCountPerPage/* + 1*/;
        logger.warn("GET ALL. PAGE: " + page + ", START: " + start);
        return votingDAO.getAll(start, defaultCountPerPage);
    }
}