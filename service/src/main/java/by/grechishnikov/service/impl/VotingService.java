package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IVotingDAO;
import by.grechishnikov.dto.ChosenVariantDTO;
import by.grechishnikov.dto.VotingsDTO;
import by.grechishnikov.entity.Variant;
import by.grechishnikov.entity.Voting;
import by.grechishnikov.service.IVotingService;
import by.grechishnikov.utils.EncodingUtils;
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
    private static Logger logger = Logger.getLogger(VotingService.class);
    private static final String IMG_PACKAGE =
            ResourceBundle.getBundle("config").getString("package.for.images");
    private static final String IMG_URL =
            ResourceBundle.getBundle("config").getString("image.url.package");
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
    public VotingsDTO getAll(int page) {
        int start = (page - 1) * defaultCountPerPage;
        long recordsCount = votingDAO.getCountOfAllVotings();
        List<Voting> votingList = votingDAO.getAll(start, defaultCountPerPage);
        return new VotingsDTO(votingList, recordsCount);
    }

    @Override
    public List<ChosenVariantDTO> getAllVotesForVoting(int votingId) {
        Voting voting = votingDAO.get(votingId);
        List<ChosenVariantDTO> result = new ArrayList<>();
        for (Variant variant : voting.getVariants()) {
            result.add(new ChosenVariantDTO(variant.getId(), variant.getVoteList().size()));
        }
        return result;
    }

    @Override
    public void createVoting(Voting voting) {
        try {
            voting.setClosingDate();
            for (Variant variant : voting.getVariants()) {
                variant.setVoting(voting);
            }
            saveOrUpdate(voting);
        } catch (Exception e) {
            logger.error("MAPPING ERROR.", e);
        }
    }

    @Override
    public void createVoting(String JSON) {
        try {
            Voting voting = unMarshal(JSON);
            createVoting(voting);
        } catch (Exception e) {
            logger.error("MAPPING ERROR.", e);
        }
    }

    @Override
    public void createVoting(String JSON, byte[] bytes, String fileName) {
        try {
            Voting voting = unMarshal(JSON);
            createVoting(voting);
            if (bytes.length > 0 && checkImageFileType(fileName)) {
                saveImage(voting, bytes, fileName);
            }
        } catch (Exception e) {
            logger.error("MAPPING WITH IMAGE ERROR.", e);
        }
    }

    /**
     * Save image to file system
     *
     * @param voting   - current voting
     * @param bytes    - image
     * @param fileName - image name
     */
    private void saveImage(Voting voting, byte[] bytes, String fileName) {
        String newFileName = String.format("%s-%d-%s",
                voting.getCreator().getLogin(), voting.getId(), fileName);
        String path = IMG_PACKAGE + newFileName;
        String url = IMG_URL + newFileName;
        logger.warn("SAVE IMAGE TO: " + path);
        logger.warn("URL: " + url);
        try (FileOutputStream output = new FileOutputStream(path)) {
            output.write(bytes);
            output.flush();
            voting.setImageLink(url);
        } catch (IOException e) {
            logger.error("SAVE IMAGE EXCEPTION", e);
        }
    }

    /**
     * Check image file type.
     *
     * @param fileName - image file name
     * @return - true if file type is jpg, png, bmp or gif
     */
    private boolean checkImageFileType(String fileName) {
        return (fileName.contains(".jpg") || fileName.contains(".jpeg") ||
                fileName.contains("png") || fileName.contains(".bmp") ||
                fileName.contains(".gif"));
    }

    /**
     * Convert JSON to Voting record
     *
     * @param JSON - JSON string with record
     * @return - Voting
     */
    private static Voting unMarshal(String JSON) throws Exception {
        JSON = EncodingUtils.encodeFromISOToUTF(JSON);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(JSON, Voting.class);
    }
}