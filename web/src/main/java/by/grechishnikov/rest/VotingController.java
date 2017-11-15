package by.grechishnikov.rest;

import by.grechishnikov.dto.ChosenVariant;
import by.grechishnikov.entity.Voting;
import by.grechishnikov.service.IVotingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/all/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<Voting>> getAll(@PathVariable int page ) {
        try {
            logger.warn("GET ALL VOTING");
            List<Voting> list = votingService.getAll(page);
            logger.warn("LIST: " + list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("VOTING EXCEPTION. GET ALL.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addVoting(@RequestBody Voting voting) {
        try {
            logger.warn("ADD VOTING: " + voting);
            logger.warn("VARIANTES: " + voting.getVariants());
            votingService.saveOrUpdate(voting);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ADD VOTING EXCEPTION.", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ChosenVariant>> getVotingResult(@PathVariable(name = "id") int votingId) {
        try {
            logger.warn("GET RESULT FOR VOTING: " + votingId);
            List<ChosenVariant> list = votingService.getAllVotesForVoting(votingId);
            logger.warn("LIST: " + list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("GET VOTING RESULT EXCEPTION.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
