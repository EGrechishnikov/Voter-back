package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IVotingDAO;
import by.grechishnikov.dto.ChosenVariant;
import by.grechishnikov.entity.Variant;
import by.grechishnikov.entity.Voting;
import by.grechishnikov.service.IVotingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
@Transactional
public class VotingService implements IVotingService {
    private Logger logger = Logger.getLogger(VotingService.class);
    private static final String DEFAULT_IMG_PACKAGE =
            ResourceBundle.getBundle("config").getString("default.package.for.images");
    private static int defaultCountPerPage =
            Integer.parseInt(ResourceBundle.getBundle("config").getString("default.count.per.page"));

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

    @Override
    public List<Voting> getAll(int page) {
        int start = (page - 1) * defaultCountPerPage/* + 1*/;
        logger.warn("GET ALL. PAGE: " + page + ", START: " + start);
        return votingDAO.getAll(start, defaultCountPerPage);
    }

    @Override
    public List<ChosenVariant> getAllVotesForVoting(int votingId) {
        Voting voting = votingDAO.get(votingId);
        List<ChosenVariant> result = new ArrayList<>();
        for(Variant variant : voting.getVariants()) {
            result.add(new ChosenVariant(variant.getId(), variant.getVoteList().size()));
        }
        return result;
    }

    @Override
    public void createVoting(String json, byte[] bytes, String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Voting voting = mapper.readValue(json, Voting.class);
            voting.setClosingDate();
            for(Variant variant : voting.getVariants()) {
                variant.setVoting(voting);
            }
            saveOrUpdate(voting);
            if(bytes.length > 0) {
                saveImage(voting, bytes, fileName);
            }
        } catch (IOException e) {
            logger.error("MAPPING ERROR.", e);
        }
    }

    private void saveImage(Voting voting, byte[] bytes, String fileName) {
        String file = String.format("%s%s-%d-%s", DEFAULT_IMG_PACKAGE,
                voting.getCreator().getLogin(), voting.getId(), fileName);
        logger.warn("SAVE IMAGE TO: " + file);
        try(FileOutputStream output = new FileOutputStream(file)) {
            output.write(bytes);
            output.flush();
            voting.setImageLink(file);
        } catch (IOException e) {
            logger.error("SAVE IMAGE EXCEPTION", e);
        }
    }
}