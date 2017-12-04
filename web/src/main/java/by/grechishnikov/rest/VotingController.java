package by.grechishnikov.rest;

import by.grechishnikov.dto.ChosenVariantDTO;
import by.grechishnikov.dto.VotingsDTO;
import by.grechishnikov.service.IVotingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Rest controller for Voting entity
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/voting")
public class VotingController {
    private static Logger logger = Logger.getLogger(VotingController.class);
    private IVotingService votingService;

    @Autowired
    public VotingController(IVotingService votingService) {
        this.votingService = votingService;
    }

    /**
     * Get voting for pagination
     *
     * @param page - number of page
     * @return - list
     */
    @RequestMapping(value = "/all/{page}", method = RequestMethod.GET)
    public ResponseEntity<VotingsDTO> getAll(@PathVariable int page) {
        try {
            return new ResponseEntity<>(votingService.getAll(page), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("VOTING EXCEPTION. GET ALL.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add a new voting to DB
     *
     * @param voting   - voting
     * @param file     - image
     * @param fileName - image name
     * @return - http status
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addVoting(@RequestParam(value = "voting") String voting,
                                    @RequestParam(value = "file", required = false) MultipartFile file,
                                    @RequestParam(value = "fileName", required = false) String fileName) {
        try {
            if (file.isEmpty() && fileName == null) {
                votingService.createVoting(voting);
            } else {
                votingService.createVoting(voting, file.getBytes(), fileName);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ADD VOTING EXCEPTION.", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get result for voting
     *
     * @param votingId - voting id in DB
     * @return - list
     */
    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ChosenVariantDTO>> getVotingResult(@PathVariable(name = "id") int votingId) {
        try {
            logger.warn("GET RESULT FOR VOTING: " + votingId);
            List<ChosenVariantDTO> list = votingService.getAllVotesForVoting(votingId);
            logger.warn("LIST: " + list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("GET VOTING RESULT EXCEPTION.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
