package by.grechishnikov.rest;

import by.grechishnikov.dto.MyVote;
import by.grechishnikov.entity.Vote;
import by.grechishnikov.service.IVoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class VoteController {
    private IVoteService voteService;
    private Logger logger = Logger.getLogger(VoteController.class);

    @Autowired
    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(value = "/vote/add", method = RequestMethod.POST)
    public ResponseEntity addVote(@RequestBody Vote vote) {
        try {
            logger.warn("VOTE: " + vote);
            voteService.saveOrUpdate(vote);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ADD VOTE EXCEPTION.", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/votes/get/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<MyVote>> getAll(@PathVariable int userId) {
        try {
            logger.warn("GET ALL VOTES FOR ID: " + userId);
            List<MyVote> list = voteService.getAllVotesForUser(userId);
            logger.warn("LIST: " + list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("GET ALL VOTES EXCEPTION.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
