package by.grechishnikov.rest;

import by.grechishnikov.dto.MyVoteDTO;
import by.grechishnikov.entity.Vote;
import by.grechishnikov.service.IVoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for Vote entity
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class VoteController {
    private IVoteService voteService;
    private static Logger logger = Logger.getLogger(VoteController.class);

    @Autowired
    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * Add a new vote to DB
     *
     * @param vote - vote
     * @return - http status
     */
    @RequestMapping(value = "/vote/add", method = RequestMethod.POST)
    public ResponseEntity addVote(@RequestBody Vote vote) {
        try {
            logger.warn("ADD VOTE: " + vote);
            voteService.saveOrUpdate(vote);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ADD VOTE EXCEPTION.", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get vote list for user
     *
     * @param userId - user id in DB
     * @return - list
     */
    @RequestMapping(value = "/votes/get/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<MyVoteDTO>> getAllVoteForUser(@PathVariable int userId) {
        try {
            List<MyVoteDTO> list = voteService.getAllVotesForUser(userId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("GET ALL VOTES EXCEPTION.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
