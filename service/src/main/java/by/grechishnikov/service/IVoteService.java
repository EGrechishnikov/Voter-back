package by.grechishnikov.service;

import by.grechishnikov.dto.MyVoteDTO;
import by.grechishnikov.entity.Vote;

import java.util.List;

/**
 * Service for Vote entity
 */
public interface IVoteService extends IBaseService<Vote> {
    /**
     * Get votes list for user
     *
     * @param userId - user id in DB
     * @return - list
     */
    List<MyVoteDTO> getAllVotesForUser(int userId);
}
