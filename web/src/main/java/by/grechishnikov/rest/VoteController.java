package by.grechishnikov.rest;

import by.grechishnikov.entity.Vote;
import by.grechishnikov.service.IVoteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/vote")
public class VoteController {
    private IVoteService voteService;
    private Logger logger = Logger.getLogger(VoteController.class);

    @Autowired
    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
}
